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
    private boolean rotating = false;
    private boolean rightCircle;
    public Number(int value, double targetAngle){
        this.value = value;
        this.targetAngle = targetAngle;
        this.currentAngle = targetAngle;
    }
    public Point getPos(){
        int x = (int)(Math.sin(Math.toRadians(currentAngle))*radius);
        int y = (int)(Math.cos(Math.toRadians(currentAngle))*radius);
        return new Point(x,y);
    }
    public void rotate(){
        if(currentAngle < targetAngle || (currentAngle > targetAngle && targetAngle == 0)){
            ++this.currentAngle;
            rotating = true;
        }else{
            rotating = false;
        }
        if(currentAngle == 360)this.currentAngle = 0;
        
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

    public boolean isRotating() {
        return rotating;
    }

    public void setRotating(boolean rotating) {
        this.rotating = rotating;
    }

    public void setCurrentAngle(double currentAngle) {
        this.currentAngle = currentAngle;
    }

    public boolean isRightCircle() {
        return rightCircle;
    }

    public void setRightCircle(boolean rightCircle) {
        this.rightCircle = rightCircle;
    }
    public boolean getRightCircle(){
        return rightCircle;
    }
}
