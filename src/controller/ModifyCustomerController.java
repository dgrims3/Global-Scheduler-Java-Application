package controller;

import DAO.CountryDAO;
import DAO.DivisionDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import model.Country;
import model.Customer;
import model.Division;
import model.SceneChange;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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


    public void setText(Customer customer) throws SQLException {
        addCustomerId.setText(String.valueOf(customer.getCustomer_ID()));
        addCustomerName.setText(customer.getCustomer_Name());
        addCustomerAddress.setText(customer.getAddress());
        addCustomerPostalCode.setText(customer.getPostal_Code());
        addCustomerPhoneNumber.setText(customer.getPhone());
        addCustomerDivisionComboBox.setValue(divDao.getDivisionForModifyCustomer(customer.getDivision_ID()));
        addCustomerCountryComboBox.setValue(dao.getCountryForModifyCustomer(customer.getDivision_ID()));
    }
    @FXML
    void onActionCancelAddCustomer(ActionEvent event) throws IOException {
        SceneChange scene = new SceneChange();
        scene.changeScene(event, "/view/MainScreen.fxml");
    }

    @FXML
    void onActionSaveAddCustomer(ActionEvent event) {

    }

    @FXML
    void onActionSelectCountry(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionSelectDivision(ActionEvent actionEvent) {
        addCustomerCountryComboBox.setItems(countryComboBox);

    }
}