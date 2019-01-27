/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import kingschallenge.Controller.Controller;
import kingschallenge.Model.Puzzle;
import kingschallenge.Model.Number;


/**
 *
 * @author Sam
 */
public class Panel extends JPanel implements ActionListener{
    private Dimension dimension;
    private Puzzle puzzle;
    private Color unsolvedColor = new Color(48,48,48);
    private Color solvedColor = new Color(105,181,72);
    private Color timeReachedColor = new Color(239,55,67);
    private Timer timer = new Timer(10, this);
    private Controller controller;
    private boolean rotating = false;
    private boolean inMenu = false;
    private boolean solved;
    private Font font; 
    //windows
    private PasswordWindow passwordWindow;
    private ScoreWindow scoreWindow;
    //buttons
    private JButton resetButton;
    private JButton solveButton;
    //time
    private int time = 75;
    private int timerIncrement = 0;
    
    public Panel(Dimension dimension, Puzzle puzzle){
        this.dimension = dimension;
        this.puzzle = puzzle;
        init();
        initButtons();
        initWindows();
    }
    public void init(){
        this.setSize(dimension);
        font = new Font("Century Gothic", Font.PLAIN, puzzle.getNumbers().get(0).getSize());
        this.setFont(font); 
        this.setForeground(unsolvedColor);
        timer.setRepeats(false);
    }
    public void initWindows(){
        
        passwordWindow = new PasswordWindow(new Dimension(350,100), new Point(this.getWidth()/2,this.getHeight()/2),this);
        passwordWindow.setVisible(false);
        scoreWindow = new ScoreWindow(new Dimension(350,100), new Point(this.getWidth()/2,this.getHeight()/2),this);
        scoreWindow.setVisible(false);
        this.add(passwordWindow);
        this.add(scoreWindow);
    }
    private void initButtons(){
        this.setLayout(null);
        resetButton = new JButton("reset");
        resetButton.setBounds(dimension.width-120, 50, 110, 25);
        solveButton = new JButton("🔒solution");
        solveButton.setBounds(dimension.width-120, 130, 110, 25);
        resetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonAction();
            }
        });
        solveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                solveButtonAction();
            }
        });
        this.add(resetButton);
        this.add(solveButton);
    }
    public void drawCircle(Graphics g){
        g.drawImage(puzzle.getImage(), 0, 0, this);
    }
    @Override
    public void actionPerformed(ActionEvent ev){
        if(ev.getSource() == timer){
            for(Number n: puzzle.getNumbers()){
                n.rotate();
                rotating = n.isRotating();
                repaint();
            }
            if(!rotating){
                if(controller.isSolving()){
                    controller.solvePuzzle();
                }
                setSolved(puzzle.isSolved());
            }
            
            if(timerIncrement % 1000 == 0 && !controller.isEnded()){
                --time;
                timerIncrement = 0;
            }
            
            timerIncrement += (int)timer.getDelay();
            if(time == 0){
                time = 0;
                controller.setEnded(true);
                this.setForeground(timeReachedColor);
            }
        }
    }
    public void drawNumbers(Graphics g){
        for(int i = 0; i < puzzle.getNumbers().size(); ++i){
            int offsetX = puzzle.getImage().getWidth()/2;
            int offsetY = puzzle.getImage().getHeight()/2;
            if(puzzle.getNumbers().get(i).isRightCircle()){
                offsetX += 206;
            }else{
                offsetX += -206;
            }
            timer.start();
            offsetX -= g.getFontMetrics(font).getStringBounds(Integer.toString(puzzle.getNumbers().get(i).getValue()), g).getWidth()/2;
            //draw numbers
            g.drawString(Integer.toString(puzzle.getNumbers().get(i).getValue()), puzzle.getNumbers().get(i).getPos().x+offsetX, -puzzle.getNumbers().get(i).getPos().y+offsetY);

        }
    }
    private String convertToMinute(int t){
        String minute = Integer.toString(t/60);
        String second = Integer.toString(t % 60);
        if (Integer.parseInt(second) < 10) second = "0"+second;
        return minute + ": "+second;
    }
    public void drawTime(Graphics g){
        g.drawString(convertToMinute(time), this.getWidth()-100, 35);
    } 
    public void resetButtonAction(){
        puzzle.reset();
    }
    public void solveButtonAction(){
        inMenu = true;
        passwordWindow.setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCircle(g);
        drawNumbers(g);
        drawTime(g);
    }

    public void setSolved(boolean solved) {
        if(solved){
            this.setForeground(solvedColor);
        }
        else{
            this.setForeground(unsolvedColor);
        }
    }
    
    public boolean isRotating() {
        return rotating;
    }

    public boolean isInMenu() {
        return inMenu;
    }

    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public Timer getTimer() {
        return timer;
    }
    
}
