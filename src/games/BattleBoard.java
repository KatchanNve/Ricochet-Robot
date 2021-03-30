package games;

import java.util.HashMap;

public class BattleBoard {

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

    public static void main(String[] args) {
        BattleBoard batBoard = new BattleBoard();
        batBoard.initBoard();
        System.out.println(batBoard.getBoard()[0][0].toString());
    }
}
