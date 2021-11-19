package main.java.visual;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static main.java.PaiSho.resources_path;

public class InterfacePaiSho extends JPanel {


    private BufferedImage background;

    public InterfacePaiSho() throws IOException {
        File pathToFile = new File(resources_path + "board.png");
        BufferedImage board = ImageIO.read(pathToFile);
        background = board;

        File pathToFile2 = new File(resources_path + "dark-lotus-tile.png");
        BufferedImage tile = ImageIO.read(pathToFile2);

        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                Graphics2D g2d = background.createGraphics();
                g2d.setColor(Color.BLACK);
                g2d.drawImage(tile, e.getX()-10, e.getY()-100-10, 20, 20, null, null);
                g2d.dispose();
                repaint();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(612, 812);
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

