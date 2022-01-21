package main.java.board;

import javax.swing.text.Position;

public class Piece {
    protected Position position;
    protected boolean canAttack;


    public Piece(){
        this.canAttack = true;
    }

    /**
     * Indica se a peça já atacou, se for o caso ela não poderá atacar de novo
     *
     * @return Um booleano indicando se ela pode atacar
     */
    public boolean canAttack(){
        return canAttack;
    }

    /**
     * Remove capacidade de ataque de uma peça
     */
    public void revokeAttackerStatus(){
        this.canAttack = false;
    }
}
