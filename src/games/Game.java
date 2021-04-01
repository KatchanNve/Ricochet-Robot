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
        Shoots validShoot = null;
        shot = scanner.next();
        try{
            validShoot = Shoots.valueOf(shot);
        }
        catch (IllegalArgumentException e){
            while (!isValidShoot(shot)){
                System.out.println("Ce coup est invalide, choisisez en un autre !");
                shot = scanner.next();
                try{
                    validShoot = Shoots.valueOf(shot);
                } catch (IllegalArgumentException ignored) {
                }
            }
        }
        System.out.println(validShoot);

    }

    //2 methode / transform int (-1) / et la partie lettre en chiffre

    public static void main(String[] args) {
        Fleet fleetOne = new Fleet();
        Fleet fleetTwo = new Fleet();
        Player playerOne = new Player(fleetOne);
        Player playerTwo = new Player(fleetTwo);
        Game game = new Game(playerOne,playerTwo);
        //game.play();

        //game.getPlayerOne().getFleet().getlistShip().get(0).getListShipBox().get(0).setPosition(2,2);
        game.getBoard_playerOne().printBoard();
        game.getBoard_playerTwo().printBoard();
    }

    public boolean isValidShoot(String shot){
        try{
            Shoots validShoot = Shoots.valueOf(shot);
            return true;
        }
        catch (IllegalArgumentException e){
            return false;
        }
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
