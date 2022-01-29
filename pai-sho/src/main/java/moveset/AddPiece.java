package main.java.moveset;

import main.java.board.Board;
import main.java.board.enums.PlayerNumber;
import main.java.visual.GameFrame;

public class AddPiece implements Move {
    protected PlayerNumber player_number;

    public AddPiece(PlayerNumber player_number) {
        this.player_number = player_number;
    }

    @Override
    public boolean executeMove(Board board) {
        return board.addPiece(player_number);
    }

    @Override
    public void render(GameFrame frame) {
        frame.addPiece(player_number);
    }
}
