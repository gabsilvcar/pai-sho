package main.java;

import main.java.visual.InterfacePaiSho;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;


public class PaiSho {

    public static final String resources_path = "pai-sho/src/main/resources/";
    public static final String game_name = "Pai-sho";
    public static final int board_size = 612; //Pixel Size

    public static void main(String [] args) {
        int matrix_size = 17;
        var imageIcon = new ImageIcon(resources_path + "iroh.jpg");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                var frame = new JFrame(game_name);

                frame.setIconImage(imageIcon.getImage());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//
//                String connection_adress = JOptionPane.showInputDialog(frame,
//                        "Enter server adress or cancel to host a new game", null);

                try {
                    var painel_interface = new InterfacePaiSho();
                    JButton add_piece_btn = new JButton("Adicionar uma peça");
                    JLabel turn_info = new JLabel("É seu turno");

                    JButton forfeit_btn = new JButton("Desistir");


                    painel_interface.add(turn_info);
                    painel_interface.add(add_piece_btn);
                    painel_interface.add(forfeit_btn);

                    frame.add(painel_interface);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
