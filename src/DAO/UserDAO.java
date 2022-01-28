package DAO;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {
    public ObservableList <User> allUsers = FXCollections.observableArrayList();
    public void fillList(){
        Statement statement = null;
        ResultSet resultSet;
        Connection connection = null;
        String sql = "SELECT * FROM USERS;";

        try {
            connection = JDBC.getConnection();
            statement.getConnection();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                allUsers.add(new User(
                        resultSet.getInt("User_ID"),
                        resultSet.getString("User_Name"),
                        resultSet.getString("Password"),
                        resultSet.getTimestamp("Create_Date"),
                        resultSet.getString("Created_By"),
                        resultSet.getTimestamp("Last_Update"),
                        resultSet.getString("Last_Updated_By")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


        public ObservableList <User> getAllUsers() {return allUsers;}
}
