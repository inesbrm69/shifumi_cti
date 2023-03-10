package common;

import java.io.Serializable;

public class PlayerCoords implements Serializable {
    private Player player;

    private int oldX;
    private int oldY;
    private String pastTile;

    private int direction;

    public PlayerCoords(Player player, int oldX, int oldY, String oldTile, int direction) {
        this.player = player;
        this.oldX = oldX;
        this.oldY = oldY;
        this.pastTile = oldTile;
        this.direction = direction;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public String getPastTile() {
        return pastTile;
    }

    public void setPastTile(String pastTile) {
        this.pastTile = pastTile;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
