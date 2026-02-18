package sk.stuba.fiit.events;

/**
 * The ArtExhibition class represents an event a user can attend.
 * It inherits attributes representing date and place (state and city).
 * It has private constructor and its instance is created using "Factory method" design pattern.
 *
 * @author Jakub Kula
 */
public class ArtExhibition extends Event{
    String place;

    private ArtExhibition(int date, int state) {
        super(date, state);
    }

    /**
     * Overridden method to return information specific to ArtExhibition object.
     *
     * @return The information about this object
     */
    @Override
    public String info() {
        return day+"."+month+". "+state+" - "+city+" '"+place+" art exhibition'\n";
    }

    /**
     * Factory method -> safely creating new object.
     *
     * @param date integer representing the date of this event
     * @param state integer representing the index of state
     *
     * @return The newly created ArtExhibition object
     */
    public static ArtExhibition artExhibitionFactory(int date, int state) {
        //input validation
        if (state<0 || state>8) {
            throw new IllegalArgumentException("Invalid input parameters for creating event");
        }

        //create and return a new instance of ArtExhibition
        return new ArtExhibition(date, state);
    }
}
