package Model;

import java.awt.Color;

/**
 * The FemaleSwimmer class alow to perform capabilities of a female swimmer.
 *
 * @author Lahiru
 */
public class FemaleSwimmer extends Swimmer {

    /**
     * Creates a new FemaleSwimmer
     *
     * @param name - name of the FemaleSwimmer
     */
    public FemaleSwimmer(String name) {
        super(name,Color.RED);
        TrackPeople.addFemaleSwimmer(this);                                     // keep track on the FemaleSwimmer created

    }

    @Override
    public void doButterflyStroke(int sleepTime) {
        updatePosition(sleepTime, 1.3);                                         // factor is made 1.3
    }

    @Override
    public void doBackStroke(int sleepTime) {
        updatePosition(sleepTime, 1.6);                                         // factor is made 1.6
    }

    @Override
    public void doBreastStroke(int sleepTime) {
        updatePosition(sleepTime, 1.2);                                         // factor is made 1.2
    }

    @Override
    public void doFreeStyle(int sleepTime) {
        updatePosition(sleepTime, 1);                                           // factor is made 1
    }

}
