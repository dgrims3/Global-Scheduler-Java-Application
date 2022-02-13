package helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeHelper {
    private static final ZoneId UTC = ZoneId.of("Etc/UTC");
    private static final ZoneId myZone = ZoneId.systemDefault();

    public static Timestamp timeConverter(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime.atZone(myZone).withZoneSameInstant(UTC).toLocalDateTime());
    }
}
