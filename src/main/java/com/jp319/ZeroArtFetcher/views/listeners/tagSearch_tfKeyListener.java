package com.jp319.ZeroArtFetcher.views.listeners;

import com.jp319.ZeroArtFetcher.views.callbacks.ZeroArtFetcherHeaderToBodyCallback;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class tagSearch_tfKeyListener implements KeyListener {
	JTextField tagSearch_tf;
	ZeroArtFetcherHeaderToBodyCallback callback;
	public tagSearch_tfKeyListener(JTextField tagSearch_tf, ZeroArtFetcherHeaderToBodyCallback callback) {
		this.tagSearch_tf = tagSearch_tf;
		this.callback = callback;
	}
	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyPressed(KeyEvent e) {

	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String tagsToSearch = tagSearch_tf.getText().trim();
			callback.tagSearchEnteredOnline(tagsToSearch);
		}
	}
}
