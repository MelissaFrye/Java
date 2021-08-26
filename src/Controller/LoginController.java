package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/**
 * Controller for Login Form.
 */
public class LoginController implements Initializable {

    /**
     * user types username here.
     */
    public TextField userIDTxt;

    /**
     * user types password here.
     */
    public TextField passwordTxt;

    /**
     * contains English and French translations of labels and button.
     */
    public ResourceBundle myBundle = ResourceBundle.getBundle("Bundle/lang");

    /**
     *
     * displays ZoneID based on he user's system default settings
     */
    public Label zoneIDLbl;
    public Label titleLbl;
    public Label usernameLbl;
    public Label passwordLbl;

    /**
     * user clicks this button to submit credentials
     */
    public Button logInEnterLbl;

    /**
     * Initializes the labels and button to display in English or French based on user settings.
     * @param url Instance of the URL class; uniform resource locator.
     * @param resourceBundle Language translation files.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        titleLbl.setText(myBundle.getString("title"));
        usernameLbl.setText(myBundle.getString("username"));
        passwordLbl.setText(myBundle.getString("password"));
        logInEnterLbl.setText(myBundle.getString("enter"));
        zoneIDLbl.setText(String.valueOf(ZoneId.systemDefault()));
    }

    /**
     * Button click validates credentials, writes to login_activity.txt, then loads menu scene.
     * @param actionEvent Click on "Enter" button initiates the action
     */
    public void menu_Scene(ActionEvent actionEvent) {
        try {
            // Filename and variables
            String filename = "login_activity.txt", itemA;

            // Create FileWriter object
            FileWriter fwriter = new FileWriter(filename, true);

            // create and open file
            PrintWriter outputFile = new PrintWriter(fwriter);

            //login validated
            int userID = DBUsers.validateUser(userIDTxt.getText(), passwordTxt.getText());

            // set variable to indicate if user has appts within i5 mins to determine displayed message
            boolean hasUpcoming = false;

            if (userID > 0) {
                // successful login, eventually load Main Menu
                // first, check for appointments using userID, then check +/- 15 mins
                for (Appointments b : DBAppointments.apptsByUserID(userID)) {
                    //is .now() <= 15 minutes before Appointment start?
                    ZonedDateTime nowzdt = ZonedDateTime.now();
                    ZoneId thisZID = ZoneId.systemDefault();

                    ZonedDateTime startzdt = b.getStart().toLocalDateTime().atZone(thisZID);
                    if (startzdt.isBefore(nowzdt.plusMinutes(15)) && (startzdt.isAfter(nowzdt) || startzdt.equals(nowzdt))) {
                        //then this user has an appointment soon
                        hasUpcoming = true;

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(myBundle.getString("welcome") + " " + userID);
                        alert.setHeaderText(null);
                        alert.setContentText(myBundle.getString("withID") + b.getA_id() + myBundle.getString("scheduled_for") + " " + b.getStart() + " " + myBundle.getString("begins"));
                        alert.show();
                        break;
                    }

                }
                if (!hasUpcoming) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(myBundle.getString("welcome") + " " + userID);
                    alert.setHeaderText(null);
                    alert.setContentText(myBundle.getString("no_upcoming"));
                    alert.show();
                }

                // write to file all user log-in attempts, dates, and time stamps, and whether each attempt was successful
                itemA =  "User " + userIDTxt.getText() + " successfully logged in at " + Timestamp.valueOf(LocalDateTime.now()).toString();
                outputFile.println(itemA);
                // close file
                outputFile.close();

                // load menu scene
                Parent root = FXMLLoader.load(getClass().getResource("/View/menu.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1400, 475);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();

            } else {
                // alert to invalid credentials
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(myBundle.getString("error"));
                alert.setHeaderText(myBundle.getString("incorrect"));
                alert.setContentText(myBundle.getString("try_again"));
                alert.showAndWait();

                // write to file all user log-in attempts, dates, and time stamps, and whether each attempt was successful
                itemA =  "User " + userIDTxt.getText() + " submitted invalid login at " + Timestamp.valueOf(LocalDateTime.now()).toString();
                outputFile.println(itemA);
                // close file
                outputFile.close();

            }
        } catch (IOException e) {
            LoginController.emptyFieldSubmitted();
        }
    }
    /**
     * Displays a pop-up dialog when no item is selected to modify or delete.
     */
    public static void nothingSelected() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Selection Made");
        alert.setHeaderText(null);
        alert.setContentText("Select an item");
        alert.showAndWait();
    }

    /**
     * Displays a pop-up dialog when an invalid entry is made.
     */
    public static void emptyFieldSubmitted() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText("Empty Fields Not Permitted");
        alert.setContentText("Please check for errors and try again");
        alert.showAndWait();
    }

}
