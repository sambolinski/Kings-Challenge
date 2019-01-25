/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.Model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Sam
 */
public class Puzzle {
    private ArrayList<Number> numbers = new ArrayList<Number>();
    private BufferedImage image;
    private final int[] NUMBER_ORDER = {1, 6, 5, 4, 1, 6, 5, 4, 3, 2};
    private final double[] ANGLES = {0.000d, 307.158d, 232.842d, 180.000d, 0.000d, 52.842d, 127.158d, 180.000d, 52.842d, 127.158d};
    public Puzzle(){
        init();
    }
    public void init(){
        //init background
        try{
            image = ImageIO.read(getClass().getResource("/kingschallenge/resources/circle.png"));
        }catch(IOException e){e.printStackTrace();System.out.println("bruh");}
        
        //init numbers arraylist
        for(int i = 0; i < 10; ++i){
            numbers.add(new Number(NUMBER_ORDER[i],ANGLES[i]));
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public ArrayList<Number> getNumbers() {
        return numbers;
    }
    
}
