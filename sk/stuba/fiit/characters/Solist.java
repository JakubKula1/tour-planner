package sk.stuba.fiit.characters;

/**
 * The Solist class represents a user of this app.
 * It inherits attributes representing username, password, type of package and list of reservations.
 *
 * @author Jakub Kula
 */
public class Solist extends Singer{
    public Solist(String username, String password, String reservations) {
        super(username, password, reservations);
    }
}
