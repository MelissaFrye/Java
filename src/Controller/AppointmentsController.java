package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Model.*;
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
import java.sql.Timestamp;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for the appointments menu.
 */
public class AppointmentsController implements Initializable {
    /**
     * user makes a start time choice.
     */
    public ComboBox<LocalTime>startTimeCombo;
    /**
     * user makes an end time choice.
     */
    public ComboBox<LocalTime> endTimeCombo;
    /**
     * user makes a contacts choice.
     */
    public ComboBox<Contacts> contactsCombo;
    /**
     * user selects a date from the calendar.
     */
    public DatePicker dateBox;
    /**
     * user makes a customer choice.
     */
    public ComboBox<Customers> customersCombo;
    /**
     * user makes a users choice.
     */
    public ComboBox<Users> usersCombo;
    /**
     * displays appointment ID.
     */
    public TableColumn aIDCol;
    /**
     * displays appointment type.
     */
    public TableColumn aTypeCol;
    /**
     * displays appointment start date and time.
     */
    public TableColumn aStartCol;
    /**
     * displays appointment end date and time.
     */
    public TableColumn aEndCol;
    /**
     * displays appointment contact ID.
     */
    public TableColumn aContactIDCol;
    /**
     * displays appointment user ID.
     */
    public TableColumn aUserIDCol;
    /**
     * displays appointment customer name.
     */
    public TableColumn aCustomerNameCol;
    /**
     * displays appointment customer ID.
     */
    public TableColumn aCustomerIDCol;
    /**
     * displays appointment title.
     */
    public TableColumn aTitleCol;
    /**
     * displays appointment description.
     */
    public TableColumn aDescCol;
    /**
     * displays appointment location.
     */
    public TableColumn aLocCol;
    /**
     * displays all appointment data.
     */
    public TableView<Appointments> allApptsTable;
    /**
     * displays appointment data for the current month.
     */
    public TableView<Appointments> allApptsTableM;
    /**
     * (month) displays appointment type.
     */
    public TableColumn aIDColM;
    /**
     * (month) displays appointment title.
     */
    public TableColumn aTitleColM;
    /**
     * (month) displays appointment description.
     */
    public TableColumn aDescColM;
    /**
     * (month) displays appointment location.
     */
    public TableColumn aLocColM;
    /**
     * (month) displays appointment type.
     */
    public TableColumn aTypeColM;
    /**
     * (month) displays appointment start date and time.
     */
    public TableColumn aStartColM;
    /**
     * (month) displays appointment end date and time.
     */
    public TableColumn aEndColM;
    /**
     * (month) displays appointment customer ID.
     */
    public TableColumn aCustomerIDColM;
    /**
     * (month) displays appointment contact ID.
     */
    public TableColumn aContactIDColM;
    /**
     * (month) displays appointment user ID.
     */
    public TableColumn aUserIDColM;
    /**
     * (month) displays appointment customer name.
     */
    public TableColumn aCustomerNameColM;
    /**
     * displays appointment data for the current week.
     */
    public TableView<Appointments> allApptsTableW;
    public TableColumn aIDColW;
    public TableColumn aTitleColW;
    public TableColumn aDescColW;
    public TableColumn aLocColW;
    public TableColumn aTypeColW;
    public TableColumn aStartColW;
    public TableColumn aEndColW;
    public TableColumn aCustomerIDColW;
    public TableColumn aContactIDColW;
    public TableColumn aUserIDColW;
    public TableColumn aCustomerNameColW;
    /**
     * appointment ID is set by the database and is disabled throughout GUI.
     */
    public TextField aIDText;
    /**
     * appointment title text field.
     */
    public TextField aTitleTxt;
    /**
     * appointment description text field.
     */
    public TextField aDescTxt;
    /**
     * appointment location text field.
     */
    public TextField aLocTxt;
    /**
     * user makes an appointment type choice from the combo box.
     */
    public ComboBox<TypeEnum> typeCombo;
    /**
     * list to contain all appointments.
     */
    private ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
    /**
     * list to contain this month's appointments.
     */
    private ObservableList<Appointments> apptsByMonth = FXCollections.observableArrayList();
    /**
     * list to contain this week's appointments.
     */
    private ObservableList<Appointments> apptsByWeek = FXCollections.observableArrayList();

