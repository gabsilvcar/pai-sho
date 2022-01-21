package main.java.visual;

import main.java.PaiSho;
import main.java.board.enums.PlayerNumber;
import main.java.visual.events.PaiShoEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class BoardPanel extends JPanel {

    private List<PaiShoEventListener> listeners = new ArrayList<PaiShoEventListener>();
    private BufferedImage[] boardBuffer = new BufferedImage[19];
    private BufferedImage application_frame;
    private ImageIcon tile_icon;
    int[][] boardMap = {
            {0, 0, 0, 0, 0, 0, 1, 12, 3, 3, 3, 18, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 12, 3, 18, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 15, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 9, 5, 11, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 9, 2, 5, 3, 11, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 9, 2, 2, 5, 3, 3, 11, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 9, 2, 2, 2, 5, 3, 3, 3, 11, 1, 1, 1, 1, 1},
            {11, 1, 1, 1, 9, 2, 2, 2, 2, 5, 3, 3, 3, 3, 11, 1, 1, 1, 17},
            {3, 11, 1, 9, 2, 2, 2, 2, 2, 5, 3, 3, 3, 3, 3, 11, 1, 17, 3},
            {3, 3, 14, 8, 8, 8, 8, 8, 8, 4, 7, 7, 7, 7, 7, 7, 16, 3, 3},
            {3, 18, 1, 12, 3, 3, 3, 3, 3, 6, 2, 2, 2, 2, 2, 10, 1, 12, 3},
            {18, 1, 1, 1, 12, 3, 3, 3, 3, 6, 2, 2, 2, 2, 10, 1, 1, 1, 12},
            {1, 1, 1, 1, 1, 12, 3, 3, 3, 6, 2, 2, 2, 10, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 12, 3, 3, 6, 2, 2, 10, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 12, 3, 6, 2, 10, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 12, 6, 10, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 13, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 17, 3, 11, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 17, 3, 3, 3, 11, 1, 0, 0, 0, 0, 0, 0}};

    public BoardPanel() {
        setSize(608, 608);
        loadImages();
        setDoubleBuffered(true);
        setLayout(null);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX());
                System.out.println(e.getY());
                buttomTest((e.getX()/32)*32+7, (e.getY()/32)*32+7);

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBoard(g);
    }

    private void loadImages() {
        try{
            this.tile_icon = ResourceHandler.getImageIcon(ProjectResources.DARK_LOTUS_TILE);
            for (int i = 0; i < 19; i++) {
                this.boardBuffer[i] = ResourceHandler.getBufferedImage(i + ".png");
            }
            application_frame = ResourceHandler.getBufferedImage(ProjectResources.APPLICATION_FRAME);
        } catch (IOException e) {
            PaiSho.logger.log(Level.SEVERE, e.getMessage());
        }

    }

    private void drawBoard(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BufferedImage tile;
        for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                tile = boardBuffer[boardMap[j][i]];
                g2.drawImage(tile, i*32, j*32, 32, 32, null);
            }
        }
        g2.drawImage(application_frame, 0, 0, 608, 608,null);
    }

    public void addPiece(PlayerNumber number){
        JButton piece = new JButton();
        piece.setIcon(tile_icon);
        piece.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("click - adam sandlers");
            }
        });
        switch (number){
            case PLAYER_ONE:
                piece.setBounds(295, 70, 20, 20);
                break;
            case PLAYER_TWO:
                piece.setBounds(295, 550, 20, 20);
                break;
        }

        this.add(piece, this);
        this.repaint();
    }

    public void buttomTest(int x, int y){
        JButton piece = new JButton();
        piece.setIcon(tile_icon);
        piece.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("click - adam sandlers");
            }
        });
        piece.setBounds(x, y, 20, 20);
        this.add(piece, this);
        this.repaint();
    }

}


