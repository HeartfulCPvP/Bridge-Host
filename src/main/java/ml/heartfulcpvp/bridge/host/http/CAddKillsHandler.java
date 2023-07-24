package ml.heartfulcpvp.bridge.host.http;

import com.sun.net.httpserver.HttpExchange;
import ml.heartfulcpvp.bridge.host.Config;
import ml.heartfulcpvp.bridge.host.LoggingUtil;
import ml.heartfulcpvp.bridge.host.SkriptUtil;

import java.io.IOException;
import java.util.Locale;

public class CAddKillsHandler implements IHostHttpHandler {
    @Override
    public String getContextPath() {
        return Config.getConfig().getAddKillsContextPath();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var requestURI = exchange.getRequestURI().toString();
        exchange.getResponseHeaders().set("Content-Type", "application/octet-stream; charset=UTF-8");
        LoggingUtil.Log("Request accepted ; " + requestURI + " / Node: " + exchange.getRemoteAddress());

        var playerUUID = requestURI.substring(requestURI.lastIndexOf("/") + 1).toLowerCase(Locale.ROOT);
        var response = new byte[0];

        var variableName = Config.getConfig().getPlayerKillsVar().replace("${player}", playerUUID);
        var variableContent = SkriptUtil.getVar(variableName);

        if (variableContent != null) {
            if (variableContent instanceof Long deaths) {
                LoggingUtil.Log("kills: " + deaths  + " -> " + deaths + 1);
                SkriptUtil.setVar(variableName, deaths + 1);
            }
        } else {
            LoggingUtil.Log("kills: 0 -> 1");
            SkriptUtil.setVar(variableName, (long) 1);
        }

        exchange.sendResponseHeaders(200, response.length);
        var os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }
}
