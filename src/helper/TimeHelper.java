package helper;

import model.Appointment;
import model.Country;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public static void appointmentAlert(){
        LocalTime startTime = LocalTime.of(14, 0);

    }
}
