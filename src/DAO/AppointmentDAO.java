package DAO;

import helper.lambdaThree;
import helper.lambdaTwo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * This class is the appointment Data Access Object. This is used for all methods that involve connecting to the database.
 */
public class AppointmentDAO {
    Connection connection = JDBC.getConnection();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    /**
     * This lambda expression takes in a LocalDateTime and gives back a Timestamp used for inserting into the database.
     * LAMBDA Expression: The Lambda makes the code more efficient by providing a quick conversion method that can be used anywhere.
     * @param LocalDateTime l
     * @returns Timestamp
     */
    lambdaTwo toTimestamp = l -> {
        ZoneId UTC = ZoneId.of("Etc/UTC");
        ZoneId myZone = ZoneId.systemDefault();
        return Timestamp.valueOf(l.atZone(myZone).withZoneSameInstant(UTC).toLocalDateTime());
    };
    /**
     * This lambda expression takes in a Timestamp and gives back a LocalDateTime used for extracting from the database.
     * LAMBDA Expression: The Lambda makes the code more efficient by providing a quick conversion method that can be used anywhere.
     * @param Timestamp t
     * @returns LocalDateTime
     */
 lambdaThree toLocal = t -> {
       ZoneId UTC = ZoneId.of("Etc/UTC");
       ZoneId myZone = ZoneId.systemDefault();
       return t.toLocalDateTime().atZone(UTC).withZoneSameInstant(myZone).toLocalDateTime();
    };

    /**
     * This method takes in a contact name and returns a contact ID.
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
     * This method takes a customer ID and returns all appointments for that customer.
     * @param id
     * @return Observable List
     */
    public ObservableList<Appointment> appointmentTimes(Integer id){
        ObservableList<Appointment> appointment = FXCollections.observableArrayList();
        String sql = "select Start, End, Appointment_ID from appointments where Customer_ID = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){appointment.add(new Appointment(
                  toLocal.toLocalDateTime(resultSet.getTimestamp(1)),
                   toLocal.toLocalDateTime(resultSet.getTimestamp(2)),
                    resultSet.getInt(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    /**
     * This method returns a list of all contacts in the database.
     * @return Observable list
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
     * This method returns all user ID's.
     * @return Observable List
     */
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

    /**
     * This method returns all customer ID's.
     * @return Observable List
     */
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

    /**
     * This method returns all appointments in the database.
     * @return Observable List
     */
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
                        /*toLocal.toLocalDateTime(resultSet.getTimestamp(6)), */resultSet.getTimestamp(6).toLocalDateTime(),
                        /*toLocal.toLocalDateTime(resultSet.getTimestamp(7)),*/resultSet.getTimestamp(7).toLocalDateTime(),
                        resultSet.getInt(12),
                        resultSet.getInt(13),
                        resultSet.getString(16)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    /**This method inserts a new appointment into the database.
     *
     * @param appointment
     */
    public void addNewAppointment(Appointment appointment){
        String sql = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Customer_ID, User_ID, Contact_ID)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, appointment.getTitle());
            statement.setString(2, appointment.getDescription());
            statement.setString(3, appointment.getLocation());
            statement.setString(4, appointment.getType());
           statement.setTimestamp(5, Timestamp.valueOf(appointment.getStart())); //statement.setTimestamp(5, toTimestamp.toTimestamp(appointment.getStart()));
            statement.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));//statement.setTimestamp(6, toTimestamp.toTimestamp(appointment.getEnd()));
            statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(8, appointment.getCustomer_ID());
            statement.setInt(9, appointment.getUser_ID());
            statement.setInt(10, appointment.getContact_ID());
            statement.executeUpdate();

        } catch (SQLException e) {
           e.printStackTrace();
        }

    }

    /**
     * This method updates appointments in the database.
     * @param appointment
     */
    public void updateAppointment(Appointment appointment){
        String sql = "UPDATE appointments SET Title = ?, Description  = ?, Location  = ?, Type  = ?, Start  = ?, End  = ?, Last_Update = ?, Customer_ID  = ?, User_ID  = ?, Contact_ID  = ? WHERE Appointment_ID = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, appointment.getTitle());
            statement.setString(2, appointment.getDescription());
            statement.setString(3, appointment.getLocation());
            statement.setString(4, appointment.getType());
            statement.setTimestamp(5, Timestamp.valueOf(appointment.getStart())); //statement.setTimestamp(5, toTimestamp.toTimestamp(appointment.getStart()));
            statement.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));//statement.setTimestamp(6, toTimestamp.toTimestamp(appointment.getEnd()));
            statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));//statement.setTimestamp(7,  toTimestamp.toTimestamp(LocalDateTime.now(ZoneId.systemDefault())));
            statement.setInt(8, appointment.getCustomer_ID());
            statement.setInt(9, appointment.getUser_ID());
            statement.setInt(10, appointment.getContact_ID());
            statement.setInt(11, appointment.getAppointment_ID());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * this method deletes an appointment from the database.
     * @param i
     */
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

    /**
     * This method takes a month int and returns a list of appointments, filtered by month.
     * @param i
     * @return Observable List
     */
    public ObservableList<Appointment> filterByMonth(int i){
        allAppointments();
        ObservableList<Appointment> month = FXCollections.observableArrayList();
        for (Appointment a:allAppointments()
             ) {
            if (a.getStart().getMonth().getValue() == i){
                month.add(a);
            }
        }
        return month;
    }

    /** this method returns a list of appointments, filtered by week.
     *
     * @return Observable List
     */
    public ObservableList<Appointment> filterByWeek(){
        allAppointments();
        ObservableList<Appointment> week = FXCollections.observableArrayList();
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        LocalDate start = null;
        LocalDate end = null;
        switch(day) {
            case SUNDAY: start = LocalDate.now(); end = LocalDate.now().plusDays(6); break;
            case MONDAY: start = LocalDate.now().minusDays(1); end = LocalDate.now().plusDays(5); break;
            case TUESDAY: start = LocalDate.now().minusDays(2); end = LocalDate.now().plusDays(4); break;
            case WEDNESDAY: start = LocalDate.now().minusDays(3); end = LocalDate.now().plusDays(3); break;
            case THURSDAY: start = LocalDate.now().minusDays(4); end = LocalDate.now().plusDays(2); break;
            case FRIDAY: start = LocalDate.now().minusDays(5); end = LocalDate.now().plusDays(1); break;
            case SATURDAY: start = LocalDate.now().minusDays(6); end = LocalDate.now(); break;
        }
        for (Appointment a:allAppointments()
             ) {
            if( (a.getStart().toLocalDate().isEqual(start) || a.getStart().toLocalDate().isAfter(start))
                    && (a.getEnd().toLocalDate().isBefore(end) || a.getEnd().toLocalDate().isEqual(end)) ){
                week.add(a);
            }
        }

        return week;
    }


}
