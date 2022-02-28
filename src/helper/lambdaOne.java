package helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * lambda interface for taking an array and computing the average.
 */
@FunctionalInterface
public interface lambdaOne {
    /**
     *
     * @param i
     * @return
     */
    int average(ArrayList<Integer> i);
}