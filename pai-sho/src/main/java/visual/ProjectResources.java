package main.java.visual;

public enum ProjectResources {
    BOARD("board.png"),
    DARK_LOTUS_TILE("dark-lotus-tile.png"),
    APPLICATION_ICON("iroh.jpg");

    public final String value;
    ProjectResources(String name) {
        value =  name;
    }
}
