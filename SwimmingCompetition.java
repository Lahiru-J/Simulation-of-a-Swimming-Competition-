package Model;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import swimmingcompetition.MainWindowGUI;
import swimmingcompetition.SwimmingPoolGUI;

/**
 * This class initiates Swimming competition
 *
 * @author Lahiru
 */
public class SwimmingCompetition {

    private static ArrayList<SwimmingEvent> swimmingEventList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());

            SerializeObject deserialize = new SerializeObject();
            TrackPeople.setPeople(deserialize.deserializePeople( // get the input from a file
                    "src/serFiles/listPeople.ser"));

            setSwimmingEvent(deserialize.deserializeEvent(
                    "src/serFiles/listSwimmingEvents.ser"));

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());

        }

        new MainWindowGUI().setVisible(true);

    }

    public static void addPeopleToTheCompetition(String person, String strName) {

        if (!TrackPeople.getNamesPeople(true).contains(strName.toLowerCase())
                && !strName.isEmpty()) {

            switch (person) {
                case "New Male Swimmer":
                    new MaleSwimmer(strName);                                   // add a new male swimmer
                    break;
                case "New Female Swimmer":
                    new FemaleSwimmer(strName);                                 // add a new female swimmer
                    break;
                case "New Judge":
                    new Judge(strName);                                         // add a new Judge
                    break;
                case "New Supporting Member":
                    new SupportingStaffMember(strName);                         // add a new Specator
                    break;
                case "New Spectator":
                    new Spectator(strName);                                     // add a new Supporting member
                    break;
            }
        } else if (!strName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "This name is already exist!",
                    "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean addNewSwimmingEvent(String eventName,
            String swimmingStyle, String gender) {

        boolean isAdded = false;

        if (!getEventNames().contains(eventName)) {
            swimmingEventList.add(new SwimmingEvent(eventName, swimmingStyle, gender));
            isAdded = true;

        } else {
            JOptionPane.showMessageDialog(null, "This event name already exists!");
        }
        return isAdded;
    }

    /**
     * This method will return the SwimmingEvent which has the name of eventName
     *
     * @param eventName name of the event
     * @return SwimmingEvent of the name eventName
     */
    public static SwimmingEvent getEvent(String eventName) {
        SwimmingEvent sEvnt = null;

        for (SwimmingEvent swimmingEvent : swimmingEventList) {
            if (swimmingEvent.getEventName().equals(eventName)) {
                sEvnt = swimmingEvent;
            }
        }
        return sEvnt;
    }

    public static void startTheEvent(String eventName) {
        SwimmingEvent event = SwimmingCompetition.getEvent(eventName);
        if (!event.isCompleted()) {
            SwimmingPoolGUI pool = new SwimmingPoolGUI(null, true, event);
            pool.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "This event has already held!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static ArrayList<SwimmingEvent> getSwimmingEvents() {
        return swimmingEventList;
    }

    public static ArrayList<String> getEventNames() {
        ArrayList<String> events = new ArrayList<>();

        for (SwimmingEvent evnt : swimmingEventList) {
            events.add(evnt.getEventName());
        }
        return events;
    }

    private static void setSwimmingEvent(ArrayList<SwimmingEvent> listSwimmingEvent) {
        if (listSwimmingEvent != null) {
            swimmingEventList = listSwimmingEvent;
        }
    }

    public static void serializeEvents() {
        SerializeObject serialize = new SerializeObject();
        serialize.serializeEvent(swimmingEventList, // save the array list SwimmingEvent objects to a file
                "src/serFiles/listSwimmingEvents.ser");
    }

    public static void removeEvent(SwimmingEvent event) {
        swimmingEventList.remove(event);
        serializeEvents();
    }
    
    public static void removeAllEvents(){
        swimmingEventList.clear();
        serializeEvents();
    }

}
