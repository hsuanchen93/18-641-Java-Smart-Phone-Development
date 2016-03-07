/*
 * Hsuan Chen (hsuanc)
 * 
 * Uses synchronized(Automobile a1) in EditOptions to achieve synchronization
 * because whenever a new thread is created to edit the instance, need to
 * secure the "lock" before making changes so other threads cannot access the 
 * static instance and make changes at the same time.
 * 
 */

package scale;

import model.Automobile;

public class EditOptions extends Thread {
	private Automobile a1;
	private String method;
	private String[] args;
	
	/* Constructor */
	public EditOptions(Automobile a1, String method, String[] args) {
		this.a1 = a1;
		this.method = method;
		this.args = args;
	}

	/* run - call on different edit methods */
	/* 1. editOptionSetName */
	/* 2. editOptionPrice */
	@Override
	public void run() {
		switch(method) {
			case "editOptionSetName": 
				editOptionSetName();
				break;
			case "editOptionPrice":	
				editOptionPrice();
				break;
			default: 
				System.out.println("No Such Edit Method: " + method);
				break;
		}
	}
	
	private void editOptionSetName() {
		//args = {optionSetName, newName}
		synchronized (a1) {
			a1.updateOptionSet(args[0], args[1]);
		}
	}
	
	private void editOptionPrice() {
		//args = {optionSetName, optionName, String.valueOf(newPrice)}
		synchronized (a1) {
			a1.updateOption(args[0], args[1], args[1], Double.parseDouble(args[2]));
		}
	}
	
	
}
