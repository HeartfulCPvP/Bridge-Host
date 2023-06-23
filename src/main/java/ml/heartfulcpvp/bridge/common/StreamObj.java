package ml.heartfulcpvp.bridge.common;

import java.io.Serializable;

public class StreamObj implements Serializable {
    private Object obj;

    public StreamObj(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }
}
