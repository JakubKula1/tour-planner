package sk.stuba.fiit.events;

/**
 * The Festival class represents an event a user can attend.
 * It inherits attributes representing date and place (state and city).
 * It has private constructor and its instance is created using "Factory method" design pattern.
 *
 * @author Jakub Kula
 */
public class Festival extends Event{
    private int duration;

    private Festival(int date, int state) {
        super(date, state);
    }

    //setter method to set the duration of Festival object
    public void setDuration(int i){
        this.duration = i;
    }

    /**
     * Overridden method to return information specific to Festival object.
     *
     * @return The information about this object
     */
    @Override
    public String info() {
        return day+"."+month+". "+state+" - "+city+" "+duration+" day 'Festival'\n";
    }

    /**
     * Factory method -> safely creating new object.
     *
     * @param date integer representing the date of this event
     * @param state integer representing the index of state
     *
     * @return The newly created Festival object
     */
    public static Festival festivalFactory(int date, int state) {
        //input validation
        if (state<0 || state>8) {
            throw new IllegalArgumentException("Invalid input parameters for creating event");
        }

        //create and return a new instance of Festival
        return new Festival(date, state);
    }
}
