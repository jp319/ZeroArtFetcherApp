package com.jp319.zeroartfetcher.testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class JRadioButtonOnOff extends JFrame implements MouseListener
{
	JRadioButton[] rb = new JRadioButton[5];
	final int RB_MAX = rb.length - 1;
	int rbSelected = RB_MAX;
	public JRadioButtonOnOff()
	{
		super("JRadioButtonOnOff");
		setSize(300,65);
		setLocation(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container frame = getContentPane();
		frame.setLayout(new FlowLayout());
		ButtonGroup rbGroup = new ButtonGroup();
		for(int x = 0; x <= RB_MAX; x++)
		{
			System.out.println(x);
			rb[x] = new JRadioButton(""+x);
			rb[x].addMouseListener(this);
			rbGroup.add(rb[x]);
			if(x < RB_MAX) frame.add(rb[x]);
		}
		System.out.println("Max: "+RB_MAX+" Selected: "+ rbSelected);
		rb[rbSelected].setSelected(true);
	}
	public void mouseClicked(MouseEvent me)
	{
		for(int x = 0; x < RB_MAX; x++)
		{
			System.out.println("Max: "+RB_MAX+" Selected: "+ rbSelected);
			if(me.getSource() == rb[x])
			{
				if(x != rbSelected) rbSelected = x;
				else
				{
					rb[RB_MAX].setSelected(true);
					rbSelected = RB_MAX;
				}
				break;
			}
		}
	}
	
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	
	public static void main(String[] args){new JRadioButtonOnOff().setVisible(true);}
}