package helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * lambda expression interface for converting from LocalDateTime to Timestamp.
 *
 */
@FunctionalInterface
public interface lambdaTwo {
    /**
     *
     * @param l
     * @return timestamp
     */
    Timestamp toTimestamp(LocalDateTime l);
}