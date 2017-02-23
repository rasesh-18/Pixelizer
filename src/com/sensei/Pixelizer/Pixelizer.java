package com.sensei.Pixelizer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Pixelizer implements ActionListener, KeyListener{
	
	public static final String DEFAULT_INPUT_DIR  = "~/temp/scans/input/";
	public static final String DEFAULT_OUTPUT_DIR = "~/temp/scans/output/"; 
	
	public static final String[] PROMPT_VALUES = {
			"Source Directory         ",
			"Output Directory         ",
			"Left Boundary            ",
			"Right Boundary           ",
			"Top Boundary             ",
			"Bottom Boundary          ",
			"Left Page Right X Offset ",
			"Right page Left X Offset " 
	};
	
	public static final int NUM_COLS     = 2;
	public static final int NUM_ROWS     = 8;
	public static final int PADDING      = 20;
	public static final int FRAME_HEIGHT = 350;
	public static final int FRAME_WIDTH  = 450;
	
	private JFrame  mainFrame  = null;
	private JPanel  mainPanel  = null;
	private JPanel  valuePanel = null;
	private JButton process    = null;

	private Map<JLabel, JTextField> inputData = null;
	
	private void setUpUI() {
		createElements();
		assignConponentsToContainers();
		showUI();
	}
	
	private void showUI() {
		mainFrame.setSize( FRAME_WIDTH, FRAME_HEIGHT );
		mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mainFrame.setLocationRelativeTo( null );
		mainFrame.setVisible( true );
	}

	private void assignConponentsToContainers() {
		process.addActionListener( this );
		
		valuePanel.setLayout( new GridLayout( NUM_ROWS, NUM_COLS ) );
		for( JLabel label : inputData.keySet() ) {
			valuePanel.add( label );
			valuePanel.add( inputData.get( label ) );
		}
		
		mainPanel.setLayout( new BoxLayout( mainPanel, BoxLayout.Y_AXIS ) );
		mainPanel.setBorder( new EmptyBorder( PADDING, 
											  PADDING, 
											  PADDING, 
											  PADDING ) );
		mainPanel.add( valuePanel );
		mainPanel.add( process );
		
		mainFrame.setContentPane( mainPanel );
	}
	
	private void createElements() {
		mainFrame  = new JFrame( "Process-scan" );
		mainPanel  = new JPanel();
		valuePanel = new JPanel();
		process    = new JButton( "Process" );
		inputData  = new LinkedHashMap<JLabel, JTextField>();
		
		populateInputDataMap();
	}
	
	private void populateInputDataMap() {
		for( String prompt : PROMPT_VALUES ) {
			JLabel key = new JLabel( prompt );
			JTextField value = new JTextField();
			
			if( prompt.matches( PROMPT_VALUES[0] ) ) {
				value.setText( DEFAULT_INPUT_DIR );
			}
			else if( prompt.matches( PROMPT_VALUES[1] ) ) {
				value.setText( DEFAULT_OUTPUT_DIR );
			}
			else {
				value.addKeyListener( this );
			}
			inputData.put( key, value );
		}
	}
	
	public static void main( String[] args ) {
		new Pixelizer().setUpUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<String> directories = new ArrayList<String>();
		List<Integer> values     = new ArrayList<Integer>();
		
		for( JLabel label : inputData.keySet() ) {
			JTextField field = inputData.get( label );
			String data = field.getText();
			
			if( data.isEmpty() ) {
				String message = "Please enter " + label.getText();
				JOptionPane.showMessageDialog( mainFrame, message );
				break;
			}
			else {
				try {
					int value = Integer.parseInt( data );
					values.add( value );
				}
				catch( IllegalArgumentException ex ) {
					directories.add( data );
				}
			}
		}
		
		// For testing purposes
		for( String s : directories ) {
			System.out.println( s );
		}
		for( Integer i : values ) {
			System.out.println( i );
		}
		
		// TODO interface Java and Shell at this point 
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if( !Character.isDigit( e.getKeyChar() ) ) {
			e.consume();
		}		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {		
	}
}
