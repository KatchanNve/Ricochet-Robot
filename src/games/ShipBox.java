package games;

public class ShipBox implements Box{

    boolean touch;

    public ShipBox(){
        touch = false;
    }

    @Override
    public boolean getTouch() {
        return touch;
    }

    @Override
    public void setTouch(boolean touch) {
        this.touch = touch;
    }
}
