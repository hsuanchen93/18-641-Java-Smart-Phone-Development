/*
 * Hsuan Chen (hsuanc)
 */

package adapter;

public interface CreateAuto {
	/* Given a text file name to build an instance of Automobile */ 
	/* This method does not have to return the Auto instance */
	public void BuildAuto(String filename, int fileType);
	
	/* This function searches and prints the properties of a given Automodel */
	public void printAuto(String modelName);
}
