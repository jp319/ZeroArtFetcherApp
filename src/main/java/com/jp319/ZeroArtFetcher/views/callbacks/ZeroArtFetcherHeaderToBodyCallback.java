package com.jp319.ZeroArtFetcher.views.callbacks;

import com.jp319.ZeroArtFetcher.utils.gui.JRadioButtonExtended;

public interface ZeroArtFetcherHeaderToBodyCallback {
	void tagSearchEnteredOnline(String tagStr);
	void idSearchEnteredOnline(String idStr);
	void filterSelected(String tagStr);
}
