package main.java.moveset;

import main.java.board.Board;
import main.java.visual.GameFrame;

public class Forfeit implements Move {
    @Override
    public boolean executeMove(Board board) {
        return false;
    }

    @Override
    public void render(GameFrame frame) {

    }
}
