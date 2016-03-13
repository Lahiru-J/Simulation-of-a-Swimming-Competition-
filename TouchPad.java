package Model;

import java.io.Serializable;

/**
 * This class holds all the functions and attributes that are needed for a 
 * Touch pad
 * @author Lahiru
 */
public class TouchPad implements Serializable{
    private long startTime;
    
    /**
     * this method will start the timer of the TouchPad
     */
    public void startTimer(){
        this.startTime = System.currentTimeMillis();
    }
    
    /**
     * This method will notify the score board that the swimmer related to this 
     * touch pad has just finished
     * @param swimmer
     * @param swimmingPool
     * @return time taken by the swimmer to complete the game
     */
    public double notifyScoreBoard(Swimmer swimmer , SwimmingPool swimmingPool){
        double timeTaken = 0.0;                                                 
        timeTaken = (System.currentTimeMillis() - startTime)/1000.000;          // to convert to seconds
        
        ScoreBoard sb = swimmingPool.getScoreBoard();
        sb.updateList(swimmer, timeTaken);                                      // update the score board
        return timeTaken;
    }
    
}
