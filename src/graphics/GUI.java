package graphics;

import games.*;
import games.Box;
import games.Void;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    public GUI() {
        Game game = new Game();
    }

    public JFrame initInterface(){
        GUI gui = new GUI();
        JFrame frame = new JFrame("Bataille navale");
        JComponent panel = gui.getPanel();
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
        JPanel panel = new JPanel(new GridLayout(1, 3));
        JPanel jBattleBoard = setupBoard(game.getBoardCurrent(),true);
        JPanel jBattleBoard2 = setupBoard(game.getBoardOpponent(),false);
        panel.add(jBattleBoard);
        panel.add(jBattleBoard2);
        jBattleBoard.setBorder(new EmptyBorder(10, 10, 30, 10));
        jBattleBoard2.setBorder(new EmptyBorder(10, 10, 30, 10));
        return panel;
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
}

