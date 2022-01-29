package main.java;

import main.java.board.Board;
import main.java.board.enums.PlayerNumber;

import java.awt.*;
import java.util.logging.Logger;


public class PaiSho {
    public static final String game_name = "Pai-sho";
    public static Logger logger = Logger.getLogger(game_name);

    public static void main(String [] args) {
        LocalPlayer localPlayer1 = new LocalPlayer(PlayerNumber.PLAYER_ONE);
        RemotePlayer localPlayer2 = new RemotePlayer(PlayerNumber.PLAYER_TWO);
        Board board = new Board();
        localPlayer1.setTurn(true);
        GameManager manager = new GameManager(board, localPlayer1, localPlayer2);
        EventQueue.invokeLater(manager);

    }

}

