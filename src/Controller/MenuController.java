package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * controller for the application main menu.
 */
public class MenuController implements Initializable {

    /**
     * Nothing is initialized in the Main Menu.
     * @param url Instance of the URL class; uniform resource locator.
     * @param resourceBundle to contain resources, if used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Loads the Customer Menu scene.
     * @param actionEvent button click initiates action.
     * @throws IOException if  fxml file is not found.
     */
    public void customer_Scene(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/View/customers.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1400, 475);
            stage.setTitle("Customer Records");
            stage.setScene(scene);
            stage.show();
    }

    /**
     * Loads the Appointment Menu scene.
     * @param actionEvent button click initiates action.
     * @throws IOException if  fxml file is not found.
     */
    public void appointment_scene(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/appointments.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,1400, 475);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads the Special Reports Menu scene.
     * @param actionEvent button click initiates action.
     * @throws IOException if  fxml file is not found.
     */
    public void specialReports_scene(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/specialReports.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1400, 475);
        stage.setTitle("Special Reports");
        stage.setScene(scene);
        stage.show();
    }

}
