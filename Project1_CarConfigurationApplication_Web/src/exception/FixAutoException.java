/*
 * Hsuan Chen (hsuanc)
 * 
 * Fix Exception 2: Missing base price for Automobile in text file
 * Fix Exception 5: Missing filename or wrong filename
 * 
 */

package exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FixAutoException {
	
	/* Fix Exception 2 */
	public String fix2(int errno) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.printf("Enter Base Price: ");
        try{
            double baseprice = Double.parseDouble(br.readLine());
            return String.valueOf(baseprice);
        }catch(NumberFormatException e){
            System.out.println("Invalid Base Price");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return String.valueOf(0);
	}
	
	/* Fix Exception 5 */
	public String fix5(int errno) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.printf("Enter Correct File Name: ");
		try {
			String filename = br.readLine();
			return filename;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        return null;
	}

}
