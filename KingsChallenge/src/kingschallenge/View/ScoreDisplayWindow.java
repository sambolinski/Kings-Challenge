/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Sam
 */
public class ScoreDisplayWindow extends JOptionPane{
    private Dimension dimension;
    private Point pos;
    private Panel panel;
    private JButton closeButton;
    private JTable table;
    private JScrollPane scrollpane;
    private String[][] data = {{"1","Sam Hughes", "1"},{"2","Oliver O'Reilly", "2"},{"3","Harrison Myah", "3"},{"3","Harrison Myah", "3"}};
    private String[] column = {"ID","Name","Time"};
    public ScoreDisplayWindow(Dimension dimension, Point pos, Panel panel){
        this.dimension = dimension;
        this.pos = pos;
        this.pos.x -= dimension.width/2;
        this.pos.y -= dimension.height/2;
        this.panel = panel;
        updateTable();
        init();
    }
    public void init(){
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBounds(pos.x, pos.y, dimension.width, dimension.height);
        this.setVisible(true);
        
        table = new JTable(data, column);
        table.setFillsViewportHeight(true);
        
        scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(dimension.width-10,(int)(dimension.height*0.8)));
        scrollpane.setBounds(10,10,dimension.width-30,(int)(dimension.height*0.8));
        
        closeButton = new JButton("close");
        closeButton.setBounds((int)(dimension.width*0.8), (int)(dimension.height*0.85), 100, 30);
        closeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                closeButtonAction();
            }
        });
        this.add(closeButton);
        this.add(scrollpane);
    }
    public void updateTable(){
        
    }
    public void closeButtonAction(){
        this.setVisible(false);
        this.panel.setInMenu(false);
    }
}
