package games;

public class ShipBox implements Box {

    boolean touch;
    boolean lockcase;
    Pair<Integer, Integer> position;
    Ship ship;

    public ShipBox(int i, int j, Ship ship) {
        position = new Pair<Integer, Integer>(i, j);
        touch = false;
        this.ship = ship;
        lockcase = true;
    }

    public Ship getShip() {
        return ship;
    }

    @Override
    public boolean getTouch() {
        return touch;
    }

    @Override
    public void setTouch(boolean touch) {
        this.touch = touch;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public int getPosition(int index) {
        if (index == 0) {
            return position.getA();
        } else if (index == 1) {
            return position.getB();
        } else {
            return -1;
        }
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public void setPosition(int i, int j) {
        this.position = new Pair<Integer, Integer>(i, j);
    }

    @Override
    public void setLockCase(boolean lockcase) {
        this.lockcase = lockcase;
    }

    @Override
    public boolean isLockCase() {
        return false;
    }
}
