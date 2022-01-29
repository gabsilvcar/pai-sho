package main.java.board;

//TODO NETGAMES AQUI
public class RemotePlayer implements Player{
    private String name;
    private int active_pieces, inactive_pieces, lost_pieces;
    boolean winner;
    private boolean turn;

    public RemotePlayer(){
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
