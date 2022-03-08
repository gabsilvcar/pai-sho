package main.java;

import main.java.board.Board;
import main.java.board.Piece;
import main.java.board.Position;
import main.java.board.enums.PlayerNumber;
import main.java.moveset.Move;

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
        if (move.executeMove(board)){
            move.render(local_player.gui);
            remote_player.netgames.enviarJogada(move);
            swapTurns();
        } else {
            System.out.println("Invalido");
        }

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
        this.local_player.playerNumber = PlayerNumber.getPlayerNumber(number);
        this.remote_player.playerNumber = PlayerNumber.getPlayerNumber((number % 2) + 1);
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
            board.addPieceInPos(pos.getX(), pos.getY(), new Piece(PlayerNumber.PLAYER_TWO));
            local_player.gui.boardPanel.createTile(pos.getX(), pos.getY(), PlayerNumber.PLAYER_TWO);

            board.addPieceInPos(-pos.getX(), -pos.getY(), new Piece(PlayerNumber.PLAYER_ONE));
            local_player.gui.boardPanel.createTile(-pos.getX(), -pos.getY(), PlayerNumber.PLAYER_ONE);
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
