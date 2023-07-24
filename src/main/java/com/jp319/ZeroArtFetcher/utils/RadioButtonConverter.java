package com.jp319.ZeroArtFetcher.utils;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RadioButtonConverter {
    public static JRadioButtonMenuItem convertToRadioButtonMenuItem(JRadioButton radioButton) {
        JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(radioButton.getText());
        radioButtonMenuItem.setSelected(radioButton.isSelected());
        radioButtonMenuItem.setEnabled(radioButton.isEnabled());

        ActionListener[] actionListeners = radioButton.getActionListeners();
        if (actionListeners.length > 0) {
            radioButtonMenuItem.addActionListener(actionListeners[0]);
        }

        return radioButtonMenuItem;
    }
}
