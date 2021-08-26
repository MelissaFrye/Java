package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBUsers;
import Model.Appointments;
import Model.Contacts;
import Model.TypeEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Controller for the special reports menu.
 */
public class SpecialReportsController implements Initializable {
    /**
     * user makes a month choice.
     */
    public ComboBox<Month> monthCombo;
    /**
     * user makes a contact choice.
     */
    public ComboBox<Contacts> contactsCombo;
    /**
     * user makes an appointment type choice.
     */
    public ComboBox<TypeEnum> typesCombo;
    /**
     * displays appointment ID.
     */
    public TableColumn aIDCol;
    /**
     * displays appointment title.
     */
    public TableColumn aTitleCol;
    /**
     * displays appointment type.
     */
    public TableColumn aTypeCol;
    /**
     * displays appointment description.
     */
    public TableColumn aDescCol;
    /**
     * displays appointment date and start time.
     */
    public TableColumn aStartCol;
    /**
     * displays appointment date and end time.
     */
    public TableColumn aEndCol;
    /**
     * displays appointment customer name.
     */
    public TableColumn aCustomerNameCol;
    /**
     * displays appointment customer ID.
     */
    public TableColumn aNewCustIDCol;
    /**
     * displays appointment contact name.
     */
    public TableColumn aContactNameCol;
    /**
     * displays results of appointments by type report.
     */
    public Label typeResultLbl;
    /**
     * displays results of appointments by month report.
     */
    public Label monthResultLbl;
    /**
     * displays results of appointments by month and type report.
     */
    public Label aByMTLbl;
    /**
     * displays all appointments initially, then when a contact is selected all appointments for that contact are displayed.
     */
    public TableView<Appointments> allApptsTable;
    /**
     * displays monthly appointments by contact.
     */
    public TableView<Appointments> allApptsTableM;
    /**
     * displays appointment ID.
     */
    public TableColumn aIDColM;
    /**
     * displays appointment title.
     */
    public TableColumn aTitleColM;
    /**
     * displays appointment description.
     */
    public TableColumn aDescColM;
    /**
     * displays appointment type.
     */
    public TableColumn aTypeColM;
    /**
     * displays appointment date and start time.
     */
    public TableColumn aStartColM;
    /**
     * displays appointment date and end time.
     */
    public TableColumn aEndColM;
    /**
     * displays appointment customer name.
     */
    public TableColumn aCustomerNameColM;
    /**
     * displays appointment customer ID.
     */
    public TableColumn aNewCusIDColM;
    /**
     * displays appointment contact name.
     */
    public TableColumn aContactNameColM;
    /**
     * displays appointments within 7 days by contact.
     */
    public TableView<Appointments> allApptsTableW;
    /**
     * displays appointment ID.
     */
    public TableColumn aIDColW;
    /**
     * displays appointment title.
     */
    public TableColumn aTitleColW;
    /**
     * displays appointment description.
     */
    public TableColumn aDescColW;
    /**
     * displays appointment type.
     */
    public TableColumn aTypeColW;
    /**
     * displays appointment date and start time.
     */
    public TableColumn aStartColW;
    /**
     * displays appointment date and end time.
     */
    public TableColumn aEndColW;
    /**
     * displays appointment customer name.
     */
    public TableColumn aCustomerNameColW;
    /**
     * displays appointment customer ID.
     */
    public TableColumn aNewCustIDColW;
    /**
     * displays appointment contact name.
     */
    public TableColumn aContactNameColW;
    /**
     * displays appointments total by user.
     */
    public TableView totalByUserTbl;
    /**
     * displays user name.
     */
    public TableColumn totalUserCol;
    /**
     * displays an total for each user.
     */
    public TableColumn totalAppsCol;
    /**
     * list of months.
     */
    private ObservableList<Month> months = FXCollections.observableArrayList();
    /**
     * list of appointments by month.
     */
    private ObservableList<Appointments> apptsByMonth = FXCollections.observableArrayList();
    /**
     * list of appointments by week.
     */
    private ObservableList<Appointments> apptsByWeek = FXCollections.observableArrayList();
    /**
     * list of appointments by userID.
     */
    private ObservableList apptsByUser = FXCollections.observableArrayList();

