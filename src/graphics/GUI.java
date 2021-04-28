package graphics;

import games.*;
import games.Box;
import games.Void;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GUI {

    Game game;
    int row, column;
    JPanel jBattleBoard;
    JPanel jBattleBoard2;
    JPanel basePanel;
    HashMap <JButton,Pair<Integer, Integer>> buttonList = new HashMap<JButton,Pair<Integer, Integer>>();

    public GUI() {
        game = new Game(true);
        initInterface();
    }

    public void initInterface(){
        JFrame frame = new JFrame("Bataille navale");
        JComponent panel = this.completeInterface();
        frame.add(panel);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        panel.setMinimumSize(new Dimension(100, 100));
        panel.setPreferredSize(new Dimension(100, 100));
        frame.setMinimumSize(new Dimension(1100, 400));
        frame.setLocation(50, 50);
        frame.pack();
        frame.setVisible(true);
    }

    public JComponent completeInterface(){
        basePanel = new JPanel(new GridLayout(1, 3));
        jBattleBoard = setupBoard(game.getBoardCurrent(),true);
        jBattleBoard2 = setupBoard(game.getBoardOpponent(),false);
        basePanel.add(jBattleBoard);
        basePanel.add(jBattleBoard2);
        jBattleBoard.setBorder(new EmptyBorder(10, 10, 30, 10));
        jBattleBoard2.setBorder(new EmptyBorder(10, 10, 30, 10));
        return basePanel;
    }

    public JPanel setupBoard(BattleBoard battleBoard, boolean show){
        JPanel panel= new JPanel(new GridLayout(0, 11));
        JButton[][] buttonBoard = new JButton[10][10];
        String letters = "ABCDEFGHIJ";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Object box = battleBoard.getBoard()[i][j];
                if (box instanceof Box) {
                    if (((Box) box).isTouch()) {
                        if (box instanceof ShipBox) {
                            JButton button = new JButton();
                            button.setIcon(new ImageIcon(getClass().getResource("/graphics/redCircle.png")));
                            button.setBackground(Color.WHITE);
                            buttonBoard[j][i] = button;
                        } else if (box instanceof Void) {
                            JButton button = new JButton();
                            button.setIcon(new ImageIcon(getClass().getResource("/graphics/redCross.png")));
                            button.setBackground(Color.WHITE);
                            buttonBoard[j][i] = button;
                        }
                    } else {
                        if(show) {
                            if (box instanceof ShipBox) {
                                JButton button = new JButton();
                                button.setIcon(new ImageIcon(getClass().getResource("/graphics/greenCircle.png")));
                                button.setBackground(Color.WHITE);
                                buttonBoard[j][i] = button;
                            } else if (box instanceof Void) {
                                JButton button = new JButton();
                                button.setBackground(Color.WHITE);
                                buttonBoard[j][i] = button;
                            }
                        }
                        else{
                            JButton button = new JButton();
                            button.setBackground(Color.WHITE);
                            buttonBoard[j][i] = button;
                            buttonList.put(button, new Pair<Integer,Integer>(i,j));
                            button.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    Pair<Integer,Integer> pair = buttonList.get(button);
                                    changeState(pair.getA(), pair.getB(), basePanel);
                                }
                            });
                        }
                    }
                }
            }
        }
        panel.add(new JLabel(""));
        for (int i = 0; i < 10; i++) {
            //substring between i and i+1
            panel.add(new JLabel(letters.substring(i, i+1),SwingConstants.CENTER));
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (j) {
                    case 0:
                        panel.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        panel.add(buttonBoard[j][i]);
                }
            }
        }
        return panel;
    }

    public void changeState(int i, int j, JPanel panel){
        Pair<Integer, Integer> shot = new Pair<Integer, Integer>(0,0);
        while (game.getBoardOpponent().getBoard()[i][j].isTouch()) {
            System.out.println("Ce coup a déjà été joué, veuillez en choisir un autre : ");
        }

        if(game.getCurrentPlayer() instanceof Human){
            System.out.println(shot.getA()+","+shot.getB());
            shot.setA(i);
            shot.setB(j);
            System.out.println(shot.getA()+","+shot.getB());
        }
        else {
            shot = game.getCurrentPlayer().getShoot();
            if(shot == null){
                System.out.println("c'est a cause du getshoot");
            }
        }

        game.shoot(shot.getA(), shot.getB());

        Object box = game.getBoardOpponent().getBoard()[shot.getA()][shot.getB()];
        if (box instanceof ShipBox) {
            ((ShipBox) box).getShip().setLifeOccurence();
            if (((ShipBox) box).getShip().isSink()) {
                game.getOpponent().getFleet().setNbrShipOccurence();
            }
        }

        panel.remove(jBattleBoard);
        panel.remove(jBattleBoard2);

    }

    /*while (!this.isOver()) {
        if(!gui){
            this.getBoardCurrent().printBoard();
            this.getBoardOpponent().printHiddenBoard();
            this.play();
            this.getBoardCurrent().printBoard();
            this.getBoardOpponent().printHiddenBoard();
        }



        shoot(shot.getA(), shot.getB());

        Object box = getBoardOpponent().getBoard()[shot.getA()][shot.getB()];
        if (box instanceof ShipBox) {
            ((ShipBox) box).getShip().setLifeOccurence();
            if (((ShipBox) box).getShip().isSink()) {
                getOpponent().getFleet().setNbrShipOccurence();
            }
        }  */
}

