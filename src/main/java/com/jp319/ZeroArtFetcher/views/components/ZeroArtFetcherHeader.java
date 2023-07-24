package com.jp319.ZeroArtFetcher.views.components;

import com.jp319.ZeroArtFetcher.utils.*;
import com.jp319.ZeroArtFetcher.views.callbacks.ZeroArtFetcherHeaderToBodyCallback;
import com.jp319.ZeroArtFetcher.views.callbacks.ZeroArtFetcherLayoutToHeaderCallback;
import com.jp319.ZeroArtFetcher.views.listeners.idSearch_tfDocumentListener;
import com.jp319.ZeroArtFetcher.views.listeners.idSearch_tfKeyListener;
import com.jp319.ZeroArtFetcher.views.listeners.tagSearch_tfDocumentListener;
import com.jp319.ZeroArtFetcher.views.listeners.tagSearch_tfKeyListener;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class ZeroArtFetcherHeader
        extends PercentageMarginPanel
        implements ZeroArtFetcherLayoutToHeaderCallback {
    private ZeroArtFetcherHeaderToBodyCallback headerToBodyCallback;
    private final JLabel header_lb = new JLabel("ZeroArtFetcher", SwingConstants.CENTER);
    private final JLabel idSearch_lb = new JLabel("ID Search", SwingConstants.LEFT);
    private final JTextField idSearch_tf = new JTextField();
    private final JLabel idSearchIcon_lb = new JLabel();
    private final JLabel tagSearch_lb = new JLabel("Tag(s) Search", SwingConstants.LEFT);
    private final JTextField tagSearch_tf = new JTextField();
    private final JLabel tagSearchIcon_lb = new JLabel();
    private final Icon searchIcon = createSearchIcon();
    JPanel sortPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
    private final ButtonGroup sortButtonGroup = new ButtonGroup();
    private final JRadioButton sortRecentBtn = new JRadioButton("Recent");
    private final JRadioButton sortPopularBtn = new JRadioButton("Popular");
    JPanel filterPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
    private final ButtonGroup filterButtonGroup = new ButtonGroup();
    private final JRadioButton filterAllBtn = new JRadioButton("All");
    private final JRadioButton filterLargeBtn = new JRadioButton("Large");
    private final JRadioButton filterHugeBtn = new JRadioButton("Huge");
    private final JRadioButton filterLandscapeBtn = new JRadioButton("Landscape");
    private final JRadioButton filterPortraitBtn = new JRadioButton("Portrait");
    private final JRadioButton filterSquareBtn = new JRadioButton("Square");
    private final JButton showSavedImagesBtn = new JButton("Saved Images");
    public ZeroArtFetcherHeader(ZeroArtFetcherHeaderToBodyCallback callback) {
        this.headerToBodyCallback = callback;
        initializeHeaderPanel();
        initializeHeaderPanelComponents();
    }
    private void initializeHeaderPanel() {
        setLayout(new GridBagLayout());
        // Set individual margins (left, right, top, and bottom) using setters
        setLeftMarginPercentage(20.0);
        setRightMarginPercentage(20.0);
        setTopMarginPercentage(5.0);
        setBottomMarginPercentage(5.0);
    }
    private void initializeHeaderPanelComponents() {
        // Customize components as needed
        header_lb.setBorder(BorderFactory
                .createEmptyBorder(10, 10,10,10));
        try {
            // Load the font file from the "fonts" folder
            File fontFile = new File("src/main/resources/fonts/Quicksand.otf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            // Derive the desired font size and style
            Font customFontSized = customFont.deriveFont(Font.BOLD, 20);
            header_lb.setFont(customFontSized);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        idSearchIcon_lb.setIcon(searchIcon);
        tagSearchIcon_lb.setIcon(searchIcon);
        //    // Sort Button Group
        sortButtonGroup.add(sortRecentBtn);
        sortButtonGroup.add(sortPopularBtn);
        //   // Filter Button Group
        filterButtonGroup.add(filterAllBtn);
        filterButtonGroup.add(filterLargeBtn);
        filterButtonGroup.add(filterHugeBtn);
        filterButtonGroup.add(filterLandscapeBtn);
        filterButtonGroup.add(filterPortraitBtn);
        filterButtonGroup.add(filterSquareBtn);
        // Add components to the panel
        add(header_lb, new GridBagConstraintsExtended(
                0,0,8, 0.0,
                GridBagConstraintsExtended.CENTER,
                GridBagConstraintsExtended.NONE
        ));
        // ----------------------------------------------
        add(idSearch_lb, new GridBagConstraintsExtended(
                0,1,1, 0.0,
                GridBagConstraintsExtended.WEST,
                GridBagConstraintsExtended.NONE
        ));
        add(idSearch_tf, new GridBagConstraintsExtended(
                1,1,2, 1.0,
                GridBagConstraintsExtended.WEST,
                GridBagConstraintsExtended.HORIZONTAL
        ));
        add(idSearchIcon_lb, new GridBagConstraintsExtended(
                3,1,1, 0.0,
                GridBagConstraintsExtended.WEST,
                GridBagConstraintsExtended.NONE
        ));
        add(tagSearch_lb, new GridBagConstraintsExtended(
                4, 1, 1, 0.0,
                GridBagConstraintsExtended.WEST,
                GridBagConstraintsExtended.NONE
        ));
        add(tagSearch_tf, new GridBagConstraintsExtended(
                5, 1, 2, 1.0,
                GridBagConstraintsExtended.WEST,
                GridBagConstraintsExtended.HORIZONTAL
        ));
        add(tagSearchIcon_lb, new GridBagConstraintsExtended(
                7,1,1, 0.0,
                GridBagConstraintsExtended.WEST,
                GridBagConstraintsExtended.NONE
        ));
        initializeGroupedButtons();
        add(showSavedImagesBtn, new GridBagConstraintsExtended(
                0,3,8,1,
                GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL
        ));
        // Add Listeners
        // Document Listeners
        DocumentListener idSearch_tfDocumentListener =
                new idSearch_tfDocumentListener(idSearch_tf, tagSearch_tf);
        DocumentListener tagSearch_tfDocumentListener =
                new tagSearch_tfDocumentListener(idSearch_tf, tagSearch_tf);
        // Key Listeners
        KeyListener idSearch_tfKeyListener =
                new idSearch_tfKeyListener(idSearch_tf, headerToBodyCallback);
        KeyListener tagSearch_tfKeyListener =
                new tagSearch_tfKeyListener(tagSearch_tf, headerToBodyCallback);
        // Add Listeners to Component
        // ID Search
        idSearch_tf.getDocument().addDocumentListener(idSearch_tfDocumentListener);
        idSearch_tf.addKeyListener(idSearch_tfKeyListener);
        // Tag Search
        tagSearch_tf.getDocument().addDocumentListener(tagSearch_tfDocumentListener);
        tagSearch_tf.addKeyListener(tagSearch_tfKeyListener);
    }
    private void initializeGroupedButtons() {
        sortPanel.add(sortRecentBtn);
        sortPanel.add(sortPopularBtn);

        add(sortPanel, new GridBagConstraintsExtended(
                0,2,4, 0.0,
                GridBagConstraintsExtended.WEST,
                GridBagConstraintsExtended.HORIZONTAL
        ));
        add(filterPanel, new GridBagConstraintsExtended(
                5,2,4, 0.0,
                GridBagConstraintsExtended.EAST,
                GridBagConstraintsExtended.HORIZONTAL
        ));

    }
    private void showFilterButtons() {
        System.out.println("Show");

        filterPanel.removeAll();
        filterPanel.add(filterAllBtn);
        filterPanel.add(filterLargeBtn);
        filterPanel.add(filterHugeBtn);
        filterPanel.add(filterLandscapeBtn);
        filterPanel.add(filterPortraitBtn);
        filterPanel.add(filterSquareBtn);
        filterPanel.revalidate();
        filterPanel.repaint();
    }
    private void hideFilterButtons() {
        System.out.println("Hide");

        filterPanel.removeAll();

        String[] filterOptions = {"All", "Large", "Huge", "Landscape", "Portrait", "Square"};
        JButton filterDropdown = new JButton("Filters ｜▼");
        filterDropdown.setHorizontalTextPosition(SwingConstants.CENTER);
        filterDropdown.setVerticalTextPosition(SwingConstants.CENTER);
        filterDropdown.setPreferredSize(new Dimension(filterDropdown.getPreferredSize().width, 30));

        JPopupMenu filterPopup = new JPopupMenu();
        ButtonGroup filterButtonGroup = new ButtonGroup();

        for (String option : filterOptions) {
            JRadioButtonMenuItem filterBtn = new JRadioButtonMenuItem(option);
            filterButtonGroup.add(filterBtn);
            filterPopup.add(filterBtn);
        }

        filterDropdown.addActionListener(e -> filterPopup.show(filterDropdown, 0, filterDropdown.getHeight()));

        filterPanel.add(filterDropdown);
        filterPanel.revalidate();
        filterPanel.repaint();
    }

    private static Icon createSearchIcon() {
        // Replace this URL with the actual URL of your search icon image
        File image = new File("src/main/resources/images/search-regular-240.png");
        ImageIcon imageIcon = new ImageIcon(image.getAbsolutePath());
        return IconScaler.createScaledIcon(imageIcon, 16, 16);
    }
    // Updates by Listeners from Layout
    @Override
    public void frameDimensionQuery(JFrame frame) {
        int width = frame.getWidth();
        if (width > 899) {
            showFilterButtons();
        } else {
            hideFilterButtons();
        }
        System.out.println("Width: " + width);
    }
}