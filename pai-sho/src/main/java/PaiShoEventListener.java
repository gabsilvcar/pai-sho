package main.java;

public interface PaiShoEventListener {
    void addPieceEvent();
    void forfeitEvent();
    void startGameEvent();
    void connectionEvent(String address, String name);
    void disconnectionEvent();
}
