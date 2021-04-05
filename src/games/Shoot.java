package games;

public class Shoot {

    ShipBox shipBox;
    Pair<Integer, Integer> position;

    public Shoot(ShipBox shipBox, Pair<Integer, Integer> position){
        this.shipBox = shipBox;
        this.position = position;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public ShipBox getShipBox() {
        return shipBox;
    }
}
