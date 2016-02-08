/*
 * Hsuan Chen (hsuanc)
 */

package util;

import java.io.*;
import java.util.StringTokenizer;
import exception.AutoException;
import model.Automobile;

public class FileIO {
	
	/* buildAutoObject - create an automotive model from file */
	public Automobile buildAutoObject(String filename) throws AutoException, FileNotFoundException {
		Automobile auto = null;
		double baseprice = 0;
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			
			//read first 2 lines to get Automotive basic info
			String line = buff.readLine();
			String delims = "[($)]+";
			String[] info = line.split(delims);
			line = buff.readLine();
			delims = "[()]+";
			String[] size = line.split(delims);
			do {
				try {
					baseprice = Double.parseDouble(info[1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					//throw new AutoException(2, "Missing Base Price");
					//fix missing base price exception
					AutoException autoE = new AutoException(2, "Missing Base Price");
					baseprice = Double.parseDouble(autoE.fix(2));
				}
			} while(baseprice==0);
			auto = new Automobile(info[0], baseprice, Integer.parseInt(size[1]));
			
			//parse OptionSet and Option
			int optset = -1;
			int opt;
			while (!eof) {
				line = buff.readLine();
				if (line == null) {
					eof = true;
				}
				else {
					StringTokenizer st = new StringTokenizer(line,"():$,");
					//create OptionSet
					if(st.hasMoreTokens()) {
						String token = st.nextToken();
						if(token.charAt(0)=='>') {
							optset++;
							int optTotal = Integer.parseInt(st.nextToken());
							auto.setOptionSet(optset, token.substring(1), optTotal);
							if(st.hasMoreTokens()) {
								token = st.nextToken();
								//create Options
								opt = 0;
								while(true) {
									auto.setOption(auto.getOptionSet(optset), opt, token, Integer.parseInt(st.nextToken()));
									if(st.hasMoreTokens()) {
										token = st.nextToken();
									}
									else {
										if((opt+1) != optTotal) {
											throw new AutoException(4, "Missing Option Data");
										}
										break;
									}
									opt++;
								}
							}
						}
					}
				}
			}
			//close buff and file afterwards
			buff.close();
			file.close();
			
			//Check if missing OptionSet data
			if((optset+1) != Integer.parseInt(size[1])) {
				throw new AutoException(3, "Missing OptionSet Data");
			}
			
		} catch (IOException e) {
			if(e.toString().split(":")[0].equals("java.io.FileNotFoundException")) {
				throw new AutoException(5, "File Not Found");
			}
		}
		return auto;
	}
	
	/* serializeAuto - serialization of an automotive model */
	public void serializeAuto(Automobile auto, String filename) {
		try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
				out.writeObject(auto);
				out.close();
			} catch(Exception e) {
				System.out.print("Error:"+e);
				System.exit(1);
			}
	}
	
	/* deserializeAuto - deserialization of an automotive model */
	public Automobile deserializeAuto(String filename) {
		Automobile auto = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			auto = (Automobile)in.readObject();
		} catch(Exception e) {
			System.out.print("Error:"+e);
			System.exit(1);
		}
		return auto;
	}

}
