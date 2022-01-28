package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

public class User {
    int user_ID;
    String user_name;
    String password;
    Timestamp create_date;
    String created_by;
    Timestamp last_update;
    String last_updated_by;

    public User(int user_ID, String user_name, String password, Timestamp create_date, String created_by, Timestamp last_update, String last_updated_by) {
        this.user_ID = user_ID;
        this.user_name = user_name;
        this.password = password;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Timestamp getLast_update() {return last_update;}

    public void setLast_update(Timestamp last_update) {this.last_update = last_update;}

    public String getLast_updated_by() { return last_updated_by; }

    public void setLast_updated_by(String last_updated_by) {this.last_updated_by = last_updated_by;}



}
