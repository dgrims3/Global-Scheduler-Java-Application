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
    @FXML public DatePicker ApptDatePicker = new DatePicker();
    LocalDate date;
    Alert alert = new Alert(Alert.AlertType.WARNING);
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
        /*  Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        LocalDateTime ldt = ts.toLocalDateTime();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtIn = utczdt.toLocalDateTime();

        ZonedDateTime zdtOut = ldtIn.atZone(ZoneId.of("UTC"));
        ZonedDateTime zdtOutToLocalTZ = zdtOut.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime ldtOutFinal = zdtOutToLocalTZ.toLocalDateTime();
        */
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

    public LocalDateTime dateTimeConverter(LocalDate date, LocalTime time){
        //Showing how to parse the Date/Time String
        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(String.valueOf(date).substring(0, 10), dFormatter);
        System.out.println("The local date is " + localDate);

        DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("kk:mm");
        LocalTime localTime = LocalTime.parse(String.valueOf(time).substring(0), tFormatter);
        System.out.println("The local time is " + localTime);

       LocalDateTime dateTime = LocalDateTime.of(date, time);

        //Convert to a ZonedDate Time in UTC
        ZoneId zid = ZoneId.systemDefault();

        ZonedDateTime zdtStart = dateTime.atZone(zid);
        System.out.println("Local Time: " + zdtStart);
        ZonedDateTime utcStart = zdtStart.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("Zoned time: " + utcStart);
        dateTime = utcStart.toLocalDateTime();
        System.out.println("Zoned time with zone stripped:" + dateTime);
        //Create Timestamp values from Instants to update database
        Timestamp startsqlts = Timestamp.valueOf(dateTime); //this value can be inserted into database
        System.out.println("Timestamp to be inserted: " +startsqlts);
        return dateTime;
    }

    @FXML void onActionAddHours(ActionEvent event) {
        LocalDate startDate = ApptDatePicker.getValue();
    }

    @FXML void onActionAddEndHours(ActionEvent event) {
        LocalDate endDate = ApptDatePicker.getValue();
    }

    @FXML void onActionCancelAddAppointment(ActionEvent event) throws IOException {
       /* SceneChange scene = new SceneChange();
        scene.changeScene(event, "/view/MainScreen.fxml");*/
        dateTimeConverter(date, apptHourPicker.getSelectionModel().getSelectedItem());
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

