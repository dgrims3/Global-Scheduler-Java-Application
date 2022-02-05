package controller;

import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.DivisionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Country;
import model.Customer;
import model.Division;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML private TextField addCustomerId;
    @FXML private TextField addCustomerName;
    @FXML private TextField addCustomerAddress;
    @FXML private TextField addCustomerPostalCode;
    @FXML private TextField addCustomerPhoneNumber;
    @FXML private ComboBox<Country> addCustomerCountryComboBox;
    @FXML private ComboBox<Division> addCustomerDivisionComboBox;
    CountryDAO dao = new CountryDAO();
    DivisionDAO divDao = new DivisionDAO();

    public ObservableList<Country> countryComboBox = dao.getAllCountries();

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


    @FXML void onActionCancelAddCustomer(ActionEvent event) {

    }
    @FXML void onActionSaveAddCustomer(ActionEvent event) {
        CustomerDAO dao = new CustomerDAO();

        int Customer_ID = -1;
        String Customer_Name = addCustomerName.getText();
        String Address = addCustomerAddress.getText();
        String Postal_Code = addCustomerPostalCode.getText();
        String Phone = addCustomerPhoneNumber.getText();
        int Division_ID = addCustomerDivisionComboBox.getSelectionModel().getSelectedItem().getDivision_ID();
        Customer c = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID);

        dao.addNewCustomer(c);


    }
    @FXML void onActionSelectCountry(ActionEvent event) {
        addCustomerDivisionComboBox.setItems(divisionComboBox(addCustomerCountryComboBox.getSelectionModel().getSelectedItem()));
    }
    @FXML void onActionSelectDivision(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerCountryComboBox.setItems(countryComboBox);
       /* DivisionDAO dao = new DivisionDAO();
        for (Division d:dao.getAllUkDivisions()
             ) {
            System.out.println(d);

        }*/
    }
}
