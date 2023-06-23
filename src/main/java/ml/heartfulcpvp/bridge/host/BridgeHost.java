package ml.heartfulcpvp.bridge.host;

import ml.heartfulcpvp.bridge.host.http.ApiServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class BridgeHost extends JavaPlugin {
    @Override
    public void onEnable() {
        LoggingUtil.setLogger(this.getLogger());

        try {
            initConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            ApiServer.start(Config.getConfig().getPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initConfig() throws IOException {
        Config conf;

        if (!Config.isConfigExists()) {
            conf = Config.getDefaultConfig();
            conf.saveConfig();
        } else {
            conf = Config.loadConfigFile();
        }

        Config.setConfig(conf);
    }
}
