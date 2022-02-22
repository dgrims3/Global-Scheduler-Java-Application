package DAO;

import helper.TimeHelper;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import model.Appointment;
import model.User;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class UserDAO {
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Connection connection = JDBC.getConnection();
    public ObservableList <User> allUsers = FXCollections.observableArrayList();

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
    public void appointmentAlert(User user){
        String  sql = "SELECT Start, End, Appointment_ID FROM appointments WHERE User_ID = ?";
        ObservableList<Appointment> t = FXCollections.observableArrayList();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getUser_ID());
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                t.add(new Appointment(TimeHelper.toLocalDateTimeConverter(resultSet.getTimestamp(1)),
                        TimeHelper.toLocalDateTimeConverter(resultSet.getTimestamp(2)),
                        resultSet.getInt(3)));
                System.out.println(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ObservableList <User> getAllUsers() {return allUsers;}

}
