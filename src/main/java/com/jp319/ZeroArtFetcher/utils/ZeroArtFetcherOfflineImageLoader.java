package com.jp319.ZeroArtFetcher.utils;

import com.jp319.ZeroArtFetcher.utils.gui.IconScaler;

import javax.swing.*;

public class ZeroArtFetcherOfflineImageLoader {
	private final JPanel imagesPanel;
	private final String imagePath;
	private final int maxWidth;
	private final int maxHeight;
	public ZeroArtFetcherOfflineImageLoader(JPanel imagesPanel, String imagePath, int maxWidth, int maxHeight) {
		this.imagesPanel = imagesPanel;
		this.imagePath = imagePath;
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
	}
	private void done() {
		ImageIcon imageIcon = new ImageIcon(imagePath);
		JLabel imageLabel =
				new JLabel(
						IconScaler.createScaledIcon(imageIcon,maxWidth,maxHeight),
						SwingConstants.CENTER
				);
		imagesPanel.add(imageLabel);
		imagesPanel.revalidate();
		imagesPanel.repaint();
	}
	public void execute() {
		done();
	}
}
