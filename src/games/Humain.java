package games;

public class Humain extends Player{
    private final String NAME;

    public Humain(Fleet fleet,String name){
        super(fleet);
        this.NAME = name;
    }

    public String getNAME() {
        return NAME;
    }
}
