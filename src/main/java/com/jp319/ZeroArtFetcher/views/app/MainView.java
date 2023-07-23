package com.jp319.ZeroArtFetcher.views.app;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.jp319.ZeroArtFetcher.utils.WrapLayout;
import com.jp319.ZeroArtFetcher.utils.ZeroArtFetcherDownloadedImages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainView extends JFrame {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        SwingUtilities.invokeLater(() -> {
            MainView frame = new MainView();
            frame.setVisible(true);
        });
    }
    private Container container;
    public MainView() {
        initializeFrame();
        initializeContainer();
        setLayoutManager();
        addComponents();
        packFrame();
        centerFrameOnScreen();
    }
    // Variable Initialization
    private JPanel headerPanel;
    private JLabel header_lb;
    private JLabel idSearch_lb;
    private JTextField idSearch_tf;
    private JLabel tagSearch_lb;
    private JTextField tagSearch_tf;
    private JPanel imagesPanel;
    // Frame Initialization
    private void initializeFrame() {
        setTitle("Centered JFrame Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int preferredWidth = 600;
        int preferredHeight = 600;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        setMinimumSize(new Dimension(preferredWidth, preferredHeight));
    }
    private void initializeContainer() {
        container = getContentPane();
    }
    private void setLayoutManager() {
        container.setLayout(new BorderLayout());
    }
    private void packFrame() {
        pack();
    }
    private void centerFrameOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - getWidth()) / 2;
        int centerY = (screenSize.height - getHeight()) / 3;
        setLocation(centerX, centerY);
    }
    // Components
    private void addComponents() {
        // Add Header Components
        addHeaderComponents();
        // Add buttons in the center (using FlowLayout)
        addBodyComponents();
    }
    private void addBodyComponents() {
        // Margin
        int margin = 10;
        // Add Image Panel to the Center of the BorderLayout with a margin
        JScrollPane imagePanelWrapper = new JScrollPane(); // Wraps images panel to make it scrollable
        imagePanelWrapper
                .setBorder(BorderFactory
                        .createEmptyBorder(margin, margin, margin, margin));
        imagePanelWrapper.getVerticalScrollBar().setUnitIncrement(16);
        // Panel that will Contain the images
        imagesPanel = new JPanel();
        imagesPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 25, 5));
        // Initialize individual Components
        // Add Individual Components to Header Panel
        addImagesToImagePanel(); // Add image to imagePanel
        // Add the images panel to its wrapper
        imagePanelWrapper.setViewportView(imagesPanel);
        // Add Components to Container
        container.add(imagePanelWrapper, BorderLayout.CENTER);
    }
    private void addImagesToImagePanel() {
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
            JLabel imageLabel = new JLabel(scaledIcon);
            imagesPanel.add(imageLabel);
        }
    }
    private void addHeaderComponents() {
        // Add Header Panel to the top (north) of the BorderLayout with a margin
        headerPanel = new PercentageMarginPanel(); // Extends JPanel() which handles percentage based margins
        headerPanel.setLayout(new GridBagLayout());
        // Set individual margins (left, right, top, and bottom) using setters
        ((PercentageMarginPanel) headerPanel).setLeftMarginPercentage(20.0);
        ((PercentageMarginPanel) headerPanel).setRightMarginPercentage(20.0);
        ((PercentageMarginPanel) headerPanel).setTopMarginPercentage(20.0);
        ((PercentageMarginPanel) headerPanel).setBottomMarginPercentage(20.0);

        // Initialize individual Components
        header_lb = new JLabel("ZeroArtFetcher", SwingConstants.CENTER);
        idSearch_lb = new JLabel("ID Search", SwingConstants.LEFT);
        idSearch_tf = new JTextField();
        tagSearch_lb = new JLabel("Tag(s) Search", SwingConstants.LEFT);
        tagSearch_tf = new JTextField();
        // Add Individual Components to Header Panel
        headerPanel.add(header_lb, gridBagConstraints(0,0,2, 0.0,GridBagConstraints.CENTER, GridBagConstraints.NONE));
        headerPanel.add(idSearch_lb, gridBagConstraints(0,1,1, 0.01,GridBagConstraints.WEST, GridBagConstraints.NONE));
        headerPanel.add(idSearch_tf, gridBagConstraints(1,1,1, 1.0,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        headerPanel.add(tagSearch_lb, gridBagConstraints(0, 2, 1, 0.01,GridBagConstraints.WEST, GridBagConstraints.NONE));
        headerPanel.add(tagSearch_tf, gridBagConstraints(1, 2, 1, 1.0,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        // Add Components to Container
        container.add(headerPanel, BorderLayout.NORTH);
    }

    // Utility Methods
    private GridBagConstraints gridBagConstraints(int x, int y, int width, double weightX, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = 1;
        gbc.anchor = anchor;
        gbc.weightx = weightX;
        gbc.fill = fill;
        gbc.insets = new Insets(5, 5, 0, 5);
        return gbc;
    }
    // Percentage Based Margin
    private static class PercentageMarginPanel extends JPanel {
        private double leftMarginPercentage = 0.0;
        private double rightMarginPercentage = 0.0;
        private double topMarginPercentage = 0.0;
        private double bottomMarginPercentage = 0.0;

        public PercentageMarginPanel() {
            // Add a component listener to track container size changes
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    updateMargin();
                }
            });
        }

        private void updateMargin() {
            int containerWidth = getWidth();
            int containerHeight = getHeight();

            int leftMarginValue = (int) (containerWidth * (leftMarginPercentage / 100.0));
            int rightMarginValue = (int) (containerWidth * (rightMarginPercentage / 100.0));
            int topMarginValue = (int) (containerHeight * (topMarginPercentage / 100.0));
            int bottomMarginValue = (int) (containerHeight * (bottomMarginPercentage / 100.0));

            // Set the margin on the layout
            setBorder(BorderFactory.createEmptyBorder(topMarginValue, leftMarginValue, bottomMarginValue, rightMarginValue));
        }

        // Individual setters for each side's margin percentage
        public void setLeftMarginPercentage(double leftMarginPercentage) {
            this.leftMarginPercentage = leftMarginPercentage;
            updateMargin();
        }

        public void setRightMarginPercentage(double rightMarginPercentage) {
            this.rightMarginPercentage = rightMarginPercentage;
            updateMargin();
        }

        public void setTopMarginPercentage(double topMarginPercentage) {
            this.topMarginPercentage = topMarginPercentage;
            updateMargin();
        }

        public void setBottomMarginPercentage(double bottomMarginPercentage) {
            this.bottomMarginPercentage = bottomMarginPercentage;
            updateMargin();
        }
    }
}