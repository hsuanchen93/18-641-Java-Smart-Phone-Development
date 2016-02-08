/*
 * Hsuan Chen (hsuanc)
 */

package driver;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.UpdateAuto;
import exception.AutoException;

public class Driver {

	public static void main(String[] args) {
		/* Test API */
		/* Creates an Auto instance based on given FordZTW */
		/* Updates "Color" to "NEW COLOR" and price to be "100.50" */
		testAPI();
		
		/* List out all Custom Exceptions */
		System.out.println("<-----Custom Exceptions List----->");
		new AutoException().allExceptions();
		System.out.println("<-----DONE Custom Exceptions List----->\n");
		
		/* Test Exception 1: Automobile Does Not Exist */
		testException1();
		
		/* Test Exception 2: Missing base price for Automobile in text file */
		testException2();
		
		/* Test Exception 3: Missing OptionSet data */
		testException3();
		
		/* Test Exception 4: Missing Option data */
		testException4();
		
		/* Test Exception 5: Wrong filename */
		testException5();
	}
	
	public static void testAPI() {
		System.out.println("<-----Testing API (Create Auto Instance)----->");
		CreateAuto autoC = new BuildAuto();
		UpdateAuto autoU = new BuildAuto();
		autoC.BuildAuto("FordZTW.txt");
		autoC.printAuto(autoC.getModelName());
		System.out.println("<-----Testing API (Update Auto Instance)----->");
		autoU.updateOptionSetName(autoC.getModelName(), "Color", "NEW COLOR");
		autoU.updateOptionPrice(autoC.getModelName(), "NEW COLOR", 
								"Fort Knox Gold Clearcoat Metallic", 100.50);
		autoC.printAuto(autoC.getModelName());
		System.out.println("<-----DONE Testing API----->\n");
	}
	
	public static void testException1() {
		System.out.println("<-----Testing Exception 1----->");
		CreateAuto autoC = new BuildAuto();
		autoC.getModelName();
		System.out.println("<-----DONE Testing Exception 1----->\n");
	}
	
	public static void testException2() {
		System.out.println("<-----Testing Exception 2----->");
		CreateAuto autoC = new BuildAuto();
		autoC.BuildAuto("FordZTW_2.txt");
		autoC.printAuto(autoC.getModelName());
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
		autoC.BuildAuto("FordZTW_5.txt");
		autoC.printAuto(autoC.getModelName());
		System.out.println("<-----DONE Testing Exception 5----->\n");
	}

}
