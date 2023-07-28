package com.jp319.ZeroArtFetcher.views.listeners;

import com.jp319.ZeroArtFetcher.views.callbacks.ZeroArtFetcherLayoutToHeaderCallback;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class frameComponentListener implements ComponentListener {
	JFrame frame;
	ZeroArtFetcherLayoutToHeaderCallback callback;

	public frameComponentListener(JFrame frame, ZeroArtFetcherLayoutToHeaderCallback callback) {
		this.frame = frame;
		this.callback = callback;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		callback.frameDimensionQuery(frame);
	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}
}
