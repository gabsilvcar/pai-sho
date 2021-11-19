package main.java.board.enums;

public enum Side {
    ATTACKER(0), DEFENDER(1);
    public int color_id;
    Side(int color_id) {
        color_id = color_id;
    }
}