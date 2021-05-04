package games;

import java.util.ArrayList;
import java.util.Random;

public class Computer implements Player{
    private Fleet fleet;
    private ArrayList<Pair<Integer,Integer>> listShot;

    public Computer(Fleet fleet){
        this.fleet = fleet;
        this.listShot = new ArrayList<Pair<Integer, Integer>>();
        initListShot();
    }

    public Fleet getFleet() {
        return fleet;
    }

    @Override
    public Pair<Integer, Integer> getShoot() {
        Random random = new Random();
        Pair<Integer, Integer> shot = listShot.get(random.nextInt(listShot.size()));
        listShot.remove(shot);
        return shot;
    }

    public ArrayList<Pair<Integer, Integer>> getListShot() {
        return listShot;
    }

    public void deleteShotElement(int i, int j){
        listShot.remove(new Pair<Integer,Integer>(i,j));
    }

    public void deleteShotElement(Pair<Integer,Integer> pair){
        listShot.remove(pair);
    }

    public void initListShot(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.listShot.add(new Pair<Integer, Integer>(i,j));
            }
        }
    }


}
