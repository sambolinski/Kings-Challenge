/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.Model;

import java.awt.Point;

/**
 *
 * @author Sam
 */
public class Number {
    private int value;
    private int radius = 238;
    private int size = 40;
    private double currentAngle;
    private double targetAngle;
    public Number(int value, double targetAngle){
        this.value = value;
        this.targetAngle = targetAngle;
        this.currentAngle = targetAngle;
    }
    public Point getPos(){
        int x = (int)(Math.sin(Math.toRadians(currentAngle))*238);
        int y = (int)(Math.cos(Math.toRadians(currentAngle))*238);
        return new Point(x,y);
    }
    public void rotate(){
        
    }
    public void setTargetAngle(double targetAngle) {
        this.targetAngle = targetAngle;
    }
    
    public int getSize() {
        return size;
    }

    public int getValue() {
        return value;
    }
    
}
