package main.java.board;

public interface Player {
    void setTurn(Boolean flag);
    Boolean isTurn();
    void addPiece();
    int getInactivePieces();
    int getActivePieces();
    int getLostPieces();
}
