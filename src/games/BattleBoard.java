package games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BattleBoard {

    @SuppressWarnings("all")
    private Box[][] board;
    private Player player;
    static HashMap<Boolean,Pair<Integer,Integer>> delta;

    public BattleBoard(Player player) {
        this.player = player;
        this.board = new Box[10][10];
        delta = new HashMap<Boolean,Pair<Integer,Integer>>();
        delta.put(true,new Pair<Integer,Integer>(1,0));
        delta.put(false,new Pair<Integer,Integer>(0,1));
    }

    public Box[][] getBoard() {
        return board;
    }

    public void initBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = new Void();
            }
        }
    }

    public void placeFleet(){
        for(Ship ship : player.getFleet().getlistShip()){
            Random random = new Random();
            boolean direction = random.nextBoolean();
            Pair<Integer,Integer> thisDelta = delta.get(direction);
            ArrayList<Pair<Integer,Integer>> listvalidPoint = listValidBeginPoint(direction, ship.getSize());
            Pair<Integer, Integer> beginPoint = listvalidPoint.get(random.nextInt(listvalidPoint.size()));
            ship.setBeginPoint(beginPoint);
            ship.setDirection(direction);
            Pair<Integer, Integer> point = new Pair<Integer,Integer>(ship.getBeginPoint().getA(),ship.getBeginPoint().getB());
            for (int k = 0; k < ship.getSize(); k++) {
                board[point.getA()][point.getB()] = new ShipBox(point.getA(), point.getB(),ship);
                board[point.getA()][point.getB()].setLockCase(true);
                point.setA(point.getA() + thisDelta.getA());
                point.setB(point.getB() + thisDelta.getB());
            }
        }
    }

    public ArrayList<Pair<Integer,Integer>> listValidBeginPoint(boolean direction, int size){
        Pair<Integer,Integer> thisDelta = delta.get(direction);
        ArrayList<Pair<Integer,Integer>> listValidPoint = new ArrayList<Pair<Integer,Integer>>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boolean isValid = true;
                if(!board[i][j].isLockCase()){
                    Pair<Integer,Integer> point = new Pair<Integer,Integer>(i,j);
                    for (int k = 1; k < size; k++) {
                        point.setA(point.getA() + thisDelta.getA());
                        point.setB(point.getB() + thisDelta.getB());
                        if(!(point.getA() < 0) && !(point.getB() < 0) && !(point.getA() > 9) && !(point.getB() > 9)){
                            if(board[point.getA()][point.getB()].isLockCase()){
                                isValid = false;
                                break;
                            }
                        }
                        else{
                            isValid = false;
                            break;
                        }
                    }
                }
                else{
                    isValid = false;
                }

                if(isValid){
                    listValidPoint.add(new Pair<Integer, Integer>(i,j));
                }
            }

        }
        return listValidPoint;
    }

    //refaire avec un boolean

    public void printBoard(){
        printBoard(true);
    }

    public void printHiddenBoard(){
        printBoard(false);
    }

    private void printBoard(boolean show){
        int countAlpha = 0;
        System.out.println(player);
        System.out.print("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print("\n");
            String c = Character.toString(countAlpha + 'A');
            System.out.print(c);
            countAlpha++;
            for (int j = 0; j < 10; j++) {
                Object box =  board[i][j];
                if(box instanceof Box){
                    if(((Box) box).isTouch()){
                        if(box instanceof ShipBox){
                            System.out.print(" \033[0;31m0\033[0m");
                        }
                        else if(box instanceof Void){
                            System.out.print(" \033[0;32mX\033[0m");
                        }
                    }
                    else{
                        //show full
                        if(show) {
                            if (box instanceof ShipBox) {
                                System.out.print(" 0");
                            } else if (box instanceof Void) {
                                System.out.print(" \033[0;34mX\033[0m");
                            }
                        }
                        //hiden
                        else{
                            System.out.print(" .");
                        }
                    }
                }
            }
        }
        System.out.println("\n");
    }

}
