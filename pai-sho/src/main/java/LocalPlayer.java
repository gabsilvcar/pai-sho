package main.java;

import main.java.board.enums.PlayerNumber;
import main.java.moveset.AddPiece;
import main.java.visual.GameFrame;

import javax.swing.*;

/**
 * Ator local do jogo
 */
public class LocalPlayer implements Player, PaiShoEventListener {
    protected String name;
    protected int active_pieces, inactive_pieces, lost_pieces;
    boolean winner;
    protected boolean turn;
    protected GameFrame gui;
    protected PlayerNumber playerNumber;
    protected GameManager manager;

    public LocalPlayer(PlayerNumber playerNumber) {
        this.gui = new GameFrame();
        this.turn = false;
        this.inactive_pieces = 8;
        this.playerNumber = playerNumber;
        this.addListenerToGui(this);
    }

    public void setManager (GameManager manager) {
        this.manager = manager;
    }

    public void addListenerToGui(PaiShoEventListener toAdd) {
        gui.addListenerToGui(toAdd);
    }

    public void setTurn(Boolean flag) {
        this.turn = flag;
    }

    public Boolean isTurn() {
        return this.turn;
    }

    @Override
    public void addPieceEvent() {
        if (inactive_pieces > 0) {
            inactive_pieces--;
            active_pieces++;
            manager.nextMove(new AddPiece(this.playerNumber));
        }
    }

    @Override
    public void forfeitEvent() {

    }

    @Override
    public void startGameEvent() {
        manager.startGame();
    }

    @Override
    public void connectionEvent(String address, String name) {
        manager.startConnection(address, name);
    }

    @Override
    public void disconnectionEvent() {
        manager.stopConnection();
    }

    public void showWarning(String message) {
        JOptionPane.showMessageDialog(this.gui, message);
    }


}
