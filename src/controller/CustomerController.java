package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Country;
import model.Division;

public class CustomerController {

    @FXML private TextField addCustomerId;
    @FXML private TextField addCustomerName;
    @FXML private TextField addCustomerAddress;
    @FXML private TextField addCustomerPostalCode;
    @FXML private TextField addCustomerPhoneNumber;
    @FXML private ComboBox<Country> addCustomerCountryComboBox;
    @FXML private ComboBox<Division> addCustomerDivisionComboBox;

    @FXML void onActionCancelAddCustomer(ActionEvent event) {

    }
    @FXML void onActionSaveAddCustomer(ActionEvent event) {

    }
    @FXML void onActionSelectCountry(ActionEvent event) {

    }
    @FXML void onActionSelectDivision(ActionEvent event) {

    }

}
