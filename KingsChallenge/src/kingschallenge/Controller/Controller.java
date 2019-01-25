/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.Controller;

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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
