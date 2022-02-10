package DAO;

import helper.TimeHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDAO {
    Connection connection = JDBC.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

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
                        TimeHelper.localTimeHelper(resultSet.getTimestamp(6)),
                        TimeHelper.localTimeHelper(  resultSet.getTimestamp(7)),
                        resultSet.getInt(12),
                        resultSet.getInt(13),
                        resultSet.getString(16)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }


}
