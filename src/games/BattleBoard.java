package games;

import java.util.Arrays;
import java.util.HashMap;

public class BattleBoard {

    @SuppressWarnings("all")
    private Box[][] board;

    public BattleBoard() {
        this.board = new Box[10][10];
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

    public void printBoard(){
        int countAlpha = 0;
        System.out.print("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print("\n");
            String c = Character.toString(countAlpha + 'A');
            System.out.print(c);
            countAlpha++;
            for (int j = 0; j < 10; j++) {
                Object box =  board[i][j];
                if(box instanceof ShipBox){
                    System.out.print(" \033[0;31mO\033[0m");
                }
                else if(box instanceof Void){
                    System.out.print(" \033[0;31mX\033[0m");
                }
            }
        }
        System.out.println("\n");
    }
}
