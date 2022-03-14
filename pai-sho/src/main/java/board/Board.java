package main.java.board;

import main.java.board.enums.PlayerNumber;
import main.java.PaiShoEventListener;
import org.apache.commons.collections.ListUtils;
import main.java.board.Piece;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    protected Position[][] positions;
    protected static final int size = 19;
    protected static final int matrix_offset = size/2;
    public Player player1, player2;
    protected boolean isRunning;

    /**
     * Classe encarregada de cuidar das operações que ocorrem na matriz do tabuleiro
     */
    public Board(){
        this.isRunning = true;
        this.positions = new Position[size][size];
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                this.positions[x][y] = new Position(x-(size/2), y-(size/2));
            }
        }
    }

    public void setPlayers(PlayerNumber p1, PlayerNumber p2){
        this.player1 = new Player(new ArrayList<Piece>(), p1);
        this.player2 = new Player(new ArrayList<Piece>(), p2);
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
            piece.setPosition(this.positions[x+matrix_offset][y+matrix_offset]);
            if (piece.playerNumber == player1.player_num){
                player1.addActivePiece(piece);
            } else{
                player2.addActivePiece(piece);
            }
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
        if (checkIfPosIsOccupied(x2, y2) && checkIfPosIsOccupied(x1, y1)){
            return (getPiece(x1, y1).playerNumber != getPiece(x2, y2).playerNumber);
        } else{
            return false;
        }

    }

    private boolean isBetweenHarmony(Piece p1, Piece p2, Piece p3){
        if(p1 != p3 && p2 != p3){
            if(p1.position().getY() == p2.position().getY() && p3.position().getY() == p2.position().getY()){
                int[] aux = {p1.position().getX(), p2.position().getX(), p3.position().getX()};
                Arrays.sort(aux);
                if(aux[1] ==  p3.position().getX()){
                    return true;
                }else {
                    return false;
                }
            } else if (p1.position().getX() == p2.position().getX() && p3.position().getX() == p2.position().getX()){
                int[] aux = {p1.position().getY(), p2.position().getY(), p3.position().getY()};
                Arrays.sort(aux);
                return aux[1] == p3.position().getY();
                }
             else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void verifyHarmonies(Player pl1, Player pl2){
        System.out.println("contagem de harmonias iniciada");
        ArrayList<Piece> my_pieces = pl1.getActive_pieces();
        pl1.cleanHarmonies();
        pl1.clearHarmonyPosition();

        for (int i = 0; i < my_pieces.size(); i++){
            ArrayList<Piece> compare_pieces = pl1.getActive_pieces();
            Piece p1 = my_pieces.get(i);

            for (int k = 0; k < compare_pieces.size();k++){
                Piece p2 = compare_pieces.get(k);

                if(p1.position().getX() == p2.position().getX() || p1.position().getY() == p2.position().getY()){
                    if(p1.position().area() != p2.position().area()) {

                        ArrayList<Piece> other_pieces = pl2.getActive_pieces();
                        ArrayList<Piece> sum_pieces = (ArrayList<Piece>) ListUtils.union(my_pieces, other_pieces);

                        Boolean flag = false;
                        for (Piece sum_piece : sum_pieces) {
                            flag = isBetweenHarmony(p1, p2, sum_piece);

                            if (flag) {
                                break;
                            }
                        }

                        if(!flag){
                            pl1.sumHarmonies(p1.position().area(), p2.position().area());
                            ArrayList<Position> h_positions = new ArrayList<Position>();
                            h_positions.add(p1.position());
                            h_positions.add(p2.position());
                            pl1.addHarmonyPosition(h_positions);
                        }

                    }
                }
            }

        }
    }

    public void erase_player_piece(int x, int y){
        if(getPiece(x, y).playerNumber == this.player2.player_num){
            this.player2.removeActivePiece(getPiece(x, y));
            this.player2.addInactivePieces(1);
        }else{
            this.player1.removeActivePiece(getPiece(x, y));
            this.player1.addInactivePieces(1);
        }
    }

    public PlayerNumber verifyHarmoniesPlayers(){
        verifyHarmonies(player1, player2);
        verifyHarmonies(player2, player1);
        if(player1.amountOfHarmonies() == 4){
            return this.player1.player_num;
        }else if(player2.amountOfHarmonies() == 4){
            return this.player2.player_num;
        }
        return null;
    }

    public boolean isRunning(){
        return this.isRunning;
    }

    public void setRunning(){
        this.isRunning = !this.isRunning;
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

            if ((checkIfPosIsOccupied(x1, y1)) && (!checkIfPosIsOccupied(x2, y2)) && checkMovementValidity(x1, y1, x2, y2)) {
                this.positions[x2 + matrix_offset][y2 + matrix_offset].occupyPostition(getPiece(x1, y1));
                getPiece(x1, y1).setPosition(this.positions[x2 + matrix_offset][y2 + matrix_offset]);
                getPosition(x1, y1).freePosition();
                return true;
            }
            //Caso para movimentos que conquistam uma peça
            if ((checkIfPosIsOccupied(x1, y1)) && (checkIfPosIsOccupied(x2, y2)) && (checkIfPiecesAreFromDifferentPlayers(x1, y1, x2, y2))) {
                erase_player_piece(x2, y2);
                getPosition(x2, y2).freePosition();
                this.positions[x2 + matrix_offset][y2 + matrix_offset].occupyPostitionAndTakePiece(getPiece(x1, y1));
                getPosition(x1, y1).freePosition();
                getPiece(x2, y2).setPosition(this.positions[x2 + matrix_offset][y2 + matrix_offset]);

                return true;
            }
            return false;

    }

}
