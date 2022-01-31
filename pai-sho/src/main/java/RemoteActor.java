package main.java;

import main.java.board.enums.PlayerNumber;
import main.java.network.NetgamesActor;

/**
 * Ator remoto do jogo
 */
public class RemoteActor implements Actor {
    protected String name;
    protected boolean turn;
    protected PlayerNumber playerNumber;
    protected GameManager manager;
    protected NetgamesActor netgames;

    public RemoteActor(PlayerNumber playerNumber){
        this.turn = false;
        this.playerNumber = playerNumber;
        this.netgames = new NetgamesActor();
    }

    public void setManager(GameManager manager) {
        this.manager = manager;
        this.netgames.defineManager(manager);
    }

    public void setTurn(Boolean flag) {
        this.turn = flag;
    }

    public Boolean isTurn() {
        return this.turn;
    }

}