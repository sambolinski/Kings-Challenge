/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kingschallenge.Controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import kingschallenge.DatabaseControl.DBConnect;
import kingschallenge.Model.Puzzle;
import kingschallenge.View.Frame;

/**
 *
 * @author Sam
 */
public class Controller {
    private String password = "apple";
    private boolean rotating = false; // so user cannot click while rotation is happening
    private boolean inMenu = false;
    private boolean started = true;
    private boolean solving = false;
    private boolean ended = false;
    private Frame frame;
    private Puzzle puzzle;
    private DBConnect dbConnection;
    private String[] solution = {"r","l","r","r","l","l","r","l","l","l","r","r","r","l","l","l","l","l"};
    private int currentSolutionIndex = 0;
    public Controller(Frame frame, Puzzle puzzle){
        this.frame = frame;
        this.frame.init();
        this.puzzle = puzzle;
        this.frame.getPanel().setController(this);
        this.frame.addMouseListener(new ControllerListener());
        this.frame.getPanel().initWindows();
        this.frame.getPanel().initButtons();
        this.frame.addPanel();
        dbSetup();
    }
    public void dbSetup(){
        dbConnection = new DBConnect("Sambolinski","apple");
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
    
    public void solvePuzzle(){
        if(currentSolutionIndex == 0){
            this.solving = true;
            this.ended = true;
            this.puzzle.initNumbers();
        }
        if(!puzzle.isSolved() && !rotating){
            if(solution[currentSolutionIndex].equals("l")){
                puzzle.getNumbers().get(8).setCurrentAngle(120);
                puzzle.getNumbers().get(9).setCurrentAngle(60);
                puzzle.getNumbers().get(8).setRightCircle(false);
                puzzle.getNumbers().get(9).setRightCircle(false);
                puzzle.rotateLeft();
            }else if(solution[currentSolutionIndex].equals("r")){
                puzzle.getNumbers().get(8).setCurrentAngle(240);
                puzzle.getNumbers().get(9).setCurrentAngle(300);
                puzzle.getNumbers().get(8).setRightCircle(true);
                puzzle.getNumbers().get(9).setRightCircle(true);
                puzzle.rotateRight();
            }
            ++currentSolutionIndex;
            puzzle.checkSolved();
        }
    }
    public void addToDatabase(String name, String time){
        dbConnection.connect();
        dbConnection.insert(Integer.toString(dbConnection.retrieveDatabaseSize() +1 ), name, time);
    }
    public ArrayList<String[]> getFromDatabase(){
        dbConnection.connect();
        return dbConnection.retrieveAllPersons();
    }
    public boolean isRotating() {
        return rotating;
    }

    public void setRotating(boolean rotating) {
        this.rotating = rotating;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public String getPassword() {
        return password;
    }

    public void setSolving(boolean solving) {
        this.solving = solving;
    }

    public boolean isSolving() {
        return solving;
    }
    
    public int getCurrentSolutionIndex() {
        return currentSolutionIndex;
    }

    public void setCurrentSolutionIndex(int currentSolutionIndex) {
        this.currentSolutionIndex = currentSolutionIndex;
    }
    
    class ControllerListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            rotating = frame.getPanel().isRotating();
            inMenu = frame.getPanel().isInMenu();
            if(!rotating && !inMenu && started && !ended && !solving){
                if(circleSelected(e.getPoint()) == -1){
                    puzzle.getNumbers().get(8).setCurrentAngle(120);
                    puzzle.getNumbers().get(9).setCurrentAngle(60);
                    puzzle.getNumbers().get(8).setRightCircle(false);
                    puzzle.getNumbers().get(9).setRightCircle(false);
                    puzzle.rotateLeft();
                }else if(circleSelected(e.getPoint()) == 1){
                    puzzle.getNumbers().get(8).setCurrentAngle(240);
                    puzzle.getNumbers().get(9).setCurrentAngle(300);
                    puzzle.getNumbers().get(8).setRightCircle(true);
                    puzzle.getNumbers().get(9).setRightCircle(true);
                    puzzle.rotateRight();
                }
                puzzle.checkSolved();
                if(puzzle.isSolved()){
                    frame.getPanel().getScoreWindow().setVisible(true);
                }
                ended = puzzle.isSolved();
            }
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
