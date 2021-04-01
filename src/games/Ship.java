package games;

import java.util.ArrayList;

public class Ship{

    String type;
    int size;
    int life;
    ArrayList<ShipBox> listShipBox;
    // rajouter direction

    public Ship(String type, int size, int life) {
        this.type = type;
        this.size = size;
        this.life = life;
    }

    public ArrayList<ShipBox> getListShipBox() {
        return listShipBox; //carre plus que bateau arrondi
    }

    public int getLife() {
        return life;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public void setLife(int life) {
        this.life = life;
    }


}
