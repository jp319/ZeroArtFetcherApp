package com.jp319.ZeroArtFetcher.utils.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShakeFrame {
	private static final int shakeDuration = 250; // Duration of the shaking animation in milliseconds
	private static final int shakeMagnitude = 5;  // Magnitude of the shaking movement in pixels
	public static void shakeFrame(JFrame frame) {
		Point originalLocation = frame.getLocation();

		Timer timer = new Timer(30, new ActionListener() {
			private long startTime = -1;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (startTime < 0) {
					startTime = System.currentTimeMillis();
				}
				long elapsed = System.currentTimeMillis() - startTime;
				if (elapsed > shakeDuration) {
					frame.setLocation(originalLocation); // Restore the original location
					((Timer) e.getSource()).stop();
				} else {
					int offsetX = (int) (Math.random() * shakeMagnitude * 2) - shakeMagnitude;
					int offsetY = (int) (Math.random() * shakeMagnitude * 2) - shakeMagnitude;
					frame.setLocation(originalLocation.x + offsetX, originalLocation.y + offsetY);
				}
			}
		});
		timer.start();
	}
}
