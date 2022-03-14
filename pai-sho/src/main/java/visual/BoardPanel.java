package main.java.visual;

import main.java.PaiSho;
import main.java.PaiShoEventListener;
import main.java.board.Position;
import main.java.board.enums.PlayerNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


/**
 * PainelTabuleiro
 *
 * Painel onde há o tabuleiro, nele o usuário pode interagir com as peças
 */
public class BoardPanel extends JPanel {

    private List<PaiShoEventListener> listeners = new ArrayList<PaiShoEventListener>();
    private BufferedImage[] boardBuffer = new BufferedImage[19];
    private BufferedImage application_frame;
    private ImageIcon tile_icon;
    private ImageIcon tile_icon_alt;
    private boolean pieceMoving = false;
    private TileButton selectedPiece;
    private ArrayList<TileButton> board_pieces;
    private final static int icon_size = 30;
    private final static int icon_offset = 2;
    private List<Line2D> harmony_lines = new ArrayList<Line2D>();
    private final static int harmony_offset = 16;
    public final static int[][] boardMap = {
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
        this.board_pieces = new ArrayList<TileButton>();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(pieceMoving);
                if(pieceMoving){
                    pieceMoving = false;
                    for (PaiShoEventListener el : listeners)
                        el.movePieceEvent(selectedPiece, pixelToCoordinate(e.getX()), pixelToCoordinate(e.getY()));
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {}

            @Override
            public void mouseExited(MouseEvent mouseEvent) {}
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBoard(g);
        drawHarmonies(g);
    }

    public void setHarmonies(ArrayList<ArrayList<Position>> harmonies1, ArrayList<ArrayList<Position>> harmonies2) {
        harmony_lines = new ArrayList<Line2D>();
        setHarmony(harmonies1);
        setHarmony(harmonies2);
        this.repaint();
    }

    private void setHarmony(ArrayList<ArrayList<Position>> harmonies) {
        for (ArrayList<Position> harmony : harmonies) {
            int x1 = coordToPixel(harmony.get(0).getX());
            int y1 = coordToPixel(harmony.get(0).getY());
            int x2 = coordToPixel(harmony.get(1).getX());
            int y2 = coordToPixel(harmony.get(1).getY());
            int h = harmony_offset;
            harmony_lines.add(new Line2D.Double(x1+h, y1+h, x2+h, y2+h));
        }
    }
    private void drawHarmonies(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (Line2D harmony : harmony_lines){
            g2.setColor(Color.BLUE);
            g2.setStroke(new BasicStroke(6f));
            g2.draw(harmony);
        }
    }
    /**
     * CarregarImagens
     * Carrega os recursos em RAM para que não sejam acessados durante o jogo
     */
    private void loadImages() {
        try{
            this.tile_icon = new ImageIcon(ResourceHandler.getBufferedImage(ProjectResources.DARK_LOTUS_TILE).getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH )) ;
            this.tile_icon_alt = new ImageIcon(ResourceHandler.getBufferedImage(ProjectResources.DARK_LOTUS_TILE_ALT).getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH )) ;
            for (int i = 0; i < 19; i++) {
                this.boardBuffer[i] = ResourceHandler.getBufferedImage(i + ".png");
            }
            application_frame = ResourceHandler.getBufferedImage(ProjectResources.APPLICATION_FRAME);
        } catch (IOException e) {
            PaiSho.logger.log(Level.SEVERE, e.getMessage());
        }

    }
    /**
     * DesenharTabuleiro
     * Cria o tabuleiro através das várias peças disponíveis na pasta resources,
     * utiliza a variavel boardmap para fazer isso
     */
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

    /**
     * Equivalente ao AdicionarPeça
     * Adiciona uma peça ao tabuleiro em qualquer posição,
     * é uma versão mais generalizada do AdicionarPeça previsto nos diagramas
     */
    public void createTile(int x, int y, PlayerNumber playerNumber){
        TileButton piece = new TileButton(x,y);
        if(playerNumber.equals(PlayerNumber.PLAYER_ONE)){
            piece.setIcon(tile_icon);
        } else {
            piece.setIcon(tile_icon_alt);
        }
        piece.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            if (pieceMoving) {
                for (PaiShoEventListener el : listeners)
                    el.movePieceEvent(selectedPiece, piece);
                pieceMoving = false;
                selectedPiece = null;
            } else {
                selectedPiece = piece;
                pieceMoving = true;
            }
            }
        });
        piece.setBounds(coordToPixel(x)+icon_offset, coordToPixel(y)+icon_offset, icon_size, icon_size);
        this.board_pieces.add(piece);
        this.add(piece, this);
        this.repaint();
    }

    public TileButton get_piece(int x, int y){
        TileButton piece = this.board_pieces.get(0);
        for(int i = 0; i < this.board_pieces.size(); i++){
            if(this.board_pieces.get(i).getCoordX() == x && this.board_pieces.get(i).getCoordY() == y){
                piece = this.board_pieces.get(i);
                this.board_pieces.remove(piece);
                return piece;
            }
        }

        return piece;
    }
    /**
     * AddListener
     * Adiciona o event listener que alertará as classes que um evento está ocorrendo
     */
    public void addListener(PaiShoEventListener toAdd) {
        listeners.add(toAdd);
    }

    /**
     * PixelParaCoordenadas
     * Transforma posições do painel da interface grafica em uma posição equivalente no tabuleiro
     */
    private int pixelToCoordinate(int pixel) {
        return (pixel/32)-9;
    }

    /**
     * CoordenadasParaPixel
     * Transforma posições do tabuleiro em uma posição na interface grafica
     */
    private int coordToPixel(int coord) {
        return (coord+9)*32;
    }

}


