package com.sensei.Pixelizer2;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pixelizer2 implements MouseListener, MouseMotionListener {
	
	public static final int    FRAME_WIDTH  = 400;
	public static final int    FRAME_HEIGHT = 400;
	public static final String FRAME_TITLE  = "Process-scan";
	
	private ArrayList<Rectangle2D> stack = null;
	
	private Point topLeftVertex = null;
	private Point bottomRightVertex = null;
	
	private JFrame mainFrame = null;
	private MainPanel mainPanel = null;
	
	public Pixelizer2() {
		createComponents();
	}
	
	public void start() {
		setUpUI();
	}

	private void setUpUI() {
		setUpMainPanel();
		setUpMainFrame();
		addPanelToFrame();
		showFrame();
	}
	
	private void showFrame() {
		mainFrame.setVisible( true );
	}

	private void addPanelToFrame() {
		mainFrame.setContentPane( mainPanel );
	}

	private void setUpMainPanel() {
		mainPanel.addMouseListener( this );
		mainPanel.addMouseMotionListener( this );
	}

	private void setUpMainFrame() {
		mainFrame.setSize( FRAME_WIDTH, FRAME_HEIGHT );
		mainFrame.setTitle( FRAME_TITLE );
		mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mainFrame.setLocationRelativeTo( null );
	}

	private void createComponents() {
		topLeftVertex = new Point( 0, 0 );
		bottomRightVertex = new Point( 0, 0 );
		
		mainFrame = new JFrame();
		mainPanel = new MainPanel();
		
		stack = new ArrayList<>();
	}

	public static void main( String[] args ) {
		new Pixelizer2().start();
	}
	
	private void addRect( Point topLeft, Point bottomRight ) {
		double width  = bottomRight.getX() - topLeft.getX();
		double height = bottomRight.getY() - topLeft.getY();
		
		double x = 0;
		double y = 0;
		
		if( width < 0 ) {
			x = bottomRight.getX();
			width *= -1;
		}
		else {
			x = topLeft.getX();
		}
		
		if( height < 0 ) {
			y = bottomRight.getY();
			height *= -1;
		}
		else {
			y = topLeft.getY();
		}
		
		stack.add( new Rectangle2D.Double( x, y, width, height ) );
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		bottomRightVertex = e.getPoint();
		mainPanel.refresh( stack, topLeftVertex, bottomRightVertex );
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		topLeftVertex = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		bottomRightVertex = e.getPoint();
		addRect( topLeftVertex, bottomRightVertex );
		mainPanel.refresh( stack, null, null );
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	
}
