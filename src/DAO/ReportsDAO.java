package DAO;

import helper.lambdaThree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
/**
 * This class is the Reports Data Access Object. This is used for all methods that involve connecting to the database.
 */
public class ReportsDAO {
    Connection connection = JDBC.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;
/*    *//**
     * Lambda expression that takes in a timestamp and returns a LocalDateTime.
      *//*
    lambdaThree toLocal = t -> {
        ZoneId UTC = ZoneId.of("Etc/UTC");
        ZoneId myZone = ZoneId.systemDefault();
        return t.toLocalDateTime().atZone(UTC).withZoneSameInstant(myZone).toLocalDateTime();
    };*/

    /**
     * Method that creates an array with the length of each appointment in the database.
     * @return Arraylist
     */
    public ArrayList<Integer> apptLength(){
        ArrayList<Integer> i = new ArrayList<>();
        String sql = "SELECT End, Start FROM appointments";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                Integer j = resultSet.getTimestamp(1).toLocalDateTime().toLocalTime().toSecondOfDay();
                Integer k = resultSet.getTimestamp(2).toLocalDateTime().toLocalTime().toSecondOfDay();
                Integer l = (j-k)/60;
                i.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * Method that returns a list of all contacts from the database.
     * @return Observable List
     */
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

    /**
     * Method that takes in a contact name and returns a contact ID.
     * @param string
     * @return int
     */
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

    /**
     * method that returns an array list of array lists of the appointments, grouped by appointment type.
     * @return ArrayList<ArrayList<String>
     */
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

    /**
     * Method that takes a Contact ID and returns all appointments for that contact, ordered chronologically.
     * @param i
     * @return Observable List
     */
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

    /**
     * Method that returns a list of start and end times of all appointments.
     * @return Observable List.
     */
    public ObservableList<Appointment> getAppointments(){
        ObservableList<Appointment> appointment = FXCollections.observableArrayList();
        String sql = "select Start, End, Appointment_ID from appointments";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){appointment.add(new Appointment(
                    resultSet.getTimestamp(1).toLocalDateTime(),
                    resultSet.getTimestamp(2).toLocalDateTime(),
                    resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    /**
     * Method that counts how many appointments happen each month.
     * @return array of int
     */
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

