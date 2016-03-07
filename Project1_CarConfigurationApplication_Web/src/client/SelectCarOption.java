/*
 * Hsuan Chen (hsuanc)
 */

package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import adapter.BuildAuto;
import adapter.UpdateAuto;
import model.Automobile;

public class SelectCarOption {
	private ArrayList<String> list;
	private String model;
	private BuildAuto auto;
	
	/* Constructor */
	public SelectCarOption(ArrayList<String> list) {
		this.list = list;
		model = null;
		auto = new BuildAuto();
	}
	
	/* Getter */
	public String getModel() {
		return model;
	}
	
	/* selectModel */
	/* Allows the user to select a model */
	public void selectModel() {
		//prompt user to select model
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("List of Available Models:");
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.printf("Select Model: ");
		try {
			model = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* selectAuto */
	public void selectAuto(Automobile auto) {
		if(auto!=null) {
			this.auto.addAuto(auto);
		}
		else {
			System.out.printf("\"%s\" model doesn't exist\n", model);
			model = null;
		}
	}
	
	/* configAuto */
	/* Allows the user to enter its respective options */
	/* Displays the selected options for a class */
	public void configAuto() {
		if(model!=null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int option = 0;
			while(option!=4) {
				printConfigMenu();
				try {
					option = Integer.parseInt(br.readLine());
					switch(option) {
						/* List all possible options */
						case 1:
							auto.printAuto(model);
							break;
						/* Select an option */
						case 2:
							System.out.printf("Enter OptionSet: ");
							String optsetName = br.readLine();
							System.out.printf("Enter Option: ");
							String optName = br.readLine();
							UpdateAuto autoU = new BuildAuto();
							autoU.setOptionChoice(model, optsetName, optName);
							break;
						/* Display selected options */
						case 3:
							auto.printChoices(model);
							System.out.printf("Total Price: $%.2f\n", auto.getTotalPrice(model));
							break;
						/* Exit */
						case 4:
							break;
						default:
							System.out.printf("No option %d\n",option);
							break;
					}
				} catch (NumberFormatException e) {
		            System.out.println("Invalid option. Please enter valid number."); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return ;
	}
	
	/* printConfigMenu */
	/* Prints out the Configure Menu */
	public void printConfigMenu() {
		System.out.println("Configure Menu:");
		System.out.println("1) List all possible options");
		System.out.println("2) Select an option");
		System.out.println("3) Display selected options");
		System.out.println("4) Exit");
		System.out.printf("Select option: ");
	}

}
