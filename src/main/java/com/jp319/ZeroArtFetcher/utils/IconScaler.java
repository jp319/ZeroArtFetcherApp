package com.jp319.ZeroArtFetcher.utils;

import javax.swing.*;
import java.awt.*;

public class IconScaler {
    public static Icon createScaledIcon(ImageIcon originalIcon, int maxImageWidth, int maxImageHeight) {
        try {
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
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
