package ml.heartfulcpvp.bridge.common.wrapper;

import java.util.Map;

public class ResponseVariables {
    private Map<String, Object> varMap;

    public Map<String, Object> getVarMap() {
        return varMap;
    }

    public void setVarMap(Map<String, Object> varMap) {
        this.varMap = varMap;
    }

    public void addObj(String flag, Object obj) {
        varMap.put(flag, obj);
    }
}
