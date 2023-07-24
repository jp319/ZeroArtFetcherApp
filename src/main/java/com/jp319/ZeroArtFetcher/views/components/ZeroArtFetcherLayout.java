package com.jp319.ZeroArtFetcher.views.components;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.jp319.ZeroArtFetcher.views.callbacks.ZeroArtFetcherLayoutToHeaderCallback;
import com.jp319.ZeroArtFetcher.views.listeners.frameComponentListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

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
        initializeBody();
        initializeHeader();
        initializeCallbacks();
        // Add Components here
        pack();
        centerFrameOnScreen();
    }
    // Initialize Variables
    private Container frameContainer;
    private ZeroArtFetcherHeader headerPanel;
    private ZeroArtFetcherBody bodyPanel;
    private ZeroArtFetcherLayoutToHeaderCallback callback;
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
        headerPanel = new ZeroArtFetcherHeader(bodyPanel);
        frameContainer.add(headerPanel, BorderLayout.NORTH);
    }
    private void initializeBody() {
        bodyPanel = new ZeroArtFetcherBody();
        frameContainer.add(bodyPanel, BorderLayout.CENTER);
    }
    private void initializeCallbacks() {
        callback = headerPanel;
        ComponentListener frameComponentListener =
                new frameComponentListener(this, callback);
        addComponentListener(frameComponentListener);
    }
}