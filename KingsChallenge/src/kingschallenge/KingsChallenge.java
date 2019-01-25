/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge;

import kingschallenge.Controller.Controller;
import kingschallenge.Model.Puzzle;
import kingschallenge.View.Frame;

/**
 *
 * @author Sam
 */
public class KingsChallenge {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Frame frame = new Frame();
        Puzzle puzzle = new Puzzle();
        Controller controller = new Controller(frame, puzzle);
    }
    
}
