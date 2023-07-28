package com.jp319.zeroartfetcher.testing.thumbnail;

import java.io.*;
import java.util.concurrent.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ThumbnailApp
{
	private DefaultListModel<Thumbnail> model = new DefaultListModel<Thumbnail>();
	private JList<Thumbnail> list = new JList<Thumbnail>(model);
	private Set<File> filesToBeLoaded = new HashSet<>();
	private ExecutorService service;
	
	public ThumbnailApp()
	{
		int processors = Runtime.getRuntime().availableProcessors();
		service = Executors.newFixedThreadPool( processors - 2 );
	}
	
	public JPanel createContentPane()
	{
		JPanel cp = new JPanel( new BorderLayout() );
		
		list.setCellRenderer( new ThumbnailRenderer<Thumbnail>() );
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		Icon empty = new EmptyIcon(160, 160);
		Thumbnail prototype = new Thumbnail(new File("01.png"), empty);
		list.setPrototypeCellValue( prototype );
		
		JScrollPane scrollPane = new JScrollPane( list );
		cp.add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.getViewport().addChangeListener((e) ->
		{
			int first = list.getFirstVisibleIndex();
			int last = list.getLastVisibleIndex();
			System.out.println(first + " : " + last);
			
			if (first == -1) return;
			
			for (int i = first; i <= last; i++)
			{
				Thumbnail thumbnail = model.elementAt(i);
				File file = thumbnail.getFile();
				
				if (filesToBeLoaded.contains(file))
				{
					filesToBeLoaded.remove(file);
					service.submit( new ThumbnailWorker(thumbnail.getFile(), model, i) );
				}
			}
			
			if (filesToBeLoaded.isEmpty())
				service.shutdown();
		});
		
		return cp;
	}
	
	public void loadImages(File directory)
	{
		new Thread( () -> createThumbnails(directory) ).start();
	}
	
	private void createThumbnails(File directory)
	{
		try
		{
			File[] files = directory.listFiles((d, f) -> {return f.endsWith(".png");});
			
			for (File file: files)
			{
				filesToBeLoaded.add( file );
				Thumbnail thumbnail = new Thumbnail(file, null);
				model.addElement( thumbnail );
			}
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	
	private static void createAndShowGUI()
	{
		ThumbnailApp app = new ThumbnailApp();
		
		JFrame frame = new JFrame("ListDrop");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane( app.createContentPane() );
		frame.setSize(600, 600);
		frame.setVisible(true);

//      File directory = new File("C:/Users/netro/Pictures/TravelSun/2019_01_Cuba");
		File directory = new File("D:\\Downloads\\HakuNeko Mangas\\Useless Ponko\\Vol.01 Ch.0001 - Ponko The Maid (en) [Cyan Steam]");
		app.loadImages( directory );
	}
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
	}
}