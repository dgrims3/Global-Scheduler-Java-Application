package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class creates Appointment Objects. it uses data from the mySQL database to make appointment objects
 */
public class Appointment {
int appointment_ID;
String title;
String description;
String location;
int contact_ID;
String type;
LocalDateTime start;
LocalDateTime end;
int customer_ID;
int user_ID;
String contact_Name;

    /**
     * Contructor for Appointment Object.
     * @param appointment_ID
     * @param title
     * @param description
     * @param location
     * @param contact_ID
     * @param type
     * @param start
     * @param end
     * @param customer_ID
     * @param user_ID
     * @param contact_Name
     */
    public Appointment(int appointment_ID, String title, String description, String location, int contact_ID, String type, LocalDateTime start, LocalDateTime end, int customer_ID, int user_ID, String contact_Name) {
        this.appointment_ID = appointment_ID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact_ID = contact_ID;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customer_ID = customer_ID;
        this.user_ID = user_ID;
        this.contact_Name = contact_Name;
    }

    /**
     * Overloaded Appointment Constructor. This is used to create pared down Appointment objects for when not all the parameters are needed.
     * @param start
     * @param end
     * @param appointment_ID
     */
    public Appointment(LocalDateTime start, LocalDateTime end, int appointment_ID){
        this.start = start;
        this.end = end;
        this.appointment_ID = appointment_ID;
    }

    /**
     * gets appt ID.
     * @return int
     */
    public int getAppointment_ID() {
        return appointment_ID;
    }

    /**
     * gets appt title.
     * @return String
     */
    public String getTitle() {
        return title;
    }
    /**
     * gets appt description
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets appt location.
     * @return String
     */
    public String getLocation() {
        return location;
    }

    /**
     * gets appt Contact ID.
     * @return int
     */
    public int getContact_ID() {
        return contact_ID;
    }

    /**
     * gets appt trye.
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * gets appt Start time.
     * @return LocalDateTime
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * gets appt End time.
     * @return LocalDateTime
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * gets appt Customer ID.
     * @return int
     */
    public int getCustomer_ID() {
        return customer_ID;
    }

    /**
     * gets appt user ID
     * @return int
     */
    public int getUser_ID() {
        return user_ID;
    }

    /**
     * gets appt contact name.
     * @return String
     */
    public String getContact_Name() {
        return contact_Name;
    }
}
