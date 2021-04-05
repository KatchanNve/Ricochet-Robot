package games;

public class Void implements Box{

    private boolean touch;
    private boolean lockCase;

    public Void(){
        touch = false;
        lockCase = false;
    }

    @Override
    public boolean getTouch() {
        return touch;
    }

    @Override
    public void setTouch(boolean touch) {
        this.touch = touch;
    }

    @Override
    public boolean isLockCase() {
        return lockCase;
    }

    @Override
    public void setLockCase(boolean lockCase) {
        this.lockCase = lockCase;
    }
}
