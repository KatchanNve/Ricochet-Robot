package games;

public class Human extends Player{
    private final String NAME;

    public Human(Fleet fleet, String name){
        super(fleet);
        this.NAME = name;
    }

    public String getNAME() {
        return NAME;
    }
}
