package driver;

import model.Automotive;
import util.FileIO;

public class Driver {

	public static void main(String[] args) {
		
		/* case 1: regular FordZTW.txt */
		System.out.printf("<<<<<CASE 1: Regular FordZTW.txt>>>>>\n");
		//Build Automobile Object from a file.
		FileIO fileIO = new FileIO();
		Automotive FordZTW = fileIO.buildAutoObject("FordZTW.txt");
		//Print attributes before serialization
		FordZTW.print();
		//Serialize the object
		fileIO.serializeAuto(FordZTW, "auto.ser");
		//Deserialize the object and read it into memory.
		Automotive newFordZTW = fileIO.deserializeAuto("auto.ser");
		System.out.printf("-----After Serialization/Deserialization-----\n");
		newFordZTW.print();
		
		
		/* case 2: 0 Options for Color */
		System.out.printf("\n\n<<<<<CASE 2: 0 Options for Color>>>>>\n");
		//Build Automobile Object from a file.
		Automotive FordZTW_2 = fileIO.buildAutoObject("FordZTW_2.txt");
		//Print attributes before serialization
		FordZTW_2.print();
		//Serialize the object
		fileIO.serializeAuto(FordZTW_2, "auto_2.ser");
		//Deserialize the object and read it into memory.
		Automotive newFordZTW_2 = fileIO.deserializeAuto("auto_2.ser");
		System.out.printf("-----After Serialization/Deserialization-----\n");
		newFordZTW_2.print();
		
		
		/* case 3: 0 OptionSets for FordZTW */
		System.out.printf("\n\n<<<<<CASE 3: 0 OptionSets for FordZTW>>>>>\n");
		//Build Automobile Object from a file.
		Automotive FordZTW_3 = fileIO.buildAutoObject("FordZTW_3.txt");
		//Print attributes before serialization
		FordZTW_3.print();
		//Serialize the object
		fileIO.serializeAuto(FordZTW_3, "auto_3.ser");
		//Deserialize the object and read it into memory.
		Automotive newFordZTW_3 = fileIO.deserializeAuto("auto_3.ser");
		System.out.printf("-----After Serialization/Deserialization-----\n");
		newFordZTW_3.print();
		
		
		/* case 4: test update and delete */
		System.out.printf("\n\n<<<<<CASE 4: Test Update and Delete>>>>>\n");
		//Build Automobile Object from a file.
		Automotive FordZTW_4 = fileIO.buildAutoObject("FordZTW_2.txt");
		System.out.printf("-----Original-----\n");
		FordZTW_4.print();
		//update OptionSet and Option
		FordZTW_4.updateOptionSet("Brakes/Traction Control", "New Option", 2);
		FordZTW_4.setOption(FordZTW_4.getOptionSet(0), 0, "111111", 0);
		FordZTW_4.setOption(FordZTW_4.getOptionSet(0), 1, "222222", 1000);
		FordZTW_4.updateOption("Power Moonroof", "Not Present", "UPDATE", 50);
		System.out.printf("-----After Updating-----\n");
		FordZTW_4.print();
		//delete
		FordZTW_4.deleteOptionSet("New Option");
		FordZTW_4.deleteOption("Power Moonroof", "UPDATE");
		System.out.printf("-----After Deleting-----\n");
		FordZTW_4.print();
		
	}

}
