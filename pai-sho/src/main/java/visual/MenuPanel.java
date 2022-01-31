package main.java.visual;

import main.java.PaiShoEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Painel interagível com botões
 */
public class MenuPanel extends JPanel {
    protected List<PaiShoEventListener> listeners = new ArrayList<PaiShoEventListener>();
    private JLabel turn_info;
    protected List<JButton> buttons = new ArrayList<JButton>();
    public MenuPanel(){
        setSize(100, 304);
        setBackground(Color.WHITE);
        setLayout(null);
        setDoubleBuffered(true);
        this.turn_info = new JLabel("Aguardando jogo começar", SwingConstants.CENTER);

        JButton add_piece_btn = new JButton("Adicionar uma peça");
        add_piece_btn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                for (PaiShoEventListener el : listeners)
                    el.addPieceEvent();
            }
        });

        JButton forfeit_btn = new JButton("Desistir");
        forfeit_btn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                for (PaiShoEventListener el : listeners)
                    el.forfeitEvent();
            }
        });
//        JButton change_player = new JButton("Passar Turno");
//        change_player.addMouseListener(new MouseAdapter(){
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                turn_info.setText("Turno do oponente");
//                for (PaiShoEventListener el : listeners)
//                    el.nextTurnEvent();
//            }
//        });

        JButton btn_start = new JButton("Iniciar");
        btn_start.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                for (PaiShoEventListener el : listeners)
                    el.startGameEvent();
            }
        });
        turn_info.setBounds(700, 50, 200, 60);
        add_piece_btn.setBounds(700, 100, 200, 60);
        forfeit_btn.setBounds(700, 220, 200, 60);
        btn_start.setBounds(700, 340, 200, 60);
//        change_player.setBounds(700, 460, 200, 60);


        this.add(turn_info);
        this.add(add_piece_btn);
        this.add(forfeit_btn);
        this.add(btn_start);
        this.buttons.add(add_piece_btn);
        this.buttons.add(forfeit_btn);
        this.buttons.add(btn_start);
//        this.add(change_player);


    }


    /**
     * Adiciona uma classe que ouvirá os eventos lançados pela interface
     * @param toAdd classe que ouvirá os eventos
     */
    public void addListener(PaiShoEventListener toAdd) {
        listeners.add(toAdd);
    }

    public void sendMessage(String msg, Color color){
        turn_info.setText(msg);
        turn_info.setForeground(color);
    }

    public void setEnableButtons(Boolean enable) {
        for (JButton button: this.buttons) {
            button.setEnabled(enable);
        }
    }

}