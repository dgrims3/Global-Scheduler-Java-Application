package helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

@FunctionalInterface
public interface lambdaTwo {
    Timestamp toTimestamp(LocalDateTime l);
}