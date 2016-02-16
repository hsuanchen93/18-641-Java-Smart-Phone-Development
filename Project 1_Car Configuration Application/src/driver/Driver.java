/*
 * Hsuan Chen (hsuanc)
 */

package driver;

import java.io.FileNotFoundException;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.EditAuto;
import exception.AutoException;
import model.Automobile;
import scale.EditOptions;
import util.FileIO;

public class Driver {

	public static void main(String[] args) {
		String modelName = "Focus Wagon ZTW";
		
		//TEST 1: test EditAuto API
		//-->working/normal code
		testAPI(modelName);
		
		//TEST 2: test success synchronization
		//-->add Thread.sleep(3000) in editOptionSetName
		testThread("Thread");
		
		//TEST 3:test fail synchronization
		//--> take out the "synchronized(a1)" in EditOptions and run testThread
		testThread("Thread");
	}
	
	
	public static void testAPI(String modelName) {
		//create regular Focus Wagon ZTW Auto instance
		System.out.println("<-----TESTING API----->");
		CreateAuto autoC = new BuildAuto();
		EditAuto autoE = new BuildAuto();
		autoC.BuildAuto("FordZTW.txt");
		autoC.printAuto(modelName);
		
		//edit OptionSet Name from "Power Moonroof" to "NEW NAME"
		System.out.println("<-----Edit Option Set Name Instance----->");
		autoE.editOptionSetName(modelName, "Power Moonroof", "NEW NAME");
		autoC.printAuto(modelName);
		
		//edit Option Price from "$0" to "$-30.78"
		System.out.println("<-----Edit Option Price Instance----->");
		autoE.editOptionPrice(modelName, "NEW NAME", "Not Present", -30.78);
		autoC.printAuto(modelName);
	}
	
	
	public static void testThread(String modelName) {
		//testing threads (not using API but Auto directly)
		System.out.println("\n\n<-----TESTING THREADS SYNCHRONIZATION----->");
		Automobile a1 = null;
		try {
			a1 = new FileIO().buildAutoObject("Thread.txt");
		} catch (FileNotFoundException | AutoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a1.print();
		
		//create 2 threads
		//editOptionSetName form "Color" to "NEW NAME YAY"
		String[] args1 = new String[]{"Color","NEW NAME YAY"};
		EditOptions t1 = new EditOptions(a1, "editOptionSetName", args1);
		//editOptionPrice from "$0" to "$-300"
		String[] args2 = new String[]{"NEW NAME YAY", "Black", "-300"};
		EditOptions t2 = new EditOptions(a1, "editOptionPrice", args2);
		
		//added Thread.sleep(3000) inside "editOptionSetName"
		//since t1 start to run first and will get the lock, t2 wouldn't be
		//able to change Option Price to -300 until t1 finishes sleeping
		//IF synchronization works
		t1.start();
		t2.start();
		
		//to see how far the main thread has gone
		//also to show that AUTO hasn't changed yet since it's still sleeping
		System.out.println("<-----immediately, original----->");
		a1.print();
		try {
			//waits for a1 to change name and price
			Thread.sleep(3050);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("<-----sleep for 3 sec, after edit----->");
		//ALSO, if synchronization didn't work, updating the option price
		//would have generated error since "NEW NAME YAY" OptionSet wouldn't 
		//exist until after t1
		a1.print();
		
	}

}
