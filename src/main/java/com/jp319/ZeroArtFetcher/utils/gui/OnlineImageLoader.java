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

public class OnlineImageLoader {
	ProgressListener progressListener;
	ZeroArtFetcherFooter footerPanel;
	JPanel imagesPanel;
	String imageUrl;
	int maxWidth;
	int maxHeight;
	public OnlineImageLoader(JPanel imagesPanel, ZeroArtFetcherFooter footerPanel, String imageUrl, int maxWidth, int maxHeight) {
		this.imagesPanel = imagesPanel;
		this.imageUrl = imageUrl;
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		this.footerPanel = footerPanel;
		progressListener = new ProgressListener(footerPanel);
	}
	public void loadImage() {
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
					JLabel imageLabel = new JLabel(IconScaler.createScaledIcon(icon, maxWidth, maxHeight));
					imagesPanel.add(imageLabel);
					imagesPanel.revalidate();
					imagesPanel.repaint();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
