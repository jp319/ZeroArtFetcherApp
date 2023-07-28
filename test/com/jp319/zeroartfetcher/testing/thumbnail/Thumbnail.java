package com.jp319.zeroartfetcher.testing.thumbnail;

import java.io.File;
import javax.swing.Icon;

public class Thumbnail
{
	private File file;
	private Icon icon;
	
	public Thumbnail(File file, Icon icon)
	{
		this.file = file;
		this.icon = icon;
	}
	
	public Icon getIcon()
	{
		return icon;
	}
	
	public void setIcon(Icon icon)
	{
		this.icon = icon;
	}
	
	public File getFile()
	{
//      return file.getName();
		return file;
	}
}