    /**
     * Initializes appointments by contact table, total appointments by user table, and combo boxes.
     * @param url Instance of the URL class; uniform resource locator.
     * @param resourceBundle to contain resources, if used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //columns and table setting for "report of my choice" table shows number of appointments by user
        totalUserCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        totalAppsCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        totalByUserTbl.setItems(apptsByUser);
        apptsByUser.addAll(DBUsers.getUserStats());

        //columns and set list for the allApptsTable
        aContactNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        aIDCol.setCellValueFactory(new PropertyValueFactory<>("a_id"));
        aTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        aTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        aDescCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        aStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        aEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        aCustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        aNewCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        allApptsTable.setItems(DBAppointments.getAppointmentsTable());

        //columns and set list for the ByMonthApptsTable
        allApptsTableM.setItems(apptsByMonth);

        aContactNameColM.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        aIDColM.setCellValueFactory(new PropertyValueFactory<>("a_id"));
        aTitleColM.setCellValueFactory(new PropertyValueFactory<>("title"));
        aDescColM.setCellValueFactory(new PropertyValueFactory<>("desc"));
        aTypeColM.setCellValueFactory(new PropertyValueFactory<>("type"));
        aStartColM.setCellValueFactory(new PropertyValueFactory<>("start"));
        aEndColM.setCellValueFactory(new PropertyValueFactory<>("end"));
        aCustomerNameColM.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        aNewCusIDColM.setCellValueFactory(new PropertyValueFactory<>("customerID"));


        //columns and set list for the ByWeekApptsTable
        allApptsTableW.setItems(apptsByWeek);

        aContactNameColW.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        aIDColW.setCellValueFactory(new PropertyValueFactory<>("a_id"));
        aTitleColW.setCellValueFactory(new PropertyValueFactory<>("title"));
        aDescColW.setCellValueFactory(new PropertyValueFactory<>("desc"));
        aTypeColW.setCellValueFactory(new PropertyValueFactory<>("type"));
        aStartColW.setCellValueFactory(new PropertyValueFactory<>("start"));
        aEndColW.setCellValueFactory(new PropertyValueFactory<>("end"));
        aCustomerNameColW.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        aNewCustIDColW.setCellValueFactory(new PropertyValueFactory<>("customerID"));



        //getting and setting all months for the combo box
        Collections.addAll(months, Month.values());
        monthCombo.setItems(months);

        //initializing contacts list
        contactsCombo.setItems(DBContacts.getAllContacts());

        // initializing types list
        typesCombo.setItems(TypeEnum.getAllTypes());

    }

    /**
     * Lambda used here to filter the allAppointments list by contactID, then displays the list in the table.
     * @param actionEvent selecting a contact from the combo box.
     */
    public void onContactsCombo(ActionEvent actionEvent) {
        try {
            // lambda here to filter allAppointments list by contact ID
            int selectedC = contactsCombo.getValue().getId();
            ObservableList<Appointments> allAppts = DBAppointments.getAppointmentsTable();

            ObservableList<Appointments> filteredAppts = allAppts.filtered(a ->{
                if(a.getContactID() == selectedC)
                    return true;
                return false;
            });

            allApptsTable.setItems(filteredAppts);

            LocalDateTime weekFromNow = LocalDateTime.now().plusDays(7);
            apptsByWeek.clear();
            for (Appointments aw : filteredAppts) {
                if (aw.getStart().toLocalDateTime().isBefore(weekFromNow)) {

                    apptsByWeek.add(aw);
                }
            }

            apptsByMonth.clear();
            for (Appointments am : filteredAppts) {
                if (am.getStart().toLocalDateTime().getMonth().equals(LocalDateTime.now().getMonth())) {
                    apptsByMonth.add(am);
                }
            }

            allApptsTableM.setItems(apptsByMonth);

        } catch (NullPointerException e) {
            LoginController.emptyFieldSubmitted();
        }

    }

