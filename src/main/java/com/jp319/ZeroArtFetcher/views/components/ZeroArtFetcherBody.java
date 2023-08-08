package com.jp319.ZeroArtFetcher.views.components;

import com.jp319.ZeroArtFetcher.controllers.ZerochanSearcherOnline;
import com.jp319.ZeroArtFetcher.controllers.ZerochanSearcherOnlineV2;
import com.jp319.ZeroArtFetcher.utils.*;
import com.jp319.ZeroArtFetcher.utils.gui.LazyOnlineImageLoader;
import com.jp319.ZeroArtFetcher.utils.gui.ShakeFrame;
import com.jp319.ZeroArtFetcher.utils.gui.WrapLayout;
import com.jp319.ZeroArtFetcher.utils.other.FilterManager;
import com.jp319.ZeroArtFetcher.utils.other.SearchConstants;
import com.jp319.ZeroArtFetcher.utils.other.StringArrayCheck;
import com.jp319.ZeroArtFetcher.utils.other.WebsiteConnectivityChecker;
import com.jp319.ZeroArtFetcher.views.callbacks.ZeroArtFetcherHeaderToBodyCallback;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZeroArtFetcherBody
		extends JScrollPane
		implements ZeroArtFetcherHeaderToBodyCallback {
	public ZeroArtFetcherBody(ZeroArtFetcherFooter footerPanel, JFrame mainFrame) {
		initializeBodyPanel(footerPanel, mainFrame);
	}
	private ZerochanSearcherOnline zerochanSearcherOnline;
	private ZerochanSearcherOnlineV2 zerochanSearcherOnlineV2;
	private JPanel imagesPanel;
	//private JProgressBar progressBar;
	private ZeroArtFetcherFooter footerPanel;
	private JFrame mainFrame;
	private ExecutorService service;

	private void initializeBodyPanel(ZeroArtFetcherFooter footerPanel, JFrame mainFrame) {
		this.footerPanel = footerPanel;
		this.mainFrame = mainFrame;
		// Customize ScrollPane
		putClientProperty("JScrollBar.showButtons", true);
		// Add Image Panel to the Center of the BorderLayout with a margin
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 20, 20, 20), // Outside Margin
				BorderFactory.createLineBorder(Color.GRAY, 3, true) // LineBorder Inside the Margin
		));
		getVerticalScrollBar().setUnitIncrement(12);
		// Panel that will Contain the images
		imagesPanel = new JPanel();
		imagesPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 25, 5));
		imagesPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		// Initialize individual Components
		// Add Individual Components (Images) to Image Panel
		loadImagesToImagePanel(new String[0], false, null);
		// Add the Image Panel to its wrapper
		setViewportView(imagesPanel);
	}
	// Methods to update ImagePanel
	private void loadImagesToImagePanel(String[] images, boolean isURL, String message) {
		int maxImageWidth = 150;
		int maxImageHeight = 150;

		System.out.println("Images to load: "+Arrays.toString(images));
		System.out.println("Is a single image: "+StringArrayCheck.isStringArrayValid(images));

		if (StringArrayCheck.isStringArrayValid(images)) {
			if (isURL) {
				loadOnlineImages(images, maxImageWidth, maxImageHeight);
			} else {
				loadOfflineImages(images, maxImageWidth, maxImageHeight);
			}
		} else {

			// Customize Progress bar
			if (message == null) {
				footerPanel.getProgressBar().setForeground(new Color(28, 235, 235));
			} else {
				footerPanel.getProgressBar().setForeground(new Color(240, 45, 45));
				ShakeFrame.shakeFrame(mainFrame);
			}

			footerPanel.setProgressBarProgress(100);
			footerPanel.setTotalItems(0);

			imagesPanel.removeAll();
			JLabel noImage;
			noImage = new JLabel(Objects.requireNonNullElse(message, "No images yet... Search images through tag or id"));
			noImage.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment to CENTER
			noImage.setVerticalAlignment(SwingConstants.CENTER);   // Set vertical alignment to CENTER
			imagesPanel.setLayout(new BorderLayout());
			imagesPanel.add(noImage, BorderLayout.CENTER);
			imagesPanel.revalidate();
			imagesPanel.repaint();
		}
	}
	private void loadOnlineImages(String[] images, int maxWidth, int maxHeight) {
		// Cancel previous tasks and create a new ExecutorService
		if (service != null) {
			service.shutdownNow();
		}
		
		imagesPanel.removeAll();
		imagesPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
		footerPanel.setTotalItems(images.length);

		int processors = Runtime.getRuntime().availableProcessors();
		service = Executors.newFixedThreadPool(processors - 2);
		System.out.println("Number of processors: "+processors);

		for (String image : images) {
			LazyOnlineImageLoader onlineImageLoader =
					new LazyOnlineImageLoader(imagesPanel, footerPanel, image, maxWidth, maxHeight);
			service.submit(onlineImageLoader.loadImage());
		}

		service.shutdown();
		SwingUtilities.invokeLater(()->{
			// Repaint the panel to show the initial placeholders for the images
			imagesPanel.revalidate();
			imagesPanel.repaint();
		});
	}
	private void loadOfflineImages(String[] images, int maxWidth, int maxHeight) {
		for (String image : images) {
			ZeroArtFetcherOfflineImageLoader offlineImageLoader =
					new ZeroArtFetcherOfflineImageLoader(imagesPanel, image, maxWidth, maxHeight);
			offlineImageLoader.execute();
		}
	}
	private void loadSavedImagesFromDirectory() {
		loadImagesToImagePanel(new ZeroArtFetcherDownloadedImages().getImageFilePaths(), false, "No Saved Images found...");
	}
	private void loadTagSearchThumbnailsToImagePanel(String tagsToSearch, String filters) {
		//TODO: Make a new instance of ZerochanSearcher
		//      Make to pass the filters
		ZerochanSearcherOnline zerochanSearcherOnline =
				new ZerochanSearcherOnline(tagsToSearch, filters);
		try {
			loadImagesToImagePanel(zerochanSearcherOnline.getThumbnails(), true, "No Results found...");
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	// Updates by Listeners from Header
	@Override
	public void tagSearchEnteredOnline(String tagStr) {
		//use this for tag search: "Enkin0k0,Saber"
//		zerochanSearcherOnline =
//				new ZerochanSearcherOnline(
//						tagStr,
//						FilterManager.getConcatenatedFilters()
//				);
		if (WebsiteConnectivityChecker.isWebsiteReachable()) {
			System.out.println("Filter used: " + FilterManager.getConcatenatedFilters());
			try (ZerochanSearcherOnlineV2 zerochanSearcherOnlineV2 =
						 new ZerochanSearcherOnlineV2(tagStr, FilterManager.getConcatenatedFilters())) {
				
				SearchConstants.PreviousSearchedString = tagStr;
				
				String[] images = zerochanSearcherOnlineV2.getThumbnails();
				loadImagesToImagePanel(images, true, "No results found for this Tag Search...");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println("Tag(s) used: " + tagStr);
		} else {
			loadImagesToImagePanel(null, true, "No internet connection...");
		}
	}
	@Override
	public void idSearchEnteredOnline(String idStr) {
		if (WebsiteConnectivityChecker.isWebsiteReachable()) {
			System.out.println("Filter used: " + FilterManager.getConcatenatedFilters());
			try (ZerochanSearcherOnlineV2 zerochanSearcherOnlineV2 =
						 new ZerochanSearcherOnlineV2(idStr)) {
				
				SearchConstants.PreviousSearchedString = idStr;
				
				String[] images = zerochanSearcherOnlineV2.getThumbnails();
				loadImagesToImagePanel(images, true, "No results found for this ID Search...");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println("ID used: " + idStr);
		} else {
			loadImagesToImagePanel(null, true, "No internet connection...");
		}
	}
	@Override
	public void filterSelected(String tagStr) {
		tagSearchEnteredOnline(tagStr);
	}
}