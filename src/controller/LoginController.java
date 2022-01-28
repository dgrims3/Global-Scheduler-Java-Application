package controller;

import DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

        ResourceBundle rb = ResourceBundle.getBundle("resourceBundle/rb", Locale.getDefault());

        @FXML
        private TextField loginPassword;

        @FXML
        private Label loginTitle;

        @FXML
        private TextField loginUserName;

        @FXML
        private Button loginButton;

        @FXML
        private Button quitButton;


        public void setText() {
                loginPassword.setText(rb.getString("Password"));
                loginTitle.setText(rb.getString("Title"));
                loginUserName.setText(rb.getString("UserName"));
                loginButton.setText(rb.getString("Login"));
                quitButton.setText(rb.getString("Quit"));
        }


        @FXML
        void onActionLoginToMain(ActionEvent event) {

        }

        @FXML
        void onActionQuitProgram(ActionEvent event) {
                System.exit(0);
        }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
                setText();
                UserDAO dao = new UserDAO();
                dao.initialize();
        }

}

