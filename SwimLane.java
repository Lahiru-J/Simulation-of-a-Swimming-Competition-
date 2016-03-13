/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Lahiru
 */
public class SwimLane implements Serializable{
    private int length;
    private final TouchPad touchPad;
    private int laneNumber;
    
    public SwimLane(){
        touchPad = new TouchPad();
    }
    
    public void setLength(int length){
        this.length = length;
    }
    
    public void setLaneNumber(int laneNumber){
        this.laneNumber = laneNumber;
    }
    
    public int getLaneNumber(){
        return this.laneNumber;
    }
    
    public int getLength(){
        return this.length;
    }
    
    public TouchPad getTouchPad(){
        return this.touchPad;
    }
}
