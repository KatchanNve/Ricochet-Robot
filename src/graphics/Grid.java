/*package graphics;

import games.*;
import games.Box;
import games.Void;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Grid{

    private JPanel panel = new JPanel(new GridLayout(1, 3));
    private JButton[][] battleBoardSquares = new JButton[10][10];
    private JPanel battleBoard;
    private JPanel battleBoard2;
    private String COLS = "ABCDEFGHIJ";
    Human player1=new Human(new Fleet(),"Thomas");
    Human player2=new Human(new Fleet(),"Babloche");
    Game game=new Game(player1,player2);
    JTextArea textArea=new JTextArea("hello");


    Grid() {
        startGame();
    }

    public void startGame(){
        battleBoard = new JPanel(new GridLayout(0, 11));
        battleBoard2 = new JPanel(new GridLayout(0, 11));
        if (currentPlayer == player1) {
            createBoard(battleBoard);
            hideBoard(battleBoard2);
        } else {
            hideBoard(battleBoard);
            createBoard(battleBoard2);
        }
        panel.add(battleBoard);
        panel.add(battleBoard2);
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(new EmptyBorder(10,10,30,30));
        panel.add(textArea);
        battleBoard.setBorder(new EmptyBorder(10, 10, 30, 10));
        battleBoard2.setBorder(new EmptyBorder(10, 10, 30, 10));
        //game.add(battleBoard);
        createBoard(game.getBoardCurrent());
        hideBoard(game.getBoardOpponent());
    }

    public void createBoard(BattleBoard battleBoard){
        JPanel newPanel= new JPanel(new GridLayout(0, 11));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Object box =  battleBoard.getBoard()[i][j];
                if(box instanceof Box){
                    if(((Box) box).isTouch()){
                        if(box instanceof ShipBox){
                            JButton button = new JButton();
                            button.setIcon(new ImageIcon(getClass().getResource("/graphics/redCircle.png")));
                            button.setBackground(Color.WHITE);
                            battleBoardSquares[j][i] = button;
                        }
                        else if(box instanceof Void){
                            JButton button = new JButton();
                            button.setIcon(new ImageIcon(getClass().getResource("/graphics/redCross.png")));
                            button.setBackground(Color.WHITE);
                            battleBoardSquares[j][i] = button;
                        }
                    }
                    else{
                        if(box instanceof ShipBox){
                            JButton button=new JButton();
                            button.setIcon(new ImageIcon(getClass().getResource("/graphics/greenCircle.png")));
                            button.setBackground(Color.WHITE);
                            battleBoardSquares[j][i] = button;
                        }
                        else if(box instanceof Void){
                            JButton button = new JButton();
                            button.setBackground(Color.WHITE);
                            battleBoardSquares[j][i] = button;
                        }
                    }
                }
            }
        }
        newPanel.add(new JLabel(""));
        for (int i = 0; i < 10; i++) {
            //substring between i and i+1
            newPanel.add(new JLabel(COLS.substring(i, i+1),SwingConstants.CENTER));
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (j) {
                    case 0:
                        newPanel.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        newPanel.add(battleBoardSquares[j][i]);
                }
            }
        }
    }

    public void hideBoard(BattleBoard battleBoard){
        JPanel newPanel= new JPanel(new GridLayout(0, 11));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Object box =  battleBoard.getBoard()[i][j];
                if(box instanceof Box){
                    if(((Box) box).isTouch()){
                        if(box instanceof ShipBox){
                            JButton button=new JButton();
                            button.setIcon(new ImageIcon(getClass().getResource("/graphics/redCross.png")));
                            button.setBackground(Color.WHITE);
                            battleBoardSquares[j][i] = button;
                        }
                        else if(box instanceof Void){
                            JButton button=new JButton();
                            button.setIcon(new ImageIcon(getClass().getResource("/graphics/greenCross.png")));
                            button.setBackground(Color.WHITE);
                            battleBoardSquares[j][i] = button;
                        }
                    }
                    else{
                        if(box instanceof ShipBox){
                            JButton button=new JButton();
                            battleBoardSquares[j][i] = button;
                            button.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    button.setIcon(new ImageIcon(getClass().getResource("/graphics/redCircle.png")));
                                    button.setBackground(Color.WHITE);
                                    ((ShipBox) box).isTouch();
                                    game.play(i,j);
                                }
                            });
                            button.setBackground(Color.WHITE);
                        }
                        else if(box instanceof Void){
                            JButton button=new JButton();
                            battleBoardSquares[j][i] = button;
                            button.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    button.setIcon(new ImageIcon(getClass().getResource("/graphics/greenCross.png")));
                                    button.setBackground(Color.WHITE);

                                }
                            });
                            button.setBackground(Color.WHITE);
                        }
                    }
                }
            }
        }
        newPanel.add(new JLabel(""));
        for (int i = 0; i < 10; i++) {
            //substring between i and i+1
            newPanel.add(new JLabel(COLS.substring(i, i+1),SwingConstants.CENTER));
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (j) {
                    case 0:
                        newPanel.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        newPanel.add(battleBoardSquares[j][i]);
                }
            }
        }
    }

    public JComponent getBattleBoard() {
        return battleBoard;
    }

    public JComponent getGame() {
        return panel;
    }

    /*@Override
    public void ModelChange(Object source){
        this.repaint();
    }*/

    /*public Box[][] setUp(){
        Human player = new Human(new Fleet(),"Hasti");
        BattleBoard board_playerOne = new BattleBoard(player);
        board_playerOne.initBoard();
        board_playerOne.placeFleet();
        return board_playerOne.getBoard();
    }

    public void changePlayer(){
        if (currentPlayer==player1){
            currentPlayer=player2;
            enemy=player1;
        }
        else{
            currentPlayer=player1;
            enemy=player2;
        }
    }
}
*/