package main.java;

import main.java.board.Board;
import main.java.board.LocalPlayer;
import main.java.board.RemotePlayer;

import java.awt.*;
import java.util.logging.Logger;


public class PaiSho {
    public static final String game_name = "Pai-sho";
    public static Logger logger = Logger.getLogger(game_name);

    public static void main(String [] args) {
        LocalPlayer localPlayer1 = new LocalPlayer();
        RemotePlayer localPlayer2 = new RemotePlayer();//TODO Adicionar interface para usuário remoto(netgames)
        Board board = new Board();
        localPlayer1.setTurn(true);
        GameManager manager = new GameManager(board, localPlayer1, localPlayer2);
        EventQueue.invokeLater(manager);

    }

}

