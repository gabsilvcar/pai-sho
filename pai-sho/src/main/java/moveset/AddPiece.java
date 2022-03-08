package main.java.moveset;

import main.java.board.Board;
import main.java.board.enums.PlayerNumber;
import main.java.visual.GameFrame;

public class AddPiece implements Move {
    protected PlayerNumber player_number;

    /**
     * Adiciona uma peça para um jogador
     *
     * @param player_number o jogador que está adicionando a peça
     */
    public AddPiece(PlayerNumber player_number) {
        this.player_number = player_number;
    }

    @Override
    public boolean executeMove(Board board) {
        return board.addPiece(player_number);
    }

    @Override
    public void render(GameFrame frame) {
        switch (player_number){
            case PLAYER_ONE:
                frame.boardPanel.createTile(0, -6, PlayerNumber.PLAYER_ONE);
                break;
            case PLAYER_TWO:
                frame.boardPanel.createTile(0, 6, PlayerNumber.PLAYER_TWO);
                break;
        }
    }
}
