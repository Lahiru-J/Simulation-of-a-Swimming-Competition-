package Model;

import java.util.ArrayList;

/**
 * This class provides help to keep records of a person whenever a person is
 * created
 *
 * @author Lahiru
 */
public class TrackPeople {  // try to reduce this class to one single list

    private static ArrayList<Person> listPerson
            = new ArrayList<>();
    private static final ArrayList<MaleSwimmer> listMaleSwimmers
            = new ArrayList<>();
    private static final ArrayList<FemaleSwimmer> listFemaleSwimmers
            = new ArrayList<>();
    private static final ArrayList<Judge> listJudges
            = new ArrayList<>();
    private static final ArrayList<Spectator> listSpectators
            = new ArrayList<>();
    private static final ArrayList<SupportingStaffMember> listSupportingMembers
            = new ArrayList<>();

    /**
     * @return ArrayList of the Person
     */
    public static ArrayList<Person> getListPeople() {
        return listPerson;
    }

    /**
     *
     * @return ArrayList of MaleSwimmers
     */
    public static ArrayList<MaleSwimmer> getListMaleSwimmers() {
        return listMaleSwimmers;
    }

    /**
     *
     * @return ArrayList of FemaleSwimmers
     */
    public static ArrayList<FemaleSwimmer> getListFemaleSwimmers() {
        return listFemaleSwimmers;
    }

    /**
     *
     * @return ArrayList of Judges
     */
    public static ArrayList<Judge> getListJudges() {
        return listJudges;
    }

    /**
     *
     * @return ArrayList of Spectators
     */
    public static ArrayList<Spectator> getListSpectators() {
        return listSpectators;
    }

    /**
     *
     * @return ArrayList of Supporting Staff Members
     */
    public static ArrayList<SupportingStaffMember> getListSupportingStaffMembers() {
        return listSupportingMembers;
    }

    /**
     * @param isLowerCase if true get the names of the person in lowercase
     * format and false to get the initial forma
     * @return names of the people
     */
    public static ArrayList<String> getNamesPeople(boolean isLowerCase) {

        ArrayList<String> nameList = new ArrayList<>();

        for (Person p : listPerson) {
            if (isLowerCase) {
                nameList.add(p.getName().toLowerCase());                        //adding the names of people to the nameList
            } else {
                nameList.add(p.getName());
            }
        }
        return nameList;
    }

    /**
     *
     * @return ArrayList of names of the Male swimmers enrolled in this
     * competition
     */
    public static ArrayList<String> getNamesMaleSwimmers() {
        ArrayList<String> listNames = new ArrayList<>();

        for (MaleSwimmer ms : listMaleSwimmers) {
            listNames.add(ms.getName());
        }
        return listNames;
    }

    /**
     *
     * @return ArrayList of names of the Female swimmers enrolled in this
     * competition
     */
    public static ArrayList<String> getNamesFemaleSwimmers() {
        ArrayList<String> listNames = new ArrayList<>();

        for (FemaleSwimmer fs : listFemaleSwimmers) {
            listNames.add(fs.getName());
        }
        return listNames;
    }

    /**
     *
     * @return ArrayList of Judges in this competition
     */
    public static ArrayList<String> getNamesJudges() {
        ArrayList<String> listNames = new ArrayList<>();

        for (Judge judge : listJudges) {
            listNames.add(judge.getName());
        }
        return listNames;
    }

    /**
     *
     * @return ArrayList of Spectators in this competition
     */
    public static ArrayList<String> getNamesSpectators() {
        ArrayList<String> listNames = new ArrayList<>();

        for (Spectator spectator : listSpectators) {
            listNames.add(spectator.getName());
        }
        return listNames;
    }

    /**
     *
     * @return ArrayList of Supporting staff members in this competition
     */
    public static ArrayList<String> getNamesSupportingMembers() {
        ArrayList<String> listNames = new ArrayList<>();

        for (SupportingStaffMember sptMmbr : listSupportingMembers) {
            listNames.add(sptMmbr.getName());
        }
        return listNames;
    }

    /**
     * This method return the person object which has a name of personName
     *
     * @param personName name of the Person
     * @return Person object
     */
    public static Person getPerson(String personName) {
        Person personObj = null;
        for (Person person : listPerson) {
            if (person.getName().equals(personName)) {
                personObj = person;
            }
        }
        return personObj;
    }

    /**
     * This Method assign listPeople to the listPerson and each MaleSwimmer,
     * FemaleSwimmer, Judge, SupportingStaffMember, Spectator to the already
     * defined ArrayLists
     *
     * @param listPeople ArrayList of Person object
     */
    public static void setPeople(ArrayList<Person> listPeople) {
        if (listPeople != null) {
            listPerson = listPeople;

            for (Person person : listPeople) {

                if (person instanceof MaleSwimmer) {
                    listMaleSwimmers.add((MaleSwimmer) person);
                } else if (person instanceof FemaleSwimmer) {
                    listFemaleSwimmers.add((FemaleSwimmer) person);
                } else if (person instanceof Judge) {
                    listJudges.add((Judge) person);
                } else if (person instanceof SupportingStaffMember) {
                    listSupportingMembers.add((SupportingStaffMember) person);
                } else if (person instanceof Spectator) {
                    listSpectators.add((Spectator) person);
                }
            }
        }
    }

    /**
     * add a new Person
     *
     * @param person
     */
    public static void addPerson(Person person) {
        TrackPeople.listPerson.add(person);

        SerializeObject serialize = new SerializeObject();
        serialize.serializePeople(listPerson, // save the array list person objects to a file
                "src/serFiles/listPeople.ser");

    }

    /**
     * add a new MaleSwimmer
     *
     * @param maleSwimmer
     */
    public static void addMaleSwimmer(MaleSwimmer maleSwimmer) {
        TrackPeople.listMaleSwimmers.add(maleSwimmer);
    }

    /**
     * add a new FemaleSwimmer
     *
     * @param femaleSwimmer
     */
    public static void addFemaleSwimmer(FemaleSwimmer femaleSwimmer) {
        TrackPeople.listFemaleSwimmers.add(femaleSwimmer);
    }

    /**
     * add a new Judge
     *
     * @param judge
     */
    public static void addJudge(Judge judge) {
        TrackPeople.listJudges.add(judge);
    }

    /**
     * add a new Spectator
     *
     * @param spectator
     */
    public static void addSpectator(Spectator spectator) {
        TrackPeople.listSpectators.add(spectator);
    }

    /**
     * add a new SupportingStaffMemeber
     *
     * @param supportingMember
     */
    public static void addSupportingMember(SupportingStaffMember supportingMember) {
        TrackPeople.listSupportingMembers.add(supportingMember);
    }

    public static void removePerson(String strPerson) {
        Person person = getPerson(strPerson);
        listPerson.remove(person);

        if (person instanceof MaleSwimmer) {
            listMaleSwimmers.remove(person);
        } else if (person instanceof FemaleSwimmer) {
            listFemaleSwimmers.remove(person);
        } else if (person instanceof Judge) {
            listJudges.remove(person);
        } else if (person instanceof SupportingStaffMember) {
            listSupportingMembers.remove(person);
        } else if (person instanceof Spectator) {
            listSpectators.remove(person);
        }

        SerializeObject serialize = new SerializeObject();
        serialize.serializePeople(listPerson, // save the array list person objects to a file
                "src/serFiles/listPeople.ser");
    }
}
