/*
 * Hsuan Chen (hsuanc)
 */

package adapter;

public interface InfoAuto {
	/* modelExist - return true if model exists */
	public boolean modelExist(String modelName);
	
	/* Print - print all available models */
	public void printAutoHashMap();
	
	/* PrintChoices - print all choices of a model */
	public void printChoices(String modelName);
	
	/* return total price of automobile */
	public double getTotalPrice(String modelName);
}
