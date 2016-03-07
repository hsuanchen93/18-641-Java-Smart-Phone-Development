/*
 * Hsuan Chen (hsuanc)
 */

package server;

import java.util.ArrayList;
import java.util.Properties;

import model.Automobile;

public interface AutoServer {
	/* Accept properties object from client socket over an ObjectStream and */
	/* create an Automobile and add it to the LinkedHashMap */
	public void buildAutoProperties(String filename, Properties props);

	/* Provide a list of available models */
	public ArrayList<String> modelList();
	
	/* Send the user selected Automobile */
	public Automobile sendAuto(String model);
}
