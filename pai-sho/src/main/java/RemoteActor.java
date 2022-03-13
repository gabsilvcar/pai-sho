package main.java;

import main.java.board.enums.PlayerNumber;
import main.java.network.NetgamesActor;

/**
 * AtorRemoto
 *
 * Classe que maneja o netgames
 */
public class RemoteActor implements Actor {
    protected String name;
    protected boolean winner;
    protected boolean turn;
    protected PlayerNumber playerNumber;
    protected GameManager manager;
    protected NetgamesActor netgames;

    /**
     * Construtor do AtorRemoto
     *
     * @param playerNumber especifica se a interface netgames pertence ao 'jogador1' ou 'jogador2'
     */
    public RemoteActor(PlayerNumber playerNumber){
        this.turn = false;
        this.winner = false;
        this.playerNumber = playerNumber;
        this.netgames = new NetgamesActor();
    }

    /**
     * SetGerenciador
     * Seta a variável que aponta para o manejador que a controla
     */
    public void setManager(GameManager manager) {
        this.manager = manager;
        this.netgames.defineManager(manager);
    }

    /**
     * SetTurno
     * Muda o estado do netgames para que somente sejam aceitos comandos caso seja o turno do jogador
     *
     * @param flag boolean que indica se é turno do jogador
     */
    public void setTurn(Boolean flag) {
        this.turn = flag;
    }

    /**
     * IsTurn
     * @return boolean que indica se é turno do jogador
     */
    public Boolean isTurn() {
        return this.turn;
    }

}
