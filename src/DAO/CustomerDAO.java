package DAO;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Customer;

import java.sql.*;
import java.time.LocalDateTime;

public class CustomerDAO {
    public ObservableList<Customer> allCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql = "SELECT * FROM customers";

        try {
            connection = JDBC.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                       /* resultSet.getTimestamp(6),
                        resultSet.getString(7),
                        resultSet.getTimestamp(8),
                        resultSet.getString(9),*/
                        resultSet.getInt(10)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }
    public void deleteCustomer (Customer customer){
        Connection connection = JDBC.getConnection();
        PreparedStatement statement = null;
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getCustomer_ID());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addNewCustomer (Customer customer){
        Connection connection = JDBC.getConnection();
        PreparedStatement statement = null;
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, last_Updated_By, Division_ID)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getCustomer_Name());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getPostal_Code());
            statement.setString(4, customer.getPhone());
            statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(6, "test");
            statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(8, "test");
            statement.setInt(9, customer.getDivision_ID());
            statement.executeUpdate();
    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Customer refreshCustomer(int i) {
       Customer customer = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";

        try {
            connection = JDBC.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, i);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer = (new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(10)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }
}
