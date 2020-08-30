package org.benti.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleLogger {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static void log(String message) {
        System.out.println(DATE_TIME_FORMATTER.format(LocalDateTime.now()) + " - " + message);
    }

}
