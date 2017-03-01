package com.sensei.Pixelizer2.ui.panels;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements MouseListener{
	
	private int topLeftXAxis     = 0;
	private int bottomRightXAxis = 0;
	private int topLeftYAxis     = 0;
	private int bottomRightYAxis = 0;

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		repaint();
		topLeftXAxis = e.getX();
		topLeftYAxis = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		repaint();
		bottomRightXAxis = e.getX();
		bottomRightYAxis = e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void paint(Graphics g) {
		 
		int width  = topLeftXAxis - bottomRightXAxis;
		int height = topLeftYAxis - bottomRightYAxis;
		
		g.drawRect( topLeftXAxis, topLeftYAxis, width, height);
		
	}	
}
