package com.jp319.zeroartfetcher.testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerWithHintExample {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Spinner with Hint Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 150);
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		JSpinner spinner = new JSpinner(spinnerModel);
		
		// Customize the spinner editor to use JFormattedTextField
		JFormattedTextField editor = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
		editor.setForeground(Color.GRAY);
		editor.setText("Entries"); // Set the initial hint text
		
		// Add a focus listener to show/hide the hint text
		editor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (editor.getText().equals("Entries")) {
					editor.setText("");
					editor.setForeground(Color.BLACK);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (editor.getText().isEmpty()) {
					editor.setForeground(Color.GRAY);
					editor.setText("Entries");
				}
			}
		});
		
		// Add a change listener to handle spinner value changes
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// Do something when the spinner value changes
				System.out.println("Value changed: " + spinner.getValue());
			}
		});
		
		frame.add(spinner, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
