package main.java;

import main.java.board.enums.PlayerNumber;
import main.java.network.NetgamesActor;

/**
 * Ator remoto do jogo
 */
public class RemotePlayer implements Player {
    protected String name;
    protected boolean turn;
    protected PlayerNumber playerNumber;
    protected GameManager manager;
    protected NetgamesActor netgames;

    public RemotePlayer(PlayerNumber playerNumber){
        this.turn = false;
        this.playerNumber = playerNumber;
        this.netgames = new NetgamesActor();
    }

    public void setManager(GameManager manager) {
        this.manager = manager;
    }

    public void setTurn(Boolean flag) {
        this.turn = flag;
    }

    public Boolean isTurn() {
        return this.turn;
    }

}
