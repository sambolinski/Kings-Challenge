/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.Model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;

/**
 *
 * @author Sam
 */
public class Puzzle {
    private ArrayList<Number> numbers;
    private BufferedImage image;
    private int puzzleRadius = 296;
    private boolean solved = false;
    private ArrayList<Number> resetList;
    private final int[] SOLVED_STATE = {1, 6, 5, 4, 1, 6, 5, 4, 3, 2};
    private final int[] DEFAULT_ORDER = {1, 4, 4, 3, 5, 5, 6, 6, 2, 1};
    private final double[] ANGLES = {0d, 300d, 240d, 180d, 0d, 60d, 120d, 180.000d, 120d, 60d, 240d, 300d};
    public Puzzle(){
        init();
    }
    public void init(){
        initBackground();
        initNumbers();
    }
    public void initBackground(){
        //init background
        try{
            image = ImageIO.read(getClass().getResource("/kingschallenge/resources/circle2.png"));
        }catch(IOException e){e.printStackTrace();}
    }
    public void initNumbers(){
        //init numbers arraylist
        numbers = new ArrayList<Number>();
        for(int i = 0; i < 10; ++i){
            Number tmp = new Number(DEFAULT_ORDER[i],ANGLES[i]);
            if(i < 4 || i > 7){
                tmp.setRightCircle(false);
            }else{
                tmp.setRightCircle(true);
            }
            numbers.add(tmp);
        }
        resetList = deepCopy(numbers);
    }
    public void shuffle(){
        Collections.shuffle(numbers);
        for(int i = 0; i < numbers.size(); ++i){
            numbers.get(i).setTargetAngle(ANGLES[i]);
            numbers.get(i).setCurrentAngle(ANGLES[i]);
            if(i < 4 || i > 7){
                numbers.get(i).setRightCircle(false);
            }else{
                numbers.get(i).setRightCircle(true);
            }
        }
        resetList = deepCopy(numbers);
    }
    public void reset(){
        numbers = deepCopy(resetList);
    }
    private void shift(ArrayList<Number> n){
        int i;
        Number tmp = n.get(0);
        for(i = 0; i < n.size() - 1; ++i){
            n.set(i, n.get(i+1));
        }
        n.set(i, tmp);
    }
    public void rotateLeft(){
        ArrayList<Number> tmpCopy = new ArrayList<Number>();
        int offset = 0;
        for(int i = 0; i < 6; ++i){
            offset = i < 4? 0: 4;
            tmpCopy.add(numbers.get(i+offset));
        }
        offset = 0;
        shift(tmpCopy);
        for(int i = 0; i < tmpCopy.size(); ++i){
            offset = i < 4? 0: 4;
            tmpCopy.get(i).setTargetAngle(ANGLES[i+offset]);
            numbers.set(i+offset, tmpCopy.get(i));
        }
    }
    public void rotateRight(){
        ArrayList<Number> tmpCopy = new ArrayList<Number>();
        for(int i = 4; i < 10; ++i){
            tmpCopy.add(numbers.get(i));
        }
        Collections.reverse(tmpCopy);
        shift(tmpCopy);
        Collections.reverse(tmpCopy);
        int offset = 4;
        int angleOffset = 0;
        for(int i = 0; i < 6; ++i){
            if(i > 3) angleOffset = 2;
            tmpCopy.get(i).setTargetAngle(ANGLES[i+offset+angleOffset]);
            numbers.set(i+offset, tmpCopy.get(i));
        }
    }
    private ArrayList<Number> deepCopy(ArrayList<Number> originalList){
        ArrayList<Number> tmp = new ArrayList<Number>();
        for(Number n: originalList){
            Number tmpNumber = new Number(n.getValue(),n.getTargetAngle());
            tmpNumber.setRightCircle(n.isRightCircle());
            tmp.add(tmpNumber);
        }
        return tmp;
    }
    public void checkSolved(){
        for(int i = 0; i < numbers.size(); ++i){
            if(numbers.get(i).getValue() != SOLVED_STATE[i]){ 
                solved = false;
                return;
            }
        }
        solved = true;
    }
    public BufferedImage getImage() {
        return image;
    }

    public ArrayList<Number> getNumbers() {
        return numbers;
    }

    public int getPuzzleRadius() {
        return puzzleRadius;
    }

    public boolean isSolved() {
        return solved;
    }

    public void debugPrintNumbers(){
        for(Number n: numbers){
            System.out.print(n.getValue()+ ", ");
        }
        System.out.println();
    }
    public void debugPrintNumbers(ArrayList<Number> a){
        for(Number n: a){
            System.out.print(n.getValue()+ ", ");
        }
        System.out.println();
    }
}
