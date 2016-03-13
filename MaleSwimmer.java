package Model;

import java.awt.Color;

/**
 * The MaleSwimmer class alow to perform capabilities of a male swimmer.
 *
 * @author Lahiru
 */
public class MaleSwimmer extends Swimmer {

    /**
     * Creates a new FemaleSwimmer
     *
     * @param name - name of the MaleeSwimmer
     */
    public MaleSwimmer(String name) {
        super(name,Color.BLUE);
        TrackPeople.addMaleSwimmer(this);                                       // keep track on the MaleSwimmer created
        
    }

    @Override
    public void doButterflyStroke(int sleepTime) {
        updatePosition(sleepTime ,0.9);                                         // factor is made 0.9
    }
    @Override
    public void doBackStroke(int sleepTime) {
        updatePosition(sleepTime , 1.1);                                        // factor is made 1.1
    }

    @Override
    public void doBreastStroke(int sleepTime) {
        updatePosition(sleepTime , 0.8);                                        // factor is made 0.8
    }

    @Override
    public void doFreeStyle(int sleepTime) {
       updatePosition(sleepTime,0.7);                                           // factor is made 0.7
        
    }
}
