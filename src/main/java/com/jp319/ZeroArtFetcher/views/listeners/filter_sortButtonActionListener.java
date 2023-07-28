package com.jp319.ZeroArtFetcher.views.listeners;

import com.jp319.ZeroArtFetcher.utils.gui.JRadioButtonExtended;
import com.jp319.ZeroArtFetcher.utils.other.FilterManager;
import com.jp319.ZeroArtFetcher.utils.other.PreviousSearch;
import com.jp319.ZeroArtFetcher.views.callbacks.ZeroArtFetcherHeaderToBodyCallback;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class filter_sortButtonActionListener implements ActionListener {
	JRadioButtonExtended jRadioButtonExtended;
	ZeroArtFetcherHeaderToBodyCallback callback;
	JTextField tagStr;
	public filter_sortButtonActionListener(
			JRadioButtonExtended jRadioButtonExtended,
			ZeroArtFetcherHeaderToBodyCallback callback,
			JTextField tagStr) {
		this.jRadioButtonExtended = jRadioButtonExtended;
		this.callback = callback;
		this.tagStr = tagStr;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread(() -> {
			// Execute the logic inside the new thread
			if (!tagStr.getText().isEmpty()) {
				callback.filterSelected(tagStr.getText().trim());
			} else {
				if (!PreviousSearch.PreviousSearchedString.isEmpty()) {
					callback.filterSelected(PreviousSearch.PreviousSearchedString);
				}
			}
			System.out.println("Filter check from action listener: " + FilterManager.getConcatenatedFilters());
		}).start(); // Start the thread's execution
	}
	
}
