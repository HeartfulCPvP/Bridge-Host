package ml.heartfulcpvp.bridge.common.wrapper;

import com.google.gson.Gson;

/**
 * 各サーバーのプレイヤー一覧を送るためのやつ
 */
public class PayloadPlayerList {
    private String serverName; // 識別子
    private String[] players; // プレイヤーのリスト

    public PayloadPlayerList(String serverName, String[] players) {
        this.serverName = serverName;
        this.players = players;
    }

    public String getServerName() {
        return serverName;
    }

    public String[] getPlayers() {
        return players;
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
