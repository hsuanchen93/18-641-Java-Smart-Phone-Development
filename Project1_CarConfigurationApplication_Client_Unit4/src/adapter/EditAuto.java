/*
 * Hsuan Chen (hsuanc)
 */

package adapter;

public interface EditAuto {
	/* Creates a new thread so can edit OptionSet's name concurrently */
	public void editOptionSetName(String modelName, String optionSetName, String newName);
	
	/* Creates a new thread so can edit Option's price concurrently */
	public void editOptionPrice(String modelName, String optionSetName, String optionName, double newPrice);

}
