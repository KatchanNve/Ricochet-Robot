package games;

public class Main {

    public static void main(String[] args) {
        Fleet fleetPlayerOne = new Fleet();
        Fleet fleetPlayerTwo = new Fleet();
        Player playerOne = new Player(fleetPlayerOne);
        Player playerTwo = new Player(fleetPlayerTwo);
        Game game = new Game(playerOne, playerTwo);
        while (!game.isOver()) {
            if (game.getCurrentPlayer() == game.getPlayerOne()) {
                game.getBoard_playerOne().printBoard();
                // Rajouter le board adverse mais invisible
                game.play();
                // game.setCurrentPlayer();
            } else {
                game.getBoard_playerTwo().printBoard();
                // Rajouter le board adverse mais invisible
                game.play();
                // game.setCurrentPlayer();
            }

        }

    }
}
