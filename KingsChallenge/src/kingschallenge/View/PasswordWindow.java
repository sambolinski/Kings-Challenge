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
import javax.swing.JPasswordField;

/**
 *
 * @author Sam
 */
public class PasswordWindow extends JOptionPane{
    private JPasswordField field;
    private Dimension dimension;
    private String password;
    private Point pos;
    private JLabel label;
    private JButton cancelButton;
    private JButton submitButton;
    private Panel panel;
    public PasswordWindow(Dimension dimension, Point pos, Panel panel){
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
        label = new JLabel("Enter Password");
        label.setBounds(10, 3, (int)(dimension.width*0.8), 30);
        field = new JPasswordField();
        field.setBounds(10, dimension.height/4, (int)(dimension.width*0.8), 30);
        cancelButton = new JButton("cancel");
        submitButton = new JButton("submit");
        cancelButton.setBounds(10, (int)(dimension.height*0.6), 100, 30);
        submitButton.setBounds(150, (int)(dimension.height*0.6), 100, 30);
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction();
            }
        });
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonAction();
            }
        });
        
        this.add(label);
        this.add(field);
        this.add(cancelButton);
        this.add(submitButton);
    }
    public void cancelButtonAction(){
        this.setVisible(false);
        this.panel.setInMenu(false);
    }
    public void submitButtonAction(){
        
    }
    public String getPassword() {
        return password;
    }
    
}
