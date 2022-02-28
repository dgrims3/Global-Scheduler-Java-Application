package helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@FunctionalInterface
public interface lambdaThree {
    LocalDateTime toLocalDateTime(Timestamp t);
}