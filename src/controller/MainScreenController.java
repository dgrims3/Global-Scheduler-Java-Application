package controller;

        import DAO.CustomerDAO;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.RadioButton;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.ToggleGroup;
        import javafx.scene.control.cell.PropertyValueFactory;
        import model.Customer;

        import java.net.URL;
        import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

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
    @FXML private TableColumn<Customer, Integer> firstLevelDivision;

    public void fillCustomerTableView(){
        CustomerDAO dao = new CustomerDAO();
        customersTableView.setItems(dao.allCustomers());
        custID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        custName.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        custAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        custZipCode.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        custPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        firstLevelDivision.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
    }

    @FXML void OnActionViewReportsScreen(ActionEvent event) {
    }

    @FXML void onActionAddAppt(ActionEvent event) {

    }

    @FXML void onActionAddCustomer(ActionEvent event) {

    }

    @FXML void onActionCancelAppt(ActionEvent event) {

    }

    @FXML void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML void onActionLogOut(ActionEvent event) {

    }

    @FXML void onActionModifyAppt(ActionEvent event) {

    }

    @FXML void onActionModifyCustomer(ActionEvent event) {

    }

    @FXML void onActionViewApptsByAll(ActionEvent event) {

    }

    @FXML void onActionViewApptsByMonth(ActionEvent event) {

    }

    @FXML void onActionViewApptsByWeek(ActionEvent event) {

    }

    @FXML void onActionViewApptsCalender(ActionEvent event) {

    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        fillCustomerTableView();
    }
}
