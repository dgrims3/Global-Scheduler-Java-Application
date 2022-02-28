package DAO;

import helper.TimeHelper;
import helper.lambdaThree;
import helper.lambdaTwo;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Appointment;
import model.User;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
/**
 * This class is the User Data Access Object. This is used for all methods that involve connecting to the database.
 */
public class UserDAO {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Connection connection = JDBC.getConnection();
    /**
     * Lambda expression that takes a Timestamp and returns a LocalDateTime.
     */
    lambdaThree toLocal = t -> {
        ZoneId UTC = ZoneId.of("Etc/UTC");
        ZoneId myZone = ZoneId.systemDefault();
        return t.toLocalDateTime().atZone(UTC).withZoneSameInstant(myZone).toLocalDateTime();
    };
    public ObservableList <User> allUsers = FXCollections.observableArrayList();

    /**
     * Method that fills the List of all users in the database.
     */
    public void fillList(){
        String sql = "SELECT User_ID, User_Name, Password FROM USERS;";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                allUsers.add(new User(
                        resultSet.getInt("User_ID"),
                        resultSet.getString("User_Name"),
                        resultSet.getString("Password")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Method that checks the start and end time for all appointments for a specific user. This method is used to set off an alert if there is an appointment within 15 minutes of login.
     * @param user
     */
    public void appointmentAlert(User user){
        String  sql = "SELECT Start, End, Appointment_ID FROM appointments WHERE User_ID = ?";
        ObservableList<Appointment> t = FXCollections.observableArrayList();
        LocalDateTime start = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime start15 = LocalDateTime.now(ZoneId.systemDefault()).plusMinutes(15);


        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getUser_ID());
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                if(toLocal.toLocalDateTime(resultSet.getTimestamp(1)).isAfter(start)/*TimeHelper.toLocalDateTimeConverter(resultSet.getTimestamp(1)).isAfter(start)*/ && /*TimeHelper.toLocalDateTimeConverter(resultSet.getTimestamp(1))*/toLocal.toLocalDateTime(resultSet.getTimestamp(1)).isBefore(start15)){
                    t.add(new Appointment( toLocal.toLocalDateTime(resultSet.getTimestamp(1)),//TimeHelper.toLocalDateTimeConverter(resultSet.getTimestamp(1)),
                            toLocal.toLocalDateTime(resultSet.getTimestamp(2)),//TimeHelper.toLocalDateTimeConverter(resultSet.getTimestamp(2)),
                            resultSet.getInt(3)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!t.isEmpty()){
            for (Appointment a:t
                 ) {
                int id = a.getAppointment_ID();
                LocalDate date = a.getStart().toLocalDate();
                LocalTime time = a.getStart().toLocalTime();
                alert.setContentText("you have an upcoming appointment. "+ "Appt ID: "+id+" At "+time+" "+date);
                alert.showAndWait();
            }
        }
        else {
            alert.setContentText("No upcoming appointments");
            alert.showAndWait();
        }
    }
    public ObservableList <User> getAllUsers() {return allUsers;}

}
