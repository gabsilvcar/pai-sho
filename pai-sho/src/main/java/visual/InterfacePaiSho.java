package main.java.visual;

import main.java.PaiSho;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static main.java.PaiSho.resources_path;

public class InterfacePaiSho extends JLayeredPane {


    private BufferedImage background;
    JButton button_to_change = null;
    public InterfacePaiSho() throws IOException {

        Image board = ImageIO.read(this.getClass().getResource((resources_path + "board.png"))).getScaledInstance(612, 612, Image.SCALE_SMOOTH);
        BufferedImage buffered = new BufferedImage(612, 612, BufferedImage.TYPE_INT_ARGB);
        buffered.getGraphics().drawImage(board, 0, 0 , null);

        background = buffered;
        setLayout(null);

        setSize(getPreferredSize());

        ImageIcon icon = new ImageIcon(this.getClass().getResource((resources_path+"dark-lotus-tile.png" )));
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon icon2 = new ImageIcon(newimg);
        int k = 9;
        for (int i = 0; i < 5; i++){
            Font font1 = new Font("SansSerif", Font.BOLD, 20);
            int aux = 0;
            for (int j = k; j>0; j--){
                final JLabel point = new JLabel();
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
                point.setBounds(aux, 25+i*30, 20, 20);
            }
            k= k + 2;
        }
        k = 19;
        for (int i = 0; i < 9; i++){
            Font font1 = new Font("SansSerif", Font.BOLD, 20);
            int aux = 0;
            for (int j = k; j>0; j--){
                final JLabel point = new JLabel();
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
                point.setBounds(aux, 175+i*30, 20, 20);
            }

        }

        k= 17;
        for (int i = 0; i < 6; i++){
            Font font1 = new Font("SansSerif", Font.BOLD, 20);
            int aux = 0;
            for (int j = k; j>0; j--){
                final JLabel point = new JLabel();
                point.setText(".");
                point.setFont(font1);
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
                add(point, 2);

                if (j == k){
                    aux = getWidth()/2 - k*30/2;
                } else { aux = aux + 30;}
                point.setBounds(aux, 445+i*30, 20, 20);
            }
            k = k - 2;
        }
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
}

