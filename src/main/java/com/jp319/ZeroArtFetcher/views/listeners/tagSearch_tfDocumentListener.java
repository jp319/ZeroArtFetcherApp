package com.jp319.ZeroArtFetcher.views.listeners;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class tagSearch_tfDocumentListener implements DocumentListener {
	private final JTextField idSearch_tf;
	private final JTextField tagSearch_tf;

	public tagSearch_tfDocumentListener(JTextField idSearch_tf, JTextField tagSearch_tf) {
		this.idSearch_tf = idSearch_tf;
		this.tagSearch_tf = tagSearch_tf;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		updateIdSearch_tfStatus();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateIdSearch_tfStatus();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		updateIdSearch_tfStatus();
	}

	private void updateIdSearch_tfStatus() {
		boolean hasText = tagSearch_tf.getText().trim().length() > 0;
		idSearch_tf.setEnabled(!hasText);
		
		tagSearch_tf.revalidate();
		tagSearch_tf.repaint();
	}
}