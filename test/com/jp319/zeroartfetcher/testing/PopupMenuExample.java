package com.jp319.zeroartfetcher.testing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupMenuExample {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Popup Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        JButton button = new JButton("Show Popup Menu");
        panel.add(button);
        frame.add(panel);

        JPopupMenu popupMenu = new JPopupMenu();
        JRadioButtonMenuItem filterAllBtn = new JRadioButtonMenuItem("All");
        JRadioButtonMenuItem filterLargeBtn = new JRadioButtonMenuItem("Large");
        JRadioButtonMenuItem filterHugeBtn = new JRadioButtonMenuItem("Huge");
        JRadioButtonMenuItem filterLandscapeBtn = new JRadioButtonMenuItem("Landscape");
        JRadioButtonMenuItem filterPortraitBtn = new JRadioButtonMenuItem("Portrait");
        JRadioButtonMenuItem filterSquareBtn = new JRadioButtonMenuItem("Square");

        ButtonGroup filterButtonGroup = new ButtonGroup();
        filterButtonGroup.add(filterAllBtn);
        filterButtonGroup.add(filterLargeBtn);
        filterButtonGroup.add(filterHugeBtn);
        filterButtonGroup.add(filterLandscapeBtn);
        filterButtonGroup.add(filterPortraitBtn);
        filterButtonGroup.add(filterSquareBtn);

        popupMenu.add(filterAllBtn);
        popupMenu.add(filterLargeBtn);
        popupMenu.add(filterHugeBtn);
        popupMenu.add(filterLandscapeBtn);
        popupMenu.add(filterPortraitBtn);
        popupMenu.add(filterSquareBtn);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(button, 0, button.getHeight());
            }
        });

        frame.setVisible(true);
    }
}
