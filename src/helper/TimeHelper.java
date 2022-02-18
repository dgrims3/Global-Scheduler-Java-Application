package helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeHelper {
    private static final ZoneId UTC = ZoneId.of("Etc/UTC");
    private static final ZoneId myZone = ZoneId.systemDefault();

    public static Timestamp toTimestampConverter(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime.atZone(myZone).withZoneSameInstant(UTC).toLocalDateTime());
    }
    public static LocalDateTime toLocalDateTimeConverter(Timestamp timestamp){
        return timestamp.toLocalDateTime().atZone(UTC).withZoneSameInstant(myZone).toLocalDateTime();
    }
}
