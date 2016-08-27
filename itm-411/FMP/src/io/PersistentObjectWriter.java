/**
 * 
 */
package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
		this.m_str_path= args[4];
		
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
