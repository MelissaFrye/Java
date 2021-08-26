package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * controller for the customers menu.
 */
public class CustomersController implements Initializable {

    /**
     * user makes one Country choice from the combo box.
     */
    public ComboBox<Countries> countryCombo;

    /**
     * user makes one Division choice from the combo box.
     */
    public ComboBox<Divisions> divisionCombo;
    public TableColumn cAddIDCol;
    public TableColumn cAddNameCol;
    public TableColumn cAddPostalCol;
    public TableColumn cAddCountryCol;
    public TableColumn cAddDivisionCol;
    public TableColumn cAddressCol;
    public TableColumn cAddPhoneCol;

    /**
     * Displays Customers data.
     */
    public TableView cAddModTable;

    /**
     * Customer ID is disabled and is set by the database.
     */
    public TextField cIDTxt;
    public TextField cNameTxt;
    public TextField cAddressTxt;
    public TextField cPostalTxt;
    public TextField cPhoneTxt;

    /**
     * Initializes Customers TableView and Country ComboBox.
     * @param url Instance of the URL class; uniform resource locator.
     * @param resourceBundle to contain resources, if used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // sets items from observable list of customers to table
        cAddModTable.setItems(DBCustomers.getCustomersTable());

        //set columns
        cAddIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        cAddNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        cAddPostalCol.setCellValueFactory(new PropertyValueFactory<>("postal"));
        cAddPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cAddCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        cAddDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));

        //sets items from observable list of countries to countryCombo
        countryCombo.setItems(DBCountries.getAllCountries());
    }

    /**
     * Loads the application main menu scene.
     * @param actionEvent button click initiates action.
     * @throws IOException in case fxml file cannot be found.
     */
    public void menu_Scene(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/menu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1400, 475);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * choosing a Country from the box filters and sets the Divisions combo.
     * @param actionEvent clicking combo box initiates action.
     */
    public void onCountryCombo(ActionEvent actionEvent) {
        try {
            int selectedC = countryCombo.getValue().getId();
            divisionCombo.setItems(DBDivisions.getDivisionsByCountryID(selectedC));
        } catch (NullPointerException e) {
            //ignore
        }
    }

    /**
     * 'Modify Selected Customer' button click saves modification to an existing Customer.
     * @param actionEvent button click initiates action.
     */
    public void onCSave(ActionEvent actionEvent)  {
        try {
            if ((cIDTxt.getLength() == 0)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR - TRY AGAIN ");
                alert.setHeaderText("Create New Customer");
                alert.setContentText("Or Select From Table");

                alert.showAndWait();
                return;
            }

            //getting the text field entries
            String name = cNameTxt.getText();
            String address = cAddressTxt.getText();
            String postal = cPostalTxt.getText();
            String phone = cPhoneTxt.getText();
            Divisions division = divisionCombo.getValue();

            //check for existing ID to determine if a new Customer is to be created
            for (Customers c : DBCustomers.getCustomersTable()) {

                    if (Integer.parseInt(cIDTxt.getText()) == c.getId()) {
                        //if id matches any existing records, update the existing record
                        DBCustomers.modifyCustomer(Integer.parseInt(cIDTxt.getText()), name, address, postal, phone, division.getId());
                    }
            }
            //reload table
            cAddModTable.setItems(DBCustomers.getCustomersTable());

        } catch (Exception e) {
            LoginController.emptyFieldSubmitted();
        }
    }

    /**
     * gets selected customer from the table and displays the data in text fields.
     * @param actionEvent button click initiates action.
     */
        public void onCModify(ActionEvent actionEvent) {
        try{
        //get selection from table to modify
        Customers cToMod = (Customers) cAddModTable.getSelectionModel().getSelectedItem();

        // in case nothing is selected, pop up box tell user to select item
        if (cToMod == null)
            LoginController.nothingSelected();

        //display selected in text fields
        else {

            int cID = cToMod.getCountry_id();
            int dID = cToMod.getFirstLvl();
            cIDTxt.setText(String.valueOf(cToMod.getId()));
            cNameTxt.setText(cToMod.getName());
            cAddressTxt.setText(cToMod.getAddress());
            cPostalTxt.setText(cToMod.getPostal());
            cPhoneTxt.setText(cToMod.getPhone());
            countryCombo.setItems(DBCountries.getAllCountries());

            for (Countries c : countryCombo.getItems()) {
                if (cID == c.getId()) {

                    countryCombo.setValue(c);
                    divisionCombo.setItems(DBDivisions.getDivisionsByCountryID(cID));
                    break;
                }
            }
            for (Divisions d : divisionCombo.getItems()) {
                if (dID == d.getId()) {
                    divisionCombo.setValue(d);
                    break;
                }
            }
        }
    } catch (NullPointerException e) {
            //LoginController.nothingSelected();
        }
        }

    /**
     * 'Delete Selected Customer' button click deletes a customer and all of their appointments from the database.
     * @param actionEvent button click initiates action.
     */
            public void onCDelete(ActionEvent actionEvent) {
        try {
            //get selection from table to delete
            Customers cToDelete = (Customers) cAddModTable.getSelectionModel().getSelectedItem();

            // in case nothing is selected
            Alert alert;
            if (cToDelete == null) {
                LoginController.nothingSelected();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Delete");
                alert.setHeaderText("This Customer And Any Associated Appointments Will Be Deleted");
                alert.setContentText("Are you ok with this?");

                int cID = cToDelete.getId();

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    //check for existing appts and delete
                    if (!DBCustomers.hasAppts(cID)) {

                        DBCustomers.deleteCustomer(cID);

                    } else {

                        //delete all appts associated with a custID
                        DBAppointments.deleteAllApptsByCustID(cID);
                        DBCustomers.deleteCustomer(cID);
                    }
                    //reload table
                    cAddModTable.setItems(DBCustomers.getCustomersTable());

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Customer Deleted");
                    alert.setHeaderText(null);
                    alert.setContentText("Customer " + cToDelete.getName() + " \n ID " + cID +  " \n and any associated appointments \n have been deleted.");

                    alert.showAndWait();

                }}
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

    /**
     * 'Create New Customer' button click saves a new customer to the database.
     * @param actionEvent button click initiates action.
     */
    public void onCCreateNew(ActionEvent actionEvent) {
        try {
        if ((divisionCombo.getValue() == null) || (cNameTxt.getText() == null) || (cAddressTxt.getText() == null) || (cPostalTxt.getText() == null) || cPhoneTxt.getText() == null) {
            LoginController.emptyFieldSubmitted();
        }
        //getting the text field entries
        String name = cNameTxt.getText();
        String address = cAddressTxt.getText();
        String postal = cPostalTxt.getText();
        String phone = cPhoneTxt.getText();
        Divisions division = divisionCombo.getValue();

            DBCustomers.createCustomer(name, address, postal, phone, division.getId());
        } catch (NullPointerException e) {
            // ignore
        }
        cAddModTable.setItems(DBCustomers.getCustomersTable());
    }

    /**
     * 'Clear Form' button click clears all text fields amd combo boxes.
     * @param actionEvent button click initiates action.
     */
    public void onClearFields(ActionEvent actionEvent) {
        try{
            countryCombo.getSelectionModel().clearSelection();
            divisionCombo.getSelectionModel().clearSelection();
            cIDTxt.clear();
            cNameTxt.clear();
            cAddressTxt.clear();
            cPostalTxt.clear();
            cPhoneTxt.clear();
        } catch (Exception e) {
            // ignore
        }
    }
}

