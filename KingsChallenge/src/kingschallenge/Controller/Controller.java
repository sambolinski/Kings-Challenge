/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.Controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import kingschallenge.Model.Puzzle;
import kingschallenge.View.Frame;

/**
 *
 * @author Sam
 */
public class Controller {
    private boolean rotating = false; // so user cannot click while rotation is happening
    private Frame frame;
    private Puzzle puzzle;
    public Controller(Frame frame, Puzzle puzzle){
        this.frame = frame;
        this.frame.init();
        this.puzzle = puzzle;
        this.frame.addMouseListener(new ControllerListener());
    }
    public int circleSelected(Point pos){
        Point leftCircle = new Point(puzzle.getImage().getWidth()/2-190,puzzle.getImage().getHeight()/2);
        Point rightCircle = new Point(puzzle.getImage().getWidth()/2+190,puzzle.getImage().getHeight()/2);
        if(pos.distance(leftCircle) <= puzzle.getPuzzleRadius() && pos.distance(rightCircle) > puzzle.getPuzzleRadius()){
            return -1;
        }else if(pos.distance(leftCircle) > puzzle.getPuzzleRadius() && pos.distance(rightCircle) <= puzzle.getPuzzleRadius()){
            return 1;
        }else{
            return 0;
        }
    }
    public boolean isRotating() {
        return rotating;
    }

    public void setRotating(boolean rotating) {
        this.rotating = rotating;
    }
    class ControllerListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(!rotating){
                if(circleSelected(e.getPoint()) == -1){
                    puzzle.rotateLeft();
                }else if(circleSelected(e.getPoint()) == 1){
                    puzzle.rotateRight();
                }
            }
            puzzle.checkSolved();
            frame.getPanel().setSolved(puzzle.isSolved());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }
}
