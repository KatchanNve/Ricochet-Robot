package games;

public class Void implements Box{

    private boolean touch;

    public Void(){
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
