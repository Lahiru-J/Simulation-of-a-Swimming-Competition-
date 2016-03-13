package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class holds all the functions and attributes that are needed for a 
 * Swimming Pool
 * @author Lahiru
 */
public class SwimmingPool implements Serializable{

    private final SwimLane[] swimLanes;
    private final ScoreBoard scoreBoard;
    
    /* number of swimmers that are in the pool */
    private ArrayList<Swimmer> listSwimmers;

    public SwimmingPool() {
        // creatting new Swim Lanes 
        swimLanes = new SwimLane[5];
        for (int i = 0; i < 5; i++) {
            swimLanes[i]=new SwimLane();
        }
        
        /* adding a new score board */
        scoreBoard = new ScoreBoard();
        listSwimmers = new ArrayList<>();
    }

    /**
     * 
     * @param laneNumber
     * @return one of the swim lane in this swimming pool
     */
    public SwimLane getSwimLane(int laneNumber) {
        return swimLanes[laneNumber];
    }
    
    /**
     * 
     * @return score board that is connected to this pool 
     */
    public ScoreBoard getScoreBoard(){
        return this.scoreBoard;
    }

    /**
     * This method will return the array list of swimmers that currently in the 
     * Swimming pool
     * @return array list of swimmers
     */
    public ArrayList<Swimmer> getNumberOfSwimmers(){
        return this.listSwimmers;
    }
    
    /**
     * This method will assign the number of swimmers in the swimming pool
     * @param listSwimmers number of swimmers that in the swimming pool
     */
    public void setNumberOfSwimmers(ArrayList<Swimmer> listSwimmers){
        this.listSwimmers = listSwimmers;
        this.scoreBoard.setNumberOfRows(listSwimmers.size());
    }
}
