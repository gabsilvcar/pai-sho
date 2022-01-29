package main.java.moveset;

import br.ufsc.inf.leobr.cliente.Jogada;
import main.java.board.Board;
import main.java.visual.GameFrame;

/**
 * Movimentos são a forma de comunicar como uma jogada ocorreu, cada jogada tem uma implementação única de como deve
 * tratar a matriz do tabuleiro e como deve ser renderizada pelo cliente
 */
public interface Move extends Jogada {
    boolean executeMove(Board board);
    void render(GameFrame frame);
}
