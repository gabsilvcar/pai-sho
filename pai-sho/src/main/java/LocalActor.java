package main.java;

import main.java.board.enums.PlayerNumber;
import main.java.moveset.AddPiece;
import main.java.visual.GameFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Ator local do jogo
 */
public class LocalActor implements Actor, PaiShoEventListener {
    protected String name;
    boolean winner;
    protected boolean turn;
    protected GameFrame gui;
    protected PlayerNumber playerNumber;
    protected GameManager manager;

    public LocalActor(PlayerNumber playerNumber) {
        this.gui = new GameFrame();
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
        if (flag) {
            gui.sendMessage("É seu turno", Color.GREEN);
            gui.menuPanel.setEnableButtons(true);
        } else {
            gui.sendMessage("Esperando jogada do adversário", Color.RED);
            gui.menuPanel.setEnableButtons(false);

        }
        this.turn = flag;
    }

    public Boolean isTurn() {
        return this.turn;
    }

    @Override
    public void addPieceEvent() {
        manager.nextMove(new AddPiece(this.playerNumber));
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
