package com.jp319.zeroartfetcher.testing;
import javax.swing.*;
import java.awt.*;

public class PipeSeparatedComponentsExample {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> createAndShowGUI());
	}
	
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Pipe Separated Components Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 100);
		
		JPanel panel = new CustomSeparatorPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		JLabel label1 = new JLabel("Component 1");
		JLabel label2 = new JLabel("Component 2");
		JLabel label3 = new JLabel("Component 3");
		
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		
		frame.add(panel);
		frame.setVisible(true);
	}
	
	static class CustomSeparatorPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int height = getHeight();
			int width = getWidth();
			
			int separatorWidth = 2; // Adjust the width of the separator here
			int padding = 5; // Adjust the padding between components and separators here
			
			int x = padding;
			int y = height / 2;
			
			g.setColor(Color.BLACK);
			for (int i = 0; i < getComponentCount() - 1; i++) {
				x += getComponent(i).getPreferredSize().width;
				g.fillRect(x, y - separatorWidth / 2, separatorWidth, separatorWidth);
				x += padding;
			}
		}
	}
}
