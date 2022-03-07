package lambda;

import javafx.collections.ObservableList;

import java.time.LocalTime;

/**
 * lambda interface for converting a Timestamp into a LocalDateTime.
 */
@FunctionalInterface
public interface lambdaThree {
    /**
     * LAMBDA Expression: this expression takes two integers and returns a list of local time objects of 15 minute increments between the two integers.
     * @param i int
     * @param j int
     * @return ObservableList
     */
    ObservableList<LocalTime> timeFiller(int i, int j);
}