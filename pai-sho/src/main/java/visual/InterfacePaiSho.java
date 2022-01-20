package main.java.visual;

import main.java.PaiSho;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;

import static main.java.PaiSho.game_name;


public class InterfacePaiSho extends JLayeredPane implements Runnable {


    private BufferedImage background;
    JButton button_to_change = null;
    public InterfacePaiSho() {
        Image board = null;
        try {
            board = ResourceHandler.getBufferedImage(ProjectResources.BOARD).getScaledInstance(612, 612, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            PaiSho.logger.log(Level.WARNING, e.getMessage());
        }
        BufferedImage buffered = new BufferedImage(612, 612, BufferedImage.TYPE_INT_ARGB);
        buffered.getGraphics().drawImage(board, 0, 0 , null); //Os parametros i e i1 podem ser utilizados para ajustar o posicionamento do tabuleiro

        background = buffered;
        setLayout(null);

        setSize(getPreferredSize());

        createButtons();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(812, 812);
    }

    public void getButton(JButton button){
        button_to_change = button;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int x = (getWidth() - background.getWidth()) / 2;
        int y = (getHeight() - background.getHeight()) / 2;
        g2d.drawImage(background, x, y, this);
        g2d.dispose();
    }

    private boolean movePiece(JLabel point){
        if (button_to_change == null){
            System.out.println("não há peça para mover");
            return false;
        }else {
            System.out.println("há peça para mover");
            button_to_change.setBounds(point.getX(), point.getY(), 20, 20);
            button_to_change = null;
            return true;
        }
    }
    private JFrame createConfiguredFrame(){
        JFrame frame = new JFrame(game_name);
        ImageIcon imageIcon = ResourceHandler.getImageIcon(ProjectResources.APPLICATION_ICON);
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(800,800));
        frame.setResizable(false);
        return frame;
    }

    private ImageIcon getScaledDownPieceIcon() {
        ImageIcon icon = ResourceHandler.getImageIcon(ProjectResources.DARK_LOTUS_TILE);
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon icon2 = new ImageIcon(newimg);
        return icon2;
    }

    /**
     * Cria botões nas posições selecionáveis do tabuleiros. Na posição destes botões serão postas as peças
     * e serão a forma de interagir com o jogador.
     */
    private void createButtons(){
        int k = 9;
        for (int i = 0; i < 5; i++){
            Font font1 = new Font("SansSerif", Font.BOLD, 20);
            int aux = 0;
            for (int j = k; j>0; j--){
                JLabel point = new JLabel(){
                    //TODO adicionar evento que limpa os botões
                };
                point.setText(".");
                point.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        point.setText("o");
                        point.setForeground(Color.BLUE);
                        if (button_to_change == null){
                            System.out.println("não há peça para mover");
                        }else{
                            System.out.println("há peça para mover");
                            button_to_change.setBounds(point.getX(), point.getY(), 20, 20);
                            button_to_change = null;
                        }

                    }
                });
                point.setFont(font1);
                add(point, 2);

                if (j == k){
                    aux = getWidth()/2 - k*30/2;
                } else { aux = aux + 30;}
                point.setBounds(aux, 48+i*30, 20, 20);
            }
            k= k + 2;
        }
        k = 19;
        for (int i = 0; i < 9; i++){
            Font font1 = new Font("SansSerif", Font.BOLD, 20);
            int aux = 0;
            for (int j = k; j>0; j--){
                JLabel point = new JLabel();
                point.setText(".");
                point.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (button_to_change == null){
                            System.out.println("não há peça para mover");
                        }else{
                            System.out.println("há peça para mover");
                            button_to_change.setBounds(point.getX(), point.getY(), 20, 20);
                            button_to_change = null;
                        }
                    }
                });
                point.setFont(font1);
                add(point, 2);

                if (j == k){
                    aux = getWidth()/2 - k*30/2;
                } else { aux = aux + 30;}
                point.setBounds(aux, 203+i*30, 20, 20);
            }

        }

        k= 17;
        for (int i = 0; i < 6; i++){
            Font font1 = new Font("SansSerif", Font.BOLD, 20);
            int aux = 0;
            for (int j = k; j>0; j--){
                JLabel point = new JLabel();
                point.setText(".");
                point.setFont(font1);
                point.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        boolean sucess = movePiece(point);
                    }
                });
                add(point, 2);

                if (j == k){
                    aux = getWidth()/2 - k*30/2;
                } else { aux = aux + 30;}
                point.setBounds(aux, 473+i*30, 20, 20);
            }
            k = k - 2;
        }
    }

    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            PaiSho.logger.log(Level.SEVERE, ex.getMessage());
        }

        JFrame frame = createConfiguredFrame();

//                String connection_adress = JOptionPane.showInputDialog(frame,
//                        "Enter server adress or cancel to host a new game", null);

        JPanel containerPane = new JPanel();
        containerPane.setLayout(new GridBagLayout());

        InterfacePaiSho painel_interface = new InterfacePaiSho();
        JButton add_piece_btn = new JButton("Adicionar uma peça");

        ImageIcon icon = getScaledDownPieceIcon();

        add_piece_btn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton piece = new JButton();
                piece.setIcon(icon);
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
        JLabel turn_info = new JLabel("É seu turno");
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

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

//    private int[] snap_to_pos(int x, int y){
//        int [] final_pos = new int[2];
//        final int distance_between_points = board_size/17;
//
//        }
//
//
//
//        if(y % 17 == 0){
//            final_pos[1] = y;
//        }
//        return final_pos;
//    }


