package games;

import graphics.AbstractModelListener;

import java.util.HashMap;
import java.util.Scanner;

public class Game extends AbstractModelListener {

    private Player playerOne;
    private Player playerTwo;
    private BattleBoard board_playerOne;
    private BattleBoard board_playerTwo;
    private Player currentPlayer;


    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
        board_playerOne = new BattleBoard(playerOne);
        board_playerOne.initBoard();
        board_playerOne.placeFleet();
        board_playerTwo = new BattleBoard(playerTwo);
        board_playerTwo.initBoard();
        board_playerTwo.placeFleet();
        while (!this.isOver()) {
            this.getBoardCurrent().printBoard();
            this.getBoardOpponent().printHiddenBoard();
            this.play();
            //fire change
            fireChange();
            //fireChange applied
            this.getBoardCurrent().printBoard();
            this.getBoardOpponent().printHiddenBoard();
            this.setCurrentPlayer();
        }
        System.out.println("Le joueur qui a gagné est : " + this.getOpponent());
    }

    public Game(){
        this(new Human(new Fleet(),"Joueur 1"),new Computer(new Fleet()));
    }

    // ne sert à rien //peut-être aussi pour le addListShotElement car on comparer
    // grâce au getTouch
    // Vérifier si nous n'avons pas délaissé certains code
    public void shoot(int i, int j) {
        Box box = getBoardOpponent().getBoard()[i][j];
        box.setTouch(true);
        currentPlayer.deleteShotElement(i, j);
    }

    // voir pour corriger le redondance de code
    public void play() {
        Pair<Integer, Integer> shot = currentPlayer.getShoot();
        while (getBoardOpponent().getBoard()[shot.getA()][shot.getB()].isTouch()) {
            System.out.println("Ce coup a déjà été joué, veuillez en choisir un autre : ");
            shot = currentPlayer.getShoot();
        }

        shoot(shot.getA(), shot.getB());

        Object box = getBoardOpponent().getBoard()[shot.getA()][shot.getB()];
        if (box instanceof ShipBox) {
            ((ShipBox) box).getShip().setLifeOccurence();
            if (((ShipBox) box).getShip().isSink()) {
                getOpponent().getFleet().setNbrShipOccurence();
            }
        }
    }

    public static Pair<Integer, Integer> translation(String enter) {
        Pair<Integer, Integer> shot = new Pair<Integer, Integer>();
        char letter = enter.charAt(0);
        int letterToInteger = (int) letter - 'A';
        if (letterToInteger >= 0 && letterToInteger < 10) {
            shot.setA(letterToInteger);
        } else {
            return null;
        }
        String numberToString = enter.substring(1);
        if (isNumeric(numberToString)) {
            int number = Integer.parseInt(numberToString);
            if (number >= 0 && number <= 10) {
                shot.setB(number - 1);
            } else {
                return null;
            }
        } else {
            return null;
        }
        return shot;
    }

    public boolean isOver() {
        return currentPlayer.getFleet().isSink();
    }

    public Player getWinner() {
        if (isOver()) {
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

    public Player getOpponent() {
        if (currentPlayer.equals(playerOne)) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    public BattleBoard getBoardOpponent() {
        if (currentPlayer.equals(playerOne)) {
            return board_playerTwo;
        } else {
            return board_playerOne;
        }
    }

    public BattleBoard getBoardCurrent() {
        if (currentPlayer.equals(playerOne)) {
            return board_playerOne;
        } else {
            return board_playerTwo;
        }
    }

    public void setCurrentPlayer() {
        this.currentPlayer = getOpponent();
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
