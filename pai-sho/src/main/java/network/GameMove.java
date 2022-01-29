package main.java.network;

import br.ufsc.inf.leobr.cliente.Jogada;
import org.apache.commons.lang3.tuple.Pair;


public class GameMove implements Jogada {
    private static final long serialVersionUID = 1L;
    Pair<Integer, Integer> fromPoint;
    Pair<Integer, Integer> toPoint;

    public Pair<Integer, Integer> getFromPoint() {
        return fromPoint;
    }

    public void setFromPoint(Pair<Integer, Integer> fromPoint) {
        this.fromPoint = fromPoint;
    }

    public Pair<Integer, Integer> getToPoint() {
        return toPoint;
    }

    public void setToPoint(Pair<Integer, Integer> toPoint) {
        this.toPoint = toPoint;
    }

    public void setMove(Pair<Integer, Integer> fromPoint, Pair<Integer, Integer> toPoint){
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
    }
}
