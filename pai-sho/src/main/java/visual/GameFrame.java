package main.java.visual;

import main.java.PaiSho;
import main.java.PaiShoEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI do jogo, onde o usuário fará todas as suas interações
 */
public class GameFrame extends JFrame {
    protected List<PaiShoEventListener> listeners = new ArrayList<PaiShoEventListener>();
    public BoardPanel boardPanel;
    public MenuPanel menuPanel;


    public GameFrame() {
        setTitle(PaiSho.game_name);
        setSize(1008, 660);
        configureMenuBar();

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

    private void configureMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Conexão");
        menuBar.add(fileMenu);
        JMenuItem start = new JMenuItem("Iniciar nova partida");
        JMenuItem connect = new JMenuItem("Conectar");
        JMenuItem disconnect = new JMenuItem("Desconectar");
        fileMenu.add(start);
        fileMenu.add(connect);
        fileMenu.add(disconnect);
        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Insira o seu nome");
                String address = JOptionPane.showInputDialog("Insira o endereço do servidor");
                for (PaiShoEventListener el : listeners)
                    el.connectionEvent(address, name);
            }
        });
        disconnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (PaiShoEventListener el : listeners)
                    el.disconnectionEvent();
            }
        });
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (PaiShoEventListener el : listeners)
                    el.startGameEvent();
            }
        });

    }

    /**
     * Adiciona uma classe que ouvirá os eventos lançados pela interface
     * @param toAdd classe que ouvirá os eventos
     */
    public void addListenerToGui(PaiShoEventListener toAdd) {
        menuPanel.addListener(toAdd);
        boardPanel.addListener(toAdd);
        this.listeners.add(toAdd);
    }

    public void sendMessage(String msg, Color color){
        menuPanel.sendMessage(msg, color);
    }
}
