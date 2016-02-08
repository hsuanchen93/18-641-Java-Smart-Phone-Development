/*
 * Hsuan Chen (hsuanc)
 */

package driver;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.InfoAuto;
import adapter.UpdateAuto;
import exception.AutoException;

public class Driver {

	public static void main(String[] args) {
		String model1 = "Focus Wagon ZTW";
		/* Test API */
		/* Creates an Auto instance based on given FordZTW */
		/* Updates "Color" to "NEW COLOR" and price to be "100.50" */
		testAPI(model1);
		
		/* List out all Custom Exceptions */
		System.out.println("<-----Custom Exceptions List----->");
		new AutoException().allExceptions();
		System.out.println("<-----DONE Custom Exceptions List----->\n");
		
		/* Test Exception 1: Automobile Does Not Exist */
		testException1("nonexistent");
		
		/* Test Exception 2: Missing base price for Automobile in text file */
		testException2();
		
		/* Test Exception 3: Missing OptionSet data */
		testException3();
		
		/* Test Exception 4: Missing Option data */
		testException4();
		
		/* Test Exception 5: Wrong filename */
		testException5();
		
		/* Test Print All and Iterator */
		testPrintAll(model1);
	}
	
	public static void testAPI(String modelName) {
		System.out.println("<-----Testing API (Create Auto Instance)----->");
		CreateAuto autoC = new BuildAuto();
		UpdateAuto autoU = new BuildAuto();
		InfoAuto autoI = new BuildAuto();
		autoC.BuildAuto("FordZTW.txt");
		autoC.printAuto(modelName);
		System.out.println("<-----Testing API (Update Auto Instance)----->");
		autoU.updateOptionSetName(modelName, "Color", "NEW COLOR");
		autoU.updateOptionPrice(modelName, "NEW COLOR", 
								"Fort Knox Gold Clearcoat Metallic", 100.50);
		autoC.printAuto(modelName);
		System.out.println("<-----Testing API (Delete Auto Instance)----->");
		autoU.deleteOptionSet(modelName, "NEW COLOR");
		autoU.deleteOptionSet(modelName, "Side Impact Air Bags");
		autoU.deleteOption(modelName, "Brakes/Traction Control", "ABS");
		autoC.printAuto(modelName);
		System.out.println("<-----Testing API (Set Auto Instance)----->");
		autoU.setOptionChoice(modelName, "Transmission", "Automatic");
		autoU.setOptionChoice(modelName, "Power Moonroof", "Present");
		autoI.printChoices(modelName);
		System.out.println("<-----DONE Testing API----->\n");
	}
	
	public static void testPrintAll(String modelName) {
		System.out.println("<-----Testing Print All----->");
		InfoAuto autoI = new BuildAuto();
		autoI.printAutoHashMap();
		System.out.println("<-----DONE Testing Print All----->\n");
		System.out.println("<-----Testing Print All Choices----->");
		autoI.printChoices(modelName);
		System.out.println("Total Price: $" + autoI.getTotalPrice(modelName));
		autoI.printChoices("Model 2");
		System.out.println("Total Price: $" + autoI.getTotalPrice("Model 2"));
		System.out.println("<-----DONE Testing Print All----->\n");
	}
	
	public static void testException1(String modelName) {
		System.out.println("<-----Testing Exception 1----->");
		InfoAuto autoI = new BuildAuto();
		autoI.modelExist(modelName);
		System.out.println("<-----DONE Testing Exception 1----->\n");
	}
	
	public static void testException2() {
		System.out.println("<-----Testing Exception 2----->");
		CreateAuto autoC = new BuildAuto();
		UpdateAuto autoU = new BuildAuto();
		autoC.BuildAuto("FordZTW_2.txt");
		autoU.setOptionChoice("Model 2", "Power Moonroof", "Present");
		autoC.printAuto("Model 2");
		System.out.println("<-----DONE Testing Exception 2----->\n");
	}
	
	public static void testException3() {
		System.out.println("<-----Testing Exception 3----->");
		CreateAuto autoC = new BuildAuto();
		autoC.BuildAuto("FordZTW_3.txt");
		System.out.println("<-----DONE Testing Exception 3----->\n");
	}
	
	public static void testException4() {
		System.out.println("<-----Testing Exception 4----->");
		CreateAuto autoC = new BuildAuto();
		autoC.BuildAuto("FordZTW_4.txt");
		System.out.println("<-----DONE Testing Exception 4----->\n");
	}
	
	public static void testException5() {
		System.out.println("<-----Testing Exception 5----->");
		CreateAuto autoC = new BuildAuto();
		autoC.BuildAuto("FordZTW_6.txt");
		autoC.printAuto("Model 5");
		System.out.println("<-----DONE Testing Exception 5----->\n");
	}

}
