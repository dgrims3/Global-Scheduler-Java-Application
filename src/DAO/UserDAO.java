package DAO;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import model.User;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserDAO {
    public ObservableList <User> allUsers = FXCollections.observableArrayList();

    public void fillList(){
        PreparedStatement statement = null;
        ResultSet resultSet;
        Connection connection = null;
        String sql = "SELECT * FROM USERS;";

        try {
            connection = JDBC.getConnection();
            statement =connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

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

    public void initialize() {
        fillList();
        for (User u: getAllUsers()) {
            System.out.println(u.getUser_ID());
        }
    }
}
