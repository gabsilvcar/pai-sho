package main.java;

import main.java.board.Board;
import main.java.board.enums.PlayerNumber;
import main.java.moveset.Move;

public class GameManager implements Runnable {
    protected Board board;
    protected LocalActor local_player;
    protected RemoteActor remote_player;


    public GameManager(Board board, LocalActor local_player, RemoteActor remote_player){
        this.board = board;
        this.local_player = local_player;
        this.remote_player = remote_player;
        local_player.setManager(this);
        local_player.gui.menuPanel.setEnableButtons(false);
        remote_player.setManager(this);
    }

    public void nextMove(Move move) {
        if (move.executeMove(board)){
            move.render(local_player.gui);
            remote_player.netgames.enviarJogada(move);
            swapTurns();
        } else {
            System.out.println("Invalido");
        }

    }

    private void swapTurns() {
        local_player.setTurn(!local_player.isTurn());
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

    public void setPlayerNumbers(int number) {
        this.local_player.playerNumber = PlayerNumber.getPlayerNumber(number);
        this.remote_player.playerNumber = PlayerNumber.getPlayerNumber((number % 2) + 1);
        System.out.println(PlayerNumber.getPlayerNumber((number % 2) + 1));

    }

    public void setupGame() {
        Boolean flag = (local_player.playerNumber == PlayerNumber.PLAYER_ONE);
        local_player.setTurn(flag);
        remote_player.setTurn(!flag);
    }

    public void setEnableButtonsInInterface(Boolean flag){
        local_player.gui.menuPanel.setEnableButtons(true);
    }

    @Override
    public void run() {

    }
}
