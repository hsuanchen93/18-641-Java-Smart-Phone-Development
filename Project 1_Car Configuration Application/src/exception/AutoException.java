/*
 * Hsuan Chen (hsuanc)
 * 
 * Exception 1: Automobile Does Not Exist
 * Exception 2: Missing base price for Automobile in text file
 * Exception 3: Missing OptionSet data
 * Exception 4: Missing Option data
 * Exception 5: Wrong filename
 * 
 */

package exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoException extends Exception {
	private int errorno;
	private String errormsg;
	
	/* Constructor */
	public AutoException() {
		super();
		errorno = 0;
		errormsg = null;
	}
	public AutoException(int errorno, String errormsg) {
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
		printmyproblem();
	}

	/* Getters */
	public int getErrorno() {
		return errorno;
	}
	public String getErrormsg() {
		return errormsg;
	}
	
	/* Setters */
	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	/* Print */
	public void printmyproblem() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		String message = formattedDate + ": AutoException [errorno=" + 
									errorno + ", errormsg=" + errormsg + "\n";
	    try {
			Files.write(Paths.get("ExceptionLog.txt"), message.getBytes(), 
													StandardOpenOption.APPEND);
		} catch (IOException e) {
			try {
				PrintWriter writer = new PrintWriter("ExceptionLog.txt", "UTF-8");
				writer.printf("%s",message);
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.printf("%s",message);
	}
	
	/* Fix Exceptions */
	public static String fix(int errno) {
		FixAutoException f = new FixAutoException();
		switch(errno) {
			case 2:	return f.fix2(errno);
			case 5: return f.fix5(errno);
		}
		return null;
	}
	
	/* List all possible Exceptions */
	public void allExceptions() {
		try {
			FileReader file = new FileReader("Exceptions.txt");
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
		
			while (!eof) {
				String line = buff.readLine();
				if (line == null) {
					eof = true;
				}
				else {
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
