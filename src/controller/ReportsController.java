package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    @FXML private ComboBox<?> chooseContactComboBox;
    @FXML private TextArea numberOfApptsTextArea;
    @FXML private TableView<?> scheduleTableView;
    @FXML void onActionBackToMain(ActionEvent event) {

    }

    @FXML
    void onActionChooseContactComboBox(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
