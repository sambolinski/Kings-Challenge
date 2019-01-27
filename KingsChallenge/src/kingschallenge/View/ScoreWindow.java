/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Sam
 */
public class ScoreWindow extends JOptionPane{
    private JTextField field;
    private Dimension dimension;
    private Point pos;
    private Panel panel;
    private JButton submitButton;
    public ScoreWindow(Dimension dimension, Point pos, Panel panel){
        this.dimension = dimension;
        this.pos = pos;
        this.pos.x -= dimension.width/2;
        this.pos.y -= dimension.height/2;
        this.panel = panel;
        init();
    }
    public void init(){
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBounds(pos.x, pos.y, dimension.width, dimension.height);
        this.setVisible(true);
        JLabel label = new JLabel("Enter Name");
        label.setBounds(10, 3, (int)(dimension.width*0.8), 30);
        field = new JTextField();
        field.setBounds(10, dimension.height/4, (int)(dimension.width*0.8), 30);
        
        submitButton = new JButton("submit");
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonAction();
            }
        });
        this.add(label);
        this.add(field);
        this.add(submitButton);
    }
    public void submitButtonAction(){
        this.setVisible(false);
        this.panel.setInMenu(false);
        
        field.setText("");
    }
}
