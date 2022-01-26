package main.java.board;

import main.java.board.enums.PlayerNumber;

import javax.swing.text.Position;

public class Piece {
    protected Position position;
    protected boolean canAttack;
    protected PlayerNumber playerNumber;


    public Piece(PlayerNumber playerNumber){
        this.canAttack = true;
        this.playerNumber = playerNumber;
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
