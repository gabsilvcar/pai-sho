package main.java;

import main.java.board.enums.PlayerNumber;
import main.java.moveset.AddPiece;
import main.java.moveset.Forfeit;
import main.java.moveset.MovePiece;
import main.java.visual.GameFrame;
import main.java.visual.TileButton;

import javax.swing.*;
import java.awt.*;

/**
 * AtorLocal
 *
 * Classe que maneja a interface gráfica e que interage com o usuário
 */
public class LocalActor implements Actor, PaiShoEventListener {
    protected String name;
    boolean winner;
    protected boolean turn;
    protected GameFrame gui;
    protected PlayerNumber playerNumber;
    protected GameManager manager;

    /**
     * Construtor do AtorLocal
     *
     * @param playerNumber especifica se a interface grafica pertence ao 'jogador1' ou 'jogador2'
     */
    public LocalActor(PlayerNumber playerNumber) {
        this.gui = new GameFrame();
        this.playerNumber = playerNumber;
        this.addListenerToGui(this);
    }

    /**
     * SetGerenciador
     * Seta a variável que aponta para o manejador que a controla
     */
    public void setManager (GameManager manager) {
        this.manager = manager;
    }

    /**
     * AddListenerToGui
     * Adiciona listener para eventos que são lançados pela interface
     */
    public void addListenerToGui(PaiShoEventListener toAdd) {
        gui.addListenerToGui(toAdd);
    }

    /**
     * SetTurno
     * Muda o estado da interface para que somente sejam aceitas entradas caso seja o turno do jogador
     *
     * @param flag boolean que indica se é turno do jogador
     */
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

    /**
     * IsTurn
     * @return boolean que indica se é turno do jogador
     */
    public Boolean isTurn() {
        return this.turn;
    }

    /**
     * MostrarAviso
     * Envia uma mensagem arbitrária para o usuário pela interface grafica
     */
    public void showWarning(String message) {
        JOptionPane.showMessageDialog(this.gui, message);
    }

    /**
     * AdicionarPeçaEvento
     * Tenta adicionar uma peça ao tabuleiro de acordo com o jogador atual
     */
    @Override
    public void addPieceEvent() {
        if (isTurn()) {
            manager.nextMove(new AddPiece(this.playerNumber));
        }
        manager.verifyWinner();
    }

    /**
     * MoverPeçaEvento
     * Tenta mover uma peça
     */
    @Override
    public void movePieceEvent(TileButton button, int x, int y) {
        if(isTurn()){
            manager.nextMove(new MovePiece(this.playerNumber, button, x, y));

        }
        manager.verifyWinner();
    }


    /**
     * MoverPeçaEvento
     * Tenta mover uma peça para um espaço já ocupado
     */
    @Override
    public void movePieceEvent(TileButton selectedPiece, TileButton targetPiece) {
        if(isTurn()){
            manager.nextMove(new MovePiece(this.playerNumber, selectedPiece, targetPiece));
        }
        manager.verifyWinner();
    }

    /**
     * DesistenciaEvento
     * Jogador atual desiste e perde a partida
     */
    @Override
    public void forfeitEvent() {
        if(isTurn()){
            manager.nextMove(new Forfeit(this.playerNumber));
        }
        manager.verifyWinner();
    }

    /**
     * IniciarJogoEvento
     * Inicia uma partida
     */
    @Override
    public void startGameEvent() {
        manager.startGame();
    }

    /**
     * ConexãoEvento
     * Há uma tentativa de conectar a um servidor
     */
    @Override
    public void connectionEvent(String address, String name) {
        manager.startConnection(address, name);
    }

    /**
     * DesconexãoEvento
     * Desconecta o jogador da partida, caso esteja em uma
     */
    @Override
    public void disconnectionEvent() {
        manager.stopConnection();
    }

}
