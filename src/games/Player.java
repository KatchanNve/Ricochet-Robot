package games;

import java.util.ArrayList;

public class Player {

    private Fleet fleet;
    private int score;
    private ArrayList<Pair<Integer,Integer>> listShot;

    public Player(Fleet fleet){
        this.fleet = fleet;
        this.score = 0;
        this.listShot = new ArrayList<Pair<Integer, Integer>>();
    }


    public int getScore() {
        return score;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public ArrayList<Pair<Integer, Integer>> getListShot() {
        return listShot;
    }

    public void addListShotElement(int i, int j){
        Pair<Integer,Integer> pair = new Pair<Integer,Integer>(i,j);
        listShot.add(pair);
    }

    public void addListShotElement(Pair<Integer,Integer> pair){
        listShot.add(pair);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(){
        score++;
    }
}
