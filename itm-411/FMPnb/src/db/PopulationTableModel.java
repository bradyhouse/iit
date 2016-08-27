/**
 * 
 */
package db;

import io.PersistentObjectWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import bo.PersistentObject;
import bo.PopulationRecord;

/**
 * This class used virtualize values stored
 * in the PopulationTable Class. NOTE - This class
 * is necessary in order to make updates (aka CRUD)
 * to the PopulationTable class.
 * 
 * @author Brady Houseknecht
 *
 */
public class PopulationTableModel extends AbstractTableModel {

	 public static final int SUMLEV_INDEX = 0;
	 public static final int REGION_INDEX = 1;
	 public static final int DIVISION_INDEX = 2;
	 public static final int STATE_INDEX = 3;
	 public static final int NAME_INDEX = 4;
	 public static final int CENSUS2010POP_INDEX = 5;
	 public static final int ESTIMATESBASE2010_INDEX = 6;
	 public static final int POPESTIMATE2010_INDEX = 7;
	 public static final int POPESTIMATE2011_INDEX = 8;
	 public static final int NPOPCHG_2010_INDEX = 9;
	 public static final int NPOPCHG_2011_INDEX = 10;
	 public static final int BIRTHS2010_INDEX = 11;
	 public static final int BIRTHS2011_INDEX = 12;
	 public static final int DEATHS2010_INDEX = 13;
	 public static final int DEATHS2011_INDEX = 14;
	 public static final int NATURALINC2010_INDEX = 15;
	 public static final int NATURALINC2011_INDEX = 16;
	 public static final int INTERNATIONALMIG2010_INDEX = 17;
	 public static final int INTERNATIONALMIG2011_INDEX = 18;
	 public static final int DOMESTICMIG2010_INDEX = 19;
	 public static final int DOMESTICMIG2011_INDEX = 20;
	 public static final int NETMIG2010_INDEX = 21;
	 public static final int NETMIG2011_INDEX = 22;
	 public static final int RESIDUAL2010_INDEX = 23;
	 public static final int RESIDUAL2011_INDEX = 24;
	 public static final int RBIRTH2011_INDEX = 25;
	 public static final int RDEATH2011_INDEX = 26;
	 public static final int RNATURALINC2011_INDEX = 27;
	 public static final int RINTERNATIONALMIG2011_INDEX = 28;
	 public static final int RDOMESTICMIG2011_INDEX = 29;
	 public static final int RNETMIG2011_INDEX = 30;
	 public static final int HIDDEN_INDEX = 31;

     protected String[] m_col_columnNames;
     protected Vector m_vect_data;
     protected String[] m_col_args;

     /**
      * Class constructor.
      * 
      * @param args string array equal command line parameters passed at startup
      * @param columnNames string array defining the header caption that will be used in the grid.
      * @param serialData ArrayList containing the deserialized data that is to be displayed
      */
     public PopulationTableModel(String[] args, String[] columnNames, ArrayList<PopulationRecord> serialRows ) {
         this.m_col_columnNames = columnNames;
         this.m_col_args = args;
         PopulationTable table = new PopulationTable(serialRows);
         table.insert(args[0], args[1], args[2], args[3]);
         table.DataBind();
         this.m_vect_data = table.getRows();
         
     } // end:constructor
     /**
      * Value function that can be used to 
      * determine the index given the name (or caption)
      * of a column.
      */
     public String getColumnName(int column) {
         return this.m_col_columnNames[column];
     } // end:getColumnName

     /**
      * Value function that can be used to determine
      * edit state of a cell based on its row 
      * and column coordinates.
      */
     public boolean isCellEditable(int row, int column) {
         if (column == HIDDEN_INDEX) return false;
         else return true;
     } // end:isCellEditable
     
     /**
      * Value function that can be used to get the
      * type of data contained in a column given its
      * index.
      */
     public Class getColumnClass(int column) {
         switch (column) {
         	 case NAME_INDEX:
         	 case STATE_INDEX:
	         case SUMLEV_INDEX:
	         case REGION_INDEX:
	         case DIVISION_INDEX:
	        	 return String.class;
	      	 case CENSUS2010POP_INDEX:
	         case ESTIMATESBASE2010_INDEX:
	         case POPESTIMATE2010_INDEX:
	         case POPESTIMATE2011_INDEX:
	         case NPOPCHG_2010_INDEX:
	         case NPOPCHG_2011_INDEX:
	         case BIRTHS2010_INDEX:
	         case BIRTHS2011_INDEX:
	         case DEATHS2010_INDEX:
	         case DEATHS2011_INDEX:
	         case NATURALINC2010_INDEX:
	         case NATURALINC2011_INDEX:
	         case INTERNATIONALMIG2010_INDEX:
	         case INTERNATIONALMIG2011_INDEX:
	         case DOMESTICMIG2010_INDEX:
	         case DOMESTICMIG2011_INDEX:
	         case NETMIG2010_INDEX:
	         case NETMIG2011_INDEX:
	         case RESIDUAL2010_INDEX:
	         case RESIDUAL2011_INDEX:
	        	 return Long.class;
	         case RBIRTH2011_INDEX:
	         case RDEATH2011_INDEX:
	         case RNATURALINC2011_INDEX:
	         case RINTERNATIONALMIG2011_INDEX:
	         case RDOMESTICMIG2011_INDEX:
	         case RNETMIG2011_INDEX:
	        	 return Double.class;
	       	 default:
	       		 return Object.class;
         } // end:switch
     } // end:getColumnClass

