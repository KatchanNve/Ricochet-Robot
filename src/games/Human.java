package games;

import java.util.ArrayList;
import java.util.Scanner;

import static games.Game.translation;

public class Human implements Player{
    private final String NAME;
    private Fleet fleet;
    private ArrayList<Pair<Integer,Integer>> listShot;

    public Human(Fleet fleet, String name){
        this.NAME = name;
        this.fleet = fleet;
        this.listShot = new ArrayList<Pair<Integer, Integer>>();
        initListShot();
    }

    public Fleet getFleet() {
        return fleet;
    }

    @Override
    public Pair<Integer, Integer> getShoot() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Votre coup ?");
        String shot;
        Pair<Integer, Integer> translateShot;
        shot = scanner.next();
        translateShot = translation(shot);
        while (translateShot == null ) {
            System.out.println("Votre coup n'est pas valide, veuillez suivre l'exemple suivant : Lettre (A-J) + Nombre (1-10) ");
            shot = scanner.next();
            translateShot = translation(shot);
        }
        return translateShot;
    }

    public void initListShot(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.listShot.add(new Pair<Integer, Integer>(i,j));
            }
        }
    }

    public void deleteShotElement(int i, int j){
        listShot.remove(new Pair<Integer,Integer>(i,j));
    }

    public void deleteShotElement(Pair<Integer,Integer> pair){
        listShot.remove(pair);
    }

    public String toString() {
        return NAME;
    }
}
