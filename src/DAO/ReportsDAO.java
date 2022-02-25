package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportsDAO {
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
    public ArrayList<String[]> typeCount(){
        ArrayList<String[]> t = new ArrayList<>();
        String[] s = new String[2];
        String sql = "select Type, count(Type) from appointments group by Type";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                s[0] = (resultSet.getString(1));
                s[1] = (String.valueOf(resultSet.getInt(2)));
                t.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public ObservableList<Appointment> contactSchedules(int i){
        ObservableList<Appointment> sched = FXCollections.observableArrayList();
        String sql = "select * from appointments WHERE Contact_ID = ? order by Start;";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, i);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                sched.add(new Appointment(
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
                        "name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sched;
    }
}
