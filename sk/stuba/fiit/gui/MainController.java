package sk.stuba.fiit.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sk.stuba.fiit.Exceptions.InvalidDateException;
import sk.stuba.fiit.characters.*;
import sk.stuba.fiit.events.Concert;
import sk.stuba.fiit.events.Event;
import sk.stuba.fiit.events.Festival;
import sk.stuba.fiit.main.GameGUI;

import java.time.LocalDate;
import java.util.*;

/**
 * The MainController class represents controller of the Scene (GUI).
 * It consists of a bunch of Buttons, TextFields and Labels.
 * It just controls the Scene (GUI).
 *
 * @author Jakub Kula
 */
public class MainController {
    private Client client;
    private Timeline timeline;
    private Boolean isArtist = false, isSolist = false, isGroup = false;
    @FXML
    private DatePicker sinceDate;
    @FXML
    private DatePicker untilDate;
    @FXML
    private CheckBox selectAll;
    @FXML
    private CheckBox portugalCheckBox;
    @FXML
    private CheckBox italyCheckBox;
    @FXML
    private CheckBox spainCheckBox;
    @FXML
    private CheckBox czechiaCheckBox;
    @FXML
    private CheckBox franceCheckBox;
    @FXML
    private CheckBox slovakiaCheckBox;
    @FXML
    private CheckBox germanyCheckBox;
    @FXML
    private CheckBox polandCheckBox;
    @FXML
    private Label selectionLbl1;
    @FXML
    private Label selectionLbl2;
    @FXML
    private Label infoLbl;
    @FXML
    private Label infoLbl2;
    @FXML
    private Label infoLbl3;
    @FXML
    private Label infoLbl4;
    @FXML
    private Button nextBtn1;
    @FXML
    private Button nextBtn2;
    @FXML
    private Button previousBtn1;
    @FXML
    private Label registerLbl1;
    @FXML
    private Label registerLbl2;
    @FXML
    private Label registrationLbl1;
    @FXML
    private Label registrationLbl2;
    @FXML
    private Label registrationLbl3;
    @FXML
    private TextField registrationUserName;
    @FXML
    private TextField registrationPassword;
    @FXML
    private Button registrationBtn;
    @FXML
    private Rectangle rectangle;
    @FXML
    private CheckBox singerCheckBox;
    @FXML
    private CheckBox artistCheckBox;
    @FXML
    private CheckBox solistCheckBox;
    @FXML
    private CheckBox groupCheckBox;
    @FXML
    private Label startScreenLbl1;
    @FXML
    private Label startScreenLbl2;
    @FXML
    private Label startScreenLbl3;
    @FXML
    private Button btnToRegister;
    @FXML
    private Button btnToLogin;
    @FXML
    private Button backBtn;
    @FXML
    private TextField userNameTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private Button loginBtn;
    @FXML
    private Label loginLbl1;
    @FXML
    private Label loginLbl2;
    @FXML
    private Label loginLbl3;
    @FXML
    private Label welcomeBackLbl;
    @FXML
    private Button logoutBtn;

