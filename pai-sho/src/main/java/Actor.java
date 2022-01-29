package main.java;

public interface Actor {
    void setTurn(Boolean flag);
    Boolean isTurn();
    void setManager(GameManager manager);
}
