package games;

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
