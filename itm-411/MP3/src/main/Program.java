package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import producer.CogsWellCogs;
import producer.SpacelySprockets;
import region.CentralRegion;
import region.EasternRegion;
import region.MountainRegion;
import region.PacificRegion;


public class Program {

	/**
	 * Main program thread.
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
		File mappingFile = new File(args[0]);
		
		if(!mappingFile.exists())
			throw new FileNotFoundException(args[0] + " could not be found.");
		
		SpacelySprockets sprocketProducer = new SpacelySprockets(args[0]);
		CogsWellCogs cogProducer = new CogsWellCogs(args[0]);
		
		EasternRegion est = new EasternRegion(sprocketProducer, cogProducer);
		MountainRegion mt = new MountainRegion(sprocketProducer, cogProducer);
		CentralRegion cst = new CentralRegion(sprocketProducer, cogProducer);
		PacificRegion pt = new PacificRegion(sprocketProducer, cogProducer);
		
		 BufferedReader br = new BufferedReader(
			        new InputStreamReader(System.in));
		 
		/// Record the starting time stamp
		Date started = new Date();
		
		System.out.println("");
		System.out.println("Simulation starting");
		
		/// wait until the user presses enter completing 
		/// the buffer line
		while (!br.ready()) {
			sprocketProducer.run();
			cogProducer.run();
			est.run();
			cst.run();
			pt.run();
			mt.run();
		} // end:while
		
		System.out.println("");
		System.out.println("Simulation ended");
		System.out.println("");
		
		/// Record ending time stamp
		Date ended = new Date();
		
		long diff = ended.getTime() - started.getTime();
		long diffSeconds = diff / 1000 % 60;  
		long diffMinutes = diff / (60 * 1000) % 60;          
    	long diffHours = diff / (60 * 60 * 1000);  
		
    	String simHours = String.valueOf(diffHours).length() == 1 ? "0" + String.valueOf(diffHours) :
    		String.valueOf(diffHours);
    	
    	String simMinutes = String.valueOf(diffMinutes).length() == 1 ? "0" + String.valueOf(diffMinutes) :
    		String.valueOf(diffMinutes);
    	
    	String simSeconds = String.valueOf(diffSeconds).length() == 1 ? "0" + String.valueOf(diffSeconds) :
    		String.valueOf(diffSeconds);
    	
    	System.out.println("");
    	
    	/// Write the contents of each 
    	/// region's product message to 
    	/// the console.

    	est.out();
    	mt.out();
    	cst.out();
    	pt.out();
    	
    	System.out.println("");
    	
    	/// Write the totals for each region
    	/// to the console.

    	System.out.println(est.toString());
    	System.out.println(mt.toString());
    	System.out.println(cst.toString());
    	System.out.println(pt.toString());
    	
    	System.out.println("");

    	/// Write the total time of the 
    	/// simulation
    	
    	System.out.println("Simulation Time: " + simHours + ":" + simMinutes + ":" + simSeconds);
    	
    	/// Output the contents of each region's
    	/// queue to the directory passed at
    	/// startup
    	
    	est.write(args[1]);
    	mt.write(args[1]);
    	cst.write(args[1]);
    	pt.write(args[1]);
    	
    	
   	
	} // end:main

} // end:class
