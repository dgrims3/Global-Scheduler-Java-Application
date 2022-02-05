package model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChange {
    public void changeScene (ActionEvent event, String string) throws IOException {
        Stage stage;
        Parent scene;
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(string));
        stage.setScene(new javafx.scene.Scene(scene));
        stage.show();
    }
}
