package com.jp319.zeroartfetcher.testing.thumbnail;

import java.awt.Component;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ThumbnailRenderer<E> extends JLabel implements ListCellRenderer<E>
{
	public ThumbnailRenderer()
	{
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
		setHorizontalTextPosition( JLabel.CENTER );
		setVerticalTextPosition( JLabel.BOTTOM );
		setBorder( new EmptyBorder(4, 4, 4, 4) );
	}
	
	/*
	 *  Display the Thumbnail Icon and file name.
	 */
	public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected, boolean cellHasFocus)
	{
		if (isSelected)
		{
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}
		else
		{
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		
		//Set the icon and filename
		
		Thumbnail thumbnail = (Thumbnail)value;
		setIcon( thumbnail.getIcon() );
		setText( thumbnail.getFile().getName() );

//      System.out.println(thumbnail.getFileName());
		
		return this;
	}
}