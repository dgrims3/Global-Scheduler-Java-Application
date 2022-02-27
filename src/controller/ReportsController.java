package controller;

import DAO.ReportsDAO;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import helper.lambdaOne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.SceneChange;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.io.IOException;
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
    @FXML private ToggleGroup numberToggleGroup;
    lambdaOne avg = i -> {
        int l =0;
        for (int j = 0; j < i.size(); j++) {
            l = l+i.get(j);
        }
        l = l/i.size();
        return l;
    };

    ReportsDAO dao = new ReportsDAO();

    @FXML void onActionBackToMain(ActionEvent event) throws IOException {
        SceneChange scene = new SceneChange();
        scene.changeScene(event, "/view/MainScreen.fxml");
    }
    @FXML
    void byMonthRdoBtn(ActionEvent event) {
        numberOfApptsTextArea.clear();
        monthCountSetText();
    }

    @FXML
    void byTypeRdoBttn(ActionEvent event) {
        numberOfApptsTextArea.clear();
        typeCountSetText();
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
    public void fillAvgTimeTextArea(){
        avgDurationTextArea.setText("Avg Length of all appointments: \n");
        avgDurationTextArea.appendText(String.valueOf(avg.average(dao.apptLength()))+" minutes");
    }
    public void typeCountSetText(){
        dao.typeCount();
        numberOfApptsTextArea.setText("Number of Appts by type are \n");
        for(int i = 0; i < dao.typeCount().size(); i++) {
            numberOfApptsTextArea.appendText(String.valueOf(dao.typeCount().get(i)));
            numberOfApptsTextArea.appendText("\n");
        }
    }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseContactComboBox.setItems(dao.allContacts());
        fillAvgTimeTextArea();
    }
}
