import java.text.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;
import bo.PopulationRecord;
import db.PopulationTable;

import ui.FrmMain;

/**
 * Main Program thread. 
 * 
 * @author Brady Houseknecht
 */
public class Program 
{
	 public static ArrayList<PopulationRecord> m_col_serializedRecords;
	 
	
	/**
	 * Main thread. From command line the parameters are
	 * parsed sequentially--
	 * 
	 * 0 = mysql Conection String
	 * 1 = mysql Database
	 * 2 = mysql login
	 * 3 = mysql password
	 * 4 = data directory path
	 * 5 = saved data (optional)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
         try {
         
        	 init(args[5],args[4]);
        	
        	 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
             JFrame frame = new JFrame("FMP");
             frame.add(new FrmMain(args,m_col_serializedRecords));
             
             /// Set the size of frame based on the user's display--i.e. fill the screen
             Toolkit tk = Toolkit.getDefaultToolkit();   
             int xSize = ((int) tk.getScreenSize().getWidth());   
             int ySize = ((int) tk.getScreenSize().getHeight());   
             frame.setSize(xSize,ySize);
             frame.setVisible(true);
             
             
         } catch (Exception e) {
             e.printStackTrace();
         } // end:catch
     } // end:main
	 
	
	 
	 /**
	  * initialization function
	  * that accepts a csv file with which 
	  * populate the population_T table. 
	  * 
	  * @param path string value equal to the data directory path
	  * @param serfile string value equal to serialized data file
	  */
	 public static void init(String path, String csv) {
	        
		    System.out.println("Program.init fired.");

	        bo.PersistentObject recs;
	        
	        System.out.println("parsing CSV...");
	        bo.PersistentObject persistentObject = 
	                new bo.PersistentObject(path+csv);
	      
	       FileOutputStream fileOut;
	        try 
	        {
	            fileOut = new FileOutputStream(path+"population-record.ser");
	            ObjectOutputStream out =
	                              new ObjectOutputStream(fileOut);
	           out.writeObject(persistentObject);
	           out.close();
	            fileOut.close();
	        } // end: try
	        catch (FileNotFoundException ex) {
	            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
	        } // end:catch
	        catch (IOException e) {
				e.printStackTrace();
			} // end:catch
	        
	        try 
	        {
	            Thread.currentThread().sleep(5000);//sleep for 1000 ms
	        } // end:try
	        catch (InterruptedException ex) {
	            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
	        } // end:catch
	        
	        try 
	        {
	            FileInputStream fileIn =
	                          new FileInputStream(path+"population-record.ser");
	            ObjectInputStream in = new ObjectInputStream(fileIn);
	            recs =(bo.PersistentObject) 
	                    in.readObject();
	            Date postSerTimeStamp = new Date();
	            double seconds = (postSerTimeStamp.getTime()-
	                   recs.getDate().getTime())/1000;
	            System.out.println(seconds + " seconds ellapsed.");
	            
	            for(bo.PopulationRecord rec : recs.getPopulationRecords())
	            {
	                System.out.println(rec.getRegion() + " 2010 Population % Incr: " + rec.popPerIncr(2010));
	                System.out.println(rec.getRegion() + " 2011 Population % Incr: " + rec.popPerIncr(2011));
	            } // end:for
	            
	            m_col_serializedRecords = recs.getPopulationRecords();
	            
	            
	            for(bo.PopulationRecord rec : recs.getPopulationRecords())
	            {
	                if(rec.getSumlev() == "40" &&
	                        rec.getState()!= "0")
	                {
	                    System.out.println(rec.getState() + " 2010 Max / Min Births: " + rec.maxBirthPerYear(2010));
	                    System.out.println(rec.getRegion() + " 2011 Max / Min Births: " + rec.maxBirthPerYear(2011));
	                    System.out.println(rec.getState() + " 2010 Max / Min Deaths: " + rec.maxDeathPerYear(2010));
	                    System.out.println(rec.getState() + " 2011 Max / Min Deaths: " + rec.maxDeathPerYear(2011));
	                } // end:if
	            } // end:for
	            
	            System.out.println("Count of States with Population Increases in 2011: " + recs.getCountOfEstPopIncr(2011));
				System.out.println("Count of States with Population Decreases in 2011: " + recs.getCountOfEstPopDecr(2011));
				
				System.out.println("State with the greatest population in 2011: " + recs.getStateWithMostPop(2011));
				System.out.println("State with the greatest population in 2010: " + recs.getStateWithMostPop(2010));
				System.out.println("State with the least population in 2011: " + recs.getStateWithLeastPop(2011));
				System.out.println("State with the least population in 2010: " + recs.getStateWithLeastPop(2010)); 
	            in.close();
	            fileIn.close();
	    	} // end:try
	        catch (ClassNotFoundException ex) {
	            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
	        } // end:catch
	        catch (FileNotFoundException e) {
				e.printStackTrace();
			} // end:catch
	        catch (IOException e) {
	        	e.printStackTrace();
			} // end:catch
	    } // end:init

} // end:class