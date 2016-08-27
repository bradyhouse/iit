
import java.io.*;
import java.util.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
* MP1 Requirement: Provide a driver class that includes the following features:
* • randomly creates 20 tablet subclass instances
* • populates each subclass instance with its type data randomly
* • collects all instances into an array of Tablet
* • displays all created tablets data using the toString() method from the array
* • displays all interface method results using the array as an argument 
* @author brady Houseknecht
*/
public class Program 
{
     private static mpUtil.StringDictionary m_col_dataDictionary;
     private static Tablets.Tablet[] m_arr_tablets;
    
     /**
     * Enumeration defining the 
     * 3 types of tablets.
     */
    public enum Type {
        None,
        Apple,
        Samsung,
        Google
    }
    
    /**
     * Application thread.
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String nl = new Character((char)10).toString();
        String cr = new Character((char)13).toString();
        String row;
        FileReader input; 
        int id = 1;
        boolean IOException = false;
        
        
        // Load the test data dictionary with the contents
        // of the tablets.csv file. The values in the dictionary
        // shoule be numerically keyed based on the line of the file.
        m_arr_tablets = new Tablets.Tablet[20];
        m_col_dataDictionary = new mpUtil.StringDictionary();

        try 
        {
            input = new FileReader("..\\data\\tablets.csv");
            BufferedReader bufRead = new BufferedReader(input); 
            try 
            {
                while ( (row = bufRead.readLine()) != null) 
                {   
                    m_col_dataDictionary.put(Integer.toString(id),row);
                    id++;
                } // end while
            
                // Create 20 random numbers less then 25
                // For each number, initialize a tablet object from
                // the data dictionary and add it to the array

                int idx = 0;
                Random randomGenerator = new Random();
                while (idx <= 19){

                    int randomInt = randomGenerator.nextInt(24);

                    try
                    {
                        if(m_col_dataDictionary.contains(Integer.toString(randomInt)))
                        {
                            String commaDelimitedLine = 
                                 m_col_dataDictionary.get(Integer.toString(randomInt));

                            String[] cols = commaDelimitedLine.split(","); 

                            Type tabletType = Type.None;

                            if("Apple".equals(cols[0].trim()))
                            {
                                tabletType = Type.Apple;
                            } // end if
                            else if ("Samsung".equals(cols[0].trim()))
                            {
                                tabletType = Type.Samsung;
                            } // end else if
                            else if ("Google".equals(cols[0].trim()))
                            {
                                tabletType = Type.Google;
                            } // end else

                            if(tabletType!= Type.None)
                            {
                                Tablets.Tablet newTablet = buildTablet(tabletType,cols);
                                if(newTablet != null)
                                {
                                    m_arr_tablets[idx] = newTablet;
                                    idx++;
                                } // end if
                            } // end if
                        } // end if
                    } // end try
                    catch (NullPointerException ex)
                    {
                         Logger.getLogger(Program.class.getName()).log(
                            Level.SEVERE, null, ex);
                         continue;
                    } // end catch
                } // end while

                System.out.println("Outputting tablet instances...");
                System.out.println();
                System.out.print(Arrays.toString(m_arr_tablets));
                System.out.println();
                System.out.println();
                System.out.println("Finding the largest display tablet...");
                System.out.println();
                System.out.print(
                        m_arr_tablets[0].findLargestDisplayTablet(m_arr_tablets));
                System.out.println();
                System.out.println();
                System.out.println("Finding the lightest tablet...");
                System.out.println();
                System.out.print(
                        m_arr_tablets[0].findLightestWeightTabletType(m_arr_tablets));
                System.out.println();
                System.out.println();
                System.out.println("Finding the cheapest tablet...");
                System.out.println();
                System.out.print(
                        m_arr_tablets[0].findLowestCostTablet(m_arr_tablets));
                System.out.println();
                System.out.println();
                System.out.println("Finding the type of tablets...");
                System.out.println();
                System.out.print(
                        m_arr_tablets[0].findNumberOfCreatedTabletTypes(m_arr_tablets));
                System.out.println();
                System.out.println();
                System.out.println();
            
            } // end try
            catch (IOException ex) 
            {
                Logger.getLogger(Program.class.getName()).log(
                        Level.SEVERE, null, ex);
                
            } // end catch
            
            
        } // end try 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Program.class.getName()).log(
                    Level.SEVERE, null, ex);
            
        } // end catch
        
      
    } // end main
    
    private static Tablets.Tablet buildTablet(Type tabletType, String[] data)
    {
            Tablets.Tablet result = null;
            switch (tabletType) 
            {
                case Apple:
                    result = new Tablets.iPadTablet(
                            data[0], // Manufacturer
                            data[1], // Model Number
                            Double.valueOf(data[2]), // Display Size
                            Double.valueOf(data[3]), // Memory
                            Boolean.valueOf(data[4]), // HasWifi
                            Boolean.valueOf(data[5]), // HasMobileCarrier
                            Double.valueOf(data[6]), // weight
                            Double.valueOf(data[7]) // cost
                            );
                    break;
                case Samsung:
                       result = new Tablets.GalaxyTablet(
                            data[0], // Manufacturer
                            data[1], // Model Number
                            Double.valueOf(data[2]), // Display Size
                            Double.valueOf(data[3]), // Memory
                            Boolean.valueOf(data[4]), // HasWifi
                            Boolean.valueOf(data[5]), // HasMobileCarrier
                            Double.valueOf(data[6]), // weight
                            Double.valueOf(data[7]) // cost
                            );
                     break;
                case Google:
                      result = new Tablets.NexusTablet(
                            data[0], // Manufacturer
                            data[1], // Model Number
                            Double.valueOf(data[2]), // Display Size
                            Double.valueOf(data[3]), // Memory
                            Boolean.valueOf(data[4]), // HasWifi
                            Boolean.valueOf(data[5]), // HasMobileCarrier
                            Double.valueOf(data[6]), // weight
                            Double.valueOf(data[7]) // cost
                            );
                    break;
             } // end switch
            return result;
    } //buildTablet
    
} // end program
