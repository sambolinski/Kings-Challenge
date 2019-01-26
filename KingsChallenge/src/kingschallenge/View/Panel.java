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
    private boolean solved;
    
    private JButton defaultButton;
    private JButton resetButton;
    private JButton shuffleButton;
    public Panel(Dimension dimension, Puzzle puzzle){
        this.dimension = dimension;
        this.puzzle = puzzle;
        init();
        initButtons();
    }
    public void init(){
        this.setSize(dimension);
        this.setFont(new Font("Century Gothic", Font.PLAIN, puzzle.getNumbers().get(0).getSize())); 
        this.setForeground(unsolvedColor);
        timer.setRepeats(false);
    }
    private void initButtons(){
        this.setLayout(null);
        resetButton = new JButton("reset");
        resetButton.setBounds(dimension.width-110, 10, 100, 25);
        defaultButton = new JButton("default");
        defaultButton.setBounds(dimension.width-110, 40, 100, 25);
        shuffleButton = new JButton("shuffle");
        shuffleButton.setBounds(dimension.width-110, 70, 100, 25);
        
        resetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonAction();
            }
        });
        defaultButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultButtonAction();
            }
        });
        shuffleButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleButtonAction();
            }
        });
        this.add(resetButton);
        this.add(defaultButton);
        this.add(shuffleButton);
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
            g.drawString(Integer.toString(puzzle.getNumbers().get(i).getValue()), puzzle.getNumbers().get(i).getPos().x+offsetX, -puzzle.getNumbers().get(i).getPos().y+offsetY);

        }
    }
    public void resetButtonAction(){
        puzzle.reset();
    }
    public void defaultButtonAction(){
        puzzle.initNumbers();
    }
    public void shuffleButtonAction(){
        puzzle.shuffle();
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
    
}
