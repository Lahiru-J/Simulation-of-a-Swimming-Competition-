package Model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class holds the basic attributes of a score board
 *
 * @author Lahiru
 */
public class ScoreBoard extends Observable implements Serializable {            // ScoreBoard is observed by Spectators

    /* Queue to hold finishing times */
    private final Queue<Double> time;

    /* Queue to hold Swimmers in the event */
    private final Queue<Swimmer> swimmers;

    /* Map final results with swimmers */
    private final Map<Swimmer, Double> finalResults;

    /* number of rows that should be in the final result */
    private int numberOfRows;
    private final Lock lock;
    private final Condition condition;

    public ScoreBoard() {
        time = new LinkedList<>();
        swimmers = new LinkedList<>();
        finalResults = new LinkedHashMap<>();
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    /**
     * This method will add swimmer and timeTaken to the list
     *
     * @param swimmer swimmer who completed the event
     * @param timeTaken time took for swimmer to complete the event
     */
    public void updateList(Swimmer swimmer, double timeTaken) {
        time.add(timeTaken);
        swimmers.add(swimmer);
    }

    /**
     * This method will start the process of the Score Board
     */
    public void powerUp() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(4000);                                         // swimmer will take more than 4s to complete a game
                    lock.lock();                                                // pass the lock to this thread
                    while (swimmers.size() != numberOfRows) {                   // wait this thread untill this is awoken
                        Thread.sleep(100);
                    }
                    comapreTimes();
                    condition.signal();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    /**
     * This method will set the number of rows in Score Board
     *
     * @param numberOfRows number of rows that should be in Score Board
     */
    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    /**
     * This method will pass the lock to the thread inside the powerUp method
     */
    public void lockTheScoreBoard() {
        this.lock.lock();
    }

    /**
     * this method will release the lock 
     */
    public void unlockTheScoreBoard() {
        this.lock.unlock();
    }

    /**
     * this method await the thread
     * @throws InterruptedException 
     */
    public void awaitScoreBoard() throws InterruptedException {
        this.condition.await();
    }

    /**
     * this method will compare the time of the swimmers
     */
    private void comapreTimes() {
        
        for (Swimmer s : swimmers) {
            this.finalResults.put(s, time.poll());
//            System.out.println("***** " + s.getName() + " time taken = " + time.poll() + " ");

        }
    }

    /**
     * this method will return the final results
     * @return map of Swimmer and Double value time
     */
    public Map<Swimmer, Double> getFinalResults() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    lock.lock();                                                // lock the thread
                    condition.await();                                          // make the thread to wait
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();                                              // release the thread
                }
            }
        }).start();
        scoreOut();                                                             // notify the observers 
        return this.finalResults;
    }

    /**
     * This method make notify all the observers when the score has updated
     */
    private void scoreOut() {
        setChanged();
        notifyObservers();
    }

}
