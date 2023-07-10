package ml.heartfulcpvp.bridge.host.http;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ml.heartfulcpvp.bridge.common.StreamObj;
import ml.heartfulcpvp.bridge.common.wrapper.PayloadVariables;
import ml.heartfulcpvp.bridge.common.wrapper.ResponseVariables;
import ml.heartfulcpvp.bridge.host.Config;
import ml.heartfulcpvp.bridge.host.LoggingUtil;
import ml.heartfulcpvp.bridge.host.SkriptUtil;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class CGetVariablesHandler implements IHostHttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var payload = HttpUtil.getPostPayload(exchange);
        exchange.getResponseHeaders().set("Content-Type", "application/octet-stream; charset=UTF-8");

        var response = new byte[0];

        var payloadObj = PayloadVariables.fromJsonStr(payload);

        var varResponse = new ResponseVariables();

        for (var variable : payloadObj.getVariables()) {
            var obj = SkriptUtil.getVar(variable);
            if (obj == null) {
                LoggingUtil.Log(variable + " is null");
            }

            varResponse.addObj(variable, obj);
        }

        try {
            var obj = new StreamObj(varResponse);
            response = SerializationUtils.serialize(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        exchange.sendResponseHeaders(200, response.length);
        var os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }

    @Override
    public String getContextPath() {
        return Config.getConfig().getVariablesContextPath();
    }
}
