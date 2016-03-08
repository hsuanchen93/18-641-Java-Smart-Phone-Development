/*
 * Hsuan Chen (hsuanc)
 */

package driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import adapter.BuildAuto;
import database.Database;
import server.Server;

public class Driver {

	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//create the tables in database
		new Database().createTables();
		//br.readLine();
	
		//add Automobile objects
		BuildAuto auto = new BuildAuto();
		auto.BuildAuto("ToyotaPrius.txt", 1);
		//br.readLine();
		auto.BuildAuto("LexusIS250.txt", 1);
		//br.readLine();
		auto.BuildAuto("FordFocus.txt", 1);
		//br.readLine();
		
		//test update OptionSet name (just one model using the OptionSet)
		auto.updateOptionSetName("IS250", "GPS", "NEW NAME");
		//br.readLine();
		//test update OptionSet name (more than one models using the OptionSet)
		auto.updateOptionSetName("Prius", "Transmission", "optionset");
		//br.readLine();
		//test update Option price (just one model using the Option)
		auto.updateOptionPrice("IS250", "NEW NAME", "Present", 1050);
		//br.readLine();
		//test update Option price (more than one models using the Option)
		auto.updateOptionPrice("Focus Wagon ZTW", "Power Moonroof", "Present", 777);
		//br.readLine();
		
		//delete the Automobile objects
		auto.deleteAuto("Focus Wagon ZTW");
		//br.readLine();
		auto.deleteAuto("Prius");
		//br.readLine();
		auto.deleteAuto("IS250");
		//br.readLine();
		
		new Database().deleteTables();
		
		//Server server = new Server();
		//server.acceptClient();
    }
	
}
