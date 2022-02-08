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
Timestamp start;
Timestamp end;
int customer_ID;
int user_ID;


    public Appointment(int appointment_ID, String title, String description, String location, int contact_ID, String type, Timestamp start, Timestamp end, int customer_ID, int user_ID) {
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

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public int getUser_ID() {
        return user_ID;
    }
    //@Override public String toString(){return location;}
}
