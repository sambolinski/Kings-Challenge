/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.View;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Sam
 */
public class Frame extends JFrame{
    private Panel panel;
    private Dimension dimension = new Dimension(1200,750);
    public Frame(){
        init();
    }
    public void init(){
        this.setTitle("Rotating Circle Puzzle");
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }

    public Dimension getDimension() {
        return dimension;
    }
    
}
