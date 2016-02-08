/*
 * Hsuan Chen (hsuanc)
 */

package adapter;

public interface UpdateAuto {
	/* This function searches the Model for a given OptionSet */
	/* and sets the name of OptionSet to newName */
	public void updateOptionSetName(String modelName, String optionSetName, String newName);
	
	/* This function searches the Model for a given OptionSet and Option name */
	/* and sets the price to newPrice */
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, double newPrice);
	
	/* Delete - delete the item */
	public void deleteOptionSet(String modelName, String optionSetName);
	public void deleteOption(String modelName, String optionSetName, String optionName);
	
	/* Set Option as choice */
	public void setOptionChoice(String modelName, String optsetName, String optName);
}
