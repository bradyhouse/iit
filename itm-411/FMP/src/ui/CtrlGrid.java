package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Class used to virtualize (or visualize)
 * the contents of a data table.  
 * 
 * @see http://docs.oracle.com/javase/tutorial/displayCode.html?code=
 * http://docs.oracle.com/javase/tutorial/uiswing/examples/
 * components/SimpleTableDemoProject/src/components/SimpleTableDemo.java

 * @author Brady Houseknecht
 *
 */
public class CtrlGrid extends JPanel {

	private boolean DEBUG = false; 
	 
	public CtrlGrid(Object[][] dataRows, String[] columnNames) {
		super(new GridLayout(1,0)); 
		final JTable table = new JTable(dataRows, columnNames);         
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));         
		table.setFillsViewportHeight(true);           
		if (DEBUG) {             
			table.addMouseListener(new MouseAdapter() 
			{                 
				public void mouseClicked(MouseEvent e) 
				{                     
					printDebugData(table);                 
				} // end:mouseClicked
			}); // end:addMouseListener
		} // end:if 
		
		//Create the scroll pane and add the table to it.         
		JScrollPane scrollPane = new JScrollPane(table);           
		//Add the scroll pane to this panel.         
		this.add(scrollPane); 
	} // end:constructor

	public CtrlGrid(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public CtrlGrid(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public CtrlGrid(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Control function used to print table
	 * attributes when the control's
	 * internal DEBUG flag is true.
	 * 
	 * @param table
	 */
    private void printDebugData(JTable table) 
    {         
    	int numRows = table.getRowCount();         
    	int numCols = table.getColumnCount();         
    	javax.swing.table.TableModel model = table.getModel();           
    	System.out.println("Value of data: ");         
    	for (int i=0; i < numRows; i++) 
    	{             
    		System.out.print("    row " + i + ":");             
    		for (int j=0; j < numCols; j++) 
    		{                 
    			System.out.print("  " + model.getValueAt(i, j));             
    		}             
    		System.out.println();         
    	}         
    	System.out.println("--------------------------");     
    } // end:printDebugData 
} // end:class
