package phonescanner;


import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableCellEditor;

public class Ps implements ActionListener{

	JButton button;
	
	JTextField textfield3 = new JTextField(3);
	String lm  = " ";
	int lm1 = 0;
	JTextField textfield4 = new JTextField(3);
    String rm = " ";
    int rm1 = 0;
    JTextField textfield5 = new JTextField(3);
	String tm = " ";
	int tm1 = 0;
	JTextField textfield6 = new JTextField(3);
	String bm = " ";
    int bm1 = 0;
    JTextField textfield7 = new JTextField(3);
    String lprxo = " ";
    int lprxo1 = 0;
    JTextField textfield8 = new JTextField(3);
    String rplxo = " ";
    int rplxo1 = 0;
    JTextField textfield9 = new JTextField(4);
    String spn = " ";
    int spn1 = 0;
    String id = " ";
    JTextField textfield1 = new JTextField(20);
    String od = " ";
    JTextField textfield2 = new JTextField(20);
    
    //boolean flag = false;
    int counter = 0;
    
    public Object process;
		
	public static void main(String[] argv) throws Exception{
		Ps gui = new Ps();
		gui.process();
	}
		
	public void process(){
		JFrame processScan = new JFrame("Process-Scan");
		JPanel panel = new JPanel();
		processScan.getContentPane().add(panel);
		button = new JButton("PROCESS");
		button.addActionListener(this);
		processScan.getContentPane().add(button);
		processScan.getContentPane().add(BorderLayout.SOUTH, button);
		TableModel model = new TableModel();
		JTable table = new JTable(model);
		processScan.add(new JScrollPane(table));
		processScan.getContentPane().add(table);
		processScan.getContentPane().add(BorderLayout.CENTER, table);
		processScan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		processScan.setSize(1000,750);
		processScan.setVisible(true);
     	model.addColumn("");
		model.addColumn("");
		
		model.addRow(new Object[] { " Input Directory " , id });
        
		model.addRow(new Object[] { "Output Directory ", od });
        
        
        model.addRow(new Object[] { " Left Margin ", lm });
        
        
    	
	    model.addRow(new Object[] { " Right Margin ", rm });
        
        
        model.addRow(new Object[] { " Top Margin ", tm });
        
        
        model.addRow(new Object[] { " Bottom Margin ", bm });
        
        
        model.addRow(new Object[] { " Left Page Right 'x' Offset ", lprxo });
   
        
        model.addRow(new Object[] { " Right Page Left 'x' Offset ", rplxo });
        
        
        
        model.addRow(new Object[] { " Starting Page # ", spn });
        
    	}		
	public void actionPerformed(ActionEvent event){
		
		textfield1.addActionListener(this);
        try{
        	id = textfield1.getText().trim();
        	if (id==" ")
        		JOptionPane.showMessageDialog(null,"Please enter the input directory");
        	else
        		counter++;
        }
        catch(Exception e){
        	JOptionPane.showMessageDialog(null,"Please enter the input directory");  
        }
        
        textfield2.addActionListener(this);
        try{
        	od = textfield2.getText().trim();
        	if (od==" ")
        		JOptionPane.showMessageDialog(null,"Please enter the output directory");
        	else
        		counter++;
        }
        catch(Exception e){
        	JOptionPane.showMessageDialog(null,"Please enter the output directory");  
        }
		
        textfield3.addActionListener(this);
        try{
        	lm = textfield3.getText().trim();
        	if(lm==" ")
        		JOptionPane.showMessageDialog(null,"Please enter the left margin value");
        	else{
        		try
            { 
            	lm1 = Integer.valueOf(lm);
            	counter++;
            }
            catch (NumberFormatException nfe)
            {
            	JOptionPane.showMessageDialog(null,"Please enter an integer for left margin");
            }
        }}
        catch(Exception e){
        	JOptionPane.showMessageDialog(null,"Please enter the left margin value");  
        }
        
        textfield4.addActionListener(this);
	    try{
        	rm = textfield4.getText().trim();
        	if(rm==" ")
        		JOptionPane.showMessageDialog(null,"Please enter the right margin value");
        	else{
        		try
            { 
            	rm1 = Integer.valueOf(rm);
            	counter++;
            }
            catch (NumberFormatException nfe)
            {
            	JOptionPane.showMessageDialog(null,"Please enter an integer for right margin");
            }
        }}
        catch(Exception e){
        	JOptionPane.showMessageDialog(null,"Please enter the right margin value");  
        }
	    
	    textfield5.addActionListener(this);
	    try{
	    	tm = textfield5.getText().trim();
        	if(tm==" ")
        		JOptionPane.showMessageDialog(null,"Please enter the top margin value");
        	else{
        		try
            { 
            	tm1 = Integer.valueOf(tm);
            	counter++;
            }
            catch (NumberFormatException nfe)
            {
            	JOptionPane.showMessageDialog(null,"Please enter an integer for top margin");
            }
        }}
        catch(Exception e){
        	JOptionPane.showMessageDialog(null,"Please enter the top margin value");  
        }
	    
	    textfield6.addActionListener(this);
	    try{
	    	bm = textfield6.getText().trim();
        	if(bm==" ")
        		JOptionPane.showMessageDialog(null,"Please enter the bottom margin value");
        	else{
        		try
            { 
            	bm1 = Integer.valueOf(bm);
            	counter++;
            }
            catch (NumberFormatException nfe)
            {
            	JOptionPane.showMessageDialog(null,"Please enter an integer for bottom margin");
            }
        }}
        catch(Exception e){
        	JOptionPane.showMessageDialog(null,"Please enter the bottom margin value");  
        }
	    
	    textfield7.addActionListener(this);
        try{
        	lprxo = textfield7.getText().trim();
        	if(lprxo==" ")
        		JOptionPane.showMessageDialog(null,"Please enter the Left Page Right 'x' Offset");
        	else{
        		try
            { 
            	lprxo1 = Integer.valueOf(lprxo);
            	counter++;
            }
            catch (NumberFormatException nfe)
            {
            	JOptionPane.showMessageDialog(null,"Please enter an integer for lprxo");
            }
        }}
        catch(Exception e){
        	JOptionPane.showMessageDialog(null,"Please enter the Left Page Right 'x' Offset");  
        }
        
        textfield8.addActionListener(this);
        rplxo = textfield8.getText();
        try{
        	rplxo = textfield8.getText().trim();
        	if(rplxo==" ")
        		JOptionPane.showMessageDialog(null,"Please enter the Right Page Left 'x' Offset");
        	else{
        		try
            { 
            	rplxo1 = Integer.valueOf(rplxo);
            	counter ++;
            }
            catch (NumberFormatException nfe)
            {
            	JOptionPane.showMessageDialog(null,"Please enter an integer for rplxo ");
            }
        }}
        catch(Exception e){
        	JOptionPane.showMessageDialog(null,"Please enter the Right Page Left 'x' Offset");  
        }
        
        
	        textfield9.addActionListener(this);
	        try{
	        	spn = textfield9.getText().trim();
	        	if(spn==" ")     		
	        		spn1 = 1;
	            else{
	        		try
	            { 
	            	spn1 = Integer.valueOf(spn);
	            }
	            catch (NumberFormatException nfe)
	            {
	            	JOptionPane.showMessageDialog(null,"Please enter an integer for starting page ");
	            }
	        	}
	        	counter++;
	        }
	        catch(Exception e){
	        	JOptionPane.showMessageDialog(null,"Please enter the Starting Page #");  
	        }
	        if (counter == 9){
	        button.setText("PROCESSING YOUR IMAGES....");
	        String[] copyS = {id,od};
	        int[] copyi = {lm1,rm1,tm1,bm1,lprxo1,rplxo1,spn1};
	        }
        }
	 
}
    class TableModel extends DefaultTableModel{
    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	public TableModel() {
    	super();
    }
    public boolean isCellEditable(int row, int col) {
    	if (col==0)
    		return false;
       	else 
       		return true;
      }
      
    }