package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bo.PopulationRecord;

import db.PopulationTableModel;

/**
 * Class used visual interface for editing the
 * NST_EST2011_ALLDATA.csv file.
 *
 * @author Brady Houseknecht
 *
 */
public class FrmMain extends JPanel {
	
	 public static final String[] m_col_columnNames = { "SUMLEV", "REGION", "DIVISION", "STATE", "NAME", "CENSUS2010POP", "ESTIMATESBASE2010", "POPESTIMATE2010", "POPESTIMATE2011", "NPOPCHG_2010", "NPOPCHG_2011", "BIRTHS2010", "BIRTHS2011", "DEATHS2010", "DEATHS2011", "NATURALINC2010", "NATURALINC2011", "INTERNATIONALMIG2010", "INTERNATIONALMIG2011", "DOMESTICMIG2010", "DOMESTICMIG2011", "NETMIG2010", "NETMIG2011", "RESIDUAL2010", "RESIDUAL2011", "RBIRTH2011", "RDEATH2011", "RNATURALINC2011", "RINTERNATIONALMIG2011", "RDOMESTICMIG2011", "RNETMIG2011", "HIDDEN"};

     protected JTable m_tbl_data;
     protected JScrollPane m_ctrl_scroller;
     protected PopulationTableModel m_mdl_popTable;
     protected int m_int_currentRow;
     
     
     /**
      * Class Constructor.
      * 
      * @param args string array equal command line parameters passed at startup
      * @param serialData ArrayList containing the deserialized data that is to be displayed
      */
     public FrmMain(String[] args, ArrayList<PopulationRecord> serialData) {
    	 super(new GridLayout(1,0));
    	 initComponent(args, serialData);
     } // end:constructor

     /**
      * Control function used to construct and configure the form.
      * 
      * @param args string array equal command line parameters passed at startup
      * @param serialData ArrayList containing the deserialized data that is to be displayed
      */
     public void initComponent(String[] args, ArrayList<PopulationRecord> serialData) {
         
    	 this.m_mdl_popTable = new PopulationTableModel(args, this.m_col_columnNames,serialData);
         this.m_mdl_popTable.addTableModelListener(new FrmMain.PopulateTableModelListener());
         this.m_tbl_data = new JTable();
         this.m_tbl_data.setAutoCreateRowSorter(true);
         this.m_tbl_data.setOpaque(true);
         this.m_tbl_data.setModel(this.m_mdl_popTable);
         this.m_tbl_data.setSurrendersFocusOnKeystroke(true);
         if (!this.m_mdl_popTable.hasEmptyRow()) {
             this.m_mdl_popTable.addEmptyRow();
         } // end:if

         this.m_ctrl_scroller = new javax.swing.JScrollPane(this.m_tbl_data);
         //this.m_tbl_data.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
         TableColumn hidden = 
        		 this.m_tbl_data.getColumnModel().getColumn(PopulationTableModel.HIDDEN_INDEX);
         hidden.setMinWidth(2);
         hidden.setPreferredWidth(2);
         hidden.setMaxWidth(2);
         hidden.setCellRenderer(new RenderingManager(PopulationTableModel.HIDDEN_INDEX));

         setLayout(new BorderLayout());
         add(this.m_ctrl_scroller, BorderLayout.CENTER);
         
         /// Set the current row index to -1
         this.m_int_currentRow = -1;
         
         /// Finally attach the "CRUD" Manager to the form.
         KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
         manager.addKeyEventDispatcher(new CrudManager());
         this.setFocusable(true);
         
     } // end:initComponent
     
     /**
      * Control function used to highlight the insert row.
      * @param row
      */
     public void highlightLastRow(int row) {
         int lastrow = this.m_mdl_popTable.getRowCount();
         if (row == lastrow - 1) {
        	 this.m_tbl_data.setRowSelectionInterval(lastrow - 1, lastrow - 1);
         } // end:if 
         else {
        	 this.m_tbl_data.setRowSelectionInterval(row + 1, row + 1);
         } // end:else

         this.m_tbl_data.setColumnSelectionInterval(0, 0);
     } // end:highlightLastRow
     
