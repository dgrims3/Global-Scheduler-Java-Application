package helper;

import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * lambda interface for converting a Timestamp into a LocalDateTime.
 */
@FunctionalInterface
public interface lambdaThree {

    ObservableList<LocalTime> timeFiller(int i, int j);
}