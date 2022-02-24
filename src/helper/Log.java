package helper;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    Logger logger = Logger.getLogger("login_activity.txt");

    {
        try {
            FileHandler fh;
            fh = new FileHandler("login_activity.txt", true);
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            logger.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
