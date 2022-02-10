package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    public int getAppointment_ID() {
        return appointment_ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public int getContact_ID() {
        return contact_ID;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public int getUser_ID() {
        return user_ID;
    }
    public String getContact_Name() {
        return contact_Name;
    }
    //@Override public String toString(){return location;}
}
