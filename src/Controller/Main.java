package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils_Database.DBConnection;

import java.io.IOException;
import java.util.ResourceBundle;

/**
Application's starting point
 **/
public class Main extends Application {

    /**
     * Sets the primary stage used to display the application's GUI.
     * @param primaryStage This stage is used to display the different scenes of the application.
     * @throws IOException When specified fxml file can't be found.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        ResourceBundle myBundle = ResourceBundle.getBundle("Bundle/lang");
        Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
        primaryStage.setTitle(myBundle.getString("titlea"));
        primaryStage.setScene(new Scene(root, 1400, 475));
        primaryStage.show();
    }

    /**
     * Starts and ends the connection with the database.
     * @param args This is where arguments, if any, are passed.
     */
    public static void main(String[] args) {

        DBConnection.startConnection(); // do this once per program

        launch(args);

        DBConnection.closeConnection(); // When program window is closed
    }
}