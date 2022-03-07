package controller;

import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.DivisionDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lambda.lambdaTwo;
import model.Country;
import model.Customer;
import model.Division;
import model.SceneChange;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller class for modifying an existing customer.
 */
public class ModifyCustomerController implements Initializable {

    @FXML private TextField addCustomerId;
    @FXML private TextField addCustomerName;
    @FXML private TextField addCustomerAddress;
    @FXML private TextField addCustomerPostalCode;
    @FXML private TextField addCustomerPhoneNumber;
    @FXML private ComboBox<Country> addCustomerCountryComboBox;
    @FXML private ComboBox<Division> addCustomerDivisionComboBox;
    CountryDAO dao = new CountryDAO();
    DivisionDAO divDao =new DivisionDAO();
    public ObservableList<Country> countryComboBox = dao.getAllCountries();

    /**
     * LAMBDA Expression: This lambda improves the code by making the process of changing scenes less verbose.
     * @param e ActionEvent
     * @param s String
     */
    lambdaTwo change = (e, s) -> {
        Stage stage;
        Parent scene;
        stage = (Stage) ((Button)e.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(s));
        stage.setScene(new Scene(scene));
        stage.show();
    };

    /**
     * Method that takes in a Country object and returns a Division Observable list.
     * @param country Country
     * @return Observable List
     */
    public ObservableList<Division> divisionComboBox(Country country){
        if(addCustomerCountryComboBox.getSelectionModel().getSelectedIndex()==0){
            return divDao.getAllUsDivisions();
        }else if(addCustomerCountryComboBox.getSelectionModel().getSelectedIndex()==1){
            return divDao.getAllUkDivisions();
        }else if(addCustomerCountryComboBox.getSelectionModel().getSelectedIndex()==2){
            return divDao.getAllCanadaDivisions();
        }
        return null;
    }

    /**
     * Method used to fill the text areas with the information of the chosen Customer.
     * @param customer Customer
     * @throws SQLException e
     */
    public void setText(Customer customer) throws SQLException {
        CountryDAO dao = new CountryDAO();
        DivisionDAO divDao = new DivisionDAO();
        addCustomerId.setText(String.valueOf(customer.getCustomer_ID()));
        addCustomerName.setText(customer.getCustomer_Name());
        addCustomerAddress.setText(customer.getAddress());
        addCustomerPostalCode.setText(customer.getPostal_Code());
        addCustomerPhoneNumber.setText(customer.getPhone());
        addCustomerDivisionComboBox.setValue(divDao.getDivisionForModifyCustomer(customer.getDivision_ID()));
        addCustomerCountryComboBox.setValue(dao.getCountryForModifyCustomer(customer.getDivision_ID()));
    }

    /**
     * Returns user to main screen.
     * @param event ActionEvent
     * @throws IOException e
     */
    @FXML void onActionCancelAddCustomer(ActionEvent event) throws IOException {
        change.sceneChange(event, "/view/MainScreen.fxml");
    }

    /**
     * Saves new Customer in database.
     * @param event ActionEvent
     * @throws SQLException
     * @throws IOException
     */
    @FXML void onActionSaveAddCustomer(ActionEvent event) throws SQLException, IOException {
        CustomerDAO dao = new CustomerDAO();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Missing Fields");
        alert.setContentText("Please fill out all fields");
        if(addCustomerName.getText().isBlank()||addCustomerAddress.getText().isBlank()||addCustomerPostalCode.getText().isBlank()||addCustomerPhoneNumber.getText().isBlank()){alert.showAndWait();}
        else{Customer customer = new Customer(
                Integer.parseInt(addCustomerId.getText()),
                addCustomerName.getText(),
                addCustomerAddress.getText(),
                addCustomerPostalCode.getText(),
                addCustomerPhoneNumber.getText(),
                dao.getDivision_ID(addCustomerDivisionComboBox.getSelectionModel().getSelectedItem()));
        dao.updateCustomer(customer);
        change.sceneChange(event, "/view/MainScreen.fxml");}

    }

    /**
     * Fills combo box with divisions.
     * @param event ActionEvent
     */
    @FXML void onActionSelectCountry(ActionEvent event) {
        addCustomerDivisionComboBox.setItems(divisionComboBox(addCustomerCountryComboBox.getSelectionModel().getSelectedItem()));
    }

    /**
     * Action event when a user selects a first level division.
     * @param actionEvent ActionEvent
     */
    @FXML void onActionSelectDivision(ActionEvent actionEvent) {

    }

    /**
     * override initialize method.
     * @param url URL
     * @param resourceBundle ResoiurceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerCountryComboBox.setItems(countryComboBox);
    }

}