package sk.stuba.fiit.main;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.stuba.fiit.characters.*;
import sk.stuba.fiit.events.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The GameGUI class represents the Main class -> containing main method and the algorithm to calculate the best road of the tourne.
 * It inherits class Application to create stage of the GUI.
 *
 * @author Jakub Kula
 */
public class GameGUI extends Application {
    public static ArrayList<Client> clientsList = new ArrayList<Client>();
    public static ArrayList<Event> eventsList = new ArrayList<Event>();
    private static EventObserver observer = new EventObserver();

    public void start(Stage stage){

        try{
            //loading and creating the scene -> loaded from external .fxml file
            Parent root = FXMLLoader.load(getClass().getResource("/sk/stuba/fiit/gui/GameGUI.fxml"));
            Scene scene = new Scene(root);

            //styling the scene -> from external .css file
            scene.getStylesheets().add(getClass().getResource("/sk/stuba/fiit/gui/application.css").toExternalForm());

            //building the stage with our created scene
            stage.setScene(scene);
            stage.setTitle("Turn(é) It Up");
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        File file;
        BufferedReader reader;

        String line;
        String[] token;

        try{
            //creating file and reader
            file = new File("sk/stuba/fiit/database/Database.csv");
            reader = new BufferedReader(new FileReader(file));

            //skipping the first row (head of the table)
            reader.readLine();

            //saving already existing clients from database
            while((line = reader.readLine()) != null){
                token = line.split(";");
                if(token[3].equals("Artist")){
                    clientsList.add(new Artist(token[0], token[1], token[4]));
                }else if(token[3].equals("Group")){
                    clientsList.add(new Group(token[0], token[1], token[4]));
                }else if(token[3].equals("Solist")){
                    clientsList.add(new Solist(token[0], token[1], token[4]));
                }else{
                    System.out.println("Database error -> data in wrong columns");
                }
            }
            reader.close();

        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        launch(args);
    }

    public static void bestRoad(Client client, int since, int until){
        Random random = new Random();
        Event event;
        int j = 0, k = 0;

        if(client instanceof Singer){
            for(int i=0; i<(until-since); i++){
                if(i==20) break;
                if(j==8) j=0;
                if(k==(until-since)) k=0;

                if(random.nextBoolean()){
                    event = Concert.concertFactory(since+k, j);
                }else{
                    event = Festival.festivalFactory(since+k, j);
                }

                eventsList.add(event);
                observer.update(event);

                if(i%3==0)
                    j++;

                k++;
            }
        }else if(client instanceof Artist){
            for(int i=0; i<(until-since); i++){
                if(i==20) break;
                if(j==8) j=0;
                if(k==(until-since)) k=0;

                event = ArtExhibition.artExhibitionFactory(since+k, j);
                eventsList.add(event);
                observer.update(event);

                if(i%3==0)
                    j++;

                k++;
            }
        }
    }
}