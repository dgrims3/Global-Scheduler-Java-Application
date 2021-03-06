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
import lambda.lambdaTwo;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TimeZone;

/**
 * Controller screen that allows a user to log in to the application.
 */
public class LoginController implements Initializable  {
        Stage stage;
        Parent scene;
        ResourceBundle rb = ResourceBundle.getBundle("resourceBundle/rb", Locale.getDefault());
        String fileName = "login_activity.txt";
        @FXML private TextField loginPassword, loginUserName;
        @FXML private Label loginTitle, loginZone;
        @FXML private Button loginButton, quitButton;
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
     * Method that writes log in attempts to a text file.
     * @param userName
     * @param password
     * @param bool
     */
    public void logInLog (String userName, String password, Boolean bool) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.append(Timestamp.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("Etc/UTC")).toLocalDateTime())+" "+userName+" "+password+" "+ "login attempt was successful = "+bool + "\n");
        pw.flush();
        pw.close();
    }


    /**
     * Sets the text for the log in screen. Depending on the users Locale the words will be in either French or English.
     */
    public void setText() {
                loginPassword.setPromptText(rb.getString("Password"));
                loginTitle.setText(rb.getString("Title"));
                loginUserName.setPromptText(rb.getString("UserName"));
                loginButton.setText(rb.getString("Login"));
                quitButton.setText(rb.getString("Quit"));
                loginZone.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
    }

    /**
     * Method that checks if username and password match a user from the database.
     * @param username
     * @param password
     * @return boolean
     */
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

    /**
     * takes a user to the main screen on successful login.
     * @param event
     * @throws IOException
     */
        @FXML void onActionLoginToMain(ActionEvent event) throws IOException {
          if (userLogin(loginUserName.getText(), loginPassword.getText())){
              logInLog(loginUserName.getText(), loginPassword.getText(), true);

              change.sceneChange(event, "/view/MainScreen.fxml");
            }
          else {
              logInLog(loginUserName.getText(), loginPassword.getText(), false);
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle(rb.getString("AlertTitle"));
              alert.setContentText(rb.getString("Alert"));
              alert.showAndWait();
          }
        }

    /**
     * Method that quits the program.
     * @param event
     */
        @FXML
        void onActionQuitProgram(ActionEvent event) {
                System.exit(0);
        }

    /**
     * Override initialize method.
     * @param url
     * @param resourceBundle
     */
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
                setText();
        }

}

