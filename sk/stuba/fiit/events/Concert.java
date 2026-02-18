package sk.stuba.fiit.events;

/**
 * The Concert class represents an event a user can attend.
 * It inherits attributes representing date and place (state and city).
 * It has private constructor and its instance is created using "Factory method" design pattern.
 *
 * @author Jakub Kula
 */
public class Concert extends Event{
    private Concert(int date, int state) {
        super(date, state);
    }

    /**
     * Overridden method to return information specific to Concert object.
     *
     * @return The information about this object
     */
    @Override
    public String info() {
        return day+"."+month+". "+state+" - "+city+" 'Concert'\n";
    }

    /**
     * Factory method -> safely creating new object.
     *
     * @param date integer representing the date of this event
     * @param state integer representing the index of state
     *
     * @return The newly created Concert object
     */
    public static Concert concertFactory(int date, int state) {
        //input validation
        if (state<0 || state>8) {
            throw new IllegalArgumentException("Invalid input parameters for creating event");
        }

        //create and return a new instance of Concert
        return new Concert(date, state);
    }
}
