package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.*;

import mp2.*;

/**
 * Class intended to virtualize 
 * the Population_T database table.
 * 
 * @author Brady Houseknecht
 */
public class PopulationTable 
{
	 private Vector m_vect_cols;
	 private Vector m_vect_rows;
	 private ArrayList<mp2.PopulationRecord> m_col_populationRecords;
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
	 public PopulationTable(ArrayList<mp2.PopulationRecord> populationRecords)
	 {
		 this.m_col_populationRecords = populationRecords;
		 
	 } // end constructor
	 
	 public void insert()
	    {
	    	System.out.println("Inserting values in Mysql database table!");
	    	  Connection con = null;
	    	  String url = "jdbc:mysql://127.0.0.1:3306/";
	    	  String db = "itm411db";
	    	  String driver = "com.mysql.jdbc.Driver";
	    	  try
	    	  {
		    	  Class.forName(driver);
		    	  con = DriverManager.getConnection(url+db,"itm","itm");
		    	  try
		    	  {
			    	  Statement st = con.createStatement();
			    	  boolean truncate = st.execute("TRUNCATE TABLE population_t");
			    	  if(truncate==true)
			    		  System.out.println("Truncated Population_T table. Beginning reload...");
	    	   
			    	  for(mp2.PopulationRecord r : this.m_col_populationRecords)
			    	  {
			    		  String sumlevcode = "";
			    		  String regioncode = "";
			    		  String divisioncode = "";
			    		  String state = "";
			    		  
			    		  switch(r.getSumlev())
			    	        {
			    	            case Nation:
			    	                sumlevcode="10";
			    	                break;
			    	            case Region:
			    	                sumlevcode = "20";
			    	                break;
			    	            case State:
			    	                sumlevcode = "40";
			    	        } // end switch
			    		  
			    		  switch(r.getRegion())
			    		  {
			    		  case Total:
			    				regioncode="0";
			    				break;
			    			case Northeast:
			    				regioncode="1";
			    				break;
			    			case Midwest:
			    				regioncode="2";
			    				break;
			    			case South:
			    				regioncode="3";
			    				break;
			    			case West:
			    				regioncode="4";
			    				break;
			    			case NA:
			    				regioncode="-1";
			    				break;
			    		  } // end switch
			    		  
			    		  switch(r.getDivision())
			    		  {
			    		    case United_States_Total:
			    				divisioncode="0";
			    				break;
			    			case New_England:
			    				divisioncode="1";
			    				break;
			    			case Middle_Atlantic:
			    				divisioncode="2";
			    				break;
			    			case East_North_Central:
			    				divisioncode="3";
			    				break;
			    			case West_North_Central:
			    				divisioncode="4";
			    				break;
			    			case South_Atlantic:
			    				divisioncode="5";
			    				break;
			    			case East_South_Central:
			    				divisioncode="6";
			    				break;
			    			case West_South_Central:
			    				divisioncode="7";
			    				break;
			    			case Mountain:
			    				divisioncode="8";
			    				break;
			    			case Pacific:
			    				divisioncode="9";
			    				break;
			    			case Not_Applicable:
			    				divisioncode="-1";
			    				break;
			    		  } // end switch
			    		  
			    		  switch(r.getState())
			    		  {
			    		    case NA: state = "0"; break;
			                case AL: state = "1"; break;
			                case AK: state = "2"; break;
			                case AZ: state = "3"; break;
			                case AR: state = "4"; break;
			                case CA: state = "5"; break;
			                case CO: state = "6"; break;
			                case CT: state = "7"; break;
			                case DE: state = "8"; break;
			                case DC: state = "9"; break;
			                case FL: state = "10"; break;
			                case GA: state = "11"; break;
			                case HI: state = "12"; break;
			                case ID: state = "13"; break;
			                case IL: state = "14"; break;
			                case IN: state = "15"; break;
			                case IA: state = "16"; break;
			                case KS: state = "17"; break;
			                case KY: state = "18"; break;
			                case LA: state = "19"; break;
			                case ME: state = "20"; break;
			                case MD: state = "21"; break;
			                case MA: state = "22"; break;
			                case MI: state = "23"; break;
			                case MN: state = "24"; break;
			                case MS: state = "25"; break;
			                case MO: state = "26"; break;
			                case MT: state = "27"; break;
			                case NE: state = "28"; break;
			                case NV: state = "29"; break;
			                case NH: state = "30"; break;
			                case NJ: state = "31"; break;
			                case NM: state = "32"; break;
			                case NY: state = "33"; break;
			                case NC: state = "34"; break;
			                case ND: state = "35"; break;
			                case OH: state = "36"; break;
			                case OK: state = "37"; break;
			                case OR: state = "38"; break;
			                case PA: state = "39"; break;
			                case RI: state = "40"; break;
			                case SC: state = "41"; break;
			                case SD: state = "42"; break;
			                case TN: state = "43"; break;
			                case TX: state = "44"; break;
			                case UT: state = "45"; break;
			                case VT: state = "46"; break;
			                case VA: state = "47"; break;
			                case WA: state = "48"; break;
			                case WV: state = "49"; break;
			                case WI: state = "50"; break;
			                case WY: state = "51"; break;
			                case PR: state = "52"; break;
			                case VI: state = "53"; break;
			    		  } // end switch
		
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
										"(" + sumlevcode +","+
					        			  regioncode +", "+
					        			  divisioncode + ", " +
					        			  state + ", \"" +
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
			  String url = "jdbc:mysql://127.0.0.1:3306/";
			  String db = "itm411db";
			  String driver = "com.mysql.jdbc.Driver";
			
			  try 
			  {
				  Class.forName(driver);
			      con = DriverManager.getConnection(url+db,"itm","itm");
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
					  Vector row = new Vector(columns);                
						for (int i = 1; i <= columns; i++) 
						{                    
							row.addElement(rs.getObject(i));                
						}                
						this.m_vect_rows.addElement(row);   
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
