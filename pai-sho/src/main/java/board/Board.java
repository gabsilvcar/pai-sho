package main.java.board;

import main.java.board.enums.PlayerNumber;
import main.java.PaiShoEventListener;

public class Board {
    protected Position[][] positions;
    private static final int size = 19;
    private static final int matrix_offset = size/2;
    protected Player player1, player2; //TODO

    /**
     * Classe encarregada de cuidar das operações que ocorrem na matriz do tabuleiro
     */
    public Board(){
        this.positions = new Position[size][size];
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                this.positions[x][y] = new Position(x-(size/2), y-(size/2));
            }
        }
    }

    /**
     * Checa se um movimento é válido.
     * Um movimento horizontal válido pode ser de até 4 casas
     * Um movimento diagonal válido pode ser de até 2 casas
     *
     * @param x1 A posição X inicial
     * @param y1 A posição Y inicial
     * @param x2 A posição X para qual o usuário está tentando mover a peça
     * @param y2 A posição Y para qual o usuário está tentando mover a peça
     *
     * @return Um booleano indicando a validez da ação
     */
    private boolean checkMovementValidity(int x1, int y1,  int x2, int y2) {
        int x_difference = Math.abs(x1 - x2);
        int y_difference = Math.abs(y1 - y2);
        return (((x_difference + y_difference) <= 4));
    }

    private boolean checkIfPosIsOccupied(int x, int y) {
        return (this.positions[x+matrix_offset][y+matrix_offset].getPiece() != null);
    }

    /**
     * Adiciona uma peça numa posição do tabuleiro
     *
     * @param x A posição X relativa ao tabuleiro (-9 até 9)
     * @param y A posição Y relativa ao tabuleiro (-9 até 9)
     * @param piece A peça que está tentará ser inserida
     *
     * @return Um booleano indicando a validez da ação (falso caso a posição já esteja ocupada)
     */
    public boolean addPieceInPos(int x, int y, Piece piece) {
        if(checkIfPosIsOccupied(x, y)) {
            return false;
        } else {
            this.positions[x+matrix_offset][y+matrix_offset].occupyPostition(piece);
            return true;
        }
    }
    /**
     * Cria uma peça nova para o jogador no evento de uma nova peça ser adicionada ao tabuleiro
     * @see PaiShoEventListener
     */
    public boolean addPiece(PlayerNumber playerNumber) {
        switch (playerNumber) {
            case PLAYER_ONE:
                return addPieceInPos(0, 7, new Piece(playerNumber));
            case PLAYER_TWO:
                return addPieceInPos(0, -7, new Piece(playerNumber));
        }
        return false;
    }

    /**
     * Retorna uma posição do tabuleiro
     *
     * @param x A posição X relativa ao tabuleiro (-9 até 9)
     * @param y A posição Y relativa ao tabuleiro (-9 até 9)
     *
     * @return A posição
     */
    public Position getPosition(int x, int y){
        return this.positions[x+matrix_offset][y+matrix_offset];
    }

    /**
     * Retorna uma peça do tabuleiro
     *
     * @param x A posição X relativa ao tabuleiro (-9 até 9)
     * @param y A posição Y relativa ao tabuleiro (-9 até 9)
     *
     * @return A peça que está na posição, pode ser nulo
     */
    public Piece getPiece(int x, int y){
        return this.positions[x+matrix_offset][y+matrix_offset].getPiece();
    }

    private boolean checkIfPiecesAreFromDifferentPlayers(int x1, int y1, int x2, int y2) {
        return (getPiece(x1, y1).playerNumber != getPiece(x2, y2).playerNumber);
    }

    /**
     * Move uma peça para uma posição do tabuleiro
     *
     * @param x1 A posição X relativa ao tabuleiro (-9 até 9)
     * @param y1 A posição Y relativa ao tabuleiro (-9 até 9)
     * @param x2 A posição X relativa ao tabuleiro (-9 até 9)
     * @param y2 A posição Y relativa ao tabuleiro (-9 até 9)
     *
     * @return True se o movimento é válido
     */
    public boolean movePiece(int x1, int y1, int x2, int y2) {
        //Caso para movimentos normais (sem conquistar peça)
        if ((checkIfPosIsOccupied(x1, y1)) && (!checkIfPosIsOccupied(x2,y2)) && checkMovementValidity(x1,y1,x2,y2)) {
            this.positions[x2+matrix_offset][y2+matrix_offset].occupyPostition(getPiece(x1,y1));
            getPosition(x1,y1).freePosition();
            return true;
        }
        //Caso para movimentos que conquistam uma peça
        if ((checkIfPosIsOccupied(x1, y1)) && (checkIfPosIsOccupied(x2,y2)) && (checkIfPiecesAreFromDifferentPlayers(x1, y1, x2, y2))) {
            getPosition(x2,y2).freePosition();
            this.positions[x2+matrix_offset][y2+matrix_offset].occupyPostitionAndTakePiece(getPiece(x1,y1));
            getPosition(x1,y1).freePosition();
            return true;
        }
        System.out.println(checkIfPiecesAreFromDifferentPlayers(x1, y1, x2, y2) + "AAA");
        return false;
    }

}
