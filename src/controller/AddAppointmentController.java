package controller;

import DAO.AppointmentDAO;
import helper.TimeHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Appointment;
import model.SceneChange;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    AppointmentDAO dao = new AppointmentDAO();
    LocalDate date;
    Alert alert = new Alert(Alert.AlertType.WARNING);
    @FXML public DatePicker ApptDatePicker = new DatePicker();
    @FXML private TextField addAppointmentId;
    @FXML private ComboBox<String> addApptContact;
    @FXML private TextField addApptCustomerID;
    @FXML private TextField addApptDescription;
    @FXML private TextField addApptLocation;
    @FXML private TextField addApptTitle;
    @FXML private TextField addApptType;
    @FXML private TextField addApptUserID;
    @FXML private ComboBox<LocalTime> apptHourPicker;
   @FXML private ComboBox<LocalTime> apptEndHourPicker;

    public ObservableList<LocalTime> setTimeComboBox(){
        ObservableList<LocalTime> time = FXCollections.observableArrayList();
        LocalTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("h:mm:ss"));
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(20, 0);

        while (start.isBefore(end.plusSeconds(1))) {
            time.add(start);
            start = start.plusMinutes(15);
        }
        return time;
    }

    @FXML void onActionAddHours(ActionEvent event) {
        LocalDate startDate = ApptDatePicker.getValue();
    }

    @FXML void onActionAddEndHours(ActionEvent event) {
        LocalDate endDate = ApptDatePicker.getValue();
    }

    @FXML void onActionCancelAddAppointment(ActionEvent event) throws IOException {
       SceneChange scene = new SceneChange();
        scene.changeScene(event, "/view/MainScreen.fxml");
    }

    @FXML void onActionSaveAddAppointment(ActionEvent event) {
       try{
            int appointment_ID = -1;
            String title = addApptTitle.getText();
            String description = addApptDescription.getText();
            String location = addApptLocation.getText();
            int contact_ID = dao.getContactID(addApptContact.getSelectionModel().getSelectedItem());
            String type = addApptType.getText();
            LocalDateTime start = LocalDateTime.of(date, apptHourPicker.getSelectionModel().getSelectedItem());
            LocalDateTime end = LocalDateTime.of(date, apptEndHourPicker.getSelectionModel().getSelectedItem());
            int customer_ID = Integer.parseInt(addApptCustomerID.getText());
            int user_ID = Integer.parseInt(addApptUserID.getText());
            String contact_Name = addApptContact.getSelectionModel().getSelectedItem();
            Appointment appointment = new Appointment(appointment_ID, title, description, location, contact_ID, type, start, end, customer_ID, user_ID, contact_Name);

            ZonedDateTime time = start.atZone(ZoneId.of("US/Eastern"));
               System.out.println(time);

            dao.addNewAppointment(appointment);
    }catch (NumberFormatException n){
            alert.setTitle("Entry Error");
            alert.setContentText("Please enter a number for ID fields");
            alert.showAndWait();
       }
    }

    @FXML void onActionSelectContact(ActionEvent event) {

    }
    @FXML void onActionAddDate(ActionEvent event) {
        date = ApptDatePicker.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptHourPicker.setItems(setTimeComboBox());
        addApptContact.setItems(dao.allContacts());
        apptEndHourPicker.setItems(setTimeComboBox());

    }
}

