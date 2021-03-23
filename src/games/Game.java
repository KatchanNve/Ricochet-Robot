package games;

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private Player playerOne;
    private Player playerTwo;
    private BattleBoard board_playerOne;
    private BattleBoard board_playerTwo;
    private Player currentPlayer;

    public Game(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
        board_playerOne = new BattleBoard();
        board_playerOne.initBoard();
        board_playerTwo = new BattleBoard();
        board_playerTwo.initBoard();
    }

    public void shoot(int i, int j){
        Box box = getBoardOpponent().getBoard()[i][j];
        box.setTouch(true);
        currentPlayer.addListShotElement(i,j);
    }

    public boolean isValid(int i, int j){
        if(i < 0 || i > 9 || j < 0 || j > 9){
            return false;
        }
        else{
            Pair<Integer, Integer> pair = new Pair<Integer,Integer>(i,j);
            return !currentPlayer.getListShot().contains(pair);
        }
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Votre coup ?");
        String shot;
        shot = scanner.next();
        if(shot.length() != 2 && (shot.startsWith("A") || shot.startsWith("B") || shot.startsWith("C") || shot.startsWith("D") ||
                shot.startsWith("E") || shot.startsWith("F") || shot.startsWith("G") || shot.startsWith("H") || shot.startsWith("I")
                || shot.startsWith("J"))){
            if(shot.endsWith("0") ||shot.endsWith("1") || shot.endsWith("2") ||shot.endsWith("3") ||shot.endsWith("4") ||shot.endsWith("5") ||
                    shot.endsWith("6") ||shot.endsWith("7") ||shot.endsWith("8") ||shot.endsWith("9")){
                Pair<Integer,Integer> pair = translation(shot);

            }
        }
    }

    public Pair<Integer,Integer> translation(String shot){
        String[] list = shot.split("");
        int j = Integer.parseInt(list[1]);
        int i = Integer.parseInt(list[0]) - 44;
        return new Pair<Integer,Integer>(i,j);
    }

    public boolean isOver(){
        return currentPlayer.getFleet().getlistShip().isEmpty();
    }

    public Player getWinner(){
        if(isOver()){
            return getOpponent();
        }
        return null;
    }

    public BattleBoard getBoard_playerOne() {
        return board_playerOne;
    }

    public BattleBoard getBoard_playerTwo() {
        return board_playerTwo;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Player getOpponent(){
        if(currentPlayer.equals(playerOne)){
            return playerTwo;
        }
        else{
            return playerOne;
        }
    }

    public BattleBoard getBoardOpponent(){
        if(currentPlayer.equals(playerOne)){
            return board_playerTwo;
        }
        else{
            return board_playerOne;
        }
    }
}
