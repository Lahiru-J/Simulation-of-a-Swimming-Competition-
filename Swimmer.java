package Model;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class holds the basic attributes and states of a swimmer
 *
 * @author Lahiru
 */
public abstract class Swimmer extends Person {

    private final Color costumeColor;                                          // color of the costume
    private final int personalBest;
    private volatile int currentPosition;
    private SwimLane swimLane;
    private final Lock lock;                                                    // lock for the thread
    private final Condition condition;
    
    protected Swimmer(String name, Color costumeColor) {
        super(name);

        this.costumeColor = costumeColor;
        Random random = new Random();
        this.personalBest = random.nextInt(20) + 8;

        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public SwimLane getSwimLane() {
        return this.swimLane;
    }

    /**
     * @return the color of the swimming costume
     */
    public Color getDressColor() {
        return this.costumeColor;
    }

    /**
     * Perform hand movements This method will give signal for all waiting
     * threads
     */
    public void doHandMovements() {
        this.condition.signalAll();
    }

    /**
     * Perform leg movements This method return the current position of the
     * swimmer
     *
     * @return current position of the swimmer
     */
    public int doLegMovements() {
        return this.currentPosition;
    }

    /**
     * perform Butterfly stroke
     *
     * @param sleepTime time that the swimmer going to get slow
     */
    protected abstract void doButterflyStroke(int sleepTime);

    /**
     * perform Backstroke
     *
     * @param sleepTime time that the swimmer going to get slow
     */
    protected abstract void doBackStroke(int sleepTime);

    /**
     * perform Breaststroke
     *
     * @param sleepTime time that the swimmer going to get slow
     */
    protected abstract void doBreastStroke(int sleepTime);

    /**
     * perform Freestyle
     *
     * @param sleepTime time that the swimmer going to get slow
     */
    protected abstract void doFreeStyle(int sleepTime);

    /**
     * This method identify which style has to be done
     *
     * @param style swimming style
     */
    public void doSwimmingStyle(String style) {
        switch (style) {

            case "Freestyle":
                doFreeStyle(personalBest);
                break;

            case "Backstroke":
                doBackStroke(personalBest);
                break;

            case "Butterfly Stroke":
                doButterflyStroke(personalBest);
                break;

            case "Breaststroke":
                doBreastStroke(personalBest);
                break;
        }
        this.swimLane.getTouchPad().startTimer();                               // start the timer when swimmer starts to swim
    }

    /**
     * This method update the position of the swimmer
     *
     * @param sleepTime time that the swimmer going to get slow
     * @param factor here swimTime = sleepTime * factor
     */
    protected void updatePosition(int sleepTime, double factor) {
        final int swimTime = (int) (sleepTime * factor);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();                                                // lock the thread
                    int endPosition = currentPosition + swimLane.getLength();   // length of the lane from staring to end

                    while (currentPosition <= endPosition) {
                        currentPosition++;
                        Thread.sleep(swimTime);
                        condition.signal();
                        condition.await();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } 
                finally {
                    lock.unlock();                                              // give up the lock from the thread
                }
            }
        }).start();
    }

    /**
     *
     * @return personal best of the swimmer
     */
    public int getPersonalBest() {
        return this.personalBest;
    }

    /**
     * set the current position of the swimmer
     *
     * @param currentPosition
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     *
     * @return current position of the swimmer
     */
    public int getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * set a swim lane for the swimmer
     *
     * @param swimLane
     */
    public void setSwimLane(SwimLane swimLane) {
        this.swimLane = swimLane;
    }

    /**
     * When swimmer finish the event swimmer should touch the touch pad. This
     * method achieve that task
     *
     * @param swimmingPool swimming pool where the touch pad is held
     * @return swimmer's finishing time
     */
    public double touchTheTouchPad(SwimmingPool swimmingPool) {
        return this.swimLane.getTouchPad().notifyScoreBoard(this, swimmingPool);
    }

    /**
     * this method will lock the thread
     *
     * @throws InterruptedException
     */
    public void slowDown() throws InterruptedException {
        this.lock.lock();
    }

    /**
     * this method will wait the thread allowing time to the swimmer to increase
     * his position
     *
     * @throws InterruptedException
     */
    public void speedUp() throws InterruptedException {
        this.condition.awaitNanos(100);

    }

    /**
     * this method will unlock the thread
     */
    public void giveUp() {
        this.lock.unlock();
    }
 
}
