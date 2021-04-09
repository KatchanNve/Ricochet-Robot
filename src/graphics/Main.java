package graphics;
import java.awt.Dimension;

import javax.swing.*;

public class Main {

    public static void main(final String[] args) {

        Grid gb = new Grid();
        JFrame frame = new JFrame("Battleship");
        JComponent board = gb.getGame();
        frame.add(board);
        frame.setLocationByPlatform(true);
        //frame.setMinimumSize(frame.getSize());
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(100, 100));
        board.setMinimumSize(new Dimension(100, 100));
        board.setPreferredSize(new Dimension(100, 100));
        //board.setBorder(BorderFactory.createEmptyBorder(50,50,0, 0));
        frame.setMinimumSize(new Dimension(900, 400));
        frame.setLocation(50, 50);
        frame.pack();
        frame.setVisible(true);

    }

}