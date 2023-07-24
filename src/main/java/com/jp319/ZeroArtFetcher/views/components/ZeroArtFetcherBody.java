package com.jp319.ZeroArtFetcher.views.components;

import com.jp319.ZeroArtFetcher.controllers.ZerochanSearcherOnline;
import com.jp319.ZeroArtFetcher.utils.*;
import com.jp319.ZeroArtFetcher.views.callbacks.ZeroArtFetcherHeaderToBodyCallback;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ZeroArtFetcherBody
        extends JScrollPane
        implements ZeroArtFetcherHeaderToBodyCallback {
    public ZeroArtFetcherBody() {
        initializeBodyPanel();
    }
    private ZerochanSearcherOnline zerochanSearcherOnline;
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
        loadImagesToImagePanel(new String[0], false);
        // Add the Image Panel to its wrapper
        setViewportView(imagesPanel);
    }
    // Methods to update ImagePanel
    private void loadImagesToImagePanel(String[] images, boolean isURL) {
        int maxImageWidth = 150;
        int maxImageHeight = 150;
        if (StringArrayCheck.isStringArrayValid(images)) {
            if (isURL) {
                loadOnlineImages(images, maxImageWidth, maxImageHeight);
            } else {
                loadOfflineImages(images, maxImageWidth, maxImageHeight);
            }
        } else {
            imagesPanel.removeAll();
            JLabel noImage = new JLabel("No images yet... Search images through tag or id");
            noImage.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment to CENTER
            noImage.setVerticalAlignment(SwingConstants.CENTER);   // Set vertical alignment to CENTER
            imagesPanel.setLayout(new BorderLayout());
            imagesPanel.add(noImage, BorderLayout.CENTER);
            imagesPanel.revalidate();
            imagesPanel.repaint();
        }
    }
    private void loadOnlineImages(String[] images, int maxWidth, int maxHeight) {
        imagesPanel.removeAll();
        imagesPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
        for (String image : images) {
            ZeroArtFetcherOnlineImageLoader onlineImageLoader =
                    new ZeroArtFetcherOnlineImageLoader(imagesPanel, image, maxWidth, maxHeight);
            onlineImageLoader.execute();
        }
    }
    private void loadOfflineImages(String[] images, int maxWidth, int maxHeight) {
        for (String image : images) {
            ZeroArtFetcherOfflineImageLoader offlineImageLoader =
                    new ZeroArtFetcherOfflineImageLoader(imagesPanel, image, maxWidth, maxHeight);
            offlineImageLoader.execute();
        }
    }
    private void loadSavedImagesFromDirectory() {
        loadImagesToImagePanel(new ZeroArtFetcherDownloadedImages().getImageFilePaths(), false);
    }
    private void loadTagSearchThumbnailsToImagePanel(String tagsToSearch, String filters) {
        //TODO: Make a new instance of ZerochanSearcher
        //      Make to pass the filters
        ZerochanSearcherOnline zerochanSearcherOnline =
                new ZerochanSearcherOnline(tagsToSearch, filters);
        try {
            loadImagesToImagePanel(zerochanSearcherOnline.getThumbnails(), true);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    // Updates by Listeners from Header
    @Override
    public void tagSearchEnteredOnline(String tagStr) {
        //use this for tag search: "Enkin0k0,Saber"
        zerochanSearcherOnline = new ZerochanSearcherOnline(tagStr, "strict&p=1&l=22&s=id");
        try {
            String[] images = zerochanSearcherOnline.getThumbnails();
            loadOnlineImages(images, 150, 150);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(tagStr);
    }
    @Override
    public void idSearchEnteredOnline(String idStr) {
        zerochanSearcherOnline = new ZerochanSearcherOnline(idStr);
        String[] image = new String[1];
        try {
            image[0] = zerochanSearcherOnline.getImg();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        loadOnlineImages(image, 150, 150);
        System.out.println(image[0]);
        System.out.println(idStr);
    }
}