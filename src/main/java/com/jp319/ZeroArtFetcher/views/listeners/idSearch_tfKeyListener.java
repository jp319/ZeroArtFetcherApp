package com.jp319.ZeroArtFetcher.views.listeners;

import com.jp319.ZeroArtFetcher.views.callbacks.ZeroArtFetcherHeaderToBodyCallback;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class idSearch_tfKeyListener implements KeyListener {
	JTextField idSearch_tf;
	ZeroArtFetcherHeaderToBodyCallback callback;
	public idSearch_tfKeyListener(JTextField idSearch_tf, ZeroArtFetcherHeaderToBodyCallback callback) {
		this.idSearch_tf = idSearch_tf;
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
			String idToSearch = idSearch_tf.getText().trim();
			callback.idSearchEnteredOnline(idToSearch);
		}
	}
}
