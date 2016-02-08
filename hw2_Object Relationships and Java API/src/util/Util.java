/*
 * Hsuan Chen (hsuanc)
 */

package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import exception.CustomException;
import model.Student;

public class Util {

	/* Method readFile */
	public static Student[] readFile(String filename, Student[] stu) 
													throws CustomException {
		int numStudent = 0;
		FileReader file = null;
		
		//reads the file and builds student array
		try {
			//open the file using FileReader Object
			file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			
			//check first line
			String line = buff.readLine();
			if(!line.equals("Stud Qu1 Qu2 Qu3 Qu4 Qu5")) {
				buff.close();
				file.close();
				throw new CustomException(2,"Invalid input!");
			}
			
			//read all lines in file with readLine method
			while(!eof) {
				line = buff.readLine();
				if(line == null) {
					eof = true;
				}
				else {
					//throw an exception when reading the 41st record
					if(numStudent==40) {
						buff.close();
						file.close();
						throw new CustomException(1,
											"There are more than 40 records!");
					}
					
					//tokenize each line using StringTokenizer Object
					int count = 0;
					String temp = null;
					int[] tempScores = new int[5];
					stu[numStudent] = new Student();
					StringTokenizer st = new StringTokenizer(line);
					while(st.hasMoreTokens()) {
						temp = st.nextToken(" ");
						int x = Integer.parseInt(temp);
						if(count==0) {
							stu[numStudent].setSID(x);
						}
						else {
							tempScores[count-1] = x;
						}
						count++;
					}
					stu[numStudent].setScores(tempScores);
					numStudent++;				
				}
			}
			buff.close();
			file.close();
		} catch(IOException e) {
			System.out.println("Error ­­ " + e.toString());
		}
		return stu;
	}

}
