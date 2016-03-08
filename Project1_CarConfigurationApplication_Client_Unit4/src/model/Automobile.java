/*
 * Hsuan Chen (hsuanc)
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Automobile implements Serializable {
	private String make;
	private String model;
	private double baseprice;
	private ArrayList<OptionSet> optset;
	private ArrayList<OptionSet.Option> choice;
	
	/* Constructors */
	public Automobile() {
		make = null;
		model = null;
		baseprice = -1;
		optset = null;
		choice = null;
	}
	public Automobile(String make, String model, double baseprice) {
		this.make = make;
		this.model = model;
		this.baseprice = baseprice;
		optset = new ArrayList<OptionSet>();
		choice = new ArrayList<OptionSet.Option>();
	}
	
	/* Getters */
	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}
	public double getBasePrice() {
		return baseprice;
	}
	public OptionSet getOptionSet(int index) {
		if(index < optset.size() && index >= 0) {
			return optset.get(index);
		}
		return null;
	}
	public String getOptionChoice(String optsetName) {
		if(findOptionSet(optsetName).equals(optsetName)) {
			return optset.get(findOptionSetIndex(optsetName)).getOptionChoice().getName();
		}
		return null;
	}
	public double getOptionChoicePrice(String optsetName) {
		if(findOptionSet(optsetName).equals(optsetName)) {
			return optset.get(findOptionSetIndex(optsetName)).getOptionChoice().getPrice();
		}
		return -1;
	}
	public String getOptionSetName(int index) {
		if(index < optset.size() && index >= 0) {
			return optset.get(index).getName();
		}
		return null;
	}
	public ArrayList<String> getOptionNames(int optsetIndex) {
		ArrayList<String> result = new ArrayList<String>();
		if(optsetIndex < optset.size() && optsetIndex >= 0) {
			for(int i=0; i<optset.get(optsetIndex).getOption().size(); i++) {
				result.add(optset.get(optsetIndex).getOption().get(i).getName());
			}
			return result;
		}
		return null;
	}
	public ArrayList<Double> getOptionPrice(int optsetIndex) {
		ArrayList<Double> result = new ArrayList<Double>();
		if(optsetIndex < optset.size() && optsetIndex >= 0) {
			for(int i=0; i<optset.get(optsetIndex).getOption().size(); i++) {
				result.add(optset.get(optsetIndex).getOption().get(i).getPrice());
			}
			return result;
		}
		return null;
	}
	
	/* Setters */
	public void setMake(String make) {
		this.make = make;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setBasePrice(double baseprice) {
		this.baseprice = baseprice;
	}
	public void setOptionSet(String name) {
		optset.add(new OptionSet(name));
	}
	public void setOption(OptionSet optset, String name, double price) {
		optset.setOption(name, price);
	}
	public void setOptionChoice(String optsetName, String optName) {
		if(findOptionSet(optsetName).equals(optsetName)) {
			if(findOption(optsetName,optName).equals(optName)) {
				optset.get(findOptionSetIndex(optsetName)).setOptionChoice(optName);
				choice.add(optset.get(findOptionSetIndex(optsetName)).getOptionChoice());
			}
		}
	}
	
	/* Delete - delete the item */
	public void deleteOptionSet(String name) {
		if(findOptionSet(name).equals(name)) {
			//delete option set from choice if it exists
			String opC = optset.get(findOptionSetIndex(name)).getOptionChoice().getName();
			if(opC!=null) {
				choice.remove(findOptionChoiceIndex(opC));
			}
			//delete option set
			optset.remove(findOptionSetIndex(name));
		}
	}
	public void deleteOption(String nameOptionSet, String nameOption) {
		if(findOption(nameOptionSet,nameOption).equals(nameOption)) {
			//delete option from choice if it exists
			if(findOptionChoice(nameOption).equals(nameOption)) {
				choice.remove(findOptionChoiceIndex(nameOption));
			}
			//delete option
			optset.get(findOptionSetIndex(nameOptionSet)).getOption().remove(findOptionIndex(nameOptionSet,nameOption));
		}
	}
	
	/* Find - return name if item exists and empty string if not present */
	/* FindIndex - return index */
	public String findOptionSet(String name) {
		for(int i=0; i<optset.size(); i++) {
			if(optset.get(i).getName().equals(name)) {
				return name;
			}
		}
		return "";
	}
	public int findOptionSetIndex(String name) {
		for(int i=0; i<optset.size(); i++) {
			if(optset.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	public String findOption(String nameOptionSet, String nameOption) {
		for(int i=0; i<optset.size(); i++) {
			if(optset.get(i).getName().equals(nameOptionSet)) {
				for(int j=0; j<(optset.get(i).getOption().size()); j++) {
					if(optset.get(i).getOption(j).getName().equals(nameOption)) {
						return nameOption;
					}
				}
			}
		}
		return "";
	}
	public int findOptionIndex(String nameOptionSet, String nameOption) {
		for(int i=0; i<optset.size(); i++) {
			if(optset.get(i).getName().equals(nameOptionSet)) {
				for(int j=0; j<(optset.get(i).getOption().size()); j++) {
					if(optset.get(i).getOption(j).getName().equals(nameOption)) {
						return j;
					}
				}
			}
		}
		return -1;
	}
	public String findOptionChoice(String name) {
		for(int i=0; i<choice.size(); i++) {
			if(choice.get(i).getName().equals(name)) {
				return name;
			}
		}
		return "";
	}
	public int findOptionChoiceIndex(String name) {
		for(int i=0; i<optset.size(); i++) {
			if(choice.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	/* Update - change original OptionSet or Option properties */
	public void updateOptionSet(String nameOld, String nameNew) {
		if(findOptionSet(nameOld).equals(nameOld)) {
			optset.get(findOptionSetIndex(nameOld)).setName(nameNew);
		}
	}
	public void updateOption(String optionSet, String nameOld, String nameNew, double priceNew) {
		if(findOption(optionSet,nameOld).equals(nameOld)) {
			//update choice with new name and price
			if(findOptionChoice("nameOld").equals(nameOld)) {
				choice.get(findOptionChoiceIndex("nameOld")).setName(nameNew);
				choice.get(findOptionChoiceIndex("nameOld")).setPrice(priceNew);
			}
			//update option
			optset.get(findOptionSetIndex(optionSet)).getOption(findOptionIndex(optionSet,nameOld)).setPrice(priceNew);
			optset.get(findOptionSetIndex(optionSet)).getOption(findOptionIndex(optionSet,nameOld)).setName(nameNew);
		}
	}
	
	/* Total Price - return the total price of Automobile */
	public double getTotalPrice() {
		double price = baseprice;
		for(int i=0; i<choice.size(); i++) {
			if(choice.get(i)!=null) {
				price += choice.get(i).getPrice();
			}
		}
		return price;	
	}
	
	/* Print - print out Automotive information */
	public void print() {
		if(make!=null && model!=null) {
			System.out.printf("Make: %s\nModel: %s\nBase Price: $%.2f\n", 
														make, model, baseprice);
			for(int i=0; i<optset.size(); i++) {
				System.out.printf("Option %d: %s\n", i+1, optset.get(i).getName());
				for(int j=0; j<optset.get(i).getOption().size(); j++) {
					System.out.printf("   %s: $%.2f\n", 
										optset.get(i).getOption(j).getName(), 
										optset.get(i).getOption(j).getPrice());
				}
			}
		}
	}
	
	/* PrintChoice - print out Automotive choices */
	public void printChoice() {
		if(make!=null && model!=null) {
			System.out.printf("Make: %s\nModel: %s\nBase Price: $%.2f\n", 
														make, model, baseprice);
			for(int i=0; i<choice.size(); i++) {
				System.out.printf("Option %d - %s: %s($%.2f)\n", i+1, optset.get(i).getName(),
					choice.get(i).getName(), choice.get(i).getPrice());
			}
		}
	}

}
