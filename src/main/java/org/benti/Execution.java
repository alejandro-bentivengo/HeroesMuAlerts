package org.benti;

import org.benti.ui.MainFrame;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Execution {


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Execution::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
