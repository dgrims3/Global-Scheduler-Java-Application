package controller;

import DAO.UserDAO;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LoginController implements Initializable {

        Stage stage;
        Parent scene;

        ResourceBundle rb = ResourceBundle.getBundle("resourceBundle/rb", Locale.getDefault());

        @FXML
        private TextField loginPassword, loginUserName;

        @FXML
        private Label loginTitle, loginZone;

        @FXML
        private Button loginButton, quitButton;



    public void setText() {
                loginPassword.setPromptText(rb.getString("Password"));
                loginTitle.setText(rb.getString("Title"));
                loginUserName.setPromptText(rb.getString("UserName"));
                loginButton.setText(rb.getString("Login"));
                quitButton.setText(rb.getString("Quit"));
                loginZone.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
        }

        public boolean userLogin(String username, String password){
               boolean bool = false;
               UserDAO dao = new UserDAO();
               dao.fillList();
               if (username.isBlank() && !password.isBlank()){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle(rb.getString("AlertTitle"));
                   alert.setContentText(rb.getString("EmptyUsername"));
                   alert.showAndWait();
                }
                if (password.isBlank() && !username.isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("AlertTitle"));
                alert.setContentText(rb.getString("EmptyPassword"));
                alert.showAndWait();
                 }
                if (password.isBlank() && username.isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("AlertTitle"));
                alert.setContentText(rb.getString("EmptyAll"));
                alert.showAndWait();}
                for (User u: dao.getAllUsers()) {
                       if (username.equals(u.getUser_name()) && password.equals(u.getPassword())) {
                           dao.appointmentAlert(u);
                               bool = true;
                       }
                 }return bool;
       }

        @FXML
        void onActionLoginToMain(ActionEvent event) throws IOException {
          if (userLogin(loginUserName.getText(), loginPassword.getText())){
              UserDAO dao = new UserDAO();
              stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
              scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
              stage.setScene(new Scene(scene));
              stage.show();
            }
          else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle(rb.getString("AlertTitle"));
              alert.setContentText(rb.getString("Alert"));
              alert.showAndWait();
          }
        }

        @FXML
        void onActionQuitProgram(ActionEvent event) {
                System.exit(0);
        }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
                setText();
        }

}

