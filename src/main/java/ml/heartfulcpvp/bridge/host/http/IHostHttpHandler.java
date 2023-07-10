package ml.heartfulcpvp.bridge.host.http;

import com.sun.net.httpserver.HttpHandler;

public interface IHostHttpHandler extends HttpHandler {
    String getContextPath();
}
