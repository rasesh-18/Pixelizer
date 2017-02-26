package com.sensei.Pixelizer;

import java.io.BufferedReader ;
import java.io.InputStream ;
import java.io.InputStreamReader ;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class CommandLineExec extends Thread{

	private static final String INFO_HDR = "INFO:" ;
	
	private String[]  command  = null;
	private Pixelizer instance = null;
	
	private int currentFileNum = 0 ;
	private int totalFileNum = 0;
	
	public CommandLineExec( String[] command, Pixelizer instance ) {
		this.command = command;
		this.instance = instance;
	}
        
    private String getCommandAsString( String[] cmdParts ) {
        
        StringBuilder builder = new StringBuilder() ;
        for( String part : cmdParts ) {
            builder.append( part ).append( " " ) ;
        }
        return builder.toString().trim() ;
    }
    
    @Override
    public void run() {
    	executeCommand( this.command, this.instance );
    }

    public int executeCommand( String[] command, Pixelizer instance ) {

        int retVal = -1 ;
        try {
            System.out.println( "Executing command = " + getCommandAsString(command) ) ;
            
            Runtime rt = Runtime.getRuntime() ;
            Process pr = rt.exec( command ) ;

            InputStream       is  = pr.getInputStream() ;
            InputStreamReader isr = new InputStreamReader( is ) ;
            BufferedReader  input = new BufferedReader( isr ) ;

            String line = null ;

            while( ( line = input.readLine() ) != null ) {
                if( line.startsWith( INFO_HDR ) ) {
                	processInfoLog( line.substring( INFO_HDR.length() )
                			            .trim()
                			            .split("\\s+") ) ;
                }
                else {
                	System.out.println( line ) ;
                }
            }

            retVal = pr.waitFor() ;
            System.out.println( "Command executed with return code = " + retVal ) ;
        }
        catch( Exception e ) {
        	System.err.println( "Command execution error." + e ) ;
        }
        
        return retVal ;
    }
    
    private void processInfoLog( String[] logParts ) {
    	final String logType = logParts[0] ;
    	switch( logType ) {
    	case "TOT_FILE_CNT":
    		processTotalFileCount( Integer.parseInt( logParts[1] ) ) ;
    		break ;
    		
    	case "PROC_FILE":
    		processProcFileLog( Integer.parseInt( logParts[1] ) ) ;
    		break ;
    		
    	case "GEN_IMG":
    		processGenImgLog( logParts[1] ) ;
    		break ;
    		
    	case "DONE":
    		processDoneLog();
    	}
    }
    
    private void processDoneLog() {
    	Map<JLabel, JTextField> inputMap = instance.getInputData();
    	JLabel[] keySet = inputMap.keySet().toArray( new JLabel[8] );
    	
    	
    	for( int i=2; i<keySet.length; i++ ) {
    		inputMap.get( keySet[i] ).setText( "" );
    	}
    	
    	instance.getInfoText().setText( "" );
    	instance.getProgressBar().setValue( 0 );
    	JOptionPane.showMessageDialog( null, "Processing complete. Files stored in "
    			+ command[2] );
    }
    
    private void processTotalFileCount( int totalFiles ) {
    	
    	this.totalFileNum = totalFiles;
    	JProgressBar bar = instance.getProgressBar() ;
    	bar.setMinimum(0) ;
    	bar.setMaximum(totalFiles*4);
    	bar.setValue(0) ;
    }
    
    private void processProcFileLog( int curFileNum ) {
    	this.currentFileNum = curFileNum ;
    }
    
    private void processGenImgLog( String imgType ) {
    	instance.getProgressBar().setValue( instance.getProgressBar().getValue()+1 ) ;
    	StringBuilder info = new StringBuilder( "Processing image " + 
    											currentFileNum + 
    											"/" + 
    											totalFileNum 
    											+ ", Generating " ) ;
    	switch ( imgType ) {
		case "BIG_EVEN":
			info.append( "even OCR image" );
			break;

		case "SMALL_EVEN":
			info.append( "even web image" );
			break;
			
		case "BIG_ODD":
			info.append( "odd OCR image" );
			break;
			
		case "SMALL_ODD":
			info.append( "odd web image" );
			break;
			
		default:
			break;
		}
    	
    	instance.getInfoText().setText( info.toString() );
    }
}