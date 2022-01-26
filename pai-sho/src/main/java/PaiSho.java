package main.java;

import main.java.board.Board;
import main.java.board.LocalPlayer;

import java.awt.*;
import java.util.logging.Logger;


public class PaiSho {
    public static final String game_name = "Pai-sho";
    public static Logger logger = Logger.getLogger(game_name);

    public static void main(String [] args) {
        int matrix_size = 17;
        final ImageIcon imageIcon = new ImageIcon(PaiSho.class.getClass().getResource(resources_path + "iroh.jpg"));
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame(game_name);

                frame.setIconImage(imageIcon.getImage());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.setLocationRelativeTo(null);


                String connection_adress = JOptionPane.showInputDialog(frame,
                        "Enter server adress or cancel to host a new game", null);

                try {
                    JPanel containerPane = new JPanel();
                    containerPane.setLayout(new GridBagLayout());

                    final InterfacePaiSho painel_interface = new InterfacePaiSho();
                    JButton add_piece_btn = new JButton("Adicionar uma peça");
                    add_piece_btn.addMouseListener(new MouseAdapter(){
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JButton piece = new JButton();
                            piece.setIcon(icon2);
                            piece.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    System.out.println("foi");
                                    painel_interface.getButton((JButton) e.getComponent());
                                }
                            });
                            painel_interface.add(piece, 3);
                            piece.setBounds(301, 55, 20, 20);

                        }
                    });
                    final JLabel turn_info = new JLabel("É seu turno");
                    JPanel data_interface = new  JPanel();
                    JButton forfeit_btn = new JButton("Desistir");
                    add_piece_btn.addMouseListener(new MouseAdapter(){
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            turn_info.setText("Você desistiu");
                        }
                    });
                    JButton change_player = new JButton("Passar Turno");
                    change_player.addMouseListener(new MouseAdapter(){
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            turn_info.setText("Turno do oponente");
                        }
                    });
                    JButton btn_start = new JButton("Iniciar");
                    btn_start.addMouseListener(new MouseAdapter(){
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            turn_info.setText("O jogo começou");
                        }
                    });
                    data_interface.add(turn_info);
                    data_interface.add(add_piece_btn);
                    data_interface.add(forfeit_btn);
                    data_interface.add(btn_start);
                    data_interface.add(change_player);

                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.BOTH;
                    c.weightx = 1;
                    c.gridy = 0;
                    c.weighty = 0.1;
                    containerPane.add(data_interface, c);
                    c.weighty = 1;
                    c.gridy = 1;
                    c.gridheight = 3;
                    containerPane.add(painel_interface, c);
                    frame.add(containerPane);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        LocalPlayer localPlayer1 = new LocalPlayer();
        LocalPlayer localPlayer2 = new LocalPlayer();//TODO Adicionar interface para usuário remoto(netgames)
        Board board = new Board();
        localPlayer1.setTurn(true);
        GameManager manager = new GameManager(board, localPlayer1, localPlayer2);
        EventQueue.invokeLater(manager);

    }

}
