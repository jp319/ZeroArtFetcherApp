package Temp_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;


public class Wall extends JFrame {
    ImageIcon[] imageIcons;
    JButton[] imageLabels;
    Container container;
    JButton Browse = new JButton("BROWSE");
    JButton My_Wall = new JButton("MY WALLPAPER");

    Wall() {
        container = getContentPane();
        setLayoutManager();
        setLocationSize();
        addComponentsToContainer();
        setTitle("Zero-Chan");
        setVisible(true);
        setBounds(0, 0, 1500, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void setLayoutManager() {
        container.setLayout(new GridLayout(0, 4, 10, 10));
    }

    public void setLocationSize() {
        Browse.setBounds(90, 20, 250, 30);
        My_Wall.setBounds(850, 20, 250, 30);

        // Image URLs
        String[] imageUrls = {
                "https://s3.zerochan.net/240/49/23/3318699.jpg","https://s3.zerochan.net/240/30/11/3793080.jpg",
                "https://s3.zerochan.net/240/37/48/3864937.jpg","https://s3.zerochan.net/240/01/48/3149901.jpg",
                "https://s3.zerochan.net/240/21/00/3542521.jpg","https://s3.zerochan.net/240/17/03/3145167.jpg",
                "https://s3.zerochan.net/240/44/03/2930194.jpg","https://s3.zerochan.net/240/23/13/3033173.jpg",
                "https://s3.zerochan.net/240/28/32/3101628.jpg","https://s3.zerochan.net/240/41/20/3396041.jpg",
                "https://s3.zerochan.net/240/18/22/3181118.jpg","https://s3.zerochan.net/240/10/15/3460760.jpg",
                "https://s3.zerochan.net/240/41/39/3116991.jpg","https://s3.zerochan.net/240/17/17/3813367.jpg",
                "https://s3.zerochan.net/240/02/13/3098152.jpg","https://s3.zerochan.net/240/15/20/3736015.jpg",
                "https://s3.zerochan.net/240/28/22/3501128.jpg","https://s3.zerochan.net/240/43/32/3081643.jpg",
                "https://s3.zerochan.net/240/29/15/3220779.jpg","https://s3.zerochan.net/240/08/20/3256008.jpg",
                "https://s3.zerochan.net/240/03/16/3085803.jpg","https://s3.zerochan.net/240/48/22/3113648.jpg",
                "https://s3.zerochan.net/240/42/46/3802342.jpg","https://s3.zerochan.net/240/07/01/3197557.jpg",
        };

        imageIcons = new ImageIcon[imageUrls.length];
        imageLabels = new JButton[imageUrls.length];

        int maxButtonWidth = 280;
        int maxButtonHeight = 180;
        for (int i = 0; i < imageUrls.length; i++) {
            final int[] index = {1};
            try {
                URL imageUrl = new URL(imageUrls[i]);
                imageIcons[i] = new ImageIcon(imageUrl);

                // Calculate the new dimensions while maintaining the aspect ratio
                int originalWidth = imageIcons[i].getIconWidth();
                int originalHeight = imageIcons[i].getIconHeight();
                int newWidth, newHeight;
                if (originalWidth > originalHeight) {
                    newWidth = maxButtonWidth;
                    newHeight = (int) (originalHeight / (float) originalWidth * maxButtonWidth);
                } else {
                    newHeight = maxButtonHeight;
                    newWidth = (int) (originalWidth / (float) originalHeight * maxButtonHeight);
                }

                // Scale the image to the new dimensions
                Image image = imageIcons[i].getImage();
                Image newImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                imageIcons[i] = new ImageIcon(newImage);

                imageLabels[i] = new JButton(imageIcons[i]);
                int finalI = i;
                imageLabels[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        index[0] = finalI;
                        System.out.println("Image label button clicked for image " + index[0]);
                        openImageUrlInBrowser(imageUrls[index[0]]);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addComponentsToContainer() {
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(0, 4, 10, 10));

        for (JButton imageLabel : imageLabels) {
            imagePanel.add(imageLabel);
        }

        // Add the imagePanel to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create a new JPanel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(Browse);
        buttonPanel.add(My_Wall);

        // Add the scrollPane and buttons panel to the main container
        container.setLayout(new BorderLayout());
        container.add(buttonPanel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
    }

    public void openImageUrlInBrowser(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            java.awt.Desktop.getDesktop().browse(url.toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Wall();
    }
}
