package com.sensei.Pixelizer2.ui;

import javax.swing.JFrame;

import com.sensei.Pixelizer2.ui.panels.MainPanel;

public class MainFrame extends JFrame {
	
	private static final String TITLE_STRING = "Hello";
	private static final int    FRAME_WIDTH  = 400;
	private static final int    FRAME_HEIGHT = 400;
	
	private MainPanel mainPanel = null;
	
	public MainFrame() {
		mainPanel = new MainPanel();
	}
	
	public void setUpUI() {
		addComponentsToFrame();
		initializeAndShowFrame();
	}

	private void initializeAndShowFrame() {
		this.setTitle( TITLE_STRING );
		this.setSize( FRAME_WIDTH, FRAME_HEIGHT );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLocationRelativeTo( null );
		this.setVisible( true );
	}

	private void addComponentsToFrame() {
		this.setContentPane( mainPanel );
	}	
	
	
}
