package controller;

        import DAO.CustomerDAO;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.stage.Stage;
        import model.Customer;
        import model.SceneChange;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML private TableView<?> appointmentsTableView;
    @FXML private TableColumn<?, ?> apptID;
    @FXML private TableColumn<?, ?> apptTitle;
    @FXML private TableColumn<?, ?> apptDescription;
    @FXML private TableColumn<?, ?> apptLocation;
    @FXML private TableColumn<?, ?> apptContact;
    @FXML private TableColumn<?, ?> apptType;
    @FXML private TableColumn<?, ?> apptStartTime;
    @FXML private TableColumn<?, ?> apptEndTime;
    @FXML private TableColumn<?, ?> apptCustomerID;
    @FXML private TableColumn<?, ?> apptUserID;

    @FXML private RadioButton viewApptsByAllRdoBtn;
    @FXML private ToggleGroup ViewAppointmentsToggleGroup;
    @FXML private RadioButton viewApptsByWeekRdoBtn;
    @FXML private RadioButton viewApptsByMonthRdoBtn;
    @FXML private DatePicker viewApptsCalender;

    @FXML private TableView<Customer> customersTableView;
    @FXML private TableColumn<Customer, Integer> custID;
    @FXML private TableColumn<Customer, String> custName;
    @FXML private TableColumn<Customer, String > custAddress;
    @FXML private TableColumn<Customer, String> custZipCode;
    @FXML private TableColumn<Customer, String> custPhoneNumber;
    @FXML private TableColumn<Customer, String> firstLevelDivision;

    //Customer Functions
    public void fillCustomerTableView(){
        CustomerDAO dao = new CustomerDAO();

        customersTableView.setItems(dao.allCustomers());
        custID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        custName.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        custAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        custZipCode.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        custPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        firstLevelDivision.setCellValueFactory( new PropertyValueFactory<>("Division_ID"));
    }

    @FXML void onActionDeleteCustomer(ActionEvent event) {
        CustomerDAO dao = new CustomerDAO();
        dao.deleteCustomer(customersTableView.getSelectionModel().getSelectedItem());
        fillCustomerTableView();
    }
    @FXML void onActionModifyCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyCustomer.fxml"));
        loader.load();
        ModifyCustomerController MCController = loader.getController();
        if (!customersTableView.getSelectionModel().isEmpty()) {
            MCController.setText(customersTableView.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modify Customer error");
            alert.setContentText("Please Select a Customer");
            alert.showAndWait();
        }
    }

    @FXML void onActionAddCustomer(ActionEvent event) throws IOException {
        SceneChange scene = new SceneChange();
        scene.changeScene(event, "/view/Customer.fxml");
    }
    //Appointment Functions

    @FXML void onActionAddAppt(ActionEvent event) {

    }

    @FXML void onActionCancelAppt(ActionEvent event) {

    }

    @FXML void onActionModifyAppt(ActionEvent event) {

    }

    @FXML void onActionViewApptsByAll(ActionEvent event) {

    }

    @FXML void onActionViewApptsByMonth(ActionEvent event) {

    }

    @FXML void onActionViewApptsByWeek(ActionEvent event) {

    }

    @FXML void onActionViewApptsCalender(ActionEvent event) {

    }
    //Other Functions

    @FXML void OnActionViewReportsScreen(ActionEvent event) {
    }
    @FXML void onActionLogOut(ActionEvent event) {

    }
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        fillCustomerTableView();
    }
}