    /**
     * Initializes appointments table and combo boxes.
     * @param url Instance of the URL class; uniform resource locator.
     * @param resourceBundle to contain resources, if used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allApptsTable.setItems(allAppointments);

        aIDCol.setCellValueFactory(new PropertyValueFactory<>("a_id"));
        aTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        aDescCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        aLocCol.setCellValueFactory(new PropertyValueFactory<>("loc"));
        aTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        aStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        aEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        aCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        aUserIDCol.setCellValueFactory(new PropertyValueFactory<>("u_id"));
        aContactIDCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        aCustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        allAppointments.addAll(DBAppointments.getAppointmentsTable());

        allApptsTableM.setItems(apptsByMonth);

        aIDColM.setCellValueFactory(new PropertyValueFactory<>("a_id"));
        aTitleColM.setCellValueFactory(new PropertyValueFactory<>("title"));
        aDescColM.setCellValueFactory(new PropertyValueFactory<>("desc"));
        aLocColM.setCellValueFactory(new PropertyValueFactory<>("loc"));
        aTypeColM.setCellValueFactory(new PropertyValueFactory<>("type"));
        aStartColM.setCellValueFactory(new PropertyValueFactory<>("start"));
        aEndColM.setCellValueFactory(new PropertyValueFactory<>("end"));
        aCustomerIDColM.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        aUserIDColM.setCellValueFactory(new PropertyValueFactory<>("u_id"));
        aContactIDColM.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        aCustomerNameColM.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        allApptsTableW.setItems(apptsByWeek);

        aIDColW.setCellValueFactory(new PropertyValueFactory<>("a_id"));
        aTitleColW.setCellValueFactory(new PropertyValueFactory<>("title"));
        aDescColW.setCellValueFactory(new PropertyValueFactory<>("desc"));
        aLocColW.setCellValueFactory(new PropertyValueFactory<>("loc"));
        aTypeColW.setCellValueFactory(new PropertyValueFactory<>("type"));
        aStartColW.setCellValueFactory(new PropertyValueFactory<>("start"));
        aEndColW.setCellValueFactory(new PropertyValueFactory<>("end"));
        aCustomerIDColW.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        aUserIDColW.setCellValueFactory(new PropertyValueFactory<>("u_id"));
        aContactIDColW.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        aCustomerNameColW.setCellValueFactory(new PropertyValueFactory<>("customerName"));


        contactsCombo.getItems().addAll(DBContacts.getAllContacts());
        usersCombo.getItems().addAll(DBUsers.getAllUsers());
        customersCombo.getItems().addAll(DBCustomers.getCustomersTable());
        typeCombo.setItems(TypeEnum.getAllTypes());

        LocalTime startBeg = LocalTime.of(8, 0);
        LocalTime endBeg =  LocalTime.of(22, 0);

        while(startBeg.isBefore(endBeg.plusSeconds(1))){
            startTimeCombo.getItems().add(startBeg);
            startBeg = startBeg.plusMinutes(15);
        }
        LocalTime startEnd = LocalTime.of(8, 0);
        LocalTime endEnd =  LocalTime.of(22, 0);

        while(startEnd.isBefore(endEnd.plusSeconds(1))){
            endTimeCombo.getItems().add(startEnd);
            startEnd = startEnd.plusMinutes(15);
        }

    }

    /**
     * Loads the application main menu scene.
     * @param actionEvent button click initiates action.
     * @throws IOException in case fxml file cannot be found.
     */
    public void menu_Scene(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/menu.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1400, 475);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 'Clear Form' button click clears all text fields amd combo boxes.
     * @param actionEvent button click initiates action.
     */
    public void onClear(ActionEvent actionEvent) {

        // clear combos and text fields
        aIDText.clear();
        aTitleTxt.clear();
        aDescTxt.clear();
        aLocTxt.clear();
        typeCombo.getSelectionModel().clearSelection();
        dateBox.setValue(null);
        startTimeCombo.getSelectionModel().clearSelection();
        endTimeCombo.getSelectionModel().clearSelection();
        customersCombo.getSelectionModel().clearSelection();
        usersCombo.getSelectionModel().clearSelection();
        contactsCombo.getSelectionModel().clearSelection();
        // reset table
        allApptsTable.setItems(allAppointments);
    }

