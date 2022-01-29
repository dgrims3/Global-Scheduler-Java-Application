package controller;

import DAO.UserDAO;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

        Stage stage;
        Parent scene;

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



        public boolean userLogin(String username, String password){
                        UserDAO dao = new UserDAO();
                        dao.fillList();
                        for (User u: dao.getAllUsers()) {
                                if (u.getUser_name() == username && u.getPassword() == password) {
                                        return false;
                                }
                        }

                return false;
        }


        @FXML
        void onActionLoginToMain(ActionEvent event) throws IOException {
            if (userLogin(loginUserName.getText(), loginPassword.getText()) == true){
                    System.out.println("success!!");
                    /*stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();*/
            }else System.out.println("boo");


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

