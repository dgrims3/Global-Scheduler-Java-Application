package helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * lambda interface for converting a Timestamp into a LocalDateTime.
 */
@FunctionalInterface
public interface lambdaThree {
    /**
     * This Lambda takes in a timestamp and returns a localdatetime.
     * @param t
     * @return LocalDateTime
     */
    LocalDateTime toLocalDateTime(Timestamp t);
}