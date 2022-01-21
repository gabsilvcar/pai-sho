package main.java.visual;

import main.java.PaiSho;
import main.java.board.enums.PlayerNumber;
import main.java.visual.events.PaiShoEventListener;

import javax.swing.*;

public class GameFrame extends JFrame {

    public BoardPanel boardPanel;
    public MenuPanel menuPanel;


    public GameFrame() {
        setTitle(PaiSho.game_name);
        setSize(1008, 630);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        boardPanel = new BoardPanel();
        menuPanel = new MenuPanel();
        add(boardPanel);
        add(menuPanel);

        boardPanel.grabFocus();
        boardPanel.requestFocusInWindow();

        revalidate();
        repaint();
    }


    /**
     * Adiciona uma classe que ouvirá os eventos lançados pela interface
     * @param toAdd classe que ouvirá os eventos
     */
    public void addListener(PaiShoEventListener toAdd) {
        menuPanel.addListener(toAdd);
    }

    public void addPiece(PlayerNumber player_number){
        boardPanel.addPiece(player_number);
    }

    public void sendWarningMessage(String msg){
        menuPanel.sendWarningMessage(msg);
    }

      public void sendMessage(String msg){
        menuPanel.sendMessage(msg);
    }
}
