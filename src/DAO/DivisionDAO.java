package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Division;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionDAO {
    public ObservableList<Division> getAllUsDivisions(){return usDivisions();}
    public ObservableList<Division> getAllUkDivisions(){return ukDivisions();}
    public ObservableList<Division> getAllCanadaDivisions(){return canadaDivisions();}

    public ObservableList<Division> usDivisions() {
        ObservableList<Division> divisions = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = 1";

        try {
            connection = JDBC.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                divisions.add(new Division(
                        resultSet.getInt(1),
                        resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisions;
    }

    public ObservableList<Division> ukDivisions() {
        ObservableList<Division> divisions = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = 2";

        try {
            connection = JDBC.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                divisions.add(new Division(
                        resultSet.getInt(1),
                        resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisions;
    }

    public ObservableList<Division> canadaDivisions() {
        ObservableList<Division> divisions = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = 3";

        try {
            connection = JDBC.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                divisions.add(new Division(
                        resultSet.getInt(1),
                        resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisions;
    }
}
