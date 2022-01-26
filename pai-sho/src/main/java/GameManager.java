package main.java;

import main.java.board.Board;
import main.java.board.Player;
import main.java.board.enums.PlayerNumber;
import main.java.visual.GameFrame;
import main.java.visual.events.PaiShoEventListener;

public class GameManager implements Runnable, PaiShoEventListener {
    protected Board board;
    protected Player player1, player2;
    protected GameFrame interfacePaiSho;

    public GameManager(Board board, Player player1, Player player2){
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    private Player getCurrentPlayer(){
        if (player1.isTurn()){
            return player1;
        } else {
            return player2;
        }
    }

    private PlayerNumber getCurrentPlayerTag(){
        if (player1.isTurn()){
            return PlayerNumber.PLAYER_ONE;
        } else {
            return PlayerNumber.PLAYER_TWO;
        }
    }


    private void swapTurns(){
        player2.setTurn(player1.isTurn());
        player1.setTurn(!player1.isTurn());

    }

    private Boolean addPiece(){
        if ((getCurrentPlayer().getInactivePieces() > 0) && (board.addPiece(getCurrentPlayerTag()))){
            getCurrentPlayer().addPiece();
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void run() {
        this.interfacePaiSho = new GameFrame();
        interfacePaiSho.addListener(this);
    }

    @Override
    public void addPieceEvent() {
        if (addPiece()){
            this.interfacePaiSho.addPiece(getCurrentPlayerTag());
            this.interfacePaiSho.sendWarningMessage("Peça adicionada - Passando o turno");
            this.swapTurns();
        }else{
            this.interfacePaiSho.sendWarningMessage("Não foi possível adicionar uma peça");
        };
    }

    @Override
    public void forfeitEvent() {

    }

    @Override
    public void nextTurnEvent() {

    }

    @Override
    public void startGameEvent() {

    }
}
