package model;

import DAO.UserDAO;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * This Class creates user objects. User information is sourced from the mySQL database.
 */
public class User {
   private int user_ID;
   private String user_name;
   private String password;

    /**
     * Constructor for the user class.
      * @param user_ID
     * @param user_name
     * @param password
     */
    public User(int user_ID, String user_name, String password) {
        this.user_ID = user_ID;
        this.user_name = user_name;
        this.password = password;

    }

    /**
     * gets the user ID.
     * @return int
     */
    public int getUser_ID() {
        return user_ID;
    }

    /**
     * gets the user name.
     * @return String
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * gets the user password.
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Override method for user Strings
     * @return String
     */
    @Override
    public String toString(){
        return user_name;
    }
}
