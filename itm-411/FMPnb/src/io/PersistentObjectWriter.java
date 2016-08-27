/**
 * 
 */
package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;


import bo.PersistentObject;


/**
 * Wrapper class for the process used to
 * Serialize the Persistent Object class.
 * 
 * @author Brady Houseknecht
 */
public class PersistentObjectWriter {

	private String m_str_path;
	private PersistentObject m_obj_data;
	private String m_str_filename;
	
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return m_str_filename;
	} // end:getFilename

	public PersistentObjectWriter(String[] args, PersistentObject obj)
	{
		this.m_obj_data = obj;
		this.m_str_path= args[5];
		
	} // end:constructor
	
	public synchronized void write() throws IOException
	{
	    	 FileOutputStream fileOut;
	         try {
	        	 this.m_str_filename = this.m_str_path + getDateTime() + ".ser";
	             fileOut = new FileOutputStream(this.m_str_filename);
	             ObjectOutputStream out =
	                               new ObjectOutputStream(fileOut);
	            out.writeObject(this.m_obj_data);
	            out.close();
	             fileOut.close();
	         } // end  try
	         catch (FileNotFoundException ex) {
	             Logger.getLogger("PersistentObjectWriter").log(Level.SEVERE, null, ex);
	         } // end catch
	    	
	} // end:write
	
	public synchronized void writeCSV(String[] columns) throws IOException
	{
	    	 FileOutputStream fileOut;
	         try {
	        	 this.m_str_filename = this.m_str_path +"population_t.csv";
	        	
	        	 File _file = new File(this.m_str_filename);

	        	 if(_file.exists())
	        		 _file.delete();
	        	 
	        	 FileWriter _outFile = new FileWriter(this.m_str_filename);
	        	 PrintWriter _out = new PrintWriter(_outFile);
	             String _header = "";
	             
	             int _index = 0;
	             
	             for(String col : columns)
	             {
	            	 if(_index < (columns.length-1))
	            		 _header += col + ",";
	            	 else
	            		 _header += col;
	            	 _index++;
	             } // end:for
	             
	             _out.println(_header);
	             for(bo.PopulationRecord r : this.m_obj_data.getPopulationRecords())
	             {
	            	 _out.println(
	            			 r.getSumlev() +","+
				        			  r.getRegion() +","+
				        			  r.getDivision() + "," +
				        			  r.getState() + "," +
				        			  r.getName() + "," +
				        			  Long.toString(r.getCensus2010pop()) + ","+
				        			  Long.toString(r.getEstimatesbase2010()) + "," +
				        			  Long.toString(r.getPopestimate2010()) + "," +
				        			  Long.toString(r.getPopestimate2011()) + "," + 
				        			  Long.toString(r.getNPopChg2010()) + "," +
				        			  Long.toString(r.getNPopChg2011()) + "," +
				        			  Long.toString(r.getBirths2010()) + "," +
				        			  Long.toString(r.getBirths2011()) + "," +
				        			  Long.toString(r.getDeaths2010()) + "," +
				        			  Long.toString(r.getDeaths2011()) + "," +
				        			  Long.toString(r.getNaturalinc2010()) + "," +
				        			  Long.toString(r.getNaturalinc2011()) + "," +
				        			  Long.toString(r.getInternationalmig2010()) + "," +
				        			  Long.toString(r.getInternationalmig2011()) + "," +
				        			  Long.toString(r.getDomesticmig2010()) + "," +
				        			  Long.toString(r.getDomesticmig2011()) + "," +
				        			  Long.toString(r.getNetmig2010()) + "," +
				        			  Long.toString(r.getNetmig2011()) + "," +
				        			  Long.toString(r.getResidual2010())+ "," +
				        			  Long.toString(r.getResidual2011())+ "," +
				        			  Double.toString(r.getRbirth2011())+ "," +
				        			  Double.toString(r.getRdeath2011()) + "," +
				        			  Double.toString(r.getRnaturalinc2011()) + "," +
				        			  Double.toString(r.getRinternationalmig2011()) + "," +
				        			  Double.toString(r.getRdomesticmig2011()) + "," +
				        			  Double.toString(r.getRnetmig2011())
	            			 );
	             } // end:for
	            _out.close();
	            _outFile.close();
	         } // end  try
	         catch (FileNotFoundException ex) {
	             Logger.getLogger("PersistentObjectWriter").log(Level.SEVERE, null, ex);
	         } // end catch
	    	
	} // end:write
	
	
	/**
	 * Value function used to create a unique filename suffix
	 * using the system time.
	 * 
	 * @return string value equal to the current time in yyyyMMdd.hhmmss format.
	 */
	private final static String getDateTime()   
	{   
	    DateFormat df = new SimpleDateFormat("yyyyMMdd.hhmmss");   
	    df.setTimeZone(TimeZone.getTimeZone("PST"));   
	    return df.format(new Date());   
	} // end:getDateTime
	
}
