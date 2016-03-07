/*
 * Hsuan Chen (hsuanc)
 */

package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CarModelOptionsIO {
	
	/* sendProperties - create and send Properties object to server from file */
	public Properties sendProperties(String filename) {
		Properties props = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(filename);
			//loads the entire file in memory
			props.load(in);
			return props;
		} catch (FileNotFoundException e) {
			System.out.printf("\"%s\" Not Found\n",filename);
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
