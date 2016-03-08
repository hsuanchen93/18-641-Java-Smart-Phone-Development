/*
 * Hsuan Chen
 */

package database;

public interface DBConstants {
	boolean DEBUG = false;
	boolean RESULT = true;
	
	/* Automobile Table */
	String auto = "automobiles(id INT NOT NULL,"
							+ "make CHAR(30) NOT NULL,"
							+ "model CHAR(35) NOT NULL,"
							+ "price DOUBLE NOT NULL,"
							+ "PRIMARY KEY (id));";
	
	/* OptionSet Table */
	String optset = "optionsets(id INT NOT NULL,"
							+ "name CHAR(40) NOT NULL,"
							+ "PRIMARY KEY (id));";
	
	/* Option Table */
	String opt = "options(id INT NOT NULL,"
						+ "name CHAR(50) NOT NULL,"
						+ "price DOUBLE,"
						+ "PRIMARY KEY (id));";

	/* Model Table */
	String model = "models(autoid INT NOT NULL,"
						+ "optsetid INT NOT NULL,"
						+ "optid INT NOT NULL,"
						+ "FOREIGN KEY (autoid) REFERENCES automobiles(id),"
						+ "FOREIGN KEY (optsetid) REFERENCES optionsets(id),"
						+ "FOREIGN KEY (optid) REFERENCES options(id));";
}
