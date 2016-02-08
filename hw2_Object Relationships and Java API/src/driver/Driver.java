/*
 * Hsuan Chen (hsuanc)
 */

package driver;

import exception.CustomException;
import model.Statistics;
import model.Student;
import util.Util;

public class Driver {

	public static void main(String[] args) throws CustomException {
		String filename = null;
		String[] info = new String[3];
		info[0] = "case 1: given input";
		info[1] = "case 2: input with more than 40 students";
		info[2] = "case 3: invalid input";
	
		/* run 3 tests */
		/* case 1: given input */
		/* case 2: input with more than 40 students */
		/* case 3: invalid input */
		for(int j=0; j<3; j++) {
			
			filename = "input_"+ Integer.toString(j+1) + ".txt";
			System.out.printf("%s\nfilename: %s\n", info[j], filename);
			
			Student lab2[] = new Student[40]; //Populate the student array
			try {
				lab2 = Util.readFile(filename, lab2);
			} catch(CustomException e) {
				System.out.printf("%s\n",e.getLocalizedMessage());
				//if invalid input, break
				if(e.getErrorno()==2) {
					break;
				}
			}
			
			Statistics statlab2 = new Statistics();
			statlab2.findHigh(lab2);
			statlab2.findLow(lab2);
			statlab2.findAvg(lab2);
			
			System.out.printf("%-11s%8s%8s%8s%8s%8s\n", "Stud", "Q1", "Q2", 
					"Q3", "Q4", "Q5");
			for(int i=0; i<lab2.length; i++) {
				if(lab2[i]!=null) {
					lab2[i].printInfo();
				}
				else {
					break;
				}
			}
			statlab2.printInfo();
			System.out.print("\n\n");
		}
	}
}
