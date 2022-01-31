package model;

import DAO.UserDAO;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

public class User {
   private int user_ID;
   private String user_name;
    private String password;


    public User(int user_ID, String user_name, String password) {
        this.user_ID = user_ID;
        this.user_name = user_name;
        this.password = password;

    }

    public int getUser_ID() {
        return user_ID;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return user_name;
    }
}
