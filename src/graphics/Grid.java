package graphics;

import games.*;
import games.Box;
import games.Void;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Grid{

    private JPanel game = new JPanel(new GridLayout(1, 3));
    private JButton[][] battleBoardSquares = new JButton[10][10];
    private JPanel battleBoard;
    private JPanel battleBoard2;
    private String COLS = "ABCDEFGHIJ";
    Human player1=new Human(new Fleet(),"Thomas");
    Human player2=new Human(new Fleet(),"Babloche");
    Human currentPlayer = player1;
    JTextArea textArea=new JTextArea("hello");
    Box[][] first = setUp();
    Box[][] second = setUp();
    Boolean over=currentPlayer.getFleet().isSink();
    boolean clicked = false;

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
        game.add(battleBoard);
        game.add(battleBoard2);
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(new EmptyBorder(10,10,30,30));
        game.add(textArea);
        battleBoard.setBorder(new EmptyBorder(10, 10, 30, 10));
        battleBoard2.setBorder(new EmptyBorder(10, 10, 30, 10));
        //game.add(battleBoard);
    }

    public void createBoard(JPanel board){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Object box =  first[i][j];
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
        board.add(new JLabel(""));
        for (int i = 0; i < 10; i++) {
            //substring between i and i+1
            board.add(new JLabel(COLS.substring(i, i+1),SwingConstants.CENTER));
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (j) {
                    case 0:
                        board.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        board.add(battleBoardSquares[j][i]);
                }
            }
        }
    }

    public void hideBoard(JPanel board){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Object box =  second[i][j];
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
                            button.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    button.setIcon(new ImageIcon(getClass().getResource("/graphics/redCircle.png")));
                                    button.setBackground(Color.WHITE);
                                    ((ShipBox) box).isTouch();
                                    changePlayer();
                                }
                            });
                            button.setBackground(Color.WHITE);
                            battleBoardSquares[j][i] = button;
                        }
                        else if(box instanceof Void){
                            JButton button=new JButton();
                            button.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    button.setIcon(new ImageIcon(getClass().getResource("/graphics/greenCross.png")));
                                    button.setBackground(Color.WHITE);
                                    ((Void) box).isTouch();
                                    changePlayer();
                                }
                            });
                            button.setBackground(Color.WHITE);
                            battleBoardSquares[j][i] = button;
                        }
                    }
                }
            }
        }
        board.add(new JLabel(""));
        for (int i = 0; i < 10; i++) {
            //substring between i and i+1
            board.add(new JLabel(COLS.substring(i, i+1),SwingConstants.CENTER));
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (j) {
                    case 0:
                        board.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        board.add(battleBoardSquares[j][i]);
                }
            }
        }
    }

    public JComponent getBattleBoard() {
        return battleBoard;
    }

    public JComponent getGame() {
        return game;
    }

    /*@Override
    public void ModelChange(Object source){
        this.repaint();
    }*/

    public Box[][] setUp(){
        Human player = new Human(new Fleet(),"Hasti");
        BattleBoard board_playerOne = new BattleBoard(player);
        board_playerOne.initBoard();
        board_playerOne.placeFleet();
        return board_playerOne.getBoard();
    }

    public void changePlayer(){
        if (currentPlayer==player1){
            currentPlayer=player2;
        }
        else{
            currentPlayer=player1;
        }
    }
}