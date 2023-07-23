package com.jp319.ZeroArtFetcher.views.components;

import com.jp319.ZeroArtFetcher.utils.PercentageMarginPanel;
import com.jp319.ZeroArtFetcher.utils.GridBagConstraints;
import com.jp319.ZeroArtFetcher.views.listeners.idSearch_tfDocumentListener;
import com.jp319.ZeroArtFetcher.views.listeners.tagSearch_tfDocumentListener;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ZeroArtFetcherHeader extends PercentageMarginPanel {
    private final JLabel header_lb = new JLabel("ZeroArtFetcher", SwingConstants.CENTER);
    private final JLabel idSearch_lb = new JLabel("ID Search", SwingConstants.LEFT);
    private final JTextField idSearch_tf = new JTextField();
    private final JLabel tagSearch_lb = new JLabel("Tag(s) Search", SwingConstants.LEFT);
    private final JTextField tagSearch_tf = new JTextField();
    public ZeroArtFetcherHeader() {
        initializeHeaderPanel();
        initializeHeaderPanelComponents();
    }
    private void initializeHeaderPanel() {
        setLayout(new GridBagLayout());
        // Set individual margins (left, right, top, and bottom) using setters
        setLeftMarginPercentage(20.0);
        setRightMarginPercentage(20.0);
        setTopMarginPercentage(20.0);
        setBottomMarginPercentage(20.0);
    }
    private void initializeHeaderPanelComponents() {
        // Customize components as needed
        header_lb.setBorder(BorderFactory
                .createEmptyBorder(10, 10,10,10));
        // Add components to the panel
        add(header_lb, new GridBagConstraints(
                0,0,7, 0.0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE
        ));
        add(idSearch_lb, new GridBagConstraints(
                0,1,1, 0.0,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE
        ));
        add(idSearch_tf, new GridBagConstraints(
                1,1,2, 1.0,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL
        ));
        add(tagSearch_lb, new GridBagConstraints(
                3, 1, 1, 0.0,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE
        ));
        add(tagSearch_tf, new GridBagConstraints(
                4, 1, 2, 1.0,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL
        ));
        // Add Listeners
        DocumentListener idSearch_tfListener = new idSearch_tfDocumentListener(idSearch_tf, tagSearch_tf);
        DocumentListener tagSearch_tfListener = new tagSearch_tfDocumentListener(idSearch_tf, tagSearch_tf);
        idSearch_tf.getDocument().addDocumentListener(idSearch_tfListener);
        tagSearch_tf.getDocument().addDocumentListener(tagSearch_tfListener);
    }
}