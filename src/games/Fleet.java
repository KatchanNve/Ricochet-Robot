package games;

import java.util.ArrayList;

public class Fleet {
    ArrayList<Ship> fleet = createFleet();

    public Fleet(ArrayList<Ship> fleet) {
        this.fleet = fleet;
    }

    public ArrayList<Ship> createFleet() {
        ArrayList<Ship> fleet = new ArrayList<Ship>();
        fleet.add(Ship.AIRCRAFT);
        fleet.add(Ship.CRUISER);
        fleet.add(Ship.ANTI_DESTROYER);
        fleet.add(Ship.ANTI_DESTROYER);
        fleet.add(Ship.DESTROYER);

        return fleet;
    }
}
