package sk.stuba.fiit.characters;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The ClientInterface contains methods every class representing a user needs.
 *
 * @author Jakub Kula/
 */
public interface ClientInterface {

    /**
     * Assign package type to user.
     *
     * @param packageType user's type of package
     */
    void setPackageType(Packages packageType);

    /**
     * Serialize the object it gets in parameters.
     *
     * @param client object to serialize
     */
    default void serialization(Client client){
        String filename = "sk/stuba/fiit/database/client.ser";
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename, true)) {
            protected void writeStreamHeader() throws IOException {
                reset();
            }
        }) {
            outputStream.writeObject(new Client(client.username, client.password, ""));

            outputStream.flush();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to client.ser");
            e.printStackTrace();
        }
    }
}