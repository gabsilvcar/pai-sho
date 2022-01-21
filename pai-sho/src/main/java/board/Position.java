package main.java.board;

import main.java.board.enums.Quadrant;

public class Position {
    protected int x, y;
    protected Quadrant quadrant;
    protected Piece piece;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
        setQuadrant();
    }
    public Quadrant getQuadrant(){
        return this.quadrant;
    }

    private void setQuadrant(){
        if(x < 0){
            if(y < 0){
                this.quadrant = Quadrant.WHITE;
            }
            else{
                this.quadrant = Quadrant.RED;
            }
        }else if(x > 0){
            if(y > 0){
                this.quadrant = Quadrant.RED;
            }
            else{
                this.quadrant = Quadrant.WHITE;
            }
        }else {
            this.quadrant = Quadrant.WOODEN;
        }
    }

    public void occupyPostition(Piece piece){
        this.piece = piece;
    }

    public void occupyPostitionAndTakePiece(Piece piece){
        this.piece = piece;
        this.piece.revokeAttackerStatus();
    }

    public void freePosition(){
        this.piece = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }
}
