package sk.stuba.fiit.events;

import java.util.Random;

/**
 * The EventObserver represent observer that listens to Event objects and all its subclasses objects.
 * It updates the date of the events and two attributes of ArtExhibition and Festival objects using down-casting.
 *
 * @author Jakub Kula
 */
public class EventObserver {
    private Random random = new Random();

    /**
     * Updates the date of events and one attribute of ArtExhibition and Festival objects.
     *
     * @param event user's type of package
     */
    public void update(Event event){
        if(event instanceof ArtExhibition){
            if(random.nextBoolean())
                ((ArtExhibition) event).place = "Museum";
            else ((ArtExhibition) event).place = "Gallery";
        }else if(event instanceof Festival){
            ((Festival) event).setDuration(random.nextInt(3)+1);
        }

        if(event.day>30){
            event.month++;
            event.day -= 30;
        }
    }
}