    /**
     * Lambda used here to filter the allAppointments list by customer ID in order to check for appointment overlap,
     * 'Save As New Appointment' button click saves a new appointment to the database. Business hours are checked against the proposed time.
     * @param actionEvent button click initiates action.
     */
    public void onCreateApp(ActionEvent actionEvent) {
        try {
            // getting the text field and combo entries
            String title = aTitleTxt.getText();
            String desc = aDescTxt.getText();
            String loc = aLocTxt.getText();
            LocalDate aLocalDate = dateBox.getValue();
            int custID = customersCombo.getValue().getId();
            LocalTime startLocalTime = startTimeCombo.getValue();
            LocalDateTime start = LocalDateTime.of(aLocalDate, startLocalTime);
            Timestamp aStartTimeDB = Timestamp.valueOf(start);
            LocalTime endLocalTime = endTimeCombo.getValue();
            LocalDateTime end = LocalDateTime.of(aLocalDate, endLocalTime);
            Timestamp aEndTimeDB = Timestamp.valueOf(end);

            int userID = usersCombo.getValue().getId();
            int contactID = contactsCombo.getValue().getId();
            String type = typeCombo.getValue().toString();

            //is the time of the proposed appointment within business hours 8am to 10pm EST?
            LocalTime estBegin = LocalTime.of(8,0);
            ZoneId estZoneID = ZoneId.of("America/New_York");
            ZonedDateTime bhoursBegin = ZonedDateTime.of(aLocalDate, estBegin, estZoneID);

            LocalTime estEnd = LocalTime.of(22, 0);
            ZonedDateTime bhoursEnd = ZonedDateTime.of(aLocalDate, estEnd, estZoneID);

            // gotta get the LDT of proposed start, then to origin zoneID, then to target zoneID
            ZonedDateTime newS = ZonedDateTime.of(aLocalDate,startLocalTime, ZoneId.systemDefault());
            ZonedDateTime newE = ZonedDateTime.of(aLocalDate,endLocalTime, ZoneId.systemDefault());

            ZonedDateTime newestS = newS.withZoneSameInstant(ZoneId.of("America/New_York"));
            ZonedDateTime newestE = newE.withZoneSameInstant(ZoneId.of("America/New_York"));

            // now see if the proposed start time is within BH:   bhoursBegin < apptESTStart < bhoursEnd
            if(newestS.toLocalTime().isBefore(bhoursBegin.toLocalTime()) ||
                    newestE.toLocalTime().isAfter(bhoursEnd.toLocalTime()) ||
                    newestE.toLocalTime().isBefore(bhoursBegin.toLocalTime())){

                // then start/end time is NOT within BH
                Alert alert;
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Time Outside Business Hours");
                alert.setHeaderText("Try Again");
                alert.setContentText("Business Hours 8am to 10pm EST");

                alert.showAndWait();
            } else {

                //lambda to filter allAppointments list by customer ID
                ObservableList<Appointments> allAppts = DBAppointments.getAppointmentsTable();

                ObservableList<Appointments> filteredApptsByCust = allAppts.filtered(a ->{
                    if(a.getCustomerID() == custID)
                        return true;
                    return false;
                });

                allApptsTable.setItems(filteredApptsByCust);

            for (Appointments ap : filteredApptsByCust) {
                Timestamp apts = ap.getStart();
                LocalDateTime startTimeX = apts.toLocalDateTime();
                LocalDateTime endTimeY = ap.getEnd().toLocalDateTime();

                if (start.isAfter(startTimeX) && start.isBefore(endTimeY)) {
                    // message indicates overlap
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Try Again");
                    alert.setHeaderText("Conflicting Appointment Scheduled");
                    alert.setContentText("Choose A New Date or Time and Try Again");

                    allApptsTable.setItems(filteredApptsByCust);

                    alert.showAndWait();
                    return;
                } else if (start.isEqual(startTimeX)) {
                    // message indicates overlap
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Try Again");
                    alert.setHeaderText("Conflicting Appointment Scheduled");
                    alert.setContentText("Choose A New Date or Time and Try Again");

                    allApptsTable.setItems(filteredApptsByCust);

                    alert.showAndWait();
                    return;

                } else if (start.isEqual(endTimeY)){

                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Try Again");
                    alert.setHeaderText("Time must be allowed between Appointments");
                    alert.setContentText("Choose A New Time and Try Again");

                    allApptsTable.setItems(filteredApptsByCust);

                    alert.showAndWait();
                    return;
                }

                else if (end.isAfter(startTimeX) && end.isBefore(endTimeY)) {
                    // message indicates overlap
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Try Again");
                    alert.setHeaderText("Conflicting Appointment Scheduled");
                    alert.setContentText("Choose A New Date or Time and Try Again");

                    allApptsTable.setItems(filteredApptsByCust);

                    alert.showAndWait();
                    return;
                } else if (end.isEqual(endTimeY)) {
                    // message indicates overlap
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Try Again");
                    alert.setHeaderText("Conflicting Appointment Scheduled");
                    alert.setContentText("Choose A New Date or Time and Try Again");

                    allApptsTable.setItems(filteredApptsByCust);

                    alert.showAndWait();
                    return;
                }else if (end.isEqual(startTimeX)){
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Try Again");
                    alert.setHeaderText("Time must be allowed between Appointments");
                    alert.setContentText("Choose A New Time and Try Again");

                    allApptsTable.setItems(DBAppointments.apptsByCusID(custID));

                    alert.showAndWait();
                    return;

                }
                else if (start.isBefore(startTimeX) && end.isAfter(startTimeX)) {
                    // message indicates overlap
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Try Again");
                    alert.setHeaderText("Conflicting Appointment Scheduled");
                    alert.setContentText("Choose A New Date or Time and Try Again");

                    allApptsTable.setItems(filteredApptsByCust);

                    alert.showAndWait();
                    return;
                } else if (end.isAfter(endTimeY) && start.isBefore(endTimeY)) {
                    // message indicates overlap
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Try Again");
                    alert.setHeaderText("Conflicting Appointment Scheduled");
                    alert.setContentText("Choose A New Date or Time and Try Again");

                    allApptsTable.setItems(filteredApptsByCust);

                    alert.showAndWait();
                    return;
                }
            }
            //create a new appointment record in DB
            DBAppointments.createAppointment(title, desc, loc, type, aStartTimeDB, aEndTimeDB, custID, userID, contactID);

            aIDText.clear();

            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New Appointment");
            alert.setHeaderText(null);
            alert.setContentText("Appointment Created");
            alert.showAndWait();

        } } catch (NullPointerException e) {
            LoginController.emptyFieldSubmitted();}

            //reload table
            allApptsTable.setItems(DBAppointments.getAppointmentsTable());
        }