     /**
      * Sub class used to manage the rendering behaivor
      * of the grid.
      * 
      * @author Brady Houseknecht
      */
     public class RenderingManager extends DefaultTableCellRenderer {
         
    	 protected int m_int_col;

         public RenderingManager(int col) {
             this.m_int_col = col;
         } // end:RenderingRenderer

         public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column)
         {
             Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
             if (column == m_int_col && hasFocus) {
                 if ((FrmMain.this.m_mdl_popTable.getRowCount() - 1) == row &&
                    !FrmMain.this.m_mdl_popTable.hasEmptyRow())
                 {
                     FrmMain.this.m_mdl_popTable.addEmptyRow();
                 } // end:if
                 highlightLastRow(row);
             } // end:if
             return c;
         } // end:getTableCellRendererComponent
     } // end:class
     
     /**
      * Sub class used to monitor the state of the 
      * data exposed by the screen as well as the
      * user location in the screen. 
	  *
      * @author Brady Houseknecht
      */
     public class PopulateTableModelListener implements TableModelListener {
         public void tableChanged(TableModelEvent evt) {
             if (evt.getType() == TableModelEvent.UPDATE) {
                 int column = evt.getColumn();
                 int row = evt.getFirstRow();
                 m_int_currentRow = row;
                 System.out.println("row: " + row + " column: " + column);
                 m_tbl_data.setColumnSelectionInterval(column + 1, column + 1);
                 m_tbl_data.setRowSelectionInterval(row, row);
             } // end:if
             if (evt.getType() == TableModelEvent.DELETE){
                 System.out.println("Row deleted!");
                 
             } // end:if
            
         } // end:tableChanged
     } // end:class
     
     /**
      * Control function used to delete the
      * currently selected rows or rows.
      */
     public void DeleteRow()
     {
    	int row = this.m_tbl_data.getSelectedRow();
    	this.m_mdl_popTable.remove(row);
    	
     } // end:DeleteRow
     
     /**
      * Control function used to update the
      * the data serialized to the population-record.ser
      * file.
      */
     public void SaveToFile()
     {
    	 this.m_mdl_popTable.serializeData();
    	 
     } // end:saveToFile
    
     /**
      * Control function used to "twist" the
      * current row into a vertical format
      * for easy editing.
      */
     public void twistRow()
     {
    	JOptionPane.showMessageDialog(null,"Twist!  Sorry ran out of time. This functionality will be implemented in a later iteration. Possibly once this application has been migrated to C# :).");
    	
     } // end:ZoomRow
    
    /**
     * Private class used to capture and process
     * key events.
	 *
     * @author Brady Houseknecht
     */
	private class CrudManager implements KeyEventDispatcher {
	        @Override
	        public boolean dispatchKeyEvent(KeyEvent e) {
	        	int keyCode = e.getKeyCode();
    			
	        	if (e.isControlDown())
	    		{
	    			/// CTRL+U Trigger Update
	    			if ( keyCode == KeyEvent.VK_U)
	    				SaveToFile();
	    			/// CTRL+C Trigger Create
	    			if ( keyCode == KeyEvent.VK_C)
	    				m_mdl_popTable.addEmptyRow();
	    			/// Shift+Z Trigger "Twist Row"
	    			if ( keyCode == KeyEvent.VK_T)
	    				twistRow();
	    		} // end:if
	    		
	    		if ( keyCode == KeyEvent.VK_DELETE)
    				DeleteRow();
	    		if ( keyCode == KeyEvent.VK_INSERT)
	    			m_mdl_popTable.addEmptyRow();
	    		
	    	    return false;
	        } // end:dispatchKeyEvent
	    } // end:class
	
} // end:class
