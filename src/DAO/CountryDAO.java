package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Customer;
import model.Division;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CountryDAO {
    public ObservableList<Country> getAllCountries(){return allCountries();}
    public ObservableList<Country> allCountries() {
        ObservableList<Country> countries = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql = "SELECT Country_ID, Country FROM countries";

        try {
            connection = JDBC.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                countries.add(new Country(
                        resultSet.getInt(1),
                        resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countries;
    }
    //
    public Country getCountryForModifyCustomer (int i) throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM first_level_divisions INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID WHERE Division_ID =  ?";
        Country country = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, i);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                country = new Country(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return country;
    }
}
