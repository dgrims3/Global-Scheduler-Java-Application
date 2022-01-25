package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {



        @FXML
        private TextField loginPassword;

        @FXML
        private TextField loginUserName;

        @FXML
        private Button loginButton;

        @FXML
        private Button quitButton;

        @FXML
        void onActionLoginToMain(ActionEvent event) {

        }

        @FXML
        void onActionQuitProgram(ActionEvent event) {
                System.exit(0);
        }

}

