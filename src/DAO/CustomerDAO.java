package DAO;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Customer;
import model.Division;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * This class is the Customer Data Access Object. This is used for all methods that involve connecting to the database.
 */
public class CustomerDAO {
    /**
     * This method returns a list of all customers in the database.
     * @return Observable List
     */
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
                        resultSet.getInt(10)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    /**
     * This method deletes a customer from the database.
     * @param customer
     */
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

    /**
     * Method that deletes appointments associated with a customer that is to be deleted.
     * @param i
     */
    public void deleteCustomerAppointment(int i){
        Connection connection = JDBC.getConnection();
        PreparedStatement statement = null;
        String sql = "delete from appointments where Customer_ID = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,i);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * this method adds a new customer to the database.
     * @param customer
     */
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

    /**
     * This method takes in a Division object and returns a division ID.
     * @param division
     * @return int
     * @throws SQLException
     */
    public int getDivision_ID (Division division) throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int i = 0;
        String sql = "select Division_ID FROM first_level_divisions WHERE Division = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, division.getDivision());
            resultSet = statement.executeQuery();
           while(resultSet.next()){
               i = resultSet.getInt("Division_ID");
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * this method updates a customer in the database.
     * @param customer
     */
    public void updateCustomer(Customer customer){
        Connection connection = JDBC.getConnection();
        PreparedStatement statement = null;
        String sql ="update customers set Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Division_ID = ? WHERE Customer_ID = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getCustomer_Name());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getPostal_Code());
            statement.setString(4, customer.getPhone());
            statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(6, String.valueOf(customer.getDivision_ID()));
            statement.setInt(7, customer.getCustomer_ID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
