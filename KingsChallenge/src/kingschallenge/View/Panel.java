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
    private Timer timer = new Timer(10, this);
    private boolean rotating = false;
    private boolean inMenu = false;
    private boolean solved;
    private Font font; 
    //windows
    private PasswordWindow passwordWindow;
    //buttons
    private JButton resetButton;
    private JButton solveButton;
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
        this.add(passwordWindow);
    }
    private void initButtons(){
        this.setLayout(null);
        resetButton = new JButton("reset");
        resetButton.setBounds(dimension.width-120, 10, 110, 25);
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
                setSolved(puzzle.isSolved());
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
            g.drawString(Integer.toString(puzzle.getNumbers().get(i).getValue()), puzzle.getNumbers().get(i).getPos().x+offsetX, -puzzle.getNumbers().get(i).getPos().y+offsetY);

        }
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
    }

    public void setSolved(boolean solved) {
        if(solved){
            this.setForeground(solvedColor);
        }
        else{
            this.setForeground(unsolvedColor);
        };
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
    
}
