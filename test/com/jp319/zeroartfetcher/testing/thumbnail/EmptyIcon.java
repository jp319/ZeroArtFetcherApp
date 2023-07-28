package com.jp319.zeroartfetcher.testing.thumbnail;

import java.awt.*;
import javax.swing.*;

public class EmptyIcon implements Icon
{
	private int width;
	private int height;
	
	public EmptyIcon(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public int getIconWidth()
	{
		return width;
	}
	
	public int getIconHeight()
	{
		return height;
	}
	
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
	}
}