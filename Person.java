package Model;

import java.io.Serializable;

/**
 * This class holds basic attributes and states of a person
 *
 * @author Lahiru
 */
public abstract class Person implements Serializable{

    /* name of the person */
    private final String name;

    public Person(String name) {
        this.name = name;
        TrackPeople.addPerson(this);                                            // keep track on the Person created
    }

    /**
     * @return the name of the specific person
     */
    public String getName() {
        return this.name;
    }
}
