package com.jp319.zeroartfetcher.testing;

import javax.swing.*;

public class EfficientJLabelUpdateExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Efficient JLabel Update Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            JLabel label = new JLabel("Initial Text");
            frame.add(label);

            JButton updateButton = new JButton("Start Updates");
            updateButton.addActionListener(e -> {
                Thread updateThread = new Thread(() -> {
                    for (int i = 1; i <= 100; i++) {
                        // Perform your loop logic here
                        // Update the label text with each iteration
                        int finalI = i;
                        SwingUtilities.invokeLater(() -> label.setText("Iteration: " + finalI));
                        try {
                            Thread.sleep(100); // Add a delay between updates
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                updateThread.start(); // Start the thread to update the label
            });

            frame.add(updateButton);
            frame.setVisible(true);
        });
    }
}
