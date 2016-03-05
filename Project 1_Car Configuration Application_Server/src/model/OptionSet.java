/*
 * Hsuan Chen (hsuanc)
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable {
	private String name;
	private ArrayList<Option> opt;
	private Option choice;
	
	/* Constructors - OptionSet */
	protected OptionSet() {
		name = null;
		opt = null;
		choice = new Option();
	}
	protected OptionSet(String name) {
		this.name = name;
		opt = new ArrayList<Option>();
		choice = new Option();
	}
	
	/* Inner Class - Option */
	protected class Option implements Serializable {
		private String name;
		private double price;
		
		/* Constructor - Option */
		protected Option() {
			name = null;
			price = -1;
		}
		protected Option(String name, double price) {
			this.name = name;
			this.price = price;
		}
		
		/* Getters - Option */
		protected String getName() {
			return name;
		}
		protected double getPrice() {
			return price;
		}
		
		/* Setters - Option */
		protected void setName(String name) {
			this.name = name;
		}
		protected void setPrice(double price) {
			this.price = price;
		}
	}
	
	/* Getters - OptionSet */
	protected String getName() {
		return name;
	}
	protected ArrayList<Option> getOption() {
		return opt;
	}
	protected Option getOption(int index) {
		if(index < opt.size() && index >= 0) {
			return opt.get(index);
		}
		return null;
	}
	protected Option getOptionChoice() {
		return choice;
	}
	
	/* Setters - OptionSet */
	protected void setName(String name) {
		this.name = name;
	}
	protected void setOption(String name, double price) {
		opt.add(new Option(name,price));
	}
	protected void setOptionChoice(String name) {
		for(int i=0; i<opt.size(); i++) {
			if(opt.get(i).getName().equals(name)) {
				choice.setName(name);
				choice.setPrice(opt.get(i).getPrice());
			}
		}
	}
	
}
