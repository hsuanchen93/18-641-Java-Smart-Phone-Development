/*
 * Hsuan Chen (hsuanc)
 */

package client;

import java.util.ArrayList;

import model.Automobile;

public interface SocketClientInterface {
	public boolean openConnection();
    public void handleSession();
    public void closeSession();
    
    /* Unit 5 */
    /* Retrieve all available models from server */
    public ArrayList<String> getAvailableModels();
    /* Retrieve all available options */
    public String getAvailableOptionSets(String model);
}
