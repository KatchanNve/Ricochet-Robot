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
    JPanel jBattleBoard;
    JPanel jBattleBoard2;
    JPanel basePanel;
    HashMap <JButton,Pair<Integer, Integer>> buttonList = new HashMap<JButton,Pair<Integer, Integer>>();
    JFrame baseFrame;

    public GUI() {
        game = new Game(true);
        baseFrame= initInterface();
    }

    public JFrame initInterface(){
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
        return frame;
    }

    public JComponent completeInterface(){
        basePanel = new JPanel(new GridLayout(0, 2));
        jBattleBoard = setupBoard(game.getBoardCurrent(),true);
        jBattleBoard2 = setupBoard(game.getBoardOpponent(),false);
        /*if((game.getCurrentPlayer() instanceof Computer) || (game.getOpponent() instanceof Computer)) {
            basePanel.add(jBattleBoard);
            basePanel.add(jBattleBoard2);
        }
        else{
            basePanel.add(jBattleBoard);
            basePanel.add(jBattleBoard2);
            JPanel panel= new JPanel();
            JButton button = new JButton();
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("I'm a button");
                }
            });
            panel.add(button);
            basePanel.add(panel);
        }*/
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
                                    if(!game.isOver()){
                                        changeState(pair.getA(), pair.getB());
                                    }
                                    else{
                                        System.out.println("La partie est terminée");
                                    }
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

    public void changeState(int i, int j){
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

        game.setCurrentPlayer();

        jBattleBoard = setupBoard(game.getBoardCurrent(),true);
        jBattleBoard2 = setupBoard(game.getBoardOpponent(),false);
        baseFrame.dispose();
        baseFrame = initInterface();
    }
}

