package games;

public class Main {

    public static void main(String[] args) {
        Fleet fleetPlayerOne = new Fleet();
        Fleet fleetPlayerTwo = new Fleet();
        Player playerOne = new Player(fleetPlayerOne);
        Player playerTwo = new Player(fleetPlayerTwo);
        Game game = new Game(playerOne, playerTwo);
    }
}
