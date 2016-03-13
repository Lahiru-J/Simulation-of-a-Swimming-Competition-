package Model;

/**
 * SupportingStaffMember class gives the functions of a supporting staff member.
 *
 * @author Lahiru 
 */
public class SupportingStaffMember extends Person{

    /**
     * Creates a new Spectator
     *
     * @param name Name of the SupportingStaffMember
     */
    public SupportingStaffMember(String name) {
        super(name);
        TrackPeople.addSupportingMember(this);      // keep track on the SupportingStaffMember created
    }

    /* support for the competition */
    public void support() {
        System.out.println("Support!!!");
    }

}
