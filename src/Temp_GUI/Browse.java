package Temp_GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Browse extends JFrame {
        private ImageIcon imageIcon;
        private JLabel imageLabel;

        public Browse() {
            // Set the title of the frame
            super("Image Display");

            // Set the layout for the frame
            setLayout(new BorderLayout());

            // Load the image from the URL
            try {
                URL imageUrl = new URL("https://initiate.alphacoders.com/images/132/stretched-1920-1080-1323270.png?6478");
                imageIcon = new ImageIcon(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Create a label to display the image
            imageLabel = new JLabel();
            imageLabel.setIcon(imageIcon);


            // Add the image label to the frame
            add(imageLabel, BorderLayout.CENTER);

            // Set frame properties
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(Browse::new);
        }
    }