    /**
     * Deletes an appointment from the database, displays a message confirming ID and type of appointment deleted.
     * @param actionEvent button click initiates action.
     */
    public void onDeleteApp(ActionEvent actionEvent) {

        //delete an appointment from the DB, get selection from table to delete
        Appointments aToDelete1 = allApptsTable.getSelectionModel().getSelectedItem();
        Appointments aToDelete2 = allApptsTableM.getSelectionModel().getSelectedItem();
        Appointments aToDelete3 = allApptsTableW.getSelectionModel().getSelectedItem();
        Appointments aToDelete = null;

        if (!(aToDelete1 == null)){
            aToDelete = aToDelete1;

        } else if (!(aToDelete2 == null)) {
            aToDelete = aToDelete2;
            System.out.println("month");

        } else if (!(aToDelete3 == null)) {
            aToDelete = aToDelete3;
            System.out.println("week");
        }

        // in case nothing is selected
        Alert alert;
        if (aToDelete == null) {
            LoginController.nothingSelected();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Cancellation");
            alert.setHeaderText("Appointment Will Be Canceled");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                //delete appointment after user clicks OK
                DBAppointments.deleteAppointment(aToDelete.getA_id());
                //reload table
                allApptsTable.setItems(DBAppointments.getAppointmentsTable());

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cancellation Successful");
                    alert.setHeaderText(null);
                    alert.setContentText( "Appointment of Type " + aToDelete.getType() + ", ID <" + aToDelete.getA_id() + "> Was Canceled.");

                alert.showAndWait();

            }
        }
    }

    /**
     * 'Save Changes To Selected Appointment' button click checks for appointment overlap and if within business.
     * @param actionEvent button click initiates action.
     */
    public void OnSaveModifyApp(ActionEvent actionEvent) {

        //get data from text boxes and combos
        try {
            // getting the text field and combo entries
            String aID = aIDText.getText();
            String title = aTitleTxt.getText();
            String desc = aDescTxt.getText();
            String loc = aLocTxt.getText();

            LocalDate aLocalDate = dateBox.getValue();

            LocalTime startLocalTime = startTimeCombo.getValue();
            LocalDateTime start = LocalDateTime.of(aLocalDate, startLocalTime);
            Timestamp aStartTimeDB = Timestamp.valueOf(start);

            LocalTime endLocalTime = endTimeCombo.getValue();
            LocalDateTime end = LocalDateTime.of(aLocalDate, endLocalTime);
            Timestamp aEndTimeDB = Timestamp.valueOf(end);

            int custID = customersCombo.getValue().getId();
            int userID = usersCombo.getValue().getId();
            int contactID = contactsCombo.getValue().getId();
            String type = typeCombo.getValue().toString();

            //is the time of the proposed appointment within business hours 8am to 10pm EST?
            LocalTime estBegin = LocalTime.of(8,0);
            ZoneId estZoneID = ZoneId.of("America/New_York");
            ZonedDateTime bhoursBegin = ZonedDateTime.of(aLocalDate, estBegin, estZoneID);

            LocalTime estEnd = LocalTime.of(22, 0);
            ZonedDateTime bhoursEnd = ZonedDateTime.of(aLocalDate, estEnd, estZoneID);

            // gotta get the LDT of proposed start, then to origin zoneID, then to target zoneID
            ZonedDateTime newS = ZonedDateTime.of(aLocalDate,startLocalTime, ZoneId.systemDefault());
            ZonedDateTime newE = ZonedDateTime.of(aLocalDate,endLocalTime, ZoneId.systemDefault());
            ZonedDateTime newestS = newS.withZoneSameInstant(ZoneId.of("America/New_York"));
            ZonedDateTime newestE = newE.withZoneSameInstant(ZoneId.of("America/New_York"));

            // now see if the proposed start time is within BH:   bhoursBegin < apptESTStart < bhoursEnd
            if(newestS.toLocalTime().isBefore(bhoursBegin.toLocalTime()) ||
                    newestE.toLocalTime().isAfter(bhoursEnd.toLocalTime()) ||
                    newestE.toLocalTime().isBefore(bhoursBegin.toLocalTime())){

                // then start/end time is NOT within BH
                Alert alert;
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Try Again");
                alert.setHeaderText("Time Outside Business Hours");
                alert.setContentText("Business Hours 8am to 10pm EST");

                alert.showAndWait();
            } else {
            //check for any overlapping appointments

            for (Appointments ap : DBAppointments.apptsByCusID(custID)) {
                LocalDateTime startTimeX = ap.getStart().toLocalDateTime(); // (startTimeX = a, start = x, end = y WRONG)
                LocalDateTime endTimeY = ap.getEnd().toLocalDateTime(); //startTimeX = x, endTimeY = y, start = a or end = a

                //  if myTime(a) falls between an existing appt (a > x && a < y), else if (a=x || a=y) then we'll say "can't do that"
                if(start.isAfter(startTimeX) && start.isBefore(endTimeY)) {
                    // message indicates overlap
                    if(ap.getA_id() == Integer.parseInt(aID)){
                        break;}

                    overlapAlert(Integer.parseInt(aID));
                }
                else if (start.isEqual(startTimeX)) {
                    if(ap.getA_id() == Integer.parseInt(aID)){
                        break;}

                    overlapAlert(Integer.parseInt(aID));
                }
                else if (start.isEqual(endTimeY)){
                    if(ap.getA_id() == Integer.parseInt(aID)){
                        break;}

                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Try Again");
                    alert.setHeaderText("Time must be allowed between Appointments");
                    alert.setContentText("Choose A New Time and Try Again");


                }
                else if (end.isAfter(startTimeX) && end.isBefore(endTimeY)) {
                    if(ap.getA_id() == Integer.parseInt(aID)){
                        break;}

                    overlapAlert(Integer.parseInt(aID));
                }
                else if (end.isEqual(endTimeY)){

                    if(ap.getA_id() == Integer.parseInt(aID)){
                        break;}

                    overlapAlert(Integer.parseInt(aID));
                }
                else if (end.isEqual(startTimeX)){
                    if(ap.getA_id() == Integer.parseInt(aID)){
                        break;}
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Try Again");
                    alert.setHeaderText("Time must be allowed between Appointments");
                    alert.setContentText("Choose A New Time and Try Again");

                    allApptsTable.setItems(DBAppointments.apptsByCusID(custID));

                    alert.showAndWait();
                    return;

                }
                else if (start.isBefore(startTimeX) && end.isAfter(startTimeX)) {

                    if(ap.getA_id() == Integer.parseInt(aID)){
                        break;}

                    overlapAlert(Integer.parseInt(aID));
                }
                else if (end.isAfter(endTimeY) && start.isBefore(endTimeY)) {

                    if(ap.getA_id() == Integer.parseInt(aID)){
                        break;}

                    overlapAlert(Integer.parseInt(aID));
                }
            }
            // Modify appointment record in DB
            DBAppointments.modifyAppointment(Integer.parseInt(aID), title, desc, loc, type, aStartTimeDB, aEndTimeDB, custID, userID, contactID);

            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Appointment Has Been Updated");

            alert.showAndWait();

        }} catch (Exception e) {
            LoginController.emptyFieldSubmitted();
        }
        //reload table
        allApptsTable.setItems(DBAppointments.getAppointmentsTable());
    }

    /**
     * 'Modify Selected Appointment' button click sets text fields with selected appointment data from the table.
     * @param actionEvent button click initiates action.
     */
        public void onSelectAppToMod(ActionEvent actionEvent) {
        //get selection from table to modify

         Appointments appToMod1 = allApptsTable.getSelectionModel().getSelectedItem();
         Appointments appToMod2 = allApptsTableM.getSelectionModel().getSelectedItem();
         Appointments appToMod3 = allApptsTableW.getSelectionModel().getSelectedItem();
         Appointments appToMod = null;

         if (!(appToMod1 == null)){
             appToMod = appToMod1;

         } else if (!(appToMod2 == null)) {
                appToMod = appToMod2;

            } else if (!(appToMod3 == null)) {
                appToMod = appToMod3;
            }

        // in case nothing is selected, pop up box tell user to select item
        if (appToMod == null)
        {
            LoginController.nothingSelected();}

        //display selected in text fields/combo boxes
        else {

            // get id ints for combo box
            int aID = appToMod.getA_id();
            int cusID = appToMod.getCustomerID();
            int userID = appToMod.getU_id();
            int conID = appToMod.getContactID();

            String title = appToMod.getTitle();
            String desc = appToMod.getDesc();
            String loc = appToMod.getLoc();
            String type = appToMod.getType();
            Timestamp start = appToMod.getStart();
            Timestamp end = appToMod.getEnd();

            //set text fields
            aIDText.setText(String.valueOf(aID));
            aTitleTxt.setText(title);
            aDescTxt.setText(desc);
            aLocTxt.setText(loc);
            typeCombo.setValue(TypeEnum.valueOf(type));

            // Timestamp to LocalDateTime
            LocalDateTime ldtStart = start.toLocalDateTime();
            LocalDateTime ldtEnd = end.toLocalDateTime();

            // LDT to Local Date and Local Time
            LocalDate aDate = ldtStart.toLocalDate();

            LocalTime startTime = ldtStart.toLocalTime();
            LocalTime endTime = ldtEnd.toLocalTime();

            //set date+time boxes
            startTimeCombo.setValue(startTime);
            endTimeCombo.setValue(endTime);
            dateBox.setValue(aDate);

            for (Customers c : customersCombo.getItems()) {
                if(c.getId() == cusID) {
                    customersCombo.setValue(c);
                    break;
                }
            }

            for (Users u : usersCombo.getItems()) {
                if (u.getId() == userID) {
                    usersCombo.setValue(u);
                    break;
                }
            }

            for (Contacts t : contactsCombo.getItems())  {
                if (t.getId() == conID)
                {
                    contactsCombo.setValue(t);
                    break;
                }
            }

        }
        // reload table
            allApptsTable.setItems(allAppointments);
            allApptsTableM.setItems(apptsByMonth);
            allApptsTableW.setItems(apptsByWeek);

    }

    /**
     * When this tab is selected, month of each appointment will be compared to the users month and displayed.
     * @param event clicking on the tab initiated the action.
     */
    public void onThisMonthTab(Event event) {
        try{
            allApptsTable.getSelectionModel().clearSelection();
            apptsByMonth.clear();
            for(Appointments am : DBAppointments.getAppointmentsTable()){

                if(am.getStart().toLocalDateTime().getMonth().equals(LocalDateTime.now().getMonth()))  {
                    apptsByMonth.add(am);
                }
            }
            allApptsTableM.setItems(apptsByMonth);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * When this tab is selected, each appointment will be checked to see if it is within the next 7 days.
     * @param event clicking on the tab initiated the action.
     */
    public void onThisWeekTab(Event event) {
        try {
            allApptsTable.getSelectionModel().clearSelection();
            apptsByWeek.clear();
            for (Appointments aw : DBAppointments.getAppointmentsTable()) {

                if(aw.getStart().toLocalDateTime().isBefore(LocalDateTime.now().plusDays(7))){

                    apptsByWeek.add(aw);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        allApptsTableW.setItems(apptsByWeek);
    }

    /**
     * Displays message indicating appointment overlap.
     * @param custID queries the DB to get a list of appointments to display.
     */
    public void overlapAlert(int custID) {
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Start time is during an Appointment");
        alert.setHeaderText("Conflicting Appointment Scheduled");
        alert.setContentText("Choose A New Time and Try Again");

        allApptsTable.setItems(DBAppointments.apptsByCusID(custID));

        alert.showAndWait();
        return;
    }
}

