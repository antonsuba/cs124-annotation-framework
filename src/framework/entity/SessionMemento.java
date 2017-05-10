package framework.entity;

public class SessionMemento {
    private String room;
    private int gameState;

    public SessionMemento(String room, int gameState){
        this.room = room;
        this.gameState = gameState;
    }

    public String getRoom() {
        return room;
    }

    public int getGameState() {
        return gameState;
    }
}
