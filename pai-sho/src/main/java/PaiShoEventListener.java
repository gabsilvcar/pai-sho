package main.java;

import main.java.visual.TileButton;

/**
 * PaiShoEventListener
 * Eventos lançados pela interface grafica que causam uma ação
 * (movimento) no jogo ou uma mudança de estados no netgames
 */
public interface PaiShoEventListener {

    /**
     * AdicionarPeçaEvento
     * Tenta adicionar uma peça ao tabuleiro de acordo com o jogador atual
     */
    void addPieceEvent();

    /**
     * MoverPeçaEvento
     * Tenta mover uma peça
     */
    void movePieceEvent(TileButton selectedPiece, int x, int y);

    /**
     * MoverPeçaEvento
     * Tenta mover uma peça para um espaço já ocupado
     */
    void movePieceEvent(TileButton selectedPiece, TileButton targetPiece);

    /**
     * DesistenciaEvento
     * Jogador atual desiste e perde a partida
     */
    void forfeitEvent();

    /**
     * IniciarJogoEvento
     * Inicia uma partida
     */
    void startGameEvent();

    /**
     * ConexãoEvento
     * Há uma tentativa de conectar a um servidor
     */
    void connectionEvent(String address, String name);

    /**
     * DesconexãoEvento
     * Desconecta o jogador da partida, caso esteja em uma
     */
    void disconnectionEvent();

}
