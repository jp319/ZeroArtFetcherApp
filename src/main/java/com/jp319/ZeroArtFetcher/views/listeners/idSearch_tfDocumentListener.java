package com.jp319.ZeroArtFetcher.views.listeners;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class idSearch_tfDocumentListener implements DocumentListener {
    private final JTextField idSearch_tf;
    private final JTextField tagSearch_tf;

    public idSearch_tfDocumentListener(JTextField idSearch_tf, JTextField tagSearch_tf) {
        this.idSearch_tf = idSearch_tf;
        this.tagSearch_tf = tagSearch_tf;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateTagSearch_tfStatus();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateTagSearch_tfStatus();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateTagSearch_tfStatus();
    }

    private void updateTagSearch_tfStatus() {
        boolean hasText = idSearch_tf.getText().trim().length() > 0;
        tagSearch_tf.setEnabled(!hasText);
    }
}