package main.java;

import main.java.board.Board;
import main.java.board.Piece;
import main.java.board.Position;
import main.java.board.enums.PlayerNumber;
import main.java.moveset.AddPiece;
import main.java.moveset.Forfeit;
import main.java.moveset.Move;
import main.java.moveset.MovePiece;
import org.apache.http.impl.io.SocketOutputBuffer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Equivalente ao GerenciadorDeJogo dos diagramas
 *
 * Classe que liga o tabuleiro, a interface grafica e o netgames
 */
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

    /**
     * ProximaJogada
     * Recebe um movimento e o executa
     *
     * @param move o tipo do movimento
     */
    public void nextMove(Move move) {
            if(move instanceof Forfeit){
                Forfeit aux = (Forfeit) move;
                if(this.local_player.playerNumber == aux.playerNumber()){
                    this.local_player.winner = true;
                }else{
                    this.remote_player.winner = true;
                }

            }
            if (move.executeMove(board)) {
                move.render(local_player.gui);
                remote_player.netgames.enviarJogada(move);
                swapTurns();
            } else {
                System.out.println("Invalido");
            }
            this.verifyWinner();

    }

    /**
     * TrocarTurnos
     * Troca o jogador ativo
     */
    private void swapTurns() {
        local_player.setTurn(!local_player.isTurn());
        remote_player.setTurn(!remote_player.isTurn());
    }

    /**
     * IniciarConexão
     * Conecta com o netgames e envia uma notificação para a interface sobre a conexão
     *
     * @param address endereço do servidor
     * @param name o nome do jogador
     */
    public void startConnection(String address, String name) {
        String info = remote_player.netgames.conectar(address, name);
        local_player.showWarning(info);
    }

    /**
     * PararConexão
     * Encerra a conexão com o netgames e envia uma notificação para a interface
     */
    public void stopConnection() {
        remote_player.netgames.desconectar();
        local_player.showWarning("Conexão encerrada");
    }

    /**
     * ComeçarJogo
     * Inicializa a partida via netgames
     */
    public void startGame() {
        remote_player.netgames.iniciarPartida();
    }

    /**
     * SetNumeroDosJogadores
     * Atrela os titulos de 'jogador 1' e de 'jogador 2',
     * isto é feito de acordo com a ordem de entrada no servidor
     */
    public void setPlayerNumbers(int number) {
        PlayerNumber lp = PlayerNumber.getPlayerNumber(number);
        PlayerNumber rp = PlayerNumber.getPlayerNumber((number % 2) + 1);
        this.local_player.playerNumber = lp;
        this.remote_player.playerNumber = rp;
        this.board.setPlayers(lp, rp);
        System.out.println(PlayerNumber.getPlayerNumber((number % 2) + 1));
    }

    /**
     * SetupJogo
     * Inicializa os jogadores e o tabuleiro
     */
    public void setupGame() {
        Boolean flag = (local_player.playerNumber == PlayerNumber.PLAYER_ONE);
        local_player.setTurn(flag);
        remote_player.setTurn(!flag);
        addStarterTiles();
    }


    /**
     * OrganizarTabuleiro
     * Adiciona as peças nas posições iniciais ao começar uma partida
     */
    public void addStarterTiles() {
        List<Position> positions = new ArrayList<Position>();

        positions.add(new Position(2,5));
        positions.add(new Position(4,3));
        positions.add(new Position(6,5));
        positions.add(new Position(-2,5));
        positions.add(new Position(-4,3));
        positions.add(new Position(-6,5));

        for (Position pos : positions) {
            Piece p1 = new Piece(PlayerNumber.PLAYER_TWO);
            Piece p2 = new Piece(PlayerNumber.PLAYER_ONE);
            board.addPieceInPos(pos.getX(), pos.getY(), new Piece(PlayerNumber.PLAYER_TWO));
            local_player.gui.boardPanel.createTile(pos.getX(), pos.getY(), PlayerNumber.PLAYER_TWO);

            board.addPieceInPos(-pos.getX(), -pos.getY(), new Piece(PlayerNumber.PLAYER_ONE));
            local_player.gui.boardPanel.createTile(-pos.getX(), -pos.getY(), PlayerNumber.PLAYER_ONE);
        }
    }

    public void verifyHarmonies(){
        if(this.board.verifyHarmoniesPlayers() == local_player.playerNumber){
            this.local_player.winner = true;
        }else if (this.board.verifyHarmoniesPlayers() == remote_player.playerNumber){
            this.remote_player.winner = true;
        }
    }

    public void verifyWinner(){
        this.verifyHarmonies();
        if(this.local_player.winner || this.remote_player.winner){
            PlayerNumber n = this.remote_player.playerNumber;
            if(this.local_player.winner){
                n = this.local_player.playerNumber;
            }
            local_player.showWarning("Jogador "+ n +" venceu");
            this.board.setRunning();
            this.remote_player.netgames.encerrarPartida();
            this.local_player.gui.sendMessage("A partida acabou", Color.CYAN);
        }
    }
    /**
     * Habilita os botões da interface, não está mais sendo usado
     */
    @Deprecated
    public void setEnableButtonsInInterface(Boolean flag){
        local_player.gui.menuPanel.setEnableButtons(true);
    }

    /**
     * É necessário que uma classe seja {@link Runnable} para que a interface não seja encerrada após um frame de execução
     */
    @Override
    public void run() {

    }
}
