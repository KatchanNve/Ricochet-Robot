package games;

public class Ship implements Box {

    String type;
    int size;
    int life;
    // rajouter direction

    public Ship(String type, int size, int life) {
        this.type = type;
        this.size = size;
        this.life = life;
    }

}
