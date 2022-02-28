package model;

import DAO.UserDAO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import DAO.JDBC;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Locale;

/**
 * Main class. Starting point of application.
 */
public class Main extends Application {
ZoneId zoneId;

    /**
     * Loads the application.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 475));
        primaryStage.show();
    }

    /**
     * Connects the driver to the database.
     * @param args
     */
    public static void main(String[] args) {
        JDBC.makeConnection();
        launch(args);
    }
}
