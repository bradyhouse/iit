package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.*;

import bo.*;


/**
 * Class intended to virtualize 
 * the Population_T database table.
 * 
 * @author Brady Houseknecht
 */
public class PopulationTable 
{
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
	 private Vector m_vect_cols;
	 private Vector m_vect_rows;
	 private ArrayList<bo.PopulationRecord> m_col_populationRecords;
	 private String m_str_mysqlconn_arg0;
	 private String m_str_mysqlDb_arg1;
	 private String m_str_mysqllogin_arg2;
	 private String m_str_mysqlPass_arg3;
	 
	 /**
	  * Class Constructor
	  */
	 public PopulationTable()
	 {
		 
	 } // end constructor
	
	 /**
	  * Overloaded constructor that will initialize the target Population_T
	  * table using a collection of PopulationRecords. NOTE - MP2 artifact.
	  *
	  * @param populationRecords Collection of PopulationRecords containing data that 
	  * should used to initialize the table.
	  */
	 public PopulationTable(ArrayList<bo.PopulationRecord> populationRecords)
	 {
		 this.m_col_populationRecords = populationRecords;
		 
	 } // end constructor
	 
	 /**
	  * Control function used update the MySql population_t table based on the
	  * populationRecords collection of the class.  The connection string
	  * information is supplied as parameters.
	  * 
	  * @param connstr string value equal to the target server connection information
	  * @param database string value equal to the name of the target database
	  * @param user string value equal to the login for the server
	  * @param pass string value equal to the password for supplied login
	  */
	 public void insert(String connstr, String database, String user, String pass)
	    {
	    	System.out.println("Inserting values in Mysql database table!");
	    	  
	    	  this.m_str_mysqlconn_arg0 = connstr;
	    	  this.m_str_mysqlDb_arg1 = database;
	    	  this.m_str_mysqlPass_arg3 = pass;
	    	  this.m_str_mysqllogin_arg2 = user;
	    	
	    	  Connection con = null;
	    	  String url = this.m_str_mysqlconn_arg0;
	    	  String db = this.m_str_mysqlDb_arg1;
	    	  String driver = "com.mysql.jdbc.Driver";
	    	  try
	    	  {
		    	  Class.forName(driver);
		    	  con = DriverManager.getConnection(url+db,this.m_str_mysqllogin_arg2,
		    			  this.m_str_mysqlPass_arg3);
		    	  try
		    	  {
			    	  Statement st = con.createStatement();
			    	  boolean truncate = st.execute("TRUNCATE TABLE population_t");
			    	  if(truncate==true)
			    		  System.out.println("Truncated Population_T table. Beginning reload...");
	    	   
			    	  for(bo.PopulationRecord r : this.m_col_populationRecords)
			    	  {
			    		  String sumlevcode = "";
			    		  String regioncode = "";
			    		  String divisioncode = "";
			    		  String state = "";
			    		  
			    		 
		
			        	  String sql = "INSERT INTO Population_T" +
										"(" +
										"SUMLEV," + 
										"REGION,"+ 
										"DIVISION,"+ 
										"STATE,"+
										"NAME,"+ 
										"CENSUS2010POP,"+
										"ESTIMATESBASE2010,"+ 
										"POPESTIMATE2010,"+ 
										"POPESTIMATE2011,"+ 
										"NPOPCHG_2010,"+ 
										"NPOPCHG_2011,"+ 
										"BIRTHS2010,"+ 
										"BIRTHS2011,"+ 
										"DEATHS2010,"+ 
										"DEATHS2011,"+ 
										"NATURALINC2010,"+ 
										"NATURALINC2011,"+ 
										"INTERNATIONALMIG2010,"+ 
										"INTERNATIONALMIG2011,"+ 
										"DOMESTICMIG2010,"+
										"DOMESTICMIG2011,"+ 
										"NETMIG2010,"+
										"NETMIG2011,"+
										"RESIDUAL2010,"+
										"RESIDUAL2011,"+ 
										"RBIRTH2011,"+
										"RDEATH2011,"+
										"RNATURALINC2011,"+
										"RINTERNATIONALMIG2011,"+ 
										"RDOMESTICMIG2011," +
										"RNETMIG2011"+
										")"+
										"VALUES"+
										"(" + r.getSumlev() +","+
					        			  r.getRegion() +", "+
					        			  r.getDivision() + ", " +
					        			  r.getState() + ", \"" +
					        			  r.getName() + "\"," +
					        			  Long.toString(r.getCensus2010pop()) + ", "+
					        			  Long.toString(r.getEstimatesbase2010()) + ", " +
					        			  Long.toString(r.getPopestimate2010()) + ", " +
					        			  Long.toString(r.getPopestimate2011()) + ", " + 
					        			  Long.toString(r.getNPopChg2010()) + ", " +
					        			  Long.toString(r.getNPopChg2011()) + ", " +
					        			  Long.toString(r.getBirths2010()) + ", " +
					        			  Long.toString(r.getBirths2011()) + ", " +
					        			  Long.toString(r.getDeaths2010()) + ", " +
					        			  Long.toString(r.getDeaths2011()) + ", " +
					        			  Long.toString(r.getNaturalinc2010()) + ", " +
					        			  Long.toString(r.getNaturalinc2011()) + ", " +
					        			  Long.toString(r.getInternationalmig2010()) + ", " +
					        			  Long.toString(r.getInternationalmig2011()) + ", " +
					        			  Long.toString(r.getDomesticmig2010()) + ", " +
					        			  Long.toString(r.getDomesticmig2011()) + ", " +
					        			  Long.toString(r.getNetmig2010()) + ", " +
					        			  Long.toString(r.getNetmig2011()) + ", " +
					        			  Long.toString(r.getResidual2010())+ ", " +
					        			  Long.toString(r.getResidual2011())+ ", " +
					        			  Double.toString(r.getRbirth2011())+ ", " +
					        			  Double.toString(r.getRdeath2011()) + ", " +
					        			  Double.toString(r.getRnaturalinc2011()) + ", " +
					        			  Double.toString(r.getRinternationalmig2011()) + ", " +
					        			  Double.toString(r.getRdomesticmig2011()) + ", " +
					        			  Double.toString(r.getRnetmig2011()) + ")";
			    		  System.out.println(sql);
			    		  Boolean rc = st.execute(sql);
		
			        	  System.out.println("1 row affected");

			    	  } // end for
	    	  
		    	  } // end:try
		    	  catch (SQLException s)
		    	  {
		    		  System.out.println("SQL statement is not executed!");
		    	  } // end:catch
	    	  } // end:try
	    	  catch (Exception e)
	    	  {
	    		  e.printStackTrace();
	    	  } // end:catch
	    } // end:insert
	 
