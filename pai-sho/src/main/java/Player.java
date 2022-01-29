package main.java;

public interface Player {
    void setTurn(Boolean flag);
    Boolean isTurn();
    void setManager(GameManager manager);
}