    /**
     * selection of appointment type sets result into label.
     * @param actionEvent selection from combo box.
     */
    public void onTypesCombo(ActionEvent actionEvent) {
        try {
            String selectedT = typesCombo.getValue().toString();

            String selRes = String.valueOf(DBAppointments.numberOfApptsByType(selectedT));
            typeResultLbl.setText("Total of " + selRes + " " + selectedT + " appointments scheduled.");

        } catch (NullPointerException e) {
            LoginController.nothingSelected();
        }
    }

    /**
     *  selection of month from combo box sets label with results.
     * @param actionEvent selection from combo box.
     */
    public void onMonthsCombo(ActionEvent actionEvent) {
        try {
            Month selectedM = monthCombo.getValue();
            int apptsBySelM = 0;

            for (Appointments a : DBAppointments.getAppointmentsTable()) {
                if (a.getStart().toLocalDateTime().getMonth() == selectedM) {
                    apptsBySelM++;
                }
            }
            monthResultLbl.setText("Total of " + apptsBySelM + " scheduled appointments in " + selectedM);
        } catch (NullPointerException e) {
            LoginController.nothingSelected();
        }
    }

    /**
     * Result of appointments by selected month and type are displayed in a label.
     * @param actionEvent button click initiates action.
     */
    public void onAByMonthType(ActionEvent actionEvent) {
        try {
            Month selM = monthCombo.getValue();
            String selT = typesCombo.getValue().toString();
            int apptsBySelM = 0;

            if (selM == null || selT == null){
                LoginController.nothingSelected();
                return;
            }
            for (Appointments a : DBAppointments.getAppointmentsTable()) {
                if (a.getType().equals(selT)) {

                    if (a.getStart().toLocalDateTime().getMonth() == selM) {
                        apptsBySelM++;
                    }
                }
            }
            aByMTLbl.setText("There are " + apptsBySelM + " " + selT + " appointments scheduled in " + selM + ".");

        } catch (NullPointerException e) {
            LoginController.nothingSelected();
        }
    }

    /**
     * When this tab is selected, month of each appointment will be compared to the user's month and resulting list displayed.
     * @param event clicking on the tab initiated the action.
     */
    public void onThisMonthTab(Event event) {
        try {
            apptsByMonth.clear();
            int selectedCM = contactsCombo.getValue().getId();
            for (Appointments am : DBAppointments.getApptsByContactID(selectedCM)) {
                if (am.getStart().toLocalDateTime().getMonth().equals(LocalDateTime.now().getMonth())) {
                    apptsByMonth.add(am);
                }
            }

            allApptsTableM.setItems(apptsByMonth);
        } catch (NullPointerException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection Made");
            alert.setHeaderText(null);
            alert.setContentText("*Be sure to select a Contact*");

            alert.showAndWait();

        }
    }

    /**
     * When this tab is selected, each appointment will be checked to see if it is within the next 7 days.
     * @param event clicking on the tab initiated the action.
     */
    public void onThisWeekTab(Event event) {
        try {
            int selectedCW = contactsCombo.getValue().getId();
        weeklyContact(selectedCW);

        } catch (NullPointerException e) {
            contactsCombo.setPromptText("Must Choose A Contact Here");
        }
    }

    /**
     * List of appointments by selected contact ID is checked for those within 7 days.
     * @param selConID ID of the selected contact.
     */
    public  void weeklyContact(int selConID) {
        try {
            apptsByWeek.clear();
            for (Appointments aw : DBAppointments.getApptsByContactID(selConID))
                if (aw.getStart().toLocalDateTime().isBefore(LocalDateTime.now().plusDays(7))) {

                    apptsByWeek.add(aw);
                }

            allApptsTableW.setItems(apptsByWeek);

        } catch (NullPointerException e) {
            contactsCombo.setPromptText("Must Choose A Contact Here");
        }
    }

    /**
     * Main menu scene is loaded when button is clicked.
     * @param actionEvent button click initiates action.
     * @throws IOException in case the fxml file cannot be found.
     */
    public void menu_scene(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/menu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1400, 475);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

}