	 	/**
	 	 * Accessor that can be used to get the columns
	 	 * headers (or captions) of the Population_T table.
	 	 * 
	 	 * @return String array instance containing 30
	 	 * strings corresponding to the column names
	 	 * of the Population_T table.
	 	 */
	 	public String[] getHeaderRow()
	 	{
	 		String[] result  = { "SUMLEV", "REGION", "DIVISION", "STATE", "NAME", "CENSUS2010POP", "ESTIMATESBASE2010", "POPESTIMATE2010", "POPESTIMATE2011", "NPOPCHG_2010", "NPOPCHG_2011", "BIRTHS2010", "BIRTHS2011", "DEATHS2010", "DEATHS2011", "NATURALINC2010", "NATURALINC2011", "INTERNATIONALMIG2010", "INTERNATIONALMIG2011", "DOMESTICMIG2010", "DOMESTICMIG2011", "NETMIG2010", "NETMIG2011", "RESIDUAL2010", "RESIDUAL2011", "RBIRTH2011", "RDEATH2011", "RNATURALINC2011", "RINTERNATIONALMIG2011", "RDOMESTICMIG2011", "RNETMIG2011"};
	 		return result;
	 	} // end:getHeaderRow

	 	/**
	 	 * Value function that returns the data
	 	 * contained in the table using a 
	 	 * two dimensional object array.
	 	 * NOTE - This meant for data binding
	 	 * to a JTable control for display
	 	 * on screen.
	 	 * 
	 	 * @return two dimensional object array of size [1][n] where
	 	 * n is based on the rows in the actual Population Table.
	 	 */
	 	public synchronized void DataBind()
	 	{
	 		  this.m_vect_rows = new Vector();
	 		  this.m_vect_cols = new Vector();
	 		
	 		  ArrayList<Object[]> datarows = new ArrayList<Object[]>();
	 		  
			  Connection con = null;
			  String url = this.m_str_mysqlconn_arg0;
			  String db = this.m_str_mysqlDb_arg1;
			  String driver = "com.mysql.jdbc.Driver";
			
			  try 
			  {
				  Class.forName(driver);
			      con = DriverManager.getConnection(url+db,this.m_str_mysqllogin_arg2,this.m_str_mysqlPass_arg3);
			      Statement st;
				  st = con.createStatement();
			      String sql = "SELECT SUMLEV, REGION, DIVISION, STATE, NAME, CENSUS2010POP, ESTIMATESBASE2010, POPESTIMATE2010, POPESTIMATE2011, NPOPCHG_2010, NPOPCHG_2011, BIRTHS2010, BIRTHS2011, DEATHS2010, DEATHS2011, NATURALINC2010, NATURALINC2011, INTERNATIONALMIG2010, INTERNATIONALMIG2011, DOMESTICMIG2010, DOMESTICMIG2011, NETMIG2010, NETMIG2011, RESIDUAL2010, RESIDUAL2011, RBIRTH2011, RDEATH2011, RNATURALINC2011, RINTERNATIONALMIG2011, RDOMESTICMIG2011, RNETMIG2011 FROM population_t";
				  ResultSet rs;
				  rs = st.executeQuery(sql);
				  ResultSetMetaData metaData = rs.getMetaData();            

				  int columns = metaData.getColumnCount();            
				  for (int i = 1; i <= columns; i++) {                
					this.m_vect_cols.addElement(metaData.getColumnName(i));            
				  } // end:for            
				  
				  while(rs.next())
				  {
					  PopulationRecord record = new PopulationRecord();
					  
					    for (int i = 1; i <= columns; i++) 
						{                    
					    	Object value = rs.getObject(i);
					    	switch(i-1)
					    	{
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
					    	} // end:switch
						}                
						this.m_vect_rows.addElement(record);   
				  } // end:while
				  rs.close();
				  st.close();
			  } // end:try
			  catch (ClassNotFoundException e) {
					e.printStackTrace();
			  } // end:catch
			  catch (SQLException e) 
			  {
				e.printStackTrace();
			  } // end:catch
	 	} // end:databind

	 	/**
	 	 * Accessor used to get the Rows Vector.
	 	 *
	 	 * @return Vector object populated with rows pulled from Population_T database table.
	 	 */
	 	public Vector getRows()
	 	{
	 		return this.m_vect_rows;
	 	} // end:getRows
	 	
	 	/**
	 	 * Accessor used to get the Columns Vector.
	 	 *
	 	 * @return Vector object populated with column metadata pulled from the Population_T database table.
	 	 */
	 	public Vector getColumns()
	 	{
	 		return this.m_vect_cols;
	 	} // end:getColumns
} // end:class
