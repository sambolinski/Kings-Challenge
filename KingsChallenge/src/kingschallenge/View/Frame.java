/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.View;

import java.awt.Dimension;
import javax.swing.JFrame;
import kingschallenge.Model.Puzzle;

/**
 *
 * @author Sam
 */
public class Frame extends JFrame{
    private Panel panel;
    private Puzzle puzzle;
    private Dimension dimension = new Dimension(1200,750);
    public Frame(){
    }
    public void init(){
        this.setTitle("Rotating Circle Puzzle");
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        panel = new Panel(dimension, puzzle);
        this.add(panel);
        this.pack();
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
