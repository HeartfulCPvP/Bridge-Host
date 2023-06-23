package ml.heartfulcpvp.bridge.host;

import java.util.logging.Logger;

public class LoggingUtil {
    private static Logger logger;

    public static void setLogger(Logger logger) {
        LoggingUtil.logger = logger;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void Log(String message) {
        logger.info(message);
    }
}