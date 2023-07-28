package com.jp319.ZeroArtFetcher.utils.gui;

import com.jp319.ZeroArtFetcher.views.components.ZeroArtFetcherFooter;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.Objects;

public class LazyOnlineImageLoader {
	ProgressListener progressListener;
	ZeroArtFetcherFooter footerPanel;
	JPanel imagesPanel;
	String imageUrl;
	int maxWidth;
	int maxHeight;

	public LazyOnlineImageLoader(JPanel imagesPanel, ZeroArtFetcherFooter footerPanel, String imageUrl, int maxWidth, int maxHeight) {
		this.imagesPanel = imagesPanel;
		this.imageUrl = imageUrl;
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		this.footerPanel = footerPanel;
		progressListener = new ProgressListener(footerPanel);
	}

	// Load images one by one and set a placeholder image until the actual image is loaded
	public Runnable loadImage() {
		ImageIcon placeholderIcon = new ImageIcon(
				Objects.requireNonNull(getClass().getResource("/images/rolling.gif"))
		);
		JLabel imageLabel = new JLabel(placeholderIcon);
		imagesPanel.add(imageLabel);
		imagesPanel.revalidate();
		imagesPanel.repaint();

/*
		// This will load images on a new thread and since it is used in a for loop
		// then it will load all images at the same time using more thread
		// making the app freeze.
		new Thread(() -> {
			try {

				URI uri = URI.create(imageUrl);
				ImageInputStream imageInputStream = ImageIO.createImageInputStream(uri.toURL().openStream());
				Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInputStream);
				if (iterator.hasNext()) {
					ImageReader reader = iterator.next();
					reader.setInput(imageInputStream);
					reader.addIIOReadProgressListener(progressListener);
					BufferedImage image = reader.read(reader.getMinIndex());
					final ImageIcon icon = new ImageIcon(image);

					// Check if the image is still required to be shown
					if (imagesPanel.isDisplayable()) {
						SwingUtilities.invokeLater(() -> {
							imageLabel.setIcon(IconScaler.createScaledIcon(icon, maxWidth, maxHeight));
							imagesPanel.revalidate();
							imagesPanel.repaint();
						});
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
*/

		return () -> {
			try {

				URI uri = URI.create(imageUrl);
				ImageInputStream imageInputStream = ImageIO.createImageInputStream(uri.toURL().openStream());
				Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInputStream);
				if (iterator.hasNext()) {
					ImageReader reader = iterator.next();
					reader.setInput(imageInputStream);
					reader.addIIOReadProgressListener(progressListener);
					BufferedImage image = reader.read(reader.getMinIndex());
					final ImageIcon icon = new ImageIcon(image);

					// Check if the image is still required to be shown
					if (imagesPanel.isDisplayable()) {
						SwingUtilities.invokeLater(() -> {
							imageLabel.setIcon(IconScaler.createScaledIcon(icon, maxWidth, maxHeight));
							imagesPanel.revalidate();
							imagesPanel.repaint();
						});
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
}
