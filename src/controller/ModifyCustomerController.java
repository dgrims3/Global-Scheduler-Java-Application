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
    @FXML
    void onActionCancelAddCustomer(ActionEvent event) throws IOException {
        SceneChange scene = new SceneChange();
        scene.changeScene(event, "/view/MainScreen.fxml");
    }

  /*  @FXML void onActionReset(ActionEvent event) throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        setText(dao.refreshCustomer(Integer.parseInt(addCustomerId.getText())));
    }*/

    @FXML void onActionSaveAddCustomer(ActionEvent event) throws SQLException, IOException {
        CustomerDAO dao = new CustomerDAO();
        SceneChange scene = new SceneChange();
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
        scene.changeScene(event, "/view/MainScreen.fxml");}

    }

    @FXML void onActionSelectCountry(ActionEvent event) {
        addCustomerDivisionComboBox.setItems(divisionComboBox(addCustomerCountryComboBox.getSelectionModel().getSelectedItem()));
    }

    @FXML void onActionSelectDivision(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerCountryComboBox.setItems(countryComboBox);
    }

}