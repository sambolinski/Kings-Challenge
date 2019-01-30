/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import kingschallenge.Model.Puzzle;

/**
 *
 * @author Sam
 */
public class Frame extends JFrame{
    private Panel panel;
    private Puzzle puzzle;
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public Frame(){
    }
    public void init(){
        this.setTitle("Rotating Circle Puzzle");
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        panel = new Panel(dimension, puzzle);
        this.setUndecorated(true);
        this.setVisible(true);
        this.pack();
    }
    public void addPanel(){
        this.add(panel);
    }
    public Dimension getDimension() {
        return dimension;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public Panel getPanel() {
        return panel;
    }
    
    
}
