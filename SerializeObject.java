package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class hold the functions to save the objects that are being created
 * in this competition
 * @author Lahiru
 */
public class SerializeObject {

    /**
     * This method will serialize i.e. save all the person objects
     * @param list  Person list
     * @param fileName  
     */
    public void serializePeople(ArrayList<Person> list, String fileName) {

        try (ObjectOutputStream objOut
                = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objOut.writeObject(list);

        } catch (IOException ex) {
            System.out.println("Problem occured during serilalization");
            ex.printStackTrace();
        }

    }

    /**
     * This method will retrieve the data from the secondary storage
     * @param fileName
     * @return ArrayList of Person
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Person> deserializePeople(String fileName)
            throws FileNotFoundException, IOException, ClassNotFoundException {

        ArrayList<Person> list = null;
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        list = (ArrayList<Person>) objIn.readObject();

        return list;
    }

    /**
     * This method will serialize all the events that are created in this 
     * competition
     * @param list
     * @param fileName 
     */
    public void serializeEvent(ArrayList<SwimmingEvent> list, String fileName) {

        try (ObjectOutputStream objOut
                = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objOut.writeObject(list);

        } catch (IOException ex) {
            System.out.println("Problem occured during serilalization");
            ex.printStackTrace();
        }

    }

    /**
     * This method will retrieve the data from the secondary storage 
     * @param fileName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public ArrayList<SwimmingEvent> deserializeEvent(String fileName) 
            throws FileNotFoundException, IOException, ClassNotFoundException {
        
        ArrayList<SwimmingEvent> list = null;
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        list = (ArrayList<SwimmingEvent>) objIn.readObject();
        return list;
    }
}
