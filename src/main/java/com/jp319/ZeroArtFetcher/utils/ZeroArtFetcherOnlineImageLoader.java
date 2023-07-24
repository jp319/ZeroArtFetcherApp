package com.jp319.ZeroArtFetcher.utils;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class ZeroArtFetcherOnlineImageLoader extends SwingWorker<ImageIcon, Void> {
    //TODO: Make the progressbar also make the JCheckboxes
    private final JPanel imagesPanel;
    private final String imageUrl;
    private final int maxWidth;
    private final int maxHeight;
    public ZeroArtFetcherOnlineImageLoader(JPanel imagesPanel, String imageUrl, int maxWidth, int maxHeight) {
        this.imagesPanel = imagesPanel;
        this.imageUrl = imageUrl;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }
    @Override
    protected ImageIcon doInBackground() throws Exception {
        URI uri = URI.create(imageUrl);
        return new ImageIcon(uri.toURL());
    }

    @Override
    protected void done() {
        try {
            ImageIcon imageIcon = get();
            JLabel imageLabel = new JLabel(
                    IconScaler.createScaledIcon(imageIcon, maxWidth, maxHeight),
                    SwingConstants.CENTER
            );
            imagesPanel.add(imageLabel);
            imagesPanel.revalidate();
            imagesPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
