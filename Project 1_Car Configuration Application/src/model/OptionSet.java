package model;

import java.io.Serializable;

public class OptionSet implements Serializable {
	private String name;
	private Option opt[];
	
	/* Constructors - OptionSet */
	protected OptionSet() {
		name = null;
		opt = null;
	}
	protected OptionSet(String name, int size) {
		this.name = name;
		opt = new Option[size];
		for(int i=0; i<opt.length; i++) {
			opt[i] = new Option();
		}
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
	protected Option[] getOption() {
		return opt;
	}
	protected Option getOption(int index) {
		return opt[index];
	}
	
	/* Setters - OptionSet */
	protected void setName(String name) {
		this.name = name;
	}
	protected void setOption(Option[] opt) {
		this.opt = opt;
	}
	
}
