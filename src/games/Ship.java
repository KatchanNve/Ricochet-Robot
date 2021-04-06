package games;

import java.util.ArrayList;
import java.util.HashMap;

public class Ship {

    private String type;
    private int size;
    private int life;
    private Pair<Integer, Integer> beginPoint;
    private boolean direction;
    private ArrayList<ShipBox> listShipBox;
    // rajouter direction

    // direction true : vertical (1,0) / false : Horirontal (0,1)
    public Ship(String type, int size, int life) {
        this.type = type;
        this.size = size;
        this.life = life;
        this.beginPoint = null;
        this.direction = true;
    }

    public Ship(String type, int size, int life, Pair<Integer, Integer> beginPoint, boolean direction) {
        super();
        this.direction = direction;
        this.beginPoint = beginPoint;

    }

    public ArrayList<ShipBox> getListShipBox() {
        return listShipBox; // carre plus que bateau arrondi
    }

    public int getLife() {
        return life;
    }

    public int getSize() {
        return size;
    }

    public Pair<Integer, Integer> getBeginPoint() {
        return beginPoint;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setListShipBox(ArrayList<ShipBox> listShipBox) {
        this.listShipBox = listShipBox;
    }

    public String getType() {
        return type;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setBeginPoint(Pair<Integer, Integer> beginPoint) {
        this.beginPoint = beginPoint;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }
}
