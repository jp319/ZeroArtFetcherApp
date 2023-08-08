package com.jp319.zeroartfetcher.testing.thumbnail;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageGallery {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Image Gallery");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		
		// List to hold the image panels
		List<ImagePanel> imagePanels = new ArrayList<>();
		
		// Container for the layered pane
		JLayeredPane layeredPane = new JLayeredPane();
		
		// Fetch and display the images
		int x = 0;
		int y = 0;
		for (int i = 0; i < 10; i++) {
			try {
				BufferedImage image = ImageIO.read(new URL("https://s3.zerochan.net/240/29/16/3990829.jpg"));
				ImagePanel panel = new ImagePanel(image);
				panel.setBounds(x, y, 200, 200);
				imagePanels.add(panel);
				layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
				x += 220; // adjust the position for the next image
				if (x > 600) { // adjust the layout for new rows of images
					x = 0;
					y += 220;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Add a download button
		JButton downloadButton = new JButton("Download Selected");
		downloadButton.addActionListener(e -> {
			for (ImagePanel panel : imagePanels) {
				if (panel.isSelected()) {
					// Download the image
					// ...
				}
			}
		});
		
		frame.setLayout(new BorderLayout());
		frame.add(new JScrollPane(layeredPane), BorderLayout.CENTER);
		frame.add(downloadButton, BorderLayout.PAGE_END);
		
		frame.setVisible(true);
	}
	
	private static class ImagePanel extends JLayeredPane {
		private final JCheckBox checkBox;
		
		public ImagePanel(BufferedImage image) {
			// Create an image label
			JLabel imageLabel = new JLabel(new ImageIcon(image));
			add(imageLabel, JLayeredPane.DEFAULT_LAYER);
			
			checkBox = new JCheckBox("Select");
			checkBox.setBounds(0, 180, 200, 20); // Set the position for the checkbox
			add(checkBox, JLayeredPane.PALETTE_LAYER); // Add the checkbox as an overlay
			
			checkBox.addItemListener(e -> {
				// Handle checkbox selection change if needed
				// ...
			});
		}
		
		public boolean isSelected() {
			return checkBox.isSelected();
		}
	}
}
