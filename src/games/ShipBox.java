package games;

public class ShipBox implements Box{

    boolean touch;
    Pair<Integer,Integer> position;

    public ShipBox(int i, int j){
        position = new Pair<Integer,Integer>(i,j);
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

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public int getPosition(int index) {
        if(index == 0){
            return position.getA();
        }
        else if(index == 1){
            return position.getB();
        }
        else{
            return -1;
        }
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public void setPosition(int i, int j) {
        this.position = new Pair<Integer,Integer>(i,j);
    }
}
