package com.jp319.zeroartfetcher.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private Container container;

    public MainWindow() {
        initializeFrame();
        initializeContainer();
        setLayoutManager();
        addComponents();
        packFrame();
        centerFrameOnScreen();
    }

    // Frame Initialization
    private void initializeFrame() {
        setTitle("Centered JFrame Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int preferredWidth = 600;
        int preferredHeight = 600;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        setMinimumSize(new Dimension(preferredWidth, preferredHeight));
    }

    private void initializeContainer() {
        container = getContentPane();
    }

    private void setLayoutManager() {
        container.setLayout(new BorderLayout());
    }

    private void packFrame() {
        pack();
    }

    private void centerFrameOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - getWidth()) / 2;
        int centerY = (screenSize.height - getHeight()) / 3;
        setLocation(centerX, centerY);
    }

    // Components
    // Components
    private void addComponents() {
        // Add item to the top (north) of the BorderLayout with a margin
        JLabel headerLabel = new JLabel("Header Label", SwingConstants.CENTER);
        int headerMargin = 10;
        headerLabel.setBorder(BorderFactory.createEmptyBorder(headerMargin, 0, headerMargin, 0));
        container.add(headerLabel, BorderLayout.NORTH);

        // Add buttons in the center (using FlowLayout)
        JPanel centerPanel = new JPanel(new FlowLayout());
        container.add(centerPanel, BorderLayout.CENTER);

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton("Button " + i);
            centerPanel.add(button);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow frame = new MainWindow();
            frame.setVisible(true);
        });
    }
}