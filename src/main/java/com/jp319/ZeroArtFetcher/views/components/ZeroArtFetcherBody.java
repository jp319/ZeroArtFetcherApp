package com.jp319.ZeroArtFetcher.views.components;

import com.jp319.ZeroArtFetcher.utils.WrapLayout;
import com.jp319.ZeroArtFetcher.utils.ZeroArtFetcherDownloadedImages;

import javax.swing.*;
import java.awt.*;

public class ZeroArtFetcherBody extends JScrollPane {
    public ZeroArtFetcherBody() {
        initializeBodyPanel();
    }
    private JPanel imagesPanel;
    private void initializeBodyPanel() {
        // Add Image Panel to the Center of the BorderLayout with a margin
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 20, 20, 20), // Outside Margin
                BorderFactory.createLineBorder(Color.GRAY, 3, true) // LineBorder Inside the Margin
        ));
        getVerticalScrollBar().setUnitIncrement(12);
        // Panel that will Contain the images
        imagesPanel = new JPanel();
        imagesPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 25, 5));
        imagesPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        // Initialize individual Components
        // Add Individual Components (Images) to Image Panel
        loadImagesToImagePanel();
        // Add the Image Panel to its wrapper
        setViewportView(imagesPanel);
    }
    private void loadImagesToImagePanel() {
        String[] imgPaths = new ZeroArtFetcherDownloadedImages().getRelativeImageFilePaths();
        int maxImageWidth = 150;
        int maxImageHeight = 150;
        for (String imgPath: imgPaths) {
            ImageIcon originalIcon = new ImageIcon(imgPath);
            Image originalImage = originalIcon.getImage();
            int originalWidth = originalIcon.getIconWidth();
            int originalHeight = originalIcon.getIconHeight();
            // Calculate the scaled width and height while preserving aspect ratio
            int scaledWidth, scaledHeight;
            if (originalWidth > originalHeight) {
                // Landscape image
                double ratio = (double) maxImageWidth / originalWidth;
                scaledWidth = maxImageWidth;
                scaledHeight = (int) (originalHeight * ratio);
            } else {
                // Portrait or square image
                double ratio = (double) maxImageHeight / originalHeight;
                scaledWidth = (int) (originalWidth * ratio);
                scaledHeight = maxImageHeight;
            }
            // Scale the image to the new dimensions
            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            // Create a new ImageIcon from the scaled image
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            // Create the JLabel and set the scaled ImageIcon
            JLabel imageLabel = new JLabel(
                    "<html>" +
                            "<body>" +
                            "<img src='file:///" + imgPath + "' />" +
                            "</body>" +
                            "</html>"
            );
            //System.out.println(imgPath);
            imagesPanel.add(imageLabel);
        }
    }
}