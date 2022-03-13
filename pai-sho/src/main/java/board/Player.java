package main.java.board;

import main.java.board.enums.PlayerNumber;
import  main.java.board.Piece;

import java.util.ArrayList;
import java.util.Hashtable;


public class Player {
    protected ArrayList<Piece> active_pieces;
    protected int inactive_pieces;
    protected PlayerNumber player_num;
    protected Hashtable<String, Integer> harmonies;

    public Player(ArrayList<Piece> active_pieces, PlayerNumber player_num){
        this.active_pieces = active_pieces;
        this.player_num = player_num;
        this.inactive_pieces = 10; //TODO - Verificar a quantidade correta
        this.harmonies = new Hashtable<String, Integer>();
        this.harmonies.put("2_1", 0);
        this.harmonies.put("3_2", 0);
        this.harmonies.put("4_3", 0);
        this.harmonies.put("4_1", 0);
    }
    public void addActivePiece(Piece p){
        this.active_pieces.add(p);
    }
    public void addInactivePieces(Integer amount){
        this.inactive_pieces += amount;
    }
    public void removeActivePiece(Piece p){
        this.active_pieces.remove(p);
        System.out.println(this.active_pieces.size());
    }
    public void removeInactivePieces(Integer amount){
        this.inactive_pieces -= amount;
    }

    public ArrayList<Piece> getActive_pieces(){
        return this.active_pieces;
    }

    public void cleanHarmonies(){
        this.harmonies.replace("2_1", 0);
        this.harmonies.replace("3_2", 0);
        this.harmonies.replace("4_3", 0);
        this.harmonies.replace("4_1", 0);
    }

    public void sumHarmonies(Integer a1, Integer a2){
        if(a1 != 0 && a2 != 0) {
            String index;
            if (a1 > a2) {
                index = a1 + "_" + a2;
            } else {
                index = a2 + "_" + a1;
            }

            this.harmonies.replace(index, 1);
        }
    }

    public int amountOfHarmonies(){
        return (this.harmonies.get("2_1")+
                this.harmonies.get("3_2")+
                this.harmonies.get("4_3")+
                this.harmonies.get("4_1"));
    }
}
