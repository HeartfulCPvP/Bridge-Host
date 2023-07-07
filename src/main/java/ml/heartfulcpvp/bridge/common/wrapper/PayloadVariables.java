package ml.heartfulcpvp.bridge.common.wrapper;

import com.google.gson.Gson;

public class PayloadVariables {
    private String[] variables;

    public String[] getVariables() {
        return variables;
    }

    public void setVariables(String[] variables) {
        this.variables = variables;
    }

    public String toJsonStr() {
        var gson = new Gson();
        return gson.toJson(this);
    }

    public static PayloadVariables fromJsonStr(String jsonStr) {
        var gson = new Gson();
        return gson.fromJson(jsonStr, PayloadVariables.class);
    }
}
