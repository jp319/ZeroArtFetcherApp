package com.jp319.ZeroArtFetcher.views.components;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;

public class ZeroArtFetcherLayout extends JFrame {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        SwingUtilities.invokeLater(() -> {
            ZeroArtFetcherLayout frame = new ZeroArtFetcherLayout();
            frame.setVisible(true);
        });
    }
    public ZeroArtFetcherLayout() {
        initializeFrame();
        initializeFrameContainer();
        // Add Components here
        initializeHeader();
        initializeBody();
        // Add Components here
        pack();
        centerFrameOnScreen();
    }
    // Initialize Variables
    private Container frameContainer;
    private ZeroArtFetcherHeader headerPanel;
    private ZeroArtFetcherBody bodyPanel;
    // Set up the whole frame
    private void initializeFrame() {
        setTitle("ZeroArtFetcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int preferredFrameWidth = 600;
        int preferredFrameHeight = 600;
        setPreferredSize(new Dimension(preferredFrameWidth, preferredFrameHeight));
        setMinimumSize(new Dimension(preferredFrameWidth, preferredFrameHeight));
    }
    private void initializeFrameContainer() {
        frameContainer = getContentPane();
        frameContainer.setLayout(new BorderLayout());
    }
    private void centerFrameOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - getWidth()) / 2;
        int centerY = (screenSize.height - getHeight()) / 3;
        setLocation(centerX, centerY);
    }
    // Set up Components
    // Header
    private void initializeHeader() {
        headerPanel = new ZeroArtFetcherHeader();
        frameContainer.add(headerPanel, BorderLayout.NORTH);
    }
    private void initializeBody() {
        bodyPanel = new ZeroArtFetcherBody();
        frameContainer.add(bodyPanel, BorderLayout.CENTER);
    }
}