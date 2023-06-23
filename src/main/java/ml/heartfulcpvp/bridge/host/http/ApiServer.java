package ml.heartfulcpvp.bridge.host.http;

import com.sun.net.httpserver.HttpServer;
import ml.heartfulcpvp.bridge.host.Config;
import ml.heartfulcpvp.bridge.host.LoggingUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class ApiServer {
    public static void start(int port) throws IOException {
        var server = HttpServer.create(new InetSocketAddress(port), 0);
        var logger = LoggingUtil.getLogger();
        var config = Config.getConfig();

        server.createContext(config.getVariableContextPath(), new CGetVariableHandler());

        server.setExecutor(null);
        server.start();
        logger.info("Listening on port " + port + ".");
    }
}
