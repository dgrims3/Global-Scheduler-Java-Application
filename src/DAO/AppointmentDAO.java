package DAO;

import helper.TimeHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.*;
import java.time.LocalDateTime;

public class AppointmentDAO {
    Connection connection = JDBC.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public ObservableList<String> allContacts(){
        ObservableList<String> contacts = FXCollections.observableArrayList();
        String sql = "Select Contact_Name from contacts";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                contacts.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public ObservableList<Appointment> allAppointments(){
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String sql = "select * from appointments left join contacts on  contacts.Contact_ID = appointments.Contact_ID";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                appointments.add(new Appointment(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(14),
                        resultSet.getString(5),
                        resultSet.getTimestamp(6).toLocalDateTime(),
                        resultSet.getTimestamp(7).toLocalDateTime(),
                        //TimeHelper.localTimeHelper(resultSet.getTimestamp(6)),
                        //TimeHelper.localTimeHelper(  resultSet.getTimestamp(7)),
                        resultSet.getInt(12),
                        resultSet.getInt(13),
                        resultSet.getString(16)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
    public void addNewAppointment(Appointment appointment){
        String sql = "INSERT INTO appointments(Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Customer_ID, User_ID, Contact_ID)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, appointment.getAppointment_ID());
            statement.setString(2, appointment.getTitle());
            statement.setString(3, appointment.getDescription());
            statement.setString(4, appointment.getLocation());
            statement.setString(5, appointment.getType());
            statement.setTimestamp(6, Timestamp.valueOf(appointment.getStart()));
            statement.setTimestamp(7, Timestamp.valueOf(appointment.getEnd()));
            statement.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(9, appointment.getCustomer_ID());
            statement.setInt(10, appointment.getUser_ID());
            statement.setInt(11, appointment.getContact_ID());
            statement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
