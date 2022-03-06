package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is the Division Data Access Object. This is used for all methods that involve connecting to the database.
 */
public class DivisionDAO {
    /**
     * @return Observable List
     */
    public ObservableList<Division> getAllUsDivisions(){return usDivisions();}
    /**
     * @return Observable List
     */
    public ObservableList<Division> getAllUkDivisions(){return ukDivisions();}
    /**
     * @return Observable List
     */
    public ObservableList<Division> getAllCanadaDivisions(){return canadaDivisions();}

    /**
     * This method returns a list of all US divisions.
     * @return Observable List
     */
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

    /**
     * this method returns a list of all UK divisions.
     * @return Observable List
     */
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

    /**
     * this method returns a list of all canadian divisions.
     * @return
     */
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

    /**
     * This method takes a division ID and returns a Division.
     * @param i
     * @return Division
     * @throws SQLException
     */
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
