import java.text.*;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

/**
 * 
 * @author Brady Houseknecht
 */
public class Program 
{
    /**
     * Application thread.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        domain.PersistentObject recs;
        System.out.println("parsing CSV...");
        domain.PersistentObject persistentObject = 
                new domain.PersistentObject("..\\data\\NST_EST2011_ALLDATA.csv");
      
       FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("..\\data\\population-record.ser");
            ObjectOutputStream out =
                              new ObjectOutputStream(fileOut);
           out.writeObject(persistentObject);
           out.close();
            fileOut.close();
        } // end  try
        catch (FileNotFoundException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        } // end catch
        
        try {
            Thread.currentThread().sleep(5000);//sleep for 1000 ms
        } // end try
        catch (InterruptedException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        } // end catch
        
        try {
            FileInputStream fileIn =
                          new FileInputStream("..\\data\\population-record.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            recs =(domain.PersistentObject) 
                    in.readObject();
            Date postSerTimeStamp = new Date();
            double seconds = (postSerTimeStamp.getTime()-
                   recs.getDate().getTime())/1000;
            System.out.println(seconds + " seconds ellapsed.");
            
            for(domain.PopulationRecord rec : recs.getPopulationRecords())
            {
                System.out.println(rec.getRegion().name() + " 2010 Population % Incr: " + rec.popPerIncr(2010));
                System.out.println(rec.getRegion().name() + " 2011 Population % Incr: " + rec.popPerIncr(2011));
            } // end for
            
            for(domain.PopulationRecord rec : recs.getPopulationRecords())
            {
                if(rec.getSumlev() == domain.Record.SumLevCode.State &&
                        rec.getState()!= domain.Record.StateFIPCode.NA)
                {
                    System.out.println(rec.getState().name() + " 2010 Max / Min Births: " + rec.maxBirthPerYear(2010));
                    System.out.println(rec.getRegion().name() + " 2011 Max / Min Births: " + rec.maxBirthPerYear(2011));
                    System.out.println(rec.getState().name() + " 2010 Max / Min Deaths: " + rec.maxDeathPerYear(2010));
                    System.out.println(rec.getState().name() + " 2011 Max / Min Deaths: " + rec.maxDeathPerYear(2011));
                } // end if
            } // end for
            
            System.out.println("Count of States with Population Increases in 2011: " + recs.getCountOfEstPopIncr(2011));
	    System.out.println("Count of States with Population Decreases in 2011: " + recs.getCountOfEstPopDecr(2011));
            
            System.out.println("State with the greatest population in 2011: " + recs.getStateWithMostPop(2011));
	    System.out.println("State with the greatest population in 2010: " + recs.getStateWithMostPop(2010));
	    System.out.println("State with the least population in 2011: " + recs.getStateWithLeastPop(2011));
	    System.out.println("State with the least population in 2010: " + recs.getStateWithLeastPop(2010));
            
            
            in.close();
            fileIn.close();
        } // end try
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        } // end catch
        
    } // end void
} // end class
