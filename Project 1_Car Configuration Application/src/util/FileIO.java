package util;

import java.io.*;
import java.util.StringTokenizer;

import model.Automotive;

public class FileIO {
	
	/* buildAutoObject - create an automotive model from file */
	public Automotive buildAutoObject(String filename) {
		Automotive auto = null;
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
			auto = new Automotive(info[0], Double.parseDouble(info[1]), Integer.parseInt(size[1]));
			
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
							auto.setOptionSet(optset, token.substring(1), Integer.parseInt(st.nextToken()));
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
		} catch (IOException e) {
			System.out.println("Error ­­ " + e.toString()); 
		}
		return auto;
	}
	
	/* serializeAuto - serialization of an automotive model */
	public void serializeAuto(Automotive auto, String filename) {
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
	public Automotive deserializeAuto(String filename) {
		Automotive auto = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			auto = (Automotive)in.readObject();
		} catch(Exception e) {
			System.out.print("Error:"+e);
			System.exit(1);
		}
		return auto;
	}

}
