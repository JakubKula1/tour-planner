package sk.stuba.fiit.characters;

/**
 * The Singer class represents a user of this app.
 * It inherits attributes representing username, password, type of package and list of reservations.
 *
 * @author Jakub Kula
 */
public class Singer extends Client{
    public Singer(String username, String password, String reservations) {
        super(username, password, reservations);
    }
}
