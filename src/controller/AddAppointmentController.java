package controller;

import helper.TimeHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    @FXML
    private DatePicker ApptDatePicker;

    @FXML
    private TextField addAppointmentId;

    @FXML
    private ComboBox<?> addApptContact;

    @FXML
    private TextField addApptCustomerID;

    @FXML
    private TextField addApptDescription;

    @FXML
    private TextField addApptLocation;

    @FXML
    private TextField addApptTitle;

    @FXML
    private TextField addApptType;

    @FXML
    private TextField addApptUserID;

    @FXML
    private ComboBox<LocalTime> apptHourPicker;

    @FXML void onActionAddHours(ActionEvent event) {
    }

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
    @FXML void onActionCancelAddAppointment(ActionEvent event) {

    }

    @FXML void onActionSaveAddAppointment(ActionEvent event) {

    }

    @FXML void onActionSelectContact(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptHourPicker.setItems(setTimeComboBox());
    }
}

