package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogCollector {

    private static final Logger logger =
            LoggerFactory.getLogger(LogCollector.class);

    private static final ThreadLocal<StringBuilder> logs =
            ThreadLocal.withInitial(StringBuilder::new);

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(LogLevel level, String message) {

        String formattedMessage = format(level, message);

        // 1️⃣ Store for Cucumber report (thread-safe)
        logs.get().append(formattedMessage).append("\n");

        // 2️⃣ Log to SLF4J (console + file if configured)
        switch (level) {
            case INFO -> logger.info(message);
            case ERROR -> logger.error(message);
            case WARN -> logger.warn(message);
            case DEBUG -> logger.debug(message);
        }
    }

    private static String format(LogLevel level, String message) {
        return formatter.format(LocalDateTime.now())
                + " [" + level + "] "
                + message;
    }

    public static String getLogs() {
        return logs.get().toString();
    }

    public static void clear() {
        logs.remove();
    }

    public static void info(String message) {
        log(LogLevel.INFO, message);
    }

    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public static void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public static void debug(String message) {
        log(LogLevel.DEBUG, message);
    }
}
