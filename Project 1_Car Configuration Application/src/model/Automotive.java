package model;

import java.io.Serializable;

public class Automotive implements Serializable {
	private String name;
	private double baseprice;
	private OptionSet optset[];
	
	/* Constructors */
	public Automotive() {
		name = null;
		baseprice = -1;
		optset = null;
	}
	public Automotive(String name, double baseprice) {
		this.name = name;
		this.baseprice = baseprice;
		optset = null;
	}
	public Automotive(String name, int size) {
		this.name = name;
		baseprice = -1;
		optset = new OptionSet[size];
		for(int i=0; i<optset.length; i++) {
			optset[i] = new OptionSet();
		}
	}
	public Automotive(String name, double baseprice, int size) {
		this.name = name;
		this.baseprice = baseprice;
		optset = new OptionSet[size];
		for(int i=0; i<optset.length; i++) {
			optset[i] = new OptionSet();
		}
	}
	
	/* Getters */
	public String getName() {
		return name;
	}
	public double getBasePrice() {
		return baseprice;
	}
	public OptionSet getOptionSet(int index) {
		return optset[index];
	}
	
	/* Setters */
	public void setName(String name) {
		this.name = name;
	}
	public void setBasePrice(double baseprice) {
		this.baseprice = baseprice;
	}
	public void setOptionSet(int index, String name, int size) {
		optset[index] = new OptionSet(name, size);
	}
	public void setOption(OptionSet optset, int index, String name, double price) {
		optset.getOption(index).setName(name);
		optset.getOption(index).setPrice(price);
	}
	
	/* Delete - delete the item (replace with empty string) */
	public void deleteOptionSet(String name) {
		if(findOptionSet(name).equals(name)) {
			for(int i=0; i<optset.length; i++) {
				if(optset[i].getName().equals(name)) {
					optset[i].setName("");
					optset[i].setOption(null);
					return ;
				}
			}
		}
	}
	public void deleteOption(String nameOptionSet, String nameOption) {
		if(findOption(nameOptionSet,nameOption).equals(nameOption)) {
			for(int i=0; i<optset.length; i++) {
				if(optset[i].getName().equals(nameOptionSet)) {
					for(int j=0; j<optset[i].getOption().length; j++) {
						if(optset[i].getOption(j).getName().equals(nameOption)) {
							optset[i].getOption(j).setName("");
							optset[i].getOption(j).setPrice(-1);
							return ;
						}
					}
				}
			}
		}
	}
	
	/* Find - return name if item exists and empty string if not present */
	public String findOptionSet(String name) {
		for(int i=0; i<optset.length; i++) {
			if(optset[i].getName().equals(name)) {
				return name;
			}
		}
		return "";
	}
	public String findOption(String nameOptionSet, String nameOption) {
		for(int i=0; i<optset.length; i++) {
			if(optset[i].getName().equals(nameOptionSet)) {
				for(int j=0; j<(optset[i].getOption().length); j++) {
					if(optset[i].getOption(j).getName().equals(nameOption)) {
						return nameOption;
					}
				}
			}
		}
		return "";
	}
	
	/* Update - change original OptionSet or Option properties */
	public void updateOptionSet(String nameOld, String nameNew, int optSize) {
		if(findOptionSet(nameOld).equals(nameOld)) {
			for(int i=0; i<optset.length; i++) {
				if(optset[i].getName().equals(nameOld)) {
					optset[i] = new OptionSet(nameNew, optSize);
				}
			}
		}
	}
	public void updateOption(String optionSet, String nameOld, String nameNew, double priceNew) {
		if(findOption(optionSet, nameOld).equals(nameOld)) {
			for(int i=0; i<optset.length; i++) {
				for(int j=0; j<(optset[i].getOption().length); j++) {
					if(optset[i].getOption(j).getName().equals(nameOld)) {
						optset[i].getOption(j).setName(nameNew);
						optset[i].getOption(j).setPrice(priceNew);
					}
				}
			}
		}
	}
	
	/* Print - print out Automotive information */
	public void print() {
		if(name!=null) {
			System.out.printf("Name: %s\nBase Price: $%.2f\n", name, baseprice);
			int num = 0;
			for(int i=0; i<optset.length; i++) {
				if(!optset[i].getName().equals("")) {
					num++;
					System.out.printf("Option %d: %s\n", num, optset[i].getName());
					for(int j=0; j<optset[i].getOption().length; j++) {
						if(!optset[i].getOption(j).getName().equals("")) {
							System.out.printf("   %s: $%.2f\n", optset[i].getOption(j).getName(), optset[i].getOption(j).getPrice());
						}
					}
				}	
			}
		}
	}

}
