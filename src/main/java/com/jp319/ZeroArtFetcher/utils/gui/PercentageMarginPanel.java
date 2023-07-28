package com.jp319.ZeroArtFetcher.utils.gui;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PercentageMarginPanel extends JPanel {
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
