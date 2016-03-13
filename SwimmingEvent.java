package Model;

import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * This class hold all the functions and the attributes that are needed for a
 * Swimming event
 *
 * @author Lahiru
 */
public class SwimmingEvent implements Serializable {

    private final ArrayList<Swimmer> swimmersInEvent = new ArrayList<>();       // to hold enrolled swimmers for the event 
    private final ArrayList<Judge> judgesInEvent = new ArrayList<>();           // to hold judges in the event
    private final ArrayList<SupportingStaffMember> sptMmbrsInEvent // to hold supporting memebers in the event
            = new ArrayList<>();
    private final ArrayList<Spectator> spectatorsInEvent = new ArrayList<>();   // to hold Spectators in the event

    private final SwimmingPool swimmingPool;
    private final String eventName;
    private final String swimmingStyle;
    private final String gender;
    private boolean isFinished;                                                 // to identify whether the event is finished
    private Swimmer swimmerWinner;                                              // winner of the event
    private Swimmer swimmerRunnerUp;                                            // runner up of the event
    private Swimmer swimmerScndRunnerUp;                                        // second runner up of the event
    private Judge headJudge;                                                    // head of the Judges in this current event

    public SwimmingEvent(String eventName, String swimmingStyle, String gender) {
        this.eventName = eventName;
        this.swimmingStyle = swimmingStyle;
        this.gender = gender;
        this.isFinished = false;
        this.swimmingPool = new SwimmingPool();
    }

    /**
     *
     * @return name of the Swimming Event
     */
    public String getEventName() {
        return this.eventName;
    }

    /**
     *
     * @return Swimming style
     */
    public String getSwimmingStyle() {
        return this.swimmingStyle;
    }

    /**
     *
     * @return gender of the competitors
     */
    public String getGender() {
        return this.gender;
    }

    /**
     *
     * @return whether the event is finished or not if finish true else false
     */
    public boolean isCompleted() {
        return this.isFinished;
    }

    /**
     * mark the event as a completed event
     */
    public void setCompleted() {
        this.isFinished = true;
        SwimmingCompetition.serializeEvents();                                  // because boolen isCompleted is changed
    }

    /**
     *
     * @return the swimming pool where the event is holding
     */
    public SwimmingPool getSwimmingPool() {
        return this.swimmingPool;
    }

    /**
     *
     * @return swimmers that are enrolled for the event
     */
    public ArrayList<Swimmer> getSwimmersInEvent() {
        return swimmersInEvent;
    }

    /**
     *
     * @return judges in the event
     */
    public ArrayList<Judge> getJudgesInEvent() {
        return judgesInEvent;
    }

    /**
     *
     * @return supporting members in the event
     */
    public ArrayList<SupportingStaffMember> getSptMmbrsInEvent() {
        return sptMmbrsInEvent;
    }

    /**
     *
     * @return spectators in the event
     */
    public ArrayList<Spectator> getSpectatorsInEvent() {
        return spectatorsInEvent;
    }

    public Judge getHeadOfTheJudges() {
        return this.headJudge;
    }

    /**
     * this method will add a swimmer to the event
     *
     * @param swimmerNameList name list of the swimmers
     */
    public void addSwimmersToEvent(Object[] swimmerNameList) {
        swimmersInEvent.clear();                                                // For editing (when adding or removing swimmers)
        if (swimmerNameList.length < 6 && swimmerNameList.length > 2) {         // there should be maximum of 5 swimmers and a minum of 3

            int laneNumber = 0;
            for (Object name : swimmerNameList) {
                Swimmer swmr = (Swimmer) TrackPeople.getPerson((String) name);  // downcast person to swimmer
                swimmersInEvent.add(swmr);
                swmr.setSwimLane(this.swimmingPool.getSwimLane(laneNumber++));  // set up a SwimLane to the swimmer
            }
            this.swimmingPool.setNumberOfSwimmers(swimmersInEvent);             // set the number of swimmers in the swimming pool
        }
    }

    /**
     * This method will add judges to the event
     *
     * @param judgeNameList name list of the judges
     */
    public void addJudgesToEvent(Object[] judgeNameList) {
        judgesInEvent.clear();
        if (judgeNameList.length > 0 && judgeNameList.length < 3) {             // minum of 1 judge and a maximum of 2

            this.headJudge = (Judge) TrackPeople.getPerson( // make the first appearing judge as the head
                    (String) judgeNameList[0]);

            for (Object name : judgeNameList) {
                judgesInEvent.add((Judge) TrackPeople.getPerson((String) name));// downcast person to judge and add to the event

            }

        }
    }

    /**
     * this method will add supporting staff members to the event
     *
     * @param sptMmbrNameList
     */
    public void addSupportingMembersToEvent(Object[] sptMmbrNameList) {
        sptMmbrsInEvent.clear();

        if (sptMmbrNameList.length > 1 && sptMmbrNameList.length < 4) {         // minimum of 2 and maximum of 3

            for (Object name : sptMmbrNameList) {
                sptMmbrsInEvent.add((SupportingStaffMember) TrackPeople. // add to the event
                        getPerson((String) name));

            }
        }
    }

    /**
     * This method will add spectators to the event
     *
     * @param spectatorNameList
     */
    public void addSpectatorsToEvent(Object[] spectatorNameList) {
        spectatorsInEvent.clear();
        ScoreBoard scoreBoard = this.swimmingPool.getScoreBoard();              // get the ScoreBoard

        if (spectatorNameList.length > 4) {                                     // minimum of 5
            for (Object name : spectatorNameList) {
                Spectator spec = (Spectator) TrackPeople.getPerson((String) name);
                spectatorsInEvent.add(spec);
                scoreBoard.addObserver(spec);                                       // adding Observer(Spectator) to the score board

            }
        }
    }

    /**
     * This method will check whether all the required people are added to the
     * event
     *
     * @return return 0 - if user select to continue even if this has failed
     * return 1 - if user wish to stay and add required people to the event
     * return -1 - event has not created by the user
     */
    public int isSet() {
        int i = -1;
        if (swimmersInEvent.isEmpty() || judgesInEvent.isEmpty()
                || sptMmbrsInEvent.isEmpty() || spectatorsInEvent.isEmpty()) {
            String msg = "";

            if (swimmersInEvent.isEmpty()) {
                msg = "Event should contain minimum if 3 swimmers "
                        + "and maximum of 5";

            } else if (judgesInEvent.isEmpty()) {
                msg = "Event should contain minimum of one judge "
                        + "and maximum of 2";

            } else if (sptMmbrsInEvent.isEmpty()) {
                msg = "Event should contain minimum of two supporting "
                        + "memebers and maximum of 3";
            } else if (spectatorsInEvent.isEmpty()) {
                msg = "Event should contain minimum of five Spectators";
            }
            Toolkit.getDefaultToolkit().beep();
            i = JOptionPane.showConfirmDialog(null,
                    msg + "\nDo you want to exit without saving?",
                    "Exit", JOptionPane.YES_NO_OPTION);
        }
        return i;
    }

    public Swimmer getWinner() {
        return this.swimmerWinner;
    }

    public void setWinner(Swimmer swimmerWinner) {
        this.swimmerWinner = swimmerWinner;
        SwimmingCompetition.serializeEvents();                                  // because boolen isCompleted is changed

    }
}
