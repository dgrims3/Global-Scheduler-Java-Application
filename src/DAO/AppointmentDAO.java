package DAO;

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
        String sql = "SELECT * FROM appointments";

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
                        resultSet.getTimestamp(6),
                        resultSet.getTimestamp(7),
                        resultSet.getInt(12),
                        resultSet.getInt(13)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }


}
