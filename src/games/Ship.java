package games;

public enum Ship {

    AIRCRAFT(5, 5), CRUISER(4, 4), ANTI_DESTROYER(3, 3), DESTROYER(2, 2);

    private final int size;
    private int life;
    // rajouter direction

    private Ship(int size, int life) {
        this.size = size;
        this.life = life;
    }

    public int getSize() {
        return this.size;
    }

    public int getLife() {
        return this.life;
    }

}