    private void loginValidation(String userName, String password, ArrayList<Client> clients){
        //looping over the already existing clients and checking if the login is valid
        for(int i=0; i<clients.size(); i++){
            //login valid -> updating the scene (app window)
            if(clients.get(i).username.equals(userName) && clients.get(i).password.equals(password)){
                loginLbl1.setVisible(false);
                loginLbl2.setVisible(false);
                userNameTxt.setVisible(false);
                passwordTxt.setVisible(false);
                loginBtn.setVisible(false);
                backBtn.setVisible(false);

                loginLbl3.setVisible(true);
                welcomeBackLbl.setVisible(true);

                timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> {
                    loginLbl3.setVisible(false);
                    welcomeBackLbl.setVisible(false);
                    rectangle.setVisible(true);
                    infoLbl4.setVisible(true);
                    infoLbl3.setVisible(true);
                    logoutBtn.setVisible(true);
                }));
                timeline.play();

                infoLbl3.setText("Your plan");
                String string = "";
                client = clients.get(i);
                skuska();

                for(Event event : client.eventsReservations){
                    string += event.info();
                }
                infoLbl4.setText(string);

                logoutBtn.setOnAction(e -> {
                    client.serialization(client);
                    rectangle.setVisible(false);
                    infoLbl4.setVisible(false);
                    infoLbl3.setVisible(false);
                    logoutBtn.setVisible(false);

                    startScreenLbl1.setVisible(true);
                    startScreenLbl2.setVisible(true);
                    startScreenLbl3.setVisible(true);
                    btnToRegister.setVisible(true);
                    btnToLogin.setVisible(true);

                    clearAll();
                });

                return;
            }
        }

        //login invalid -> rendering "Login Failed" (info) label
        loginLbl2.setVisible(true);

        //stop rendering "Login Failed" (info) label with 4sec delay
        timeline = new Timeline(new KeyFrame(Duration.seconds(4), e -> {
            loginLbl2.setVisible(false);
        }));
        timeline.play();
    }

    public void login(ActionEvent event){
        loginValidation(userNameTxt.getText(), passwordTxt.getText(), GameGUI.clientsList);
    }

    private void changeScene(Boolean b1, Boolean b2, Boolean b3){
        //from "Login" or "Registration" scene to "Start" scene
        startScreenLbl1.setVisible(b1);
        startScreenLbl2.setVisible(b1);
        startScreenLbl3.setVisible(b1);
        btnToRegister.setVisible(b1);
        btnToLogin.setVisible(b1);

        //from "Start" scene to "Login" scene
        loginLbl1.setVisible(b2);
        userNameTxt.setVisible(b2);
        passwordTxt.setVisible(b2);
        loginBtn.setVisible(b2);

        //from "Start" scene to "Registration" scene
        registrationLbl1.setVisible(b3);
        registrationLbl2.setVisible(b3);
        registrationLbl3.setVisible(b3);
        registrationBtn.setVisible(b3);
        registrationUserName.setVisible(b3);
        registrationPassword.setVisible(b3);

        backBtn.setVisible(!(b1));
    }

    /**
     * Method called after logging in to deserialize serialized objects of class Client.
     *
     * @param event ActionEvent object
     */
    public void loggingIn(ActionEvent event){
        client = Client.deserialization(userNameTxt.getText(), passwordTxt.getText());
    }

    public void backToStart(ActionEvent event){
        changeScene(true, false, false);
    }

    public void toLogin(ActionEvent event){
        changeScene(false, true, false);
    }

    public void toRegister(ActionEvent event){
        changeScene(false, false, true);
    }

    public void halfRegistered(ActionEvent event){
        //loggingIn(event);
        if(!(registrationUserName.getText().isEmpty()) && !(registrationPassword.getText().isEmpty())){
            registrationLbl1.setVisible(false);
            registrationLbl2.setVisible(false);
            registrationLbl3.setVisible(false);
            registrationBtn.setVisible(false);
            registrationUserName.setVisible(false);
            registrationPassword.setVisible(false);
            backBtn.setVisible(false);

            rectangle.setVisible(true);
            registerLbl1.setVisible(true);
            registerLbl2.setVisible(true);
            registerLbl2.setText("Hello "+registrationUserName.getText());
            singerCheckBox.setVisible(true);
            artistCheckBox.setVisible(true);
            nextBtn1.setVisible(true);

        }else{
            if(registrationUserName.getText().isEmpty()){
                registrationUserName.setStyle("-fx-background-color: rgba(200,25,25,0.5);");
                timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                    registrationUserName.setStyle("-fx-background-color: white;");
                }));
                timeline.play();
            }
            if(registrationPassword.getText().isEmpty()){
                registrationPassword.setStyle("-fx-background-color: rgba(200,25,25,0.5)");
                timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                    registrationPassword.setStyle("-fx-background-color: white;");
                }));
                timeline.play();
            }
        }
    }

    public void typeSelected(ActionEvent event){
        //creating (signing up) new client
        if(artistCheckBox.isSelected())
            client = new Artist(userNameTxt.getText(), passwordTxt.getText(), "none");
        else if(solistCheckBox.isSelected())
            client = new Solist(userNameTxt.getText(), passwordTxt.getText(), "none");
        else if(groupCheckBox.isSelected())
            client = new Group(userNameTxt.getText(), passwordTxt.getText(), "none");
        else{
            artistCheckBox.lookup(".box").setStyle("-fx-background-color: rgba(200,25,25,0.5);");
            singerCheckBox.lookup(".box").setStyle("-fx-background-color: rgba(200,25,25,0.5);");
            solistCheckBox.lookup(".box").setStyle("-fx-background-color: rgba(200,25,25,0.5);");
            groupCheckBox.lookup(".box").setStyle("-fx-background-color: rgba(200,25,25,0.5);");
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                artistCheckBox.lookup(".box").setStyle("");
                singerCheckBox.lookup(".box").setStyle("");
                solistCheckBox.lookup(".box").setStyle("");
                groupCheckBox.lookup(".box").setStyle("");
            }));
            timeline.play();
            return;
        }

        artistCheckBox.setVisible(false);
        singerCheckBox.setVisible(false);
        solistCheckBox.setVisible(false);
        groupCheckBox.setVisible(false);
        registerLbl1.setVisible(false);
        registerLbl2.setVisible(false);
        nextBtn1.setVisible(false);

        sinceDate.setVisible(true);
        untilDate.setVisible(true);
        portugalCheckBox.setVisible(true);
        italyCheckBox.setVisible(true);
        spainCheckBox.setVisible(true);
        czechiaCheckBox.setVisible(true);
        slovakiaCheckBox.setVisible(true);
        franceCheckBox.setVisible(true);
        germanyCheckBox.setVisible(true);
        polandCheckBox.setVisible(true);
        selectAll.setVisible(true);
        selectionLbl1.setVisible(true);
        selectionLbl2.setVisible(true);
        previousBtn1.setVisible(true);
        nextBtn2.setVisible(true);
    }

    public void previousOne(ActionEvent event){
        artistCheckBox.setVisible(true);
        artistCheckBox.setSelected(false);
        singerCheckBox.setVisible(true);
        singerCheckBox.setSelected(false);
        solistCheckBox.setSelected(false);
        groupCheckBox.setSelected(false);
        registerLbl1.setVisible(true);
        registerLbl2.setVisible(true);
        nextBtn1.setVisible(true);

        sinceDate.setVisible(false);
        untilDate.setVisible(false);
        portugalCheckBox.setVisible(false);
        italyCheckBox.setVisible(false);
        spainCheckBox.setVisible(false);
        czechiaCheckBox.setVisible(false);
        slovakiaCheckBox.setVisible(false);
        franceCheckBox.setVisible(false);
        germanyCheckBox.setVisible(false);
        polandCheckBox.setVisible(false);
        selectAll.setVisible(false);
        selectionLbl1.setVisible(false);
        selectionLbl2.setVisible(false);
        previousBtn1.setVisible(false);
        nextBtn2.setVisible(false);
    }

    public void singerSelected(ActionEvent event){
        if(artistCheckBox.isSelected())
            artistCheckBox.setSelected(false);

        if(singerCheckBox.isSelected()){
            solistCheckBox.setVisible(true);
            groupCheckBox.setVisible(true);
        }else{
            solistCheckBox.setSelected(false);
            groupCheckBox.setSelected(false);
            solistCheckBox.setVisible(false);
            groupCheckBox.setVisible(false);
        }

        isArtist = false;
        isSolist = false;
        isGroup = false;
    }

    public void artistSelected(ActionEvent event){
        if(singerCheckBox.isSelected()){
            singerCheckBox.setSelected(false);
            solistCheckBox.setSelected(false);
            groupCheckBox.setSelected(false);
            solistCheckBox.setVisible(false);
            groupCheckBox.setVisible(false);
        }

        isArtist = true;
        isSolist = false;
        isGroup = false;
    }

    public void solistSelected(ActionEvent event){
        if(groupCheckBox.isSelected())
            groupCheckBox.setSelected(false);

        isArtist = false;
        isSolist = true;
        isGroup = false;
    }

    public void groupSelected(ActionEvent event){
        if(solistCheckBox.isSelected())
            solistCheckBox.setSelected(false);

        isArtist = false;
        isSolist = false;
        isGroup = true;
    }

    public void allSelect(ActionEvent event){
        if(selectAll.isSelected()){
            portugalCheckBox.setSelected(true);
            italyCheckBox.setSelected(true);
            spainCheckBox.setSelected(true);
            czechiaCheckBox.setSelected(true);
            franceCheckBox.setSelected(true);
            slovakiaCheckBox.setSelected(true);
            germanyCheckBox.setSelected(true);
            polandCheckBox.setSelected(true);
        }else{
            portugalCheckBox.setSelected(false);
            italyCheckBox.setSelected(false);
            spainCheckBox.setSelected(false);
            czechiaCheckBox.setSelected(false);
            franceCheckBox.setSelected(false);
            slovakiaCheckBox.setSelected(false);
            germanyCheckBox.setSelected(false);
            polandCheckBox.setSelected(false);
        }
    }

    private void skuska(){
        client.eventsReservations.add(Concert.concertFactory(520, 0));
        client.eventsReservations.add(Concert.concertFactory(521, 0));
        client.eventsReservations.add(Festival.festivalFactory(522, 1));
        client.eventsReservations.add(Concert.concertFactory(524, 1));
        client.eventsReservations.add(Concert.concertFactory(526, 2));
        client.eventsReservations.add(Concert.concertFactory(527, 2));
        client.eventsReservations.add(Concert.concertFactory(528, 2));
        client.eventsReservations.add(Concert.concertFactory(529, 3));
        client.eventsReservations.add(Concert.concertFactory(601, 3));
        client.eventsReservations.add(Festival.festivalFactory(602, 4));
        client.eventsReservations.add(Concert.concertFactory(605, 4));
        client.eventsReservations.add(Concert.concertFactory(607, 5));
        client.eventsReservations.add(Festival.festivalFactory(608, 5));
        client.eventsReservations.add(Festival.festivalFactory(611, 6));
        client.eventsReservations.add(Concert.concertFactory(614, 6));
        client.eventsReservations.add(Concert.concertFactory(616, 7));
        client.eventsReservations.add(Concert.concertFactory(617, 7));
    }

    public void countriesSelected(ActionEvent event){
        try{
            if(sinceDate.getValue().isBefore(LocalDate.now().plusWeeks(1)))
                throw new InvalidDateException(sinceDate, untilDate);
        }catch(InvalidDateException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Date");
            alert.setHeaderText("The selected date is invalid.");
            alert.setContentText("The date has been set to "+sinceDate.getValue()+" - "+untilDate.getValue());
            alert.showAndWait();
        }

        if(sinceDate.getValue() != null && untilDate.getValue() != null){
            int since = Integer.parseInt(String.format("%02d%02d", sinceDate.getValue().getMonthValue(),
                    sinceDate.getValue().getDayOfMonth()));
            int until = Integer.parseInt(String.format("%02d%02d", untilDate.getValue().getMonthValue(),
                    untilDate.getValue().getDayOfMonth()));

            if(!(until > since)){
                System.out.println("Since date is bigger than Until date");
                sinceDate.getEditor().setStyle("-fx-background-color: rgba(200,25,25,0.5);");
                untilDate.getEditor().setStyle("-fx-background-color: rgba(200,25,25,0.5);");
                timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                    sinceDate.getEditor().setStyle("-fx-background-color: white;");
                    untilDate.getEditor().setStyle("-fx-background-color: white;");
                }));
                timeline.play();

                return;
            }

            GameGUI.bestRoad(client, since, until);

            //sorting the events by the location
            if(portugalCheckBox.isSelected()){
                client.eventsReservations.addAll(GameGUI.eventsList.stream()
                        .filter(eventik -> eventik.state.equals("Portugal")).toList());
            }if(spainCheckBox.isSelected()){
                client.eventsReservations.addAll(GameGUI.eventsList.stream()
                        .filter(eventik -> eventik.state.equals("Spain")).toList());
            }if(franceCheckBox.isSelected()){
                client.eventsReservations.addAll(GameGUI.eventsList.stream()
                        .filter(eventik -> eventik.state.equals("France")).toList());
            }if(italyCheckBox.isSelected()){
                client.eventsReservations.addAll(GameGUI.eventsList.stream()
                        .filter(eventik -> eventik.state.equals("Italy")).toList());
            }if(germanyCheckBox.isSelected()){
                client.eventsReservations.addAll(GameGUI.eventsList.stream()
                        .filter(eventik -> eventik.state.equals("Germany")).toList());
            }if(czechiaCheckBox.isSelected()){
                client.eventsReservations.addAll(GameGUI.eventsList.stream()
                        .filter(eventik -> eventik.state.equals("Czechia")).toList());
            }if(slovakiaCheckBox.isSelected()){
                client.eventsReservations.addAll(GameGUI.eventsList.stream()
                        .filter(eventik -> eventik.state.equals("Slovakia")).toList());
            }if(polandCheckBox.isSelected()){
                client.eventsReservations.addAll(GameGUI.eventsList.stream()
                        .filter(eventik -> eventik.state.equals("Poland")).toList());
            }

            sinceDate.setVisible(false);
            untilDate.setVisible(false);
            portugalCheckBox.setVisible(false);
            italyCheckBox.setVisible(false);
            spainCheckBox.setVisible(false);
            czechiaCheckBox.setVisible(false);
            slovakiaCheckBox.setVisible(false);
            franceCheckBox.setVisible(false);
            germanyCheckBox.setVisible(false);
            polandCheckBox.setVisible(false);
            selectAll.setVisible(false);
            selectionLbl1.setVisible(false);
            selectionLbl2.setVisible(false);
            previousBtn1.setVisible(false);
            nextBtn2.setVisible(false);

            infoLbl.setVisible(true);
            infoLbl2.setVisible(true);
            infoLbl2.setText("Plan for your "+(until-since)+" day tour");
            logoutBtn.setVisible(true);

            logoutBtn.setOnAction(e -> {
                client.serialization(client);
                rectangle.setVisible(false);
                infoLbl.setVisible(false);
                infoLbl2.setVisible(false);
                logoutBtn.setVisible(false);

                startScreenLbl1.setVisible(true);
                startScreenLbl2.setVisible(true);
                startScreenLbl3.setVisible(true);
                btnToRegister.setVisible(true);
                btnToLogin.setVisible(true);

                clearAll();
            });

            String output = "";

            client.sorting();

            for(Event events : client.eventsReservations){
                output += events.info();
            }
            infoLbl.setText(output);

        }else{
            if(sinceDate.getValue() == null){
                sinceDate.getEditor().setStyle("-fx-background-color: rgba(200,25,25,0.5);");
                timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                    sinceDate.getEditor().setStyle("-fx-background-color: white;");
                }));
                timeline.play();
            }
            if(untilDate.getValue() == null){
                untilDate.getEditor().setStyle("-fx-background-color: rgba(200,25,25,0.5);");
                timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                    untilDate.getEditor().setStyle("-fx-background-color: white;");
                }));
                timeline.play();
            }
        }
    }

    public void clearAll() {
        registrationUserName.setText("");
        registrationPassword.setText("");
        userNameTxt.setText("");
        passwordTxt.setText("");

        selectAll.setSelected(false);
        portugalCheckBox.setSelected(false);
        italyCheckBox.setSelected(false);
        spainCheckBox.setSelected(false);
        czechiaCheckBox.setSelected(false);
        franceCheckBox.setSelected(false);
        slovakiaCheckBox.setSelected(false);
        germanyCheckBox.setSelected(false);
        polandCheckBox.setSelected(false);
        singerCheckBox.setSelected(false);
        artistCheckBox.setSelected(false);

        sinceDate.setValue(null);
        untilDate.setValue(null);
    }
}