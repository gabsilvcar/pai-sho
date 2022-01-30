package main.java;

import main.java.board.Board;
import main.java.board.enums.PlayerNumber;

import java.awt.*;
import java.util.logging.Logger;


public class PaiSho {
    public static final String game_name = "Pai-sho";
    public static Logger logger = Logger.getLogger(game_name);

    public static void main(String [] args) {
        LocalActor localActor1 = new LocalActor(PlayerNumber.PLAYER_ONE);
        RemoteActor localPlayer2 = new RemoteActor(PlayerNumber.PLAYER_TWO);
        Board board = new Board();
        GameManager manager = new GameManager(board, localActor1, localPlayer2);
        EventQueue.invokeLater(manager);

    }

}

