package helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * lambda interface for converting a Timestamp into a LocalDateTime.
 */
@FunctionalInterface
public interface lambdaThree {
    /**
     *
     * @param t
     * @return LocalDateTime
     */
    LocalDateTime toLocalDateTime(Timestamp t);
}