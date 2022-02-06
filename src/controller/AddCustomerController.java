package controller;

import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.DivisionDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Country;
import model.Customer;
import model.Division;
import model.SceneChange;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {


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


    @FXML void onActionCancelAddCustomer(ActionEvent event) throws IOException {
        SceneChange scene = new SceneChange();
        scene.changeScene(event, "/view/MainScreen.fxml");
    }
    @FXML void onActionSaveAddCustomer(ActionEvent event) throws IOException {
        CustomerDAO dao = new CustomerDAO();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Missing Fields");
        alert.setContentText("Please fill out all fields");
    try {
        int Customer_ID = -1;
        String Customer_Name = addCustomerName.getText();
        String Address = addCustomerAddress.getText();
        String Postal_Code = addCustomerPostalCode.getText();
        String Phone = addCustomerPhoneNumber.getText();
        int Division_ID = addCustomerDivisionComboBox.getSelectionModel().getSelectedItem().getDivision_ID();

        if(Customer_Name.isBlank() || Address.isBlank() || Postal_Code.isBlank() || Phone.isBlank()){alert.showAndWait();}
        else{Customer c = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID);
            dao.addNewCustomer(c);
           SceneChange scene = new SceneChange(); scene.changeScene(event, "view/MainScreen.fxml");}
    }catch (NullPointerException nullPointerException){
        nullPointerException.printStackTrace();
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setTitle("Missing Fields");
        alert1.setContentText("Please enter your country and state/province");
        alert1.showAndWait();
    }
    }
    @FXML void onActionSelectCountry(ActionEvent event) {
        addCustomerDivisionComboBox.setItems(divisionComboBox(addCustomerCountryComboBox.getSelectionModel().getSelectedItem()));
    }
    @FXML void onActionSelectDivision(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerCountryComboBox.setItems(countryComboBox);
    }
}
