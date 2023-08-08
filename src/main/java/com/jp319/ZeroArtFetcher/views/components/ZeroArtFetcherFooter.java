package com.jp319.ZeroArtFetcher.views.components;

import com.jp319.ZeroArtFetcher.utils.gui.GridBagConstraintsExtended;

import javax.swing.*;
import java.awt.*;

public class ZeroArtFetcherFooter extends JPanel {
	private final JProgressBar progressBar;
	private final JLabel downloadRatio = new JLabel("0/0");
	private int totalItems;
	private int loadedItem = 0;
	public ZeroArtFetcherFooter(JProgressBar progressBar) {
		this.progressBar = progressBar;
		initializeFooterPanel();
	}
	private void initializeFooterPanel() {
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(0, 20, 20, 20), // Outside Margin
				BorderFactory.createLineBorder(Color.GRAY, 1, false) // LineBorder Inside the Margin
		));
		setLayout(new GridBagLayout());
		initializeComponents();
		add(downloadRatio, new GridBagConstraintsExtended(
				9,0,1,0.0,
				GridBagConstraints.EAST,
				GridBagConstraints.EAST
		));
		add(progressBar, new GridBagConstraintsExtended(
				0,0,8,1.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL
		));
	}
	private void initializeComponents() {
		downloadRatio.putClientProperty( "FlatLaf.styleClass", "monospaced" );

		progressBar.putClientProperty("JProgressBar.largeHeight", true);
		progressBar.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		progressBar.setForeground(new Color(0, 255, 216));
	}
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	public void setProgressBarProgress(int progress) {
		progressBar.setValue(progress);
	}
	public void updateLoadedItemLabel() {
		SwingUtilities.invokeLater(() -> downloadRatio.setText(incrementLoadedItem() + "/" + totalItems));
	}
	public void setTotalItems(int totalDownloads) {
		SwingUtilities.invokeLater(() -> {
			totalItems = totalDownloads;
			resetLoadedItem();
			downloadRatio.setText(loadedItem+"/" + totalItems);
		});
	}
	private int incrementLoadedItem() {
		return loadedItem+=1;
	}
	public void resetLoadedItem() {
		loadedItem = 0;
	}
}
