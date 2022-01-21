package main.java.board;

public class Board {
    private Position[][] positions;
    private static final int size = 17;

    /**
     * Classe encarregada de cuidar das operações que ocorrem na matriz do tabuleiro
     */
    public Board(){
        this.positions = new Position[17][17];
        for(int x = 0; x < 17; x++){
            for(int y = 0; y < 17; y++){
                this.positions[x][y] = new Position(x-(size/2), y-(size/2));
            }
        }
    }
    public Position getPosition(int x, int y){
        return positions[x+(size/2)][y+(size/2)];
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

    private boolean checkIfPosIsOccupied(int x, int y){
        return (positions[x+(size/2)][y+(size/2)].getPiece() == null);
    }
    /**
     * Tenta mover uma peça e retorna o sucesso da operação
     * @param x1 A posição X inicial
     * @param y1 A posição Y inicial
     * @param x2 A posição X para qual o usuário está tentando mover a peça
     * @param y2 A posição Y para qual o usuário está tentando mover a peça
     *
     * @return Um booleano indicando a validez da ação
     */
    public boolean movePiece(int x1, int y1,  int x2, int y2){ //TODO Checar times antes de comer peça
        if(checkMovementValidity(x1, y1, x2, y2)){
            if(checkIfPosIsOccupied(x2, y2)){
                positions[x2+(size/2)][y2+(size/2)].occupyPostitionAndTakePiece(positions[x1+(size/2)][y1+(size/2)].getPiece());
                positions[x1+(size/2)][y1+(size/2)].freePosition();
                return true;
            }else{
                positions[x2+(size/2)][y2+(size/2)].occupyPostition(positions[x1+(size/2)][y1+(size/2)].getPiece());
                positions[x1+(size/2)][y1+(size/2)].freePosition();
                return true;
            }

        }else {
            return false;
        }
    }

}
