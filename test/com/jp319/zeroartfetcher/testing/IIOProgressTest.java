package com.jp319.zeroartfetcher.testing;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.event.IIOReadProgressListener;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;

public class IIOProgressTest
{
    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        final JLabel imageLabel = new JLabel();
        JScrollPane scrollPane = new JScrollPane(imageLabel);
        final JButton startBtn = new JButton("Load Image");
        final JProgressBar progressBar = new JProgressBar();
        progressBar.setVisible(false);
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.add(startBtn, BorderLayout.WEST);
        p.add(progressBar, BorderLayout.CENTER);

        final IIOReadProgressListener progressListener = new IIOReadProgressListener() {
            public void imageStarted(ImageReader source, int imageIndex)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        startBtn.setEnabled(false);
                        progressBar.setValue(0);
                        progressBar.setVisible(true);
                    }
                });
            }
            public void imageProgress(ImageReader source, final float percentageDone)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        System.out.println("percentageDone" + percentageDone);
                        progressBar.setValue((int) percentageDone);
                    }
                });
            }
            public void imageComplete(ImageReader source)
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    public void run()
                    {
                        startBtn.setEnabled(true);
                        progressBar.setValue(100);
                        progressBar.setVisible(false);
                    }
                });
            }

            public void sequenceStarted(ImageReader source, int minIndex) {}
            public void sequenceComplete(ImageReader source) {}
            public void thumbnailStarted(ImageReader source, int imageIndex, int thumbnailIndex) {}
            public void thumbnailProgress(ImageReader source, float percentageDone) {}
            public void thumbnailComplete(ImageReader source) {}
            public void readAborted(ImageReader source) {}
        };

        startBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                final String urlStr = JOptionPane.showInputDialog(
                        SwingUtilities.getWindowAncestor(startBtn), "Input image URL");
                if (urlStr == null) {
                    return;
                }

                new Thread() {
                    public void run()
                    {
                        try {
                            //URL url = new URL(urlStr);
                            URI url = URI.create(urlStr);
                            ImageInputStream iis = ImageIO.createImageInputStream(url.toURL().openStream());
                            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
                            if (iter.hasNext()) {
                                ImageReader reader = (ImageReader) iter.next();
                                reader.setInput(iis);
                                reader.addIIOReadProgressListener(progressListener);
                                BufferedImage image = reader.read(reader.getMinIndex());
                                final ImageIcon icon = new ImageIcon(image);

                                SwingUtilities.invokeLater(new Runnable()
                                {
                                    public void run()
                                    {
                                        imageLabel.setIcon(icon);
                                        imageLabel.revalidate();
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        JFrame f = new JFrame("IIOProgressTest");
        f.getContentPane().setLayout(new BorderLayout(5, 5));
        f.getContentPane().add(scrollPane, BorderLayout.CENTER);
        f.getContentPane().add(p, BorderLayout.SOUTH);
        f.setSize(800, 600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}