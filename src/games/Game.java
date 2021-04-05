package games;

import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private Player playerOne;
    private Player playerTwo;
    private BattleBoard board_playerOne;
    private BattleBoard board_playerTwo;
    private Player currentPlayer;

    public static void main(String[] args) {
        Fleet fleetOne = new Fleet();
        Fleet fleetTwo = new Fleet();
        Player playerOne = new Player(fleetOne);
        Player playerTwo = new Player(fleetTwo);
        Game game = new Game(playerOne,playerTwo);
        game.getBoard_playerOne().printBoard();
        game.getBoard_playerTwo().printBoard();
        game.play();
        //game.getPlayerOne().getFleet().getlistShip().get(0).getListShipBox().get(0).setPosition(2,2);
        game.getBoard_playerOne().printBoard();
        game.getBoard_playerTwo().printBoard();
    }

    public Game(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
        board_playerOne = new BattleBoard(playerOne);
        board_playerOne.initBoard();
        board_playerOne.placeFleet();
        board_playerTwo = new BattleBoard(playerTwo);
        board_playerTwo.initBoard();
        board_playerTwo.placeFleet();
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
        Pair<Integer,Integer> translateShot;
        shot = scanner.next();
        translateShot = translation(shot);
        while (translateShot == null){
            System.out.println("Votre coup n'est pas valide, veuillez suivre l'exemple suivant : A10 | G9");
            shot = scanner.next();
            translateShot = translation(shot);
        }
        getBoardOpponent().getBoard()[translateShot.getA()][translateShot.getB()].setTouch(true);
        System.out.println(shot + " " + translateShot.getA() + " " + translateShot.getB());

    }

    //2 methode / transform int (-1) / et la partie lettre en chiffre


    public Pair<Integer, Integer> translation(String enter){
        Pair<Integer,Integer> shot = new Pair<Integer, Integer>();
        char letter = enter.charAt(0);
        int letterToInteger = (int) letter - 'A';
        if(letterToInteger >= 0 && letterToInteger < 10){
            shot.setA(letterToInteger);
        }
        else{
            return null;
        }
        String numberToString = enter.substring(1);
        if(isNumeric(numberToString)){
            int number = Integer.parseInt(numberToString);
            if(number >= 0 && number <= 10){
                shot.setB(number - 1);
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
        return shot;
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

    public static int testInteger(String entry, Scanner scanner) {
        while (!isNumeric(entry)) {
            System.out.println("\033[0;31mErreur de saisie\033[0m : Veuillez rentrer une valeur correcte !");
            entry = scanner.next();
        }
        return Integer.parseInt(entry);
    }

    public static boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
