package main.java.visual;

import javax.swing.*;

/**
 * BotãoPeça
 * Classe auxiliar criada durante o desenvolvimento que
 * guarda uma variável de posição, visto que botões não armazenam informações
 */
public class TileButton extends JButton {
    protected int x,y;

    public TileButton(int x, int y) {
        this.x = x;
        this.y = y;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }

    public int getCoordX(){
        return x;
    }

    public int getCoordY(){
        return y;
    }
}
