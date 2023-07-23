package com.jp319.ZeroArtFetcher.utils;

import java.awt.*;

public class GridBagConstraints extends java.awt.GridBagConstraints {
    public GridBagConstraints(int x, int y, int width, double weightX, int anchor, int fill) {
        this.gridx = x;
        this.gridy = y;
        this.gridwidth = width;
        this.gridheight = 1;
        this.anchor = anchor;
        this.weightx = weightX;
        this.fill = fill;
        this.insets = new Insets(5, 5, 0, 5);
    }
}
