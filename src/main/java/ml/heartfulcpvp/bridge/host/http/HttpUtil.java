package ml.heartfulcpvp.bridge.host.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpUtil {
    public static String getPostPayload(HttpExchange exchange) throws IOException {
        var requestBody = exchange.getRequestBody();

        var reader = new BufferedReader(new InputStreamReader(requestBody));
        var payload = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            payload.append(line);
        }

        return payload.toString();
    }
}
