package ml.heartfulcpvp.bridge.host;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Config {
    private static final String CONFIG_DIRECTORY_PATH = "./plugins/HeartfulCPvP/bridge-host";
    private static final String CONFIG_FILE_PATH = "./plugins/HeartfulCPvP/bridge-host/config.json";
    public static final int DEFAULT_PORT = 8080;
    public static final String DEFAULT_VARIABLE_CONTEXT_PATH = "/variable/";
    public static final String DEFAULT_ADD_KILLS_CONTEXT_PATH = "/addkills/";
    public static final String DEFAULT_ADD_DEATHS_CONTEXT_PATH = "/adddeaths/";
    public static final String DEFAULT_PLAYER_DEATHS_VAR = "${player}::hoge";
    public static final String DEFAULT_PLAYER_KILLS_VAR = "${player}::fuga";

    private int port;
    private String variableContextPath;
    private String addKillsContextPath;
    private String addDeathsContextPath;
    private String playerDeathsVar;
    private String playerKillsVar;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getVariableContextPath() {
        return variableContextPath;
    }

    public void setVariableContextPath(String variableContextPath) {
        this.variableContextPath = variableContextPath;
    }

    public String getAddKillsContextPath() {
        return addKillsContextPath;
    }

    public void setAddKillsContextPath(String addKillsContextPath) {
        this.addKillsContextPath = addKillsContextPath;
    }

    public String getAddDeathsContextPath() {
        return addDeathsContextPath;
    }

    public void setAddDeathsContextPath(String addDeathsContextPath) {
        this.addDeathsContextPath = addDeathsContextPath;
    }

    public String getPlayerDeathsVar() {
        return playerDeathsVar;
    }

    public void setPlayerDeathsVar(String playerDeathsVar) {
        this.playerDeathsVar = playerDeathsVar;
    }

    public String getPlayerKillsVar() {
        return playerKillsVar;
    }

    public void setPlayerKillsVar(String playerKillsVar) {
        this.playerKillsVar = playerKillsVar;
    }

    private static Config config;

    public void saveConfig() throws IOException {
        var gson = new Gson();
        var jsonStr = gson.toJson(this);

        var dir = new File(CONFIG_DIRECTORY_PATH);
        var file = new File(CONFIG_FILE_PATH);

        if (!dir.exists()) {
            dir.mkdir();
        }

        if (file.exists()) {
            file.delete();
        }

        Files.createFile(file.toPath());
        Files.writeString(file.toPath(), jsonStr);
    }

    public static boolean isConfigExists() {
        var file = new File(CONFIG_FILE_PATH);
        return file.exists();
    }

    public static Config loadConfigFile() throws IOException {
        var file = new File(CONFIG_FILE_PATH);
        var content = Files.readString(file.toPath());
        var gson = new Gson();
        var conf = gson.fromJson(content, Config.class);

        return conf;
    }

    public static Config getDefaultConfig() {
        var conf = new Config();

        conf.setPort(DEFAULT_PORT);
        conf.setAddDeathsContextPath(DEFAULT_ADD_DEATHS_CONTEXT_PATH);
        conf.setAddKillsContextPath(DEFAULT_ADD_KILLS_CONTEXT_PATH);
        conf.setPlayerDeathsVar(DEFAULT_PLAYER_DEATHS_VAR);
        conf.setPlayerKillsVar(DEFAULT_PLAYER_KILLS_VAR);
        conf.setVariableContextPath(DEFAULT_VARIABLE_CONTEXT_PATH);

        return conf;
    }

    public static void setConfig(Config config) {
        Config.config = config;
    }

    public static Config getConfig() {
        return config;
    }
}