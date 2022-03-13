package main.java.moveset;

import main.java.board.Board;
import main.java.board.enums.PlayerNumber;
import main.java.visual.GameFrame;
import main.java.visual.TileButton;

public class MovePiece implements Move {
    protected int x1, x2, y1, y2;
    protected PlayerNumber playerNumber;
    protected TileButton tile, target_tile;

    /**
     * Move uma peça para uma posição desocupada
     *
     * @param player_number o jogador que está movendo a peça
     * @param tile o botão que representa a peça que está sendo movida
     * @param x2 a coordenada X de destino
     * @param y2 a coordenada Y de destino
     */
    public MovePiece(PlayerNumber player_number, TileButton tile, int x2, int y2) {
        this.playerNumber = player_number;
        this.x1 = tile.getCoordX();
        this.y1 = tile.getCoordY();
        this.x2 = x2;
        this.y2 = y2;
        this.tile = tile;
        this.target_tile = null;
    }

    /**
     * Move uma peça para uma posição já ocupada
     *
     * @param player_number o jogador que está movendo a peça
     * @param selected_tile o botão que representa a peça que está sendo movida
     * @param target_tile o botão que representa a peça que o jogador está tentando capturar
     */
    public MovePiece(PlayerNumber player_number, TileButton selected_tile, TileButton target_tile) {
        this.playerNumber = player_number;
        this.x1 = selected_tile.getCoordX();
        this.y1 = selected_tile.getCoordY();
        this.x2 = target_tile.getCoordX();
        this.y2 = target_tile.getCoordY();
        this.tile = selected_tile;
        this.target_tile = target_tile;
    }

    @Override
    public boolean executeMove(Board board) {
        if(board.getPiece(x1, y1).playerNumber() != this.playerNumber){
            return false;
        }
        return board.movePiece(x1, y1, x2, y2);
    }

    @Override
    public void render(GameFrame frame) {
        frame.boardPanel.remove(frame.boardPanel.get_piece(x1, y1));
        if (target_tile != null) {
            frame.boardPanel.remove(frame.boardPanel.get_piece(x2, y2));
        }

        frame.boardPanel.createTile(x2, y2, playerNumber);

    }
}
