package controller;

import DAO.ReportsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

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
    ReportsDAO dao = new ReportsDAO();

    @FXML void onActionBackToMain(ActionEvent event) {

    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseContactComboBox.setItems(dao.allContacts());
        //dao.typeCount();
    }
}
