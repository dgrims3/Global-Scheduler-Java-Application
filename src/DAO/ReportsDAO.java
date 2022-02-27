package DAO;

import helper.TimeHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.*;
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
    public ArrayList<ArrayList<String>> typeCount(){
        ArrayList<ArrayList<String>> t = new ArrayList<>();
        String sql = "select Type, count(Type) from appointments group by Type";

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                ArrayList<String> s = new ArrayList<>();
                s.add(resultSet.getString(1));
                s.add(String.valueOf(resultSet.getInt(2)));
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

    public ObservableList<Appointment> getAppointments(){
        ObservableList<Appointment> appointment = FXCollections.observableArrayList();
        String sql = "select Start, End, Appointment_ID from appointments";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){appointment.add(new Appointment(
                    TimeHelper.toLocalDateTimeConverter(resultSet.getTimestamp(1)),
                    TimeHelper.toLocalDateTimeConverter(resultSet.getTimestamp(2)),
                    resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }
    public int[] filterByMonth(){
       int[] l = new int[12];
        for (Appointment a: getAppointments()
             ) {
            if(a.getStart().getMonthValue() == 1)l[0]++;
            else if(a.getStart().getMonthValue() == 2)l[1]++;
            else if(a.getStart().getMonthValue() == 3)l[2]++;
            else if(a.getStart().getMonthValue() == 4)l[3]++;
            else if(a.getStart().getMonthValue() == 5)l[4]++;
            else if(a.getStart().getMonthValue() == 6)l[5]++;
            else if(a.getStart().getMonthValue() == 7)l[6]++;
            else if(a.getStart().getMonthValue() == 8)l[7]++;
            else if(a.getStart().getMonthValue() == 9)l[8]++;
            else if(a.getStart().getMonthValue() == 10)l[9]++;
            else if(a.getStart().getMonthValue() == 11)l[10]++;
            else if(a.getStart().getMonthValue() == 12)l[12]++;
        }
        return l;
    }
}

