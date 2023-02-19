package interfaces;

public interface IClient{
    String getPlayerUsername();
    int getPlayerId();
    int getPlayerScore();

    void setPlayerScore(int score);
    void setPlayerUsername(String playerUsername);
    void setPlayerId(int playerId);
}
