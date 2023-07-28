package com.jp319.ZeroArtFetcher.utils.gui;

import javax.swing.*;

public class JRadioButtonExtended extends JRadioButton {
	private final String value;
	public JRadioButtonExtended(String text, String value) {
		super(text);
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
