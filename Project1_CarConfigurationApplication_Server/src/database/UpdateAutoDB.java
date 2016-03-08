/*
 * Hsuan Chen (hsuanc)
 */

package database;

public interface UpdateAutoDB {
	/* Update the name of OptionSet to "newName" */
	public void updateOptionSetNameDB(String modelName, String optionSetName, String newName);
	
	/* Update the price of Option to "newPrice" */
	public void updateOptionPriceDB(String modelName, String optionSetName, String optionName, double oldPrice, double newPrice);
}
