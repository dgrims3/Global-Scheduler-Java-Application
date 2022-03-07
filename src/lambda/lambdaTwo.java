package lambda;


import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * lambda interface for changing scenes in the application.
 *
 */
@FunctionalInterface
/**
 * LAMBDA Expression: This lambda improves the code by making the process of changing scenes less verbose.
 * @param e ActionEvent
 * @param s String
 */
public interface lambdaTwo {
   void sceneChange (ActionEvent e, String s) throws IOException;

}