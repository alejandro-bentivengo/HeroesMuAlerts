package org.benti.ui.dialogs;

import org.benti.ui.MainFrame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Toolkit;

public class AlertDialog extends JOptionPane {

    private final JFrame frame;
    private volatile int exitOption = -50;

    public AlertDialog(String s) {
        MainFrame.getFrame().toFront();
        MainFrame.getFrame().requestFocus();
        MainFrame.getFrame().setAlwaysOnTop(true);
        MainFrame.getFrame().setAlwaysOnTop(false);
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        SwingUtilities.invokeLater(() ->
                exitOption = JOptionPane.showConfirmDialog(frame, s, "Atencion", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE)
        );
        Toolkit.getDefaultToolkit().beep();
        frame.setAlwaysOnTop(true);
        frame.setAlwaysOnTop(false);
        frame.setLocationRelativeTo(null);
    }

    public boolean isOpen() {
        return exitOption == -50;
    }

    public void bringToFront() {
        frame.toFront();
        frame.requestFocus();
        frame.setAlwaysOnTop(true);
        frame.setAlwaysOnTop(false);
    }

}