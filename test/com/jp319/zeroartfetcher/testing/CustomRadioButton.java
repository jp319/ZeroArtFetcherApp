package com.jp319.zeroartfetcher.testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class CustomRadioButton extends JComponent {
    private boolean selected;
    private String text;

    public CustomRadioButton(String text) {
        this.text = text;
        this.selected = false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelected(!isSelected());
            }
        });
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the radio button circle
        g.setColor(Color.BLACK);
        g.drawOval(2, 2, 16, 16);

        // Draw the check mark if selected
        if (isSelected()) {
            g.drawLine(4, 10, 8, 14);
            g.drawLine(8, 14, 14, 6);
        }

        // Draw the wrapped text
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        int maxWidth = getWidth() - 24; // Adjust for the radio button size
        int textX = 24;
        int textY = 14;

        Font font = g2d.getFont();
        TextLayout textLayout = new TextLayout(text, font, frc);
        Rectangle2D bounds = textLayout.getBounds();

        // Check if the text needs to be wrapped
        if (bounds.getWidth() > maxWidth) {
            // Wrap the text
            String[] words = text.split(" ");
            StringBuilder wrappedText = new StringBuilder();
            StringBuilder line = new StringBuilder();

            for (String word : words) {
                if (textLayout
                        .getBounds()
                        .getWidth() + textLayout
                        .getBounds()
                        .getWidth() <= maxWidth) {
                    line.append(word).append(" ");
                } else {
                    wrappedText.append(line.toString().trim()).append("\n");
                    line.setLength(0);
                    line.append(word).append(" ");
                }
            }

            wrappedText.append(line.toString().trim());

            // Draw the wrapped text
            g2d.drawString(wrappedText.toString(), textX, textY);
        } else {
            // Draw the original text
            g2d.drawString(text, textX, textY);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Graphics g = getGraphics();
        if (g != null) {
            FontMetrics fm = g.getFontMetrics();
            int height = fm.getHeight();
            g.dispose();
            return new Dimension((int) Math.ceil(fm.getStringBounds(text, getGraphics()).getWidth()) + 24, height);
        }
        return new Dimension(100, 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Radio Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        CustomRadioButton radioButton1 = new CustomRadioButton("Option 1");
        CustomRadioButton radioButton2 = new CustomRadioButton("Option 2 with a long text that wraps dynamically");
        CustomRadioButton radioButton3 = new CustomRadioButton("Option 3");

        panel.add(radioButton1);
        panel.add(radioButton2);
        panel.add(radioButton3);

        frame.add(panel);
        frame.setVisible(true);
    }
}
