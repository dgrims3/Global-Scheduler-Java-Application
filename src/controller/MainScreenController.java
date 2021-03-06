package controller;

        import DAO.AppointmentDAO;
        import DAO.CountryDAO;
        import DAO.CustomerDAO;
        import DAO.DivisionDAO;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.stage.Stage;
        import lambda.lambdaTwo;
        import model.Appointment;
        import model.Customer;
        import model.SceneChange;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.SQLException;
        import java.sql.Timestamp;
        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.util.Optional;
        import java.util.ResourceBundle;

/**
 * Controller for the main screen of the application.
 */
public class MainScreenController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML private TableView<Appointment> appointmentsTableView;
    @FXML private TableColumn<Appointment, Integer> apptID;
    @FXML private TableColumn<Appointment, String> apptTitle;
    @FXML private TableColumn<Appointment, String> apptDescription;
    @FXML private TableColumn<Appointment, String> apptLocation;
    @FXML private TableColumn<Appointment, Integer> apptContact;
    @FXML private TableColumn<Appointment, String> apptType;
    @FXML private TableColumn<Appointment, Timestamp> apptStartTime;
    @FXML private TableColumn<Appointment, Timestamp> apptEndTime;
    @FXML private TableColumn<Appointment, Integer> apptCustomerID;
    @FXML private TableColumn<Appointment, Integer> apptUserID;
    @FXML private TableView<Customer> customersTableView;
    @FXML private TableColumn<Customer, Integer> custID;
    @FXML private TableColumn<Customer, String> custName;
    @FXML private TableColumn<Customer, String > custAddress;
    @FXML private TableColumn<Customer, String> custZipCode;
    @FXML private TableColumn<Customer, String> custPhoneNumber;
    @FXML private TableColumn<Customer, String> firstLevelDivision;

    //Customer Functions

    /**
     * Method to fill the table view with customer objects.
     */
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

    /**
     * Method for deleting a customer from the database.
     * @param event
     */
    @FXML void onActionDeleteCustomer(ActionEvent event) {
        CustomerDAO dao = new CustomerDAO();

        if (!customersTableView.getSelectionModel().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setContentText("Are you sure you want to delete " + customersTableView.getSelectionModel().getSelectedItem().getCustomer_Name() + " and all their appointments?");
        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
            dao.deleteCustomerAppointment(customersTableView.getSelectionModel().getSelectedItem().getCustomer_ID());
            dao.deleteCustomer(customersTableView.getSelectionModel().getSelectedItem());
            fillAppointmentTableView();
            fillCustomerTableView();
                }
        }
        else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Delete Customer Error");
        alert.setContentText("Please Select a Customer");
        alert.showAndWait();
        }
    }

    /**
     * Method for modifying a customer object in the database.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML void onActionModifyCustomer(ActionEvent event) throws IOException, SQLException {
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

    /**
     * Method used to bring user to the add customer screen.
     * @param event
     * @throws IOException
     */
    @FXML void onActionAddCustomer(ActionEvent event) throws IOException {
       change.sceneChange(event, "/view/Customer.fxml");
    }
    //Appointment Functions

    /**
     * Method used to fill the table view with appointment objects from the database.
     *
     */
    public void fillAppointmentTableView(){
        AppointmentDAO dao = new AppointmentDAO();
        appointmentsTableView.setItems(dao.allAppointments());
        apptID.setCellValueFactory(new PropertyValueFactory<>("appointment_ID"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptContact.setCellValueFactory(new PropertyValueFactory<>("contact_Name"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartTime.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        apptUserID.setCellValueFactory(new PropertyValueFactory<>("user_ID"));
    }

    /**
     * Method that brings the user to the add appointment screen.
     * @param event
     * @throws IOException
     */
    @FXML void onActionAddAppt(ActionEvent event) throws IOException {
        change.sceneChange(event,  "/view/AddAppointment.fxml");
    }

    /**
     * Method used to delete an appointment from a database.
     * @param event
     */
    @FXML void onActionCancelAppt(ActionEvent event) {
        AppointmentDAO dao = new AppointmentDAO();

        if (!appointmentsTableView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setContentText("Cancel Appt "+appointmentsTableView.getSelectionModel().getSelectedItem().getAppointment_ID()+", "+appointmentsTableView.getSelectionModel().getSelectedItem().getDescription()+"?" );
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                dao.deleteAppointment(appointmentsTableView.getSelectionModel().getSelectedItem().getAppointment_ID());
                fillAppointmentTableView();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Appointment Error");
            alert.setContentText("Please Select an appointment");
            alert.showAndWait();
        }
    }

    /**
     * Method that takes a selected appointment and brings it to the modify appointment screen.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML void onActionModifyAppt(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AddAppointment.fxml"));
        loader.load();
        AddAppointmentController AAController = loader.getController();
        if (!appointmentsTableView.getSelectionModel().isEmpty()) {
            AAController.setText(appointmentsTableView.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modify Appointment error");
            alert.setContentText("Please Select an appointment");
            alert.showAndWait();
        }
    }

    /**
     * Fills table view with all appointments.
     * @param event
     */
    @FXML void onActionViewApptsByAll(ActionEvent event) {
        AppointmentDAO dao = new AppointmentDAO();
        appointmentsTableView.setItems(dao.allAppointments());
    }

    /**
     * Fills table view with appointments of current month.
     * @param event
     */
    @FXML void onActionViewApptsByMonth(ActionEvent event) {
        AppointmentDAO dao = new AppointmentDAO();
        appointmentsTableView.setItems(dao.filterByMonth(LocalDateTime.now().getMonthValue()));

    }

    /**
     *Fills table view with appointments of current week.
     * @param event
     */
    @FXML void onActionViewApptsByWeek(ActionEvent event) {
        AppointmentDAO dao = new AppointmentDAO();
        appointmentsTableView.setItems(dao.filterByWeek());
    }

    //Other Functions

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
     * Brings user to the business reports page.
     * @param event
     * @throws IOException
     */
    @FXML void OnActionViewReportsScreen(ActionEvent event) throws IOException {
      change.sceneChange(event,  "/view/Reports.fxml");
    }

    /**
     * Logs out the user from the application.
     * @param event
     */
    @FXML void onActionLogOut(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Overload initialize method.
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        fillCustomerTableView();
        fillAppointmentTableView();
    }
}
