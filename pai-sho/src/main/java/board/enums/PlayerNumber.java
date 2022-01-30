package main.java.board.enums;

public enum PlayerNumber {
    PLAYER_ONE, PLAYER_TWO;

    public static PlayerNumber getPlayerNumber(int i) {
        switch (i){
            case 1:
                return PLAYER_ONE;
            case 2:
                return PLAYER_TWO;
            default:
                return null;
        }
    }
}

