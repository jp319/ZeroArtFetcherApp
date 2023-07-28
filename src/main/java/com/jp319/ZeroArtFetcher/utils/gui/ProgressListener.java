package com.jp319.ZeroArtFetcher.utils.gui;

import com.jp319.ZeroArtFetcher.views.components.ZeroArtFetcherFooter;

import javax.imageio.ImageReader;
import javax.imageio.event.IIOReadProgressListener;
import javax.swing.*;
import java.awt.*;

public class ProgressListener implements IIOReadProgressListener {
	ZeroArtFetcherFooter footerPanel;
	int imageCount;
	public ProgressListener(ZeroArtFetcherFooter footerPanel) {

		this.footerPanel = footerPanel;
	}

	@Override
	public void imageStarted(ImageReader source, int imageIndex) {
		SwingUtilities.invokeLater(() -> {
			footerPanel.setProgressBarProgress(0);
			footerPanel.getProgressBar().setVisible(true);
			footerPanel.getProgressBar().setForeground(new Color(21, 240, 65));
			footerPanel.updateLoadedItemLabel();
		});
//        progressBar.setValue(0);
//        progressBar.setVisible(true);
	}

	@Override
	public void imageProgress(ImageReader source, float percentageDone) {
		//System.out.println("percentageDone" + percentageDone);
		SwingUtilities.invokeLater(() -> {
			footerPanel.setProgressBarProgress((int) percentageDone);
		});
//        progressBar.setValue((int) percentageDone);
//        progressBar.revalidate();
//        progressBar.repaint();
	}

	@Override
	public void imageComplete(ImageReader source) {
		SwingUtilities.invokeLater(() -> {
			footerPanel.setProgressBarProgress(100);
		});
//        progressBar.setValue(100);
//        progressBar.setVisible(false);
	}

	@Override
	public void sequenceStarted(ImageReader source, int minIndex) {

	}

	@Override
	public void sequenceComplete(ImageReader source) {

	}

	@Override
	public void thumbnailStarted(ImageReader source, int imageIndex, int thumbnailIndex) {

	}

	@Override
	public void thumbnailProgress(ImageReader source, float percentageDone) {

	}

	@Override
	public void thumbnailComplete(ImageReader source) {

	}

	@Override
	public void readAborted(ImageReader source) {

	}
}
