package controller;

import DAO.AppointmentDAO;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import helper.Lambda;
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
import org.w3c.dom.ls.LSOutput;


import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class AddAppointmentController implements Initializable {
    AppointmentDAO dao = new AppointmentDAO();
    LocalDate date;
    Alert alert = new Alert(Alert.AlertType.WARNING);
    ZoneId sysDef = ZoneId.systemDefault();
    ZoneId EST = ZoneId.of("America/New_York");
    @FXML public DatePicker ApptDatePicker = new DatePicker();
    @FXML private TextField addAppointmentId;
    @FXML private TextField addApptDescription;
    @FXML private TextField addApptLocation;
    @FXML private TextField addApptTitle;
    @FXML private TextField addApptType;
    @FXML private ComboBox<LocalTime> apptHourPicker;
    @FXML private ComboBox<LocalTime> apptEndHourPicker;
    @FXML private ComboBox<Integer> addApptUserID;
    @FXML private ComboBox<Integer> addApptCustomerID;
    @FXML private ComboBox<String> addApptContact;


        public ObservableList<LocalTime> setTimeComboBox(){
        ObservableList<LocalTime> time = FXCollections.observableArrayList();
        LocalTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("h:mm:ss"));
        LocalTime start = LocalTime.of(5, 0);
        LocalTime end = LocalTime.of(23, 0);

        while (start.isBefore(end.plusSeconds(1))) {
            time.add(start);
            start = start.plusMinutes(15);
        }
        return time;
    }

    @FXML void onActionSelectUserID(ActionEvent event) {};
    @FXML void onActionSelectCustomerID(ActionEvent actionEvent){};
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
    public boolean compareTimes(ZonedDateTime localStart, ZonedDateTime localEnd) {
           ZonedDateTime zonedStart = localStart.withZoneSameInstant(EST); //ZonedDateTime.of(localStart.toLocalDateTime(), EST);
           ZonedDateTime zonedEnd = localEnd.withZoneSameInstant(EST); //ZonedDateTime.of(localEnd.toLocalDateTime(), EST);
           LocalDateTime selectedStartTime = zonedStart.withZoneSameInstant(EST).toLocalDateTime();
           LocalDateTime selectedEndTime = zonedEnd.withZoneSameInstant(EST).toLocalDateTime();

           ZonedDateTime openTime = (localStart.toLocalDate().atTime(8, 0).atZone(EST));
           ZonedDateTime closeTime = (localEnd.toLocalDate().atTime(22, 0).atZone(EST));
           LocalDateTime bizOpenTime = openTime.withZoneSameInstant(EST).toLocalDateTime();
           LocalDateTime bizCloseTime = closeTime.withZoneSameInstant(EST).toLocalDateTime();

       if(selectedStartTime.isBefore(bizOpenTime)){
         alert.setContentText("Time selected is before 8:00 A.M. EST");
         alert.showAndWait();
         return false;
       }
       else if (selectedEndTime.isAfter(bizCloseTime)){
           alert.setContentText("Time selected is after 10:00 P.M. EST");
           alert.showAndWait();
           return false;
       }
       else if(selectedStartTime.isAfter(selectedEndTime) || selectedStartTime.isEqual(selectedEndTime)){
           alert.setContentText("Start time must be before end time");
           alert.showAndWait();
           return false;
       }
       return true;
    }
    @FXML void onActionSaveAddAppointment(ActionEvent event) throws IOException {
        LocalDateTime start = LocalDateTime.of(date, apptHourPicker.getSelectionModel().getSelectedItem());
        LocalDateTime end = LocalDateTime.of(date, apptEndHourPicker.getSelectionModel().getSelectedItem());
        ZonedDateTime zonedStart = LocalDateTime.of(date, apptHourPicker.getSelectionModel().getSelectedItem()).atZone(sysDef);
        ZonedDateTime zonedEnd = LocalDateTime.of(date, apptEndHourPicker.getSelectionModel().getSelectedItem()).atZone(sysDef);


       try{
           if (compareTimes(zonedStart, zonedEnd)){
               int appointment_ID = -1;
               String title = addApptTitle.getText();
               String description = addApptDescription.getText();
               String location = addApptLocation.getText();
               int contact_ID = dao.getContactID(addApptContact.getSelectionModel().getSelectedItem());
               String type = addApptType.getText();
               int customer_ID = addApptCustomerID.getValue();
               int user_ID = addApptUserID.getValue();
               String contact_Name = addApptContact.getSelectionModel().getSelectedItem();
               Appointment appointment = new Appointment(appointment_ID, title, description, location, contact_ID, type, start, end, customer_ID, user_ID, contact_Name);

               int i = 0;
               LocalDateTime aS = null;
               LocalDateTime aE = null;
               if(appointment.getStart().isBefore(appointment.getEnd()) || !appointment.getStart().isEqual(appointment.getEnd())){
                   for (Appointment a: dao.appointmentTimes(addApptCustomerID.getValue())
                        ) {
                       if(timeCollision(appointment, a) == 1){
                           i++;
                           aS = a.getStart();
                           aE = a.getEnd();

                       }
                   }
                   if(i==0){
                           dao.addNewAppointment(appointment);
                           SceneChange scene = new SceneChange();
                           scene.changeScene(event, "/view/MainScreen.fxml");
                   }
                   else {
                       if (i > 0) {
                           alert.setContentText("There is already an appointment scheduled between " + aS.toLocalTime() + " and " + aE.toLocalTime());
                           alert.showAndWait();
                           System.out.println("no");
                       }
                   }
               }
               else {
                   alert.setContentText("Start time must be before end time");
                   alert.showAndWait();
               }
           }
       }catch (NumberFormatException n){
            alert.setTitle("Entry Error");
            alert.setContentText("Please enter a number for ID fields");
            alert.showAndWait();
       }
    }
    public int timeCollision(Appointment a, Appointment b){
        int i = 0;
        if(a.getStart().isBefore(b.getStart()) && (a.getEnd().isAfter(b.getStart()) || a.getEnd().isEqual(b.getStart())) ){
            i=1;
        }
        else if((a.getStart().isAfter(b.getStart()) || a.getStart().isEqual(b.getStart())) && (a.getEnd().isBefore(b.getEnd()) || a.getEnd().isEqual(b.getEnd()))){
            i=1;
            }
        else if((a.getStart().isBefore(b.getEnd()) || a.getStart().isEqual(b.getEnd())) && a.getEnd().isAfter(b.getEnd())){
            i=1;
            }
        else if(a.getStart().isEqual(b.getStart()) && a.getEnd().isEqual(b.getEnd())){
            i=1;
        }
        return i;
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
        addApptUserID.setItems(dao.allUserIDs());
        addApptCustomerID.setItems(dao.allCustomerIDs());

    }

}

