/*
 * Hsuan Chen (hsuanc)
 */

package util;

import java.io.*;
import java.util.Properties;
import java.util.StringTokenizer;
import exception.AutoException;
import model.Automobile;

public class FileIO {
	
	/* buildAutoObjectProperties - create Automobile from Properties file */
	public Automobile buildAutoObjectProperties(String filename) {
		try {
			//loads props from file
			Properties props = new Properties();
			FileInputStream in = new FileInputStream(filename);
			props.load(in);
			
			//instantiate Automobile
			Automobile auto = null;
			String CarMake = props.getProperty("CarMake");
			String CarModel = props.getProperty("CarModel");
			double baseprice = Double.parseDouble(props.getProperty("BasePrice"));
			auto = new Automobile(CarMake, CarModel, baseprice);
			
			//read properties to get OptionSets and Options
			if(auto!=null) {
				int optionNum = 1;
				String option = "Option1";
				int optionValueNum = 97;
				String optionValue = "OptionValue1a";
				String optionPrice = "OptionPrice1a";
				//get OptionSets
				while(props.getProperty(option)!=null) {
					auto.setOptionSet(props.getProperty(option));
					//get Options and Options.price
					while(props.getProperty(optionValue)!=null) {
						auto.setOption(auto.getOptionSet(optionNum-1), props.getProperty(optionValue), Double.parseDouble(props.getProperty(optionPrice)));
						optionValueNum++;
						optionValue = optionValue.substring(0, optionValue.length()-1);
						optionValue += Character.toString((char)optionValueNum);
						optionPrice = optionPrice.substring(0, optionPrice.length()-1);
						optionPrice += Character.toString((char)optionValueNum);
					}
					
					//get Strings for next OptionSet and Options
					optionNum++;
					optionValueNum = 97;
					String numLast = Integer.toString(optionNum-1);
					String num = Integer.toString(optionNum);
					option = option.replace(numLast, num);
					optionValue = optionValue.replace(numLast, num);
					optionPrice = optionPrice.replace(numLast, num);
					optionValue = optionValue.substring(0, optionValue.length()-1);
					optionValue += Character.toString((char)optionValueNum);
					optionPrice = optionPrice.substring(0, optionPrice.length()-1);
					optionPrice += Character.toString((char)optionValueNum);
				}
			}
			return auto;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/* buildAutoObject - create Automobile from file */
	public Automobile buildAutoObject(String filename) throws AutoException, FileNotFoundException {
		Automobile auto = null;
		double baseprice = 0;
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			
			//read first 4 lines to get Automotive basic info
			//make
			String delims = "[:$]+";
			String line = buff.readLine();
			String[] make = line.split(delims);
			//model
			line = buff.readLine();
			String[] model = line.split(delims);
			//baseprice
			line = buff.readLine();
			String[] price = line.split(delims);
			//OptionSet
			delims = "[()]+";
			line = buff.readLine();
			String[] size = line.split(delims);
			do {
				try {
					baseprice = Double.parseDouble(price[1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					//throw new AutoException(2, "Missing Base Price");
					//fix missing base price exception
					AutoException autoE = new AutoException(2, "Missing Base Price");
					baseprice = Double.parseDouble(autoE.fix(2));
				}
			} while(baseprice==0);
			auto = new Automobile(make[1], model[1], baseprice);
			
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
							auto.setOptionSet(token.substring(1));
							//auto.setOptionSet(optset, token.substring(1), optTotal);
							if(st.hasMoreTokens()) {
								token = st.nextToken();
								//create Options
								opt = 0;
								while(true) {
									auto.setOption(auto.getOptionSet(optset), token, Integer.parseInt(st.nextToken()));
									//auto.setOption(auto.getOptionSet(optset), opt, token, Integer.parseInt(st.nextToken()));
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
