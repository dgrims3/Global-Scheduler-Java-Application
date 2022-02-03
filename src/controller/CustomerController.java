package controller;

import DAO.CountryDAO;
import DAO.DivisionDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Country;
import model.Division;

import java.net.URL;
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

    public ObservableList<Division> divisionComboBox(){
        if(addCustomerCountryComboBox.getSelectionModel().getSelectedIndex()==0){
            return divDao.getAllUsDivisions();
        }else if(addCustomerCountryComboBox.getSelectionModel().getSelectedIndex()==1){
            return divDao.ukDivisions();
        }else if(addCustomerCountryComboBox.getSelectionModel().getSelectedIndex()==2){
            return divDao.canadaDivisions();
        }
        return null;
    }


    @FXML void onActionCancelAddCustomer(ActionEvent event) {

    }
    @FXML void onActionSaveAddCustomer(ActionEvent event) {

    }
    @FXML void onActionSelectCountry(ActionEvent event) {

    }
    @FXML void onActionSelectDivision(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerCountryComboBox.setItems(countryComboBox);
        addCustomerDivisionComboBox.setItems(divisionComboBox());
    }
}
