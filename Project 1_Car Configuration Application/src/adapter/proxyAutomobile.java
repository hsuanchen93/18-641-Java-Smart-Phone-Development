/*
 * Hsuan Chen (hsuanc)
 */

package adapter;

import java.io.FileNotFoundException;
import exception.AutoException;
import model.*;
import util.FileIO;

public abstract class proxyAutomobile {
	private static Automobile a1;
	
	/* Constructor */
	public proxyAutomobile() {
		a1 = null;
	}
	
	/* Getter */
	public String getModelName() {
		if(a1!=null) {
			return a1.getName();
		}
		else {
			try {
				throw new AutoException(1, "Automobile Does Not Exist");
			} catch (AutoException e) { }
		}
		return null;
	}
	
	/* BuildAuto */
	public void BuildAuto(String filename) {
		try {
			a1 = new FileIO().buildAutoObject(filename);
		} catch (AutoException e) {
			//Fix exception 5: invalid filename
			if(e.getErrorno()==5) {
				FixAuto f = new BuildAuto();
				filename = f.fix(e.getErrorno());
				BuildAuto(filename);
			}
		} catch (FileNotFoundException e) { }
	}
	
	/* printAuto */
	public void printAuto(String modelName) {
		if(a1.getName().equals(modelName)) {
			a1.print();
		}
	}
	
	/* updateOptionSetName */
	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		if(a1.getName().equals(modelName)) {
			a1.updateOptionSet(optionSetName, newName);
		}
	}
	
	/* updateOptionPrice */
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, double newPrice) {
		if(a1.getName().equals(modelName)) {
			a1.updateOption(optionSetName, optionName, optionName, newPrice);
		}
	}
	
	/* fix (Fix Exceptions) */
	public String fix(int errno) {
		return exception.AutoException.fix(errno);
	}
	
}
