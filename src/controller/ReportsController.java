package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class ReportsController {

    @FXML
    private TableColumn<?, ?> apptContact;

    @FXML
    private TableColumn<?, ?> apptCustomerID;

    @FXML
    private TableColumn<?, ?> apptDescription;

    @FXML
    private TableColumn<?, ?> apptEndTime;

    @FXML
    private TableColumn<?, ?> apptID;

    @FXML
    private TableColumn<?, ?> apptStartTime;

    @FXML
    private TableColumn<?, ?> apptTitle;

    @FXML
    private TableColumn<?, ?> apptType;

    @FXML
    private TextArea avgDurationTextArea;

    @FXML
    private ComboBox<?> chooseContactComboBox;

    @FXML
    private TextArea numberOfApptsTextArea;

    @FXML
    private TableView<?> scheduleTableView;

    @FXML
    void onActionBackToMain(ActionEvent event) {

    }

    @FXML
    void onActionChooseContactComboBox(ActionEvent event) {

    }

}
