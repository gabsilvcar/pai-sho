package main.java.visual;

public enum ProjectResources {
    DARK_LOTUS_TILE("dark-lotus-tile.png"),
    DARK_LOTUS_TILE_ALT("dark-lotus-tile-alt.png"),
    APPLICATION_ICON("iroh.jpg"),
    APPLICATION_FRAME("frame.png");

    public final String value;
    ProjectResources(String name) {
        value =  name;
    }
}
