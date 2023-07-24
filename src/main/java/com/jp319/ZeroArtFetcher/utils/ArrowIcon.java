package com.jp319.ZeroArtFetcher.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ArrowIcon {
    public static class DownArrowIcon implements Icon {
        private static int SIZE;
        public DownArrowIcon(int SIZE) {
            DownArrowIcon.SIZE = SIZE;
        }

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
    public static class DownArrowImageIcon {
        private final int size;
        public DownArrowImageIcon(int size) {
            this.size = size;
        }
        public ImageIcon getImageIcon() {
            Icon arrowIcon = new DownArrowIcon(size);
            // Convert the ArrowIcon to ImageIcon
            Image image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) image.getGraphics();
            arrowIcon.paintIcon(null, g2d, 0, 0);
            g2d.dispose();
            return new ImageIcon(image);
        }
    }
}
