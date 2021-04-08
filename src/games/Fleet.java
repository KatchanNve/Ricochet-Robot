package games;

import java.util.ArrayList;

public class Fleet {
    ArrayList<Ship> fleet = createFleet();
    int nbrShip;

    public Fleet(ArrayList<Ship> fleet) {
        this.fleet = fleet;
        nbrShip = 5;
    }
    public Fleet() {
        fleet = createFleet();
        nbrShip = 5;
    }

    public ArrayList<Ship> createFleet() {
        ArrayList<Ship> fleet = new ArrayList<Ship>();
        Ship aircraft = new Ship("AIRCRAFT", 5,5);
        Ship cruiser = new Ship("CRUISER", 4, 4);
        Ship antiDestroyer1 = new Ship("ANTIDESTROYER", 3, 3);
        Ship antiDestroyer2 = new Ship("ANTIDESTROYER", 3, 3);
        Ship destroyer = new Ship("DESTROYER", 2, 2);

        fleet.add(aircraft);
        fleet.add(cruiser);
        fleet.add(antiDestroyer1);
        fleet.add(antiDestroyer2);
        fleet.add(destroyer);

        return fleet;
    }

    public void setNbrShip(int nbrShip) {
        this.nbrShip = nbrShip;
    }

    public void setNbrShipOccurence() {
        this.nbrShip--;
    }

    public int getNbrShip() {
        return nbrShip;
    }

    public boolean isSink() {
        return nbrShip <= 0;
    }

    public ArrayList<Ship> getlistShip() {
        return fleet;
    }
}
