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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lambda.lambdaTwo;
import model.Country;
import model.Customer;
import model.Division;
import model.SceneChange;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that adds customers to the database.
 */
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
     * Method that takes a Country as input and returns an observable list of first level divisions.
     * @param country
     * @return Observable list
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
     * Brings user back to the main screen.
     * @param event
     * @throws IOException
     */
    @FXML void onActionCancelAddCustomer(ActionEvent event) throws IOException {
        change.sceneChange(event, "/view/MainScreen.fxml");
    }

    /**
     * Saves a new customer into the database.
     * @param event
     * @throws IOException
     */
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
            change.sceneChange(event, "/view/MainScreen.fxml");}
         }catch (NullPointerException nullPointerException){
            nullPointerException.printStackTrace();
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Missing Fields");
            alert1.setContentText("Please fill out all fields");
            alert1.showAndWait();
        }
    }

    /**
     * Method that fills a combo box of first level divisions.
     * @param event
     */
    @FXML void onActionSelectCountry(ActionEvent event) {
        addCustomerDivisionComboBox.setItems(divisionComboBox(addCustomerCountryComboBox.getSelectionModel().getSelectedItem()));
    }
    @FXML void onActionSelectDivision(ActionEvent event) {
    }

    /**
     * override initialize method.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerCountryComboBox.setItems(countryComboBox);
    }
}
