package com.jp319.ZeroArtFetcher.views.components;

import com.jp319.ZeroArtFetcher.utils.gui.*;
import com.jp319.ZeroArtFetcher.utils.other.FilterManager;
import com.jp319.ZeroArtFetcher.views.callbacks.ZeroArtFetcherHeaderToBodyCallback;
import com.jp319.ZeroArtFetcher.views.callbacks.ZeroArtFetcherLayoutToHeaderCallback;
import com.jp319.ZeroArtFetcher.views.listeners.*;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ZeroArtFetcherHeader
		extends PercentageMarginPanel
		implements MouseListener, ZeroArtFetcherLayoutToHeaderCallback {
	private final ZeroArtFetcherHeaderToBodyCallback headerToBodyCallback;
	private final JLabel header_lb = new JLabel("ZeroArtFetcher", SwingConstants.CENTER);
	private final JLabel idSearch_lb = new JLabel("ID Search", SwingConstants.LEFT);
	private final JTextField idSearch_tf = new JTextField();
	private final JButton idSearchBtn = new JButton();
	private final JLabel tagSearch_lb = new JLabel("Tag(s) Search", SwingConstants.LEFT);
	private final JTextField tagSearch_tf = new JTextField();
	private final JButton tagSearchBtn = new JButton();
	private final Icon searchIcon = createSearchIcon();
	JPanel sortPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
	private final ButtonGroup sortButtonGroup = new ButtonGroup();
	private final JRadioButtonExtended[] sortRadioButtonArray = new JRadioButtonExtended[3];
	private final int totalSortButtons = sortRadioButtonArray.length - 1;
	private int selectedSortButton = totalSortButtons;
	private final JCheckBox strictMode = new JCheckBox("Strict Mode");
	JPanel filterPanel = new JPanel(new WrapLayout(FlowLayout.RIGHT));
	private final ButtonGroup filterButtonGroup = new ButtonGroup();
	private final JRadioButtonExtended[] filterRadioButtonArray = new JRadioButtonExtended[7];
	private final int totalFilterButtons = filterRadioButtonArray.length - 1;
	private int selectedFilterButton = totalFilterButtons;
	private final JButton filterDropdown = new JButton("Filters ｜▼");
	private final JPopupMenu filterPopup = new JPopupMenu();
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

		idSearchBtn.setIcon(searchIcon);
		tagSearchBtn.setIcon(searchIcon);

		idSearch_tf.putClientProperty("JTextField.trailingComponent", idSearchBtn);
		idSearch_tf.putClientProperty("JComponent.roundRect", true);
		idSearch_tf.putClientProperty("JTextField.placeholderText", "3793685");
		tagSearch_tf.putClientProperty("JTextField.trailingComponent", tagSearchBtn);
		tagSearch_tf.putClientProperty("JTextField.padding", new Insets(2,2,2,2));
		tagSearch_tf.putClientProperty("JComponent.roundRect", true);
		tagSearch_tf.putClientProperty("JTextField.placeholderText", "Genshin+Impact,Klee");
		tagSearch_tf.putClientProperty("JTextField.padding", new Insets(2,2,2,2));

		// Sort Button Group
		// Is initialized in method initializeRadioButtons
		
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
				1,1,3, 1.0,
				GridBagConstraintsExtended.WEST,
				GridBagConstraintsExtended.HORIZONTAL
		));
		add(tagSearch_lb, new GridBagConstraintsExtended(
				4, 1, 1, 0.0,
				GridBagConstraintsExtended.WEST,
				GridBagConstraintsExtended.NONE
		));
		add(tagSearch_tf, new GridBagConstraintsExtended(
				5, 1, 3, 1.0,
				GridBagConstraintsExtended.WEST,
				GridBagConstraintsExtended.HORIZONTAL
		));
		initializeGroupedButtons();
		add(strictMode, new GridBagConstraintsExtended(
				0,3,8,1.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL
		));
		add(showSavedImagesBtn, new GridBagConstraintsExtended(
				0,4,8,1,
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
		// Sort and Filter Listener
		// Is initialized in method initializeRadioButtons
	}
	private void initializeGroupedButtons() {
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
		
		initializeRadioButtons();
	}
	private void initializeRadioButtons() {
		// Sort Buttons
		sortRadioButtonArray[0] = new JRadioButtonExtended("Recent","s=id&");
		sortRadioButtonArray[1] = new JRadioButtonExtended("Popular","s=fav&");
		sortRadioButtonArray[2] = new JRadioButtonExtended("","");
		
		// Filter Buttons
		filterRadioButtonArray[0] = new JRadioButtonExtended("All", "d=all&");
		filterRadioButtonArray[1] = new JRadioButtonExtended("Large", "d=large&");
		filterRadioButtonArray[2] = new JRadioButtonExtended("Huge", "d=huge&");
		filterRadioButtonArray[3] = new JRadioButtonExtended("Landscape", "d=landscape&");
		filterRadioButtonArray[4] = new JRadioButtonExtended("Portrait", "d=portrait&");
		filterRadioButtonArray[5] = new JRadioButtonExtended("Square", "d=square&");
		filterRadioButtonArray[6] = new JRadioButtonExtended("", "");
		
		// Action Listeners
		setupRadioButtons(totalFilterButtons, filterButtonGroup, filterRadioButtonArray, filterPanel);
		setupRadioButtons(totalSortButtons, sortButtonGroup, sortRadioButtonArray, sortPanel);
	}
	private void setupRadioButtons(int totalButtons, ButtonGroup buttonGroup,
								   JRadioButtonExtended[] radioButtonArray,
								   JPanel filterPanel) {
		for (int i = 0; i <= totalButtons; i++ ) {
			radioButtonArray[i].addMouseListener(this);
			buttonGroup.add(radioButtonArray[i]);
			radioButtonArray[i].addActionListener(new filter_sortButtonActionListener(
					radioButtonArray[i], headerToBodyCallback,
					tagSearch_tf
			));
			if (i < totalButtons) {
				filterPanel.add(radioButtonArray[i]);
			}
		}
	}
	
	private void showFilterButtons() {
		filterPanel.removeAll();
		
		for (int i = 0; i <= 5; i++ ) {
			filterPanel.add(filterRadioButtonArray[i]);
		}
		
		filterPanel.revalidate();
		filterPanel.repaint();
	}
	private void hideFilterButtons() {
		filterPanel.removeAll();
		
		if (filterPopup.getComponentCount() == 0) {
			filterDropdown.setVerticalTextPosition(SwingConstants.CENTER);
			filterDropdown.setHorizontalTextPosition(SwingConstants.CENTER);
			filterDropdown.setPreferredSize(new Dimension(filterDropdown.getPreferredSize().width, filterDropdown.getPreferredSize().height));
			
			for (int i = 0; i <= 5; i++ ) {
				filterPopup.add(filterRadioButtonArray[i]);
			}
			
			// Calculate the preferred width and height based on the preferred sizes of the buttons
			// Also puts padding through empty border
			filterPopup.setLayout(new WrapLayout(FlowLayout.LEFT));
			filterPopup.setBorder(BorderFactory.createEmptyBorder(
					1,1,1,1
			));
			filterPopup
					.setPopupSize(
							filterDropdown.getPreferredSize().width,
							filterRadioButtonArray[0].getPreferredSize().height*8
					);
			
			filterDropdown.addActionListener(e -> filterPopup.show(filterDropdown, 0, filterDropdown.getHeight()));
		}
		SwingUtilities.invokeLater(() -> {
			filterPanel.add(filterDropdown, BorderLayout.CENTER);
			filterPanel.revalidate();
			filterPanel.repaint();
		});
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
	}
	// Radio Button Listeners On and Off Logic
	public void mouseClicked(MouseEvent me) {
		for (int x = 0; x < totalSortButtons; x++) {
			if (me.getSource() == sortRadioButtonArray[x]) {
				if (x != selectedSortButton) {
					// Set the new sorting option and remove the previous one from filters
					if (selectedSortButton != totalSortButtons) {
						FilterManager.unsetFilter(sortRadioButtonArray[selectedSortButton].getValue());
					}
					selectedSortButton = x;
					FilterManager.setFilter(sortRadioButtonArray[selectedSortButton].getValue());
				} else {
					// Deselect the current sorting option and remove it from filters
					sortRadioButtonArray[totalSortButtons].setSelected(true);
					if (selectedSortButton != totalSortButtons) {
						FilterManager.unsetFilter(sortRadioButtonArray[selectedSortButton].getValue());
					}
					selectedSortButton = totalSortButtons;
				}
				break;
			}
		}
		for (int x = 0; x < totalFilterButtons; x++) {
			if (me.getSource() == filterRadioButtonArray[x]) {
				if (x != selectedFilterButton) {
					// Set the new sorting option and remove the previous one from filters
					if (selectedFilterButton != totalFilterButtons) {
						FilterManager.unsetFilter(filterRadioButtonArray[selectedFilterButton].getValue());
					}
					selectedFilterButton = x;
					FilterManager.setFilter(filterRadioButtonArray[selectedFilterButton].getValue());
				} else {
					// Deselect the current sorting option and remove it from filters
					filterRadioButtonArray[totalFilterButtons].setSelected(true);
					if (selectedFilterButton != totalFilterButtons) {
						FilterManager.unsetFilter(filterRadioButtonArray[selectedFilterButton].getValue());
					}
					selectedFilterButton = totalFilterButtons;
				}
				break;
			}
		}
		System.out.println("Filters: " + FilterManager.getConcatenatedFilters());
	}
	public void mousePressed(MouseEvent me) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}