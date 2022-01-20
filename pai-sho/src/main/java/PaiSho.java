package main.java;

import main.java.visual.InterfacePaiSho;

import java.awt.*;
import java.util.logging.Logger;


public class PaiSho {
    public static final String game_name = "Pai-sho";
    public static Logger logger = Logger.getLogger(game_name);

    public static void main(String [] args) {
        EventQueue.invokeLater(new InterfacePaiSho());
    }

}
