package controller;

import DAO.AppointmentDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lambda.lambdaThree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lambda.lambdaTwo;
import model.Appointment;
import model.SceneChange;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 *Controller class that adds new appointments to database.
 */
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
    @FXML private Label addApptLabel;

    /**
     * LAMBDA Expression: takes in two integers, a start and end time. and returns an observable list of 15 minute increments between those numbers to fill the time combo box with.
     * @param i int
     * @param j int
     * @returns ObservableList<LocalTime>
     */
lambdaThree time = (i, j) -> {
      ObservableList<LocalTime> time = FXCollections.observableArrayList();
      LocalTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("h:mm:ss"));
      LocalTime start = LocalTime.of(i, 0);
      LocalTime end = LocalTime.of(j, 30);

      while (start.isBefore(end.plusSeconds(1))) {
          time.add(start);
          start = start.plusMinutes(15);
      }
      return time;
  };
    /**
     * LAMBDA Expression: This lambda improves the code by making the process of changing scenes less verbose.
     * @param e ActionEvent
     * @param s String
     */
    lambdaTwo change = (e, s) -> {
        Stage stage;
        Parent scene;
        stage = (Stage) ((Button)e.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(s));
        stage.setScene(new Scene(scene));
        stage.show();
    };

    @FXML void onActionSelectUserID(ActionEvent event) {};
    @FXML void onActionSelectCustomerID(ActionEvent actionEvent){};

    /**
     * sets start time for new appointment.
     * @param event
     */
    @FXML void onActionAddHours(ActionEvent event) {
        LocalDate startDate = ApptDatePicker.getValue();
    }

    /**
     * sets end time for new appointment.
     * @param event
     */
    @FXML void onActionAddEndHours(ActionEvent event) {
        LocalDate endDate = ApptDatePicker.getValue();
    }

    /**
     * brings user back to the main screen.
     * @param event
     * @throws IOException
     */
    @FXML void onActionCancelAddAppointment(ActionEvent event) throws IOException {
     change.sceneChange(event, "/view/MainScreen.fxml");
    }

    /**
     * Method that checks if the user selected a time before the businesses open hours.
     * @param localStart
     * @param localEnd
     * @return boolean
     */
    public boolean compareTimes(ZonedDateTime localStart, ZonedDateTime localEnd) {
           ZonedDateTime zonedStart = localStart.withZoneSameInstant(EST);
           ZonedDateTime zonedEnd = localEnd.withZoneSameInstant(EST);
           LocalDateTime selectedStartTime = zonedStart.withZoneSameInstant(EST).toLocalDateTime();
           LocalDateTime selectedEndTime = zonedEnd.withZoneSameInstant(EST).toLocalDateTime();

           ZonedDateTime openTime = (localStart.toLocalDate().atTime(8, 0).atZone(EST));
           ZonedDateTime closeTime = (localEnd.toLocalDate().atTime(22, 0).atZone(EST));
           LocalDateTime bizOpenTime = openTime.withZoneSameInstant(EST).toLocalDateTime();
           LocalDateTime bizCloseTime = closeTime.withZoneSameInstant(EST).toLocalDateTime();

       if(selectedStartTime.isBefore(bizOpenTime)){
         alert.setContentText(localStart.toLocalTime()+ " " + ZoneId.of(String.valueOf(sysDef)) +" is before 8:00 A.M. EST");
         alert.showAndWait();
         return false;
       }
       else if (selectedEndTime.isAfter(bizCloseTime)){
           alert.setContentText(localEnd.toLocalTime()+ " " + ZoneId.of(String.valueOf(sysDef)) +" is after 10:00 P.M. EST");
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

    /**
     * Method that saves the new appointment into the database.
     * @param event
     * @throws IOException
     */
    @FXML void onActionSaveAddAppointment(ActionEvent event) throws IOException {
        try{
            LocalDateTime start = LocalDateTime.of(date, apptHourPicker.getSelectionModel().getSelectedItem());
            LocalDateTime end = LocalDateTime.of(date, apptEndHourPicker.getSelectionModel().getSelectedItem());
            ZonedDateTime zonedStart = LocalDateTime.of(date, apptHourPicker.getSelectionModel().getSelectedItem()).atZone(sysDef);
            ZonedDateTime zonedEnd = LocalDateTime.of(date, apptEndHourPicker.getSelectionModel().getSelectedItem()).atZone(sysDef);
           if(ifAnyEmpty()){
               if (compareTimes(zonedStart, zonedEnd)){
               int appointment_ID;
               if(addAppointmentId.getText().isEmpty()){appointment_ID = -1;} else {appointment_ID = Integer.parseInt(addAppointmentId.getText());
               }
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
                           if(addAppointmentId.getText().isEmpty()){dao.addNewAppointment(appointment);}else{dao.updateAppointment(appointment);}
                            change.sceneChange(event, "/view/MainScreen.fxml");
                   }
                   else {
                       if (i > 0) {
                           alert.setContentText("There is already an appointment scheduled between \n" + aS.toLocalTime() + " and " + aE.toLocalTime());
                           alert.showAndWait();
                       }
                   }
               }
               else {
                   alert.setContentText("Start time must be before end time");
                   alert.showAndWait();
               }
             }
           }
           else{ alert.setContentText("No fields may be left Blank");
               alert.showAndWait();}
       }catch (NullPointerException n){
            alert.setTitle("Entry Error");
            alert.setContentText("Please enter a number for ID fields");
            alert.showAndWait();
       }
    }

    /**
     * Method that makes sure that the new appointment does not overlap with an already existing appointment.
     * @param a
     * @param b
     * @return int
     */
    public int timeCollision(Appointment a, Appointment b){
        int i = 0;
        if(a.getStart().isBefore(b.getStart()) && (a.getEnd().isAfter(b.getStart()) || a.getEnd().isEqual(b.getStart())) && (addAppointmentId.getText().isBlank()||Integer.parseInt(addAppointmentId.getText()) != b.getAppointment_ID())){
            i=1;
        }
        else if((a.getStart().isAfter(b.getStart()) || a.getStart().isEqual(b.getStart())) && (a.getEnd().isBefore(b.getEnd()) || a.getEnd().isEqual(b.getEnd())) && (addAppointmentId.getText().isBlank()||Integer.parseInt(addAppointmentId.getText()) != b.getAppointment_ID())){
            i=1;
            }
        else if((a.getStart().isBefore(b.getEnd()) || a.getStart().isEqual(b.getEnd())) && a.getEnd().isAfter(b.getEnd()) && (addAppointmentId.getText().isBlank()||Integer.parseInt(addAppointmentId.getText()) != b.getAppointment_ID())){
            i=1;
            }
        else if(a.getStart().isEqual(b.getStart()) && a.getEnd().isEqual(b.getEnd()) && (addAppointmentId.getText().isBlank()||Integer.parseInt(addAppointmentId.getText()) != b.getAppointment_ID())){
            i=1;
        }
        return i;
    }

    /**
     * Method that makes sure no fields are left empty.
     * @return boolean
     */
    public boolean ifAnyEmpty(){
        if(addApptTitle.getText().isBlank()||addApptUserID.getItems().isEmpty()||addApptType.getText().isBlank()||addApptContact.getItems().isEmpty()||addApptLocation.getText().isBlank()||addApptDescription.getText().isBlank()||addApptCustomerID.getItems().isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * Method that fills the text fields with data from the selected appointment.
     * @param appointment
     * @throws SQLException
     */
    public void setText(Appointment appointment) throws SQLException {
        date = appointment.getStart().toLocalDate();

        addApptLabel.setText("Modify Appointment");
        addAppointmentId.setText(String.valueOf(appointment.getAppointment_ID()));
        addApptTitle.setText(appointment.getTitle());
        addApptDescription.setText(appointment.getDescription());
        addApptLocation.setText(appointment.getLocation());
        addApptContact.setValue(appointment.getContact_Name());
        addApptType.setText(appointment.getType());
        addApptCustomerID.setValue(appointment.getCustomer_ID());
        addApptUserID.setValue(appointment.getUser_ID());
        ApptDatePicker.setValue(appointment.getStart().toLocalDate());
        apptHourPicker.setValue(appointment.getStart().toLocalTime());
        apptEndHourPicker.setValue(appointment.getEnd().toLocalTime());
    }

    /**
     * method that takes in an action event when a contact is selected.
     * @param event
     */
    @FXML void onActionSelectContact(ActionEvent event) {}

    /**
     * sets the date parameter with the user selected time.
     * @param event
     */
    @FXML void onActionAddDate(ActionEvent event) {
        date = ApptDatePicker.getValue();
    }

    /**
     * overload initialize method.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apptHourPicker.setItems(time.timeFiller(1,23));
        addApptContact.setItems(dao.allContacts());
        apptEndHourPicker.setItems(time.timeFiller(1,23));
        addApptUserID.setItems(dao.allUserIDs());
        addApptCustomerID.setItems(dao.allCustomerIDs());

    }

}

