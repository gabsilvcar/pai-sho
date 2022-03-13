package main.java.moveset;

import main.java.board.Board;
import main.java.board.enums.PlayerNumber;
import main.java.visual.GameFrame;

public class Forfeit implements Move {
    protected PlayerNumber player_number;

    /**
     * Adiciona uma peça para um jogador
     *
     * @param player_number o jogador que está adicionando a peça
     */
    public Forfeit(PlayerNumber player_number) {
        this.player_number = player_number;
    }

    @Override
    public boolean executeMove(Board board) {
        return true;
    }

    @Override
    public void render(GameFrame frame) {

    }

    public PlayerNumber playerNumber(){
        return this.player_number;
    }
}
