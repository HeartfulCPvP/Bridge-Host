package ml.heartfulcpvp.bridge.host;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.function.Function;
import ch.njol.skript.variables.Variables;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SkriptUtil {
    @Nullable
    public static Object getVar(String varName){
        return Variables.getVariable(varName, null, false);
    }

    public static void setVar(String varName, String value){
        Variables.setVariable(varName, value, null, false);
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        ObjectOutputStream oStream = new ObjectOutputStream(bStream);
        oStream.writeObject(obj);

        return bStream.toByteArray();
    }
}