     public Object getValueAt(int row, int column) {
         PopulationRecord record = (PopulationRecord)this.m_vect_data.get(row);
         switch (column) {
	         case STATE_INDEX:
	        	  return record.getState();
	         case SUMLEV_INDEX:
	        	  return record.getSumlev();
	         case REGION_INDEX:
	        	  return record.getRegion();
	         case DIVISION_INDEX:
	        	  return record.getDivision();
	         case NAME_INDEX:
	        	  return record.getName();
	         case CENSUS2010POP_INDEX:
	        	  return record.getCensus2010pop();
	         case ESTIMATESBASE2010_INDEX:
	        	  return record.getEstimatesbase2010();
	         case POPESTIMATE2010_INDEX:
	        	  return record.getPopestimate2010();
	         case POPESTIMATE2011_INDEX:
	        	  return record.getPopestimate2011();
	         case NPOPCHG_2010_INDEX:
	        	  return record.getNPopChg2010();
	         case NPOPCHG_2011_INDEX:
	        	  return record.getNPopChg2011();
	         case BIRTHS2010_INDEX:
	        	  return record.getBirths2010();
	         case BIRTHS2011_INDEX:
	        	  return record.getBirths2011();
	         case DEATHS2010_INDEX:
	        	  return record.getDeaths2010();
	         case DEATHS2011_INDEX:
	        	  return record.getDeaths2011();
	         case NATURALINC2010_INDEX:
	        	  return record.getNaturalinc2010();
	         case NATURALINC2011_INDEX:
	        	  return record.getNaturalinc2011();
	         case INTERNATIONALMIG2010_INDEX:
	        	  return record.getInternationalmig2010();
	         case INTERNATIONALMIG2011_INDEX:
	        	  return record.getInternationalmig2011();
	         case DOMESTICMIG2010_INDEX:
	        	  return record.getDomesticmig2010();
	         case DOMESTICMIG2011_INDEX:
	        	  return record.getDomesticmig2011();
	         case NETMIG2010_INDEX:
	        	  return record.getNetmig2010();
	         case NETMIG2011_INDEX:
	        	  return record.getNetmig2011();
	         case RESIDUAL2010_INDEX:
	        	  return record.getResidual2010();
	         case RESIDUAL2011_INDEX:
	        	  return record.getResidual2011();
	         case RBIRTH2011_INDEX:
	        	  return record.getRbirth2011();
	         case RDEATH2011_INDEX:
	        	  return record.getRdeath2011();
	         case RNATURALINC2011_INDEX:
	        	  return record.getRnaturalinc2011();
	         case RINTERNATIONALMIG2011_INDEX:
	        	  return record.getRinternationalmig2011();
	         case RDOMESTICMIG2011_INDEX:
	        	  return record.getRdomesticmig2011();
	         case RNETMIG2011_INDEX:
	        	  return record.getRnetmig2011();
             default:
            	  return new Object();
         } // end:switch
     } // end:getValueAt
     
     /**
      * Control function used to delete a single PopulateRecord from
      * the current vector.
      * 
      * @param row int value equal to the row id that should be deleted.
      */
     public void remove(int row) {
         this.m_vect_data.remove(row);
         this.fireTableRowsDeleted(row, row);
     } // end:remove


