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
}

