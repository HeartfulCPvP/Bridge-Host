package ml.heartfulcpvp.bridge.host.http;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ml.heartfulcpvp.bridge.host.Config;
import ml.heartfulcpvp.bridge.host.LoggingUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class ApiServer {
    private static final IHostHttpHandler[] handlers = {
            new CGetVariableHandler(),
            new CGetVariablesHandler(),
            new CAddDeathsHandler(),
            new CAddKillsHandler()
    };

    public static void start(int port) throws IOException {
        var server = HttpServer.create(new InetSocketAddress(port), 0);
        var logger = LoggingUtil.getLogger();

        for (var handler : handlers) {
            logger.info("Initializing http handler : " + handler.getClass().getSimpleName() + " ; " + handler.getContextPath());
            server.createContext(handler.getContextPath(), handler);
        }

        server.setExecutor(null);
        server.start();
        logger.info("Listening on port " + port + ".");
    }
}
