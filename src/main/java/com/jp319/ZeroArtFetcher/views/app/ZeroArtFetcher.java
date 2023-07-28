package com.jp319.ZeroArtFetcher.views.app;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;

public class ZeroArtFetcher extends JFrame {
	public static void main(String[] args) {
		FlatDarculaLaf.setup();
		SwingUtilities.invokeLater(() -> {
			ZeroArtFetcher frame = new ZeroArtFetcher();
			frame.setVisible(true);
		});
	}

	public ZeroArtFetcher() {
		initializeFrame();
		initializeFrameContainer();
		// Add Components here
		pack();
		centerFrameOnScreen();
	}
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
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
	}
	private void centerFrameOnScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (screenSize.width - getWidth()) / 2;
		int centerY = (screenSize.height - getHeight()) / 3;
		setLocation(centerX, centerY);
	}
}