package main.java.board;

public class LocalPlayer implements Player{
    private String name;
    private int active_pieces, inactive_pieces, lost_pieces;
    boolean winner;
    private boolean turn;

    public LocalPlayer(){
        this.turn = false;
        this.inactive_pieces = 8;
    }

    public void setTurn(Boolean flag) {
        this.turn = flag;
    }

    public Boolean isTurn() {
        return this.turn;
    }

    public int getInactivePieces(){
        return inactive_pieces;
    }

    public int getActivePieces(){
        return active_pieces;
    }

    public int getLostPieces(){
        return lost_pieces;
    }

    public void addPiece() {
        inactive_pieces--;
        active_pieces++;
    }
}
