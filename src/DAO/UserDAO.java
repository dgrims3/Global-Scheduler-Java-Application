package DAO;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public ObservableList <User> allUsers = FXCollections.observableArrayList();
    public ObservableList <User> getAllUsers() {return allUsers;}

    PreparedStatement ps;
    ResultSet rs;
    String query = "SELECT * FROM USERS;";
}
