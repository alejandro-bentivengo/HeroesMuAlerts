package org.benti.ui.panels;

import org.benti.process.ResetType;
import org.benti.process.UserType;
import org.benti.ui.alerts.AlertManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class MainPanel extends JPanel implements ActionListener, ChangeListener {

    private static final int ELEMENT_SPACING = 1;
    private static final int FORM_SPACING = 15;

    private ButtonGroup userTypeRadioGroup;
    private ButtonGroup resetTypeRadioGroup;
    private JLabel currentDelay;
    private JSlider alertDelaySlider;
    private JTextField characterName;

    private AlertManager alertManager;

    public MainPanel() {
        initializePanel();
        this.setVisible(true);
    }

    private void initializePanel() {
        this.setLayout(null);
        int xElementPosition = 5;
        int yElementPosition = ELEMENT_SPACING;

        // User type
        JLabel characterLabel1 = new JLabel("Nombre del personaje:");
        this.add(characterLabel1);
        characterLabel1.setBounds(xElementPosition, yElementPosition, characterLabel1.getPreferredSize().width, characterLabel1.getPreferredSize().height);
        yElementPosition += ELEMENT_SPACING + characterLabel1.getHeight();

        JLabel characterLabel2 = new JLabel("Dejar vacio para el primero que se encuentre");
        this.add(characterLabel2);
        characterLabel2.setBounds(xElementPosition, yElementPosition, characterLabel2.getPreferredSize().width, characterLabel2.getPreferredSize().height);
        yElementPosition += ELEMENT_SPACING + characterLabel2.getHeight();

        characterName = new JTextField("");
        characterName.setColumns(15);
        this.add(characterName);
        characterName.setBounds(xElementPosition, yElementPosition, characterName.getPreferredSize().width, characterName.getPreferredSize().height);
        yElementPosition += FORM_SPACING + characterName.getHeight();

        // User type
        JLabel userTypeLabel = new JLabel("Seleccione el tipo de usuario");
        this.add(userTypeLabel);
        userTypeLabel.setBounds(xElementPosition, yElementPosition, userTypeLabel.getPreferredSize().width, userTypeLabel.getPreferredSize().height);
        yElementPosition += ELEMENT_SPACING + userTypeLabel.getHeight();

        // User Type Radio Button Options
        JRadioButton normalUser = new JRadioButton("Free");
        normalUser.setActionCommand(UserType.FREE.name());
        normalUser.setSelected(true);
        normalUser.setBounds(xElementPosition, yElementPosition, normalUser.getPreferredSize().width, normalUser.getPreferredSize().height);
        this.add(normalUser);
        yElementPosition += ELEMENT_SPACING + normalUser.getHeight();

        JRadioButton vipUser = new JRadioButton("VIP");
        vipUser.setActionCommand(UserType.VIP.name());
        vipUser.setBounds(xElementPosition, yElementPosition, vipUser.getPreferredSize().width, vipUser.getPreferredSize().height);
        this.add(vipUser);
        yElementPosition += FORM_SPACING + vipUser.getHeight();

        // Radio Button Group
        userTypeRadioGroup = new ButtonGroup();
        userTypeRadioGroup.add(normalUser);
        userTypeRadioGroup.add(vipUser);

        // Radio Button Label
        JLabel resetLabel = new JLabel("Seleccione el tipo de reset a detectar");
        this.add(resetLabel);
        resetLabel.setBounds(xElementPosition, yElementPosition, resetLabel.getPreferredSize().width, resetLabel.getPreferredSize().height);
        yElementPosition += ELEMENT_SPACING + resetLabel.getHeight();

        // Radio Button Options
        JRadioButton normalReset = new JRadioButton("Reset Normal");
        normalReset.setActionCommand(ResetType.RR.name());
        normalReset.setSelected(true);
        normalReset.setBounds(xElementPosition, yElementPosition, normalReset.getPreferredSize().width, normalReset.getPreferredSize().height);
        this.add(normalReset);
        yElementPosition += ELEMENT_SPACING + normalReset.getHeight();

        JRadioButton masterReset = new JRadioButton("Master Reset");
        masterReset.setActionCommand(ResetType.MR.name());
        masterReset.setBounds(xElementPosition, yElementPosition, masterReset.getPreferredSize().width, masterReset.getPreferredSize().height);
        this.add(masterReset);
        yElementPosition += ELEMENT_SPACING + masterReset.getHeight();

        JRadioButton c4Reset = new JRadioButton("C4 Reset");
        c4Reset.setActionCommand(ResetType.C4.name());
        c4Reset.setBounds(xElementPosition, yElementPosition, c4Reset.getPreferredSize().width, c4Reset.getPreferredSize().height);
        this.add(c4Reset);
        yElementPosition += FORM_SPACING + c4Reset.getHeight();

        // Radio Button Group
        resetTypeRadioGroup = new ButtonGroup();
        resetTypeRadioGroup.add(normalReset);
        resetTypeRadioGroup.add(masterReset);
        resetTypeRadioGroup.add(c4Reset);

        // Alert delay
        JLabel alertDelay = new JLabel("Seleccione el tiempo entre cada recordatorio (minutos)");
        this.add(alertDelay);
        alertDelay.setBounds(xElementPosition, yElementPosition, alertDelay.getPreferredSize().width, alertDelay.getPreferredSize().height);
        yElementPosition += ELEMENT_SPACING + alertDelay.getHeight();

        alertDelaySlider = new JSlider(1, 10, 5);
        this.add(alertDelaySlider);
        alertDelaySlider.setBounds(xElementPosition, yElementPosition, alertDelaySlider.getPreferredSize().width, alertDelaySlider.getPreferredSize().height + 40);
        alertDelaySlider.setMinorTickSpacing(1);
        alertDelaySlider.setPaintTicks(true);
        alertDelaySlider.setPaintLabels(true);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1, new JLabel("1"));
        labelTable.put(10, new JLabel("10"));
        alertDelaySlider.setLabelTable(labelTable);
        alertDelaySlider.addChangeListener(this);
        yElementPosition += ELEMENT_SPACING + alertDelaySlider.getHeight();

        currentDelay = new JLabel("Valor de retardo seleccionado: " + 5);
        this.add(currentDelay);
        currentDelay.setBounds(xElementPosition, yElementPosition, currentDelay.getPreferredSize().width + 10, currentDelay.getPreferredSize().height);
        yElementPosition += FORM_SPACING + currentDelay.getHeight();

        JButton button = new JButton("Iniciar");
        button.setActionCommand("start");
        this.add(button);
        button.setBounds(xElementPosition, yElementPosition, button.getPreferredSize().width + 10, button.getPreferredSize().height);
        button.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getActionCommand().equals("start")) {
            alertManager = new AlertManager(alertDelaySlider.getValue(),
                    characterName.getText(),
                    parseUserType(userTypeRadioGroup.getSelection()),
                    parseResetType(resetTypeRadioGroup.getSelection())
            );
            alertManager.start();
            button.setText("Detener");
            button.setActionCommand("stop");
            for (Component component : button.getParent().getComponents()) {
                if (component instanceof JRadioButton || component instanceof JSlider || component instanceof JTextField) {
                    component.setEnabled(false);
                }
            }
        } else {
            alertManager.interrupt();
            alertManager = null;
            button.setText("Iniciar");
            button.setActionCommand("start");
            for (Component component : button.getParent().getComponents()) {
                if (component instanceof JRadioButton || component instanceof JSlider || component instanceof JTextField) {
                    component.setEnabled(true);
                }
            }
        }

    }

    private ResetType parseResetType(ButtonModel selection) {
        switch (selection.getActionCommand()) {
            case "MR":
                return ResetType.MR;
            case "C4":
                return ResetType.C4;
            case "RR":
            default:
                return ResetType.RR;
        }
    }

    private UserType parseUserType(ButtonModel selection) {
        if (selection.getActionCommand().equals("FREE")) {
            return UserType.FREE;
        } else {
            return UserType.VIP;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        currentDelay.setText("Valor de retardo seleccionado: " + slider.getValue());
    }
}
