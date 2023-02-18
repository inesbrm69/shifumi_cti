package common;

public class PlayerSingleton {
    private static PlayerSingleton instance;
    private Player playerToSend;

    private PlayerSingleton() {
        playerToSend = null;
    }

    public static PlayerSingleton getInstance() {
        if (instance == null) {
            instance = new PlayerSingleton();
        }
        return instance;
    }

    public Player getObject() {
        return playerToSend;
    }

    public void setObject(Player player) {
        this.playerToSend = player;
    }
}
