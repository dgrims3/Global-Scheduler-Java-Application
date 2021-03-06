package controller;

import DAO.ReportsDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lambda.lambdaOne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import lambda.lambdaTwo;
import model.Appointment;
import model.SceneChange;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Controller for the reports class. This class generates business reports.
 */
public class ReportsController implements Initializable {
    @FXML private TableColumn<Appointment, String> apptContact;
    @FXML private TableColumn<Appointment, Integer> apptCustomerID;
    @FXML private TableColumn<Appointment, String> apptDescription;
    @FXML private TableColumn<Appointment, LocalDateTime> apptEndTime;
    @FXML private TableColumn<Appointment, Integer> apptID;
    @FXML private TableColumn<Appointment, LocalDateTime> apptStartTime;
    @FXML private TableColumn<Appointment, String> apptTitle;
    @FXML private TableColumn<Appointment, String> apptType;
    @FXML private TextArea avgDurationTextArea;
    @FXML private ComboBox<String> chooseContactComboBox;
    @FXML private TextArea numberOfApptsTextArea;
    @FXML private TableView<Appointment> scheduleTableView;
    @FXML private ToggleGroup numberToggleGroup;
    /**
     * This lambda expression takes an array of integers and returns the average of all numbers in the array. This is used to find the average length of all appointments.
     * @param int i
     * @returns int
     *
     */
    lambdaOne avg = i -> {
        int l =0;
        for (int j = 0; j < i.size(); j++) {
            l = l+i.get(j);
        }
        l = l/i.size();
        return l;
    };
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

    ReportsDAO dao = new ReportsDAO();

    /**
     * Brings user back to the main screen of application.
     * @param event
     * @throws IOException
     */
    @FXML void onActionBackToMain(ActionEvent event) throws IOException {
       change.sceneChange(event, "/view/MainScreen.fxml");
    }

    /**
     * Radio button that displays number of appointments per month.
     * @param event
     */
    @FXML void byMonthRdoBtn(ActionEvent event) {
        numberOfApptsTextArea.clear();
        monthCountSetText();
    }

    /**
     * Radio button that displays number of appointments by type.
     * @param event
     */
    @FXML
    void byTypeRdoBttn(ActionEvent event) {
        numberOfApptsTextArea.clear();
        typeCountSetText();
    }

    /**
     * fills combo box with all contacts.
     * @param event
     */
    @FXML
    void onActionChooseContactComboBox(ActionEvent event) {
        scheduleTableView.setItems(dao.contactSchedules(dao.getContactID(chooseContactComboBox.getSelectionModel().getSelectedItem())));
        apptID.setCellValueFactory(new PropertyValueFactory<>("appointment_ID"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptContact.setCellValueFactory(new PropertyValueFactory<>("contact_Name"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartTime.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
    }

    /**
     * fills text area with average appt time.
     * The lambda expression is used in the method
     */
    public void fillAvgTimeTextArea(){
        avgDurationTextArea.setText("Avg Length of all appointments: \n");
        avgDurationTextArea.appendText(String.valueOf(avg.average(dao.apptLength()))+" minutes");
    }

    /**
     * fills text area with number of appointments by type.
     */
    public void typeCountSetText(){
        dao.typeCount();
        numberOfApptsTextArea.setText("Number of Appts by type are \n");
        for(int i = 0; i < dao.typeCount().size(); i++) {
            numberOfApptsTextArea.appendText(String.valueOf(dao.typeCount().get(i)));
            numberOfApptsTextArea.appendText("\n");
        }
    }

    /**
     * function that counts how many appointments take place each month.
     */
    public void monthCountSetText(){
        dao.filterByMonth();
        numberOfApptsTextArea.appendText("January = "+dao.filterByMonth()[0]+"\n");
        numberOfApptsTextArea.appendText("February = " +dao.filterByMonth()[1]+"\n");
        numberOfApptsTextArea.appendText("March = " +dao.filterByMonth()[2]+"\n");
        numberOfApptsTextArea.appendText("April = " +dao.filterByMonth()[3]+"\n");
        numberOfApptsTextArea.appendText("May = " +dao.filterByMonth()[4]+"\n");
        numberOfApptsTextArea.appendText("June = " +dao.filterByMonth()[5]+"\n");
        numberOfApptsTextArea.appendText("July = " +dao.filterByMonth()[6]+"\n");
        numberOfApptsTextArea.appendText("August = " +dao.filterByMonth()[7]+"\n");
        numberOfApptsTextArea.appendText("Sep = " +dao.filterByMonth()[8]+"\n");
        numberOfApptsTextArea.appendText("October = " +dao.filterByMonth()[9]+"\n");
        numberOfApptsTextArea.appendText("November = " +dao.filterByMonth()[10]+"\n");
        numberOfApptsTextArea.appendText("December = " +dao.filterByMonth()[11]+"\n");

    }

    /**
     *Override initialize method.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseContactComboBox.setItems(dao.allContacts());
        fillAvgTimeTextArea();
    }
}
