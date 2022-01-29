package main.java;

import main.java.board.Board;
import main.java.moveset.Move;

public class GameManager implements Runnable {
    protected Board board;
    protected LocalPlayer local_player;
    protected RemotePlayer remote_player;


    public GameManager(Board board, LocalPlayer local_player, RemotePlayer remote_player){
        this.board = board;
        this.local_player = local_player;
        this.remote_player = remote_player;
        local_player.setManager(this);
        remote_player.setManager(this);
    }

    public void nextMove(Move move) {
        if(local_player.isTurn()){
            if (move.executeMove(board)){
                move.render(local_player.gui);
                remote_player.netgames.enviarJogada(move);
                swapTurns();
            } else {
                System.out.println("Invalido");
            }
        }
    }

    private void swapTurns() {
        local_player.setTurn(local_player.isTurn());
        remote_player.setTurn(!remote_player.isTurn());
    }

    public void startConnection(String address, String name) {
        String info = remote_player.netgames.conectar(address, name);
        local_player.showWarning(info);
    }

    public void stopConnection() {
        remote_player.netgames.desconectar();
        local_player.showWarning("Conexão encerrada");
    }

    public void startGame() {
        remote_player.netgames.iniciarPartida();
    }

    @Override
    public void run() {

    }
}
