package model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class for changing scenes. This was made to make scene changes less verbose.
 */
public class SceneChange {
    /**
     * Method that is used to change screens in the application.
     * @param event
     * @param string
     * @throws IOException
     */
    public void changeScene (ActionEvent event, String string) throws IOException {
        Stage stage;
        Parent scene;
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(string));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
