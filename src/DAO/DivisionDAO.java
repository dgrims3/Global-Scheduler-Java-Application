package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Country_ID = 1";

        try {
            connection = JDBC.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                divisions.add(new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division")));
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
        String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Country_ID = 2";

        try {
            connection = JDBC.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                divisions.add(new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division")));
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
        String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Country_ID = 3";

        try {
            connection = JDBC.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                divisions.add(new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisions;
    }

        public Division getDivisionForModifyCustomer (int i) throws SQLException {
            Connection connection = JDBC.getConnection();
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            String sql = "SELECT Division, Division_ID FROM first_level_divisions WHERE Division_ID = ?";
            Division division = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, i);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    division = new Division(
                            resultSet.getInt("Division_ID"),
                            resultSet.getString("Division"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return division;
        }
}
