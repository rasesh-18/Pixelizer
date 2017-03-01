package com.sensei.Pixelizer2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	private ArrayList<Rectangle2D> drawingStack = null;
	private Point top    = null;
	private Point bottom = null;
	
	public void refresh( ArrayList<Rectangle2D> rectangles, Point top, Point bottom ) {
		this.drawingStack = rectangles;
		this.top = top;
		this.bottom = bottom;
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;

		for( Rectangle2D r2d : drawingStack ) {
			g2d.fill( r2d );
		}
		
		if( top != null && bottom != null ) {
			double width  = bottom.getX() - top.getX();
			double height = bottom.getY() - top.getY();
			
			double x = 0;
			double y = 0;
			
			if( width < 0 ) {
				x = bottom.getX();
				width *= -1;
			}
			else {
				x = top.getX();
			}
			
			if( height < 0 ) {
				y = bottom.getY();
				height *= -1;
			}
			else {
				y = top.getY();
			}
			
			g2d.fill( new Rectangle2D.Double( x, y, width, height ) );
		}
	}

}
