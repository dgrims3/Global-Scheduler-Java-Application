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

    public int getContactID (String string){
        String sql = "Select Contact_ID from contacts WHERE Contact_Name = ?";
        int i = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, string);
            resultSet = statement.executeQuery();
          while (resultSet.next()){
              i = resultSet.getInt(1);
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public ObservableList<Appointment> appointmentTimes(Integer id){
        ObservableList<Appointment> appointment = FXCollections.observableArrayList();
        String sql = "select Start, End from appointments where Customer_ID = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){appointment.add(new Appointment(
                    TimeHelper.toLocalDateTimeConverter(resultSet.getTimestamp(1)),
                    TimeHelper.toLocalDateTimeConverter(resultSet.getTimestamp(2))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

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
    public ObservableList<Integer> allUserIDs(){
        ObservableList<Integer> users = FXCollections.observableArrayList();
        String sql = "Select User_ID from users order by User_ID";
        try {statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                users.add((resultSet.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public ObservableList<Integer> allCustomerIDs(){
        ObservableList<Integer> customers = FXCollections.observableArrayList();
        String sql = "Select Customer_ID from customers order by Customer_ID";
        try {statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                customers.add((resultSet.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
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
        String sql = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Customer_ID, User_ID, Contact_ID)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, appointment.getTitle());
            statement.setString(2, appointment.getDescription());
            statement.setString(3, appointment.getLocation());
            statement.setString(4, appointment.getType());
            statement.setTimestamp(5, TimeHelper.toTimestampConverter(appointment.getStart()));
            statement.setTimestamp(6, TimeHelper.toTimestampConverter(appointment.getEnd()));
            statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(8, appointment.getCustomer_ID());
            statement.setInt(9, appointment.getUser_ID());
            statement.setInt(10, appointment.getContact_ID());
            statement.executeUpdate();

        } catch (SQLException e) {
           e.printStackTrace();
        }

    }
    public void deleteAppointment(int i){
       String sql = "delete from appointments where Appointment_ID = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,i);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
