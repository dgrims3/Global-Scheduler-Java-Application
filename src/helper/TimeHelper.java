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

    /*   Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        LocalDateTime ldt = ts.toLocalDateTime();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtIn = utczdt.toLocalDateTime();

        ZonedDateTime zdtOut = ldtIn.atZone(ZoneId.of("UTC"));
        ZonedDateTime zdtOutToLocalTZ = zdtOut.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime ldtOutFinal = zdtOutToLocalTZ.toLocalDateTime();*/
}