     /**
      * Control function used to update a cell of the grid.
      */
     public void setValueAt(Object value, int row, int column) {
         PopulationRecord record = (PopulationRecord)m_vect_data.get(row);
         switch (column) {
	         case STATE_INDEX:
	      	   record.setState((String)value); break;
	       case SUMLEV_INDEX:
	      	   record.setSumlev((String)value); break;
	       case REGION_INDEX:
	      	   record.setRegion((String)value); break;
	       case DIVISION_INDEX:
	      	   record.setDivision((String)value); break;
	       case NAME_INDEX:
	      	   record.setName((String)value); break;
	       case CENSUS2010POP_INDEX:
	      	   record.setCensus2010pop((Long)value); break;
	       case ESTIMATESBASE2010_INDEX:
	      	   record.setEstimatesbase2010((Long)value); break;
	       case POPESTIMATE2010_INDEX:
	      	   record.setPopestimate2010((Long)value); break;
	       case POPESTIMATE2011_INDEX:
	      	   record.setPopestimate2011((Long)value); break;
	       case NPOPCHG_2010_INDEX:
	      	   record.setNPopChg2010((Long)value); break;
	       case NPOPCHG_2011_INDEX:
	      	   record.setNPopChg2011((Long)value); break;
	       case BIRTHS2010_INDEX:
	      	   record.setBirths2010((Long)value); break;
	       case BIRTHS2011_INDEX:
	      	   record.setBirths2011((Long)value); break;
	       case DEATHS2010_INDEX:
	      	   record.setDeaths2010((Long)value); break;
	       case DEATHS2011_INDEX:
	      	   record.setDeaths2011((Long)value); break;
	       case NATURALINC2010_INDEX:
	      	   record.setNaturalinc2010((Long)value); break;
	       case NATURALINC2011_INDEX:
	      	   record.setNaturalinc2011((Long)value); break;
	       case INTERNATIONALMIG2010_INDEX:
	      	   record.setInternationalmig2010((Long)value); break;
	       case INTERNATIONALMIG2011_INDEX:
	      	   record.setInternationalmig2011((Long)value); break;
	       case DOMESTICMIG2010_INDEX:
	      	   record.setDomesticmig2010((Long)value); break;
	       case DOMESTICMIG2011_INDEX:
	      	   record.setDomesticmig2011((Long)value); break;
	       case NETMIG2010_INDEX:
	      	   record.setNetmig2010((Long)value); break;
	       case NETMIG2011_INDEX:
	      	   record.setNetmig2011((Long)value); break;
	       case RESIDUAL2010_INDEX:
	      	   record.setResidual2010((Long)value); break;
	       case RESIDUAL2011_INDEX:
	      	   record.setResidual2011((Long)value); break;
	       case RBIRTH2011_INDEX:
	      	   record.setRbirth2011((Double)value); break;
	       case RDEATH2011_INDEX:
	      	   record.setRdeath2011((Double)value); break;
	       case RNATURALINC2011_INDEX:
	      	   record.setRnaturalinc2011((Double)value); break;
	       case RINTERNATIONALMIG2011_INDEX:
	      	   record.setRinternationalmig2011((Double)value); break;
	       case RDOMESTICMIG2011_INDEX:
	      	   record.setRdomesticmig2011((Double)value); break;
	       case RNETMIG2011_INDEX:
	      	   record.setRnetmig2011((Double)value); break;
         default:
                System.out.println("invalid index");
         } // end:switch
         fireTableCellUpdated(row, column);
     } // end:setValueAt

     /**
      * Row count accessor.
      */
     public int getRowCount() {
         return m_vect_data.size();
     } // end:getRowCount
     /**
      * Column count accessor.
      */
     public int getColumnCount() {
         return m_col_columnNames.length;
     } // end:getColumnCount
     
     /**
      * Value function that can be used to
      * determine whether there is currently
      * an empty row defined.
      * 
      * @return boolean value indicating whether an empty value exists.
      */
     public boolean hasEmptyRow() {
         if (m_vect_data.size() == 0) return false;
         PopulationRecord record = (PopulationRecord)m_vect_data.get(m_vect_data.size() - 1);
         if (record.getState().toString().trim().equals("") &&
        		 record.getSumlev().toString().trim().equals("") &&
        		 record.getRegion().toString().trim().equals("") &&
        		 record.getDivision().toString().trim().equals("") &&
        		 record.getName().toString().trim().equals(""))
         {
            return true;
         }
         else return false;
     } // end:hasEmptyRow

     /**
      * Control function used to add an empty row to the table.
      */
     public void addEmptyRow() {
         this.m_vect_data.add(new PopulationRecord());
         fireTableRowsInserted(
            this.m_vect_data.size() - 1,
            this.m_vect_data.size() - 1);
     } // end:addEmptyRow

     /**
      * Control function that can be used to
      * write the current contents of the
      * grid to a file.
      */
     public synchronized void serializeData() {
    	 PersistentObject obj = new PersistentObject();
    	 obj.LoadVector(this.m_vect_data);
    	 PersistentObjectWriter w = new PersistentObjectWriter(this.m_col_args,obj);
    	 try {
    		 String[] cols = { "SUMLEV", "REGION", "DIVISION", "STATE", "NAME", "CENSUS2010POP", "ESTIMATESBASE2010", "POPESTIMATE2010", "POPESTIMATE2011", "NPOPCHG_2010", "NPOPCHG_2011", "BIRTHS2010", "BIRTHS2011", "DEATHS2010", "DEATHS2011", "NATURALINC2010", "NATURALINC2011", "INTERNATIONALMIG2010", "INTERNATIONALMIG2011", "DOMESTICMIG2010", "DOMESTICMIG2011", "NETMIG2010", "NETMIG2011", "RESIDUAL2010", "RESIDUAL2011", "RBIRTH2011", "RDEATH2011", "RNATURALINC2011", "RINTERNATIONALMIG2011", "RDOMESTICMIG2011", "RNETMIG2011"};
    		 w.writeCSV(cols);
    		 
    		 JOptionPane.showMessageDialog(null,"The contents of the grid has been written to " + w.getFilename());
		 } catch (IOException e) {
			e.printStackTrace();
		} // end:catch
     } // end:serializeData


} // end:class
