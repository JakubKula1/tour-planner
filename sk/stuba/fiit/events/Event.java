package sk.stuba.fiit.events;

import java.io.*;
import java.util.Random;

/**
 * The Event class represents an event a user can attend.
 * It contains attributes representing state, city and date of the event.
 * It implements interface Serializable to serialize its objects (it overrides methods writeObject and readObject to serialize only a certain attributes).
 *
 * @author Jakub Kula
 */
public abstract class Event implements Serializable {
    transient Random random = new Random();
    public String state, city;
    private transient final String[][] location = {
            {"Portugal", "Porto", "Lisbon", "Braga", "Braga", "Lagos"},
            {"Spain", "Madrid", "Barcelona", "Valencia", "Seville", "Bilbao"},
            {"France", "Paris", "Marseille", "Strasbourg", "Lyon", "Lille"},
            {"Italy", "Rome", "Milan", "Naples", "Turin", "Bologna"},
            {"Germany", "Munich", "Berlin", "Hamburg", "Leipzig", "Frankfurt"},
            {"Czechia", "Prague", "Brno", "Ostrava", "Liberec", "Olomouc"},
            {"Slovakia", "Bratislava", "Kosice", "Nitra", "Zilina", "Poprad"},
            {"Poland", "Warsaw", "Krakow", "Wroclaw", "Poznan", "Katowice"}
    };
    public int date;
    public transient int day, month;

    public Event(int date, int state){
        this.state = location[state][0];
        this.city = location[state][random.nextInt(5)+1];
        this.date = date;
        this.day = (date%100);
        this.month = (date/100);
    }

    /**
     * Method returning information about an object that is overridden because of the differences of its subclasses.
     */
    public abstract String info();

    /**
     * Overridden method of Serializable interface for writing the chosen attributes (state) of the object.
     * Method throws IOException if there is a problem with ObjectOutputStream.
     *
     * @param out ObjectOutputStream
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(state);
        out.writeObject(city);
        out.writeObject(date);
    }

    /**
     * Overridden method of Serializable interface for reading the chosen attributes (state) of the object.
     * Method throws IOException if there is a problem with ObjectOutputStream and ClassNotFoundException exception if the class was not found.
     *
     * @param in ObjectInputStream
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        state = (String) in.readObject();
        city = (String) in.readObject();
        date = (int) in.readObject();
    }
}
