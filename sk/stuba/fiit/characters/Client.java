package sk.stuba.fiit.characters;

import sk.stuba.fiit.events.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * The Client class represents a user of this app with username, password, type of package and list of reservations.
 * It includes methods for setting the package type, sorting the list of reservations to correct order and deserialization.
 * It implements interface Serializable to serialize its objects (it overrides methods writeObject and readObject to serialize only a certain attributes).
 * It also implements interface ClientInterface -> overriding methods packageType and serialization.
 *
 * @author Jakub Kula
 */
public class Client implements Serializable, ClientInterface {
    public String username;
    public String password;
    public transient String reservations;
    public ArrayList<Event> eventsReservations = new ArrayList<Event>();
    public transient Packages packageType;

    public Client(String username, String password, String reservations){
        this.username = username;
        this.password = password;
        this.reservations = reservations;
    }


    /**
     * Assign package type to user.
     *
     * @param packageType user's type of package
     */
    @Override
    public void setPackageType(Packages packageType){
        this.packageType = packageType;
    }

    /**
     * Deserialize objects in the "client.ser" file and returns the one with equal username and password.
     * If there is no such object (the user isn't registered), it will return null
     *
     * @param enteredName name from TextField for comparison
     * @param enteredPassword password from TextField for comparison
     *
     * @return object Client with matching username and password, if it's not found it returns null
     */
    public static Client deserialization(String enteredName, String enteredPassword){
        String filename = "sk/stuba/fiit/database/client.ser";
        File file = new File(filename);
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Client client = (Client) inputStream.readObject();
                if (client.username.equals(enteredName) && client.password.equals(enteredPassword))
                    return client;
            }
        } catch (EOFException e) {
            // End of file
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while reading from client.ser.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Overridden method of Serializable interface for writing the chosen attributes (state) of the object.
     * Method throws IOException if there is a problem with ObjectOutputStream.
     *
     * @param out ObjectOutputStream
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(username);
        out.writeObject(password);
        out.writeObject(eventsReservations);
    }

    /**
     * Overridden method of Serializable interface for reading the chosen attributes (state) of the object.
     * Method throws IOException if there is a problem with ObjectOutputStream and ClassNotFoundException exception if the class was not found.
     *
     * @param in ObjectInputStream
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        username = (String) in.readObject();
        password = (String) in.readObject();
        eventsReservations = (ArrayList<Event>) in.readObject();
    }

    /**
     * Method for sorting the user's list of events to correct order.
     */
    public void sorting(){
        //sorting the events by date
        this.eventsReservations.sort(Comparator.comparing((Event e) -> e.month)
                .thenComparing((Event e) -> e.day));
    }
}