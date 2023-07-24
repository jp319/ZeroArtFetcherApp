package com.jp319.zeroartfetcher.testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropdownButtonExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dropdown Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JButton dropdownButton = new JButton("Dropdown");
        dropdownButton.setHorizontalTextPosition(SwingConstants.LEADING);
        dropdownButton.setIcon(new DownArrowIcon());

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem item1 = new JMenuItem("Item 1");
        JMenuItem item2 = new JMenuItem("Item 2");

        popupMenu.add(item1);
        popupMenu.add(item2);

        dropdownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(dropdownButton, 0, dropdownButton.getHeight());
            }
        });

        frame.add(dropdownButton);
        frame.setVisible(true);
    }

    static class DownArrowIcon implements Icon {
        private static final int SIZE = 8;

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.BLACK);
            int[] xPoints = {x, x + SIZE, x + SIZE / 2};
            int[] yPoints = {y, y, y + SIZE};
            g2d.fillPolygon(xPoints, yPoints, 3);
            g2d.dispose();
        }

        @Override
        public int getIconWidth() {
            return SIZE;
        }

        @Override
        public int getIconHeight() {
            return SIZE;
        }
    }
}
