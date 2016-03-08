/*
 * Hsuan Chen (hsuanc)
 */

package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import model.Automobile;

public class Database implements CreateAutoDB, UpdateAutoDB, DeleteAutoDB, DBConstants {
	ArrayList<String> rows;
	ArrayList<String> cols;

	/* createTables - create tables */
	public void createTables() {
		//create automobiles, optionsets, options, and models tables
		JDBCAdapter jdbc = new JDBCAdapter();
		jdbc.executeQuery("CREATE TABLE "+auto);
		jdbc.executeQuery("CREATE TABLE "+optset);
		jdbc.executeQuery("CREATE TABLE "+opt);
		jdbc.executeQuery("CREATE TABLE "+model);
		jdbc.close();
	}
	
	/* deleteTables - delete all the tables */
	public void deleteTables() {
		//create automobiles, optionsets, options, and models tables
		JDBCAdapter jdbc = new JDBCAdapter();
		jdbc.executeQuery("DROP TABLE IF EXISTS models;");
		jdbc.executeQuery("DROP TABLE IF EXISTS automobiles, options, optionsets;");
		jdbc.close();
	}
	
	/* createAutoDB - add Automobile to DB */
	@Override
	public void createAutoDB(Automobile auto) {
		JDBCAdapter jdbc = new JDBCAdapter();
		String query, query2, query3, select;
		
		//insert into automobiles
		int autoid = getID(jdbc,"automobiles");
		query = "INSERT INTO automobiles (id, make, model, price) VALUES (";
		query+= Integer.toString(autoid)+",'"+auto.getMake()+"','"
				+auto.getModel()+"',"+Double.toString(auto.getBasePrice())+");";
		jdbc.executeQuery(query);
		
		//insert into optionsets and options
		int i=0;	
		while(auto.getOptionSet(i)!=null) {
			//get next available optsetid
			int optsetid = getID(jdbc,"optionsets");
			//if a row with same OptionSet name doesn't exists, INSERT
			select = "SELECT * FROM optionsets WHERE name='"+auto.getOptionSetName(i)+"';";
			getResult(jdbc.selectQuery(select));
			if(rows.size()==0) {
				query = "INSERT INTO optionsets (id, name) VALUES (";
				query+= Integer.toString(optsetid)+",'"+auto.getOptionSetName(i)+"');";
				jdbc.executeQuery(query);
			}
			//a row with same OptionSet name already exist, get id
			else {
				optsetid = Integer.parseInt(rows.get(0).split("[(,)]+")[1]);
			}
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<Double> prices = new ArrayList<Double>();
			if((names=auto.getOptionNames(i))!=null) {
				prices=auto.getOptionPrice(i);
				for(int j=0; j<names.size(); j++) {
					//get next available optid
					int optid = getID(jdbc,"options");
					//if a row with same Option name and price doesn't exists, INSERT
					select = "SELECT * FROM options WHERE name='"+names.get(j)+"' AND price="+prices.get(j)+";";
					getResult(jdbc.selectQuery(select));
					if(rows.size()==0) {
						query2 = "INSERT INTO options (id, name, price) VALUES (";
						query2 += Integer.toString(optid)+",'"+names.get(j)+"',"+Double.toString(prices.get(j))+");";
						jdbc.executeQuery(query2);
					}
					//a row with same Option name and price already exist, get id
					else {
						optid = Integer.parseInt(rows.get(0).split("[(,)]+")[1]);
					}
					
					//insert into models
					query3 = "INSERT INTO models (autoid, optsetid, optid) VALUES (";
					query3 += Integer.toString(autoid)+","+Integer.toString(optsetid)+","+Integer.toString(optid)+");";
					jdbc.executeQuery(query3);
				}
			}
			i++;
		}
		getID(jdbc,"models");
		getID(jdbc,"automobiles");
		getID(jdbc,"optionsets");
		getID(jdbc,"options");
		jdbc.close();
	}
	
	/* DeleteAuto - removes automobile model from database */
	@Override
	public void deleteAuto(String modelName) {
		JDBCAdapter jdbc = new JDBCAdapter();
		//get autoid for given model
		String query = "SELECT * FROM automobiles WHERE model='"+modelName+"';";
		getResult(jdbc.selectQuery(query));
		int autoid = Integer.parseInt(rows.get(0).split("[(,)]+")[1]);
		//get all optsetids and optids for given model
		query = "SELECT * FROM models WHERE autoid="+autoid+";";
		getResult(jdbc.selectQuery(query));
		Set<Integer> optsetid = new HashSet<Integer>();
		Set<Integer> optid = new HashSet<Integer>();
		for(int i=0; i<rows.size(); i++) {
			optsetid.add(Integer.parseInt(rows.get(i).split("[(, )]+")[2]));
			optid.add(Integer.parseInt(rows.get(i).split("[(, )]+")[3]));
		}
		//System.out.println(optsetid);
		//System.out.println(optid);
		//delete given models
		query = "DELETE FROM models WHERE autoid="+Integer.toString(autoid)+";";
		jdbc.executeQuery(query);
		//delete empty automobiles
		query = "DELETE FROM automobiles WHERE id="+Integer.toString(autoid)+";";
		jdbc.executeQuery(query);
		//delete empty optionsets
		Iterator<Integer> itr = optsetid.iterator();
	    while (itr.hasNext()){
	    	int id = itr.next();
	    	query = "SELECT * FROM models WHERE optsetid="+id+";";
	    	getResult(jdbc.selectQuery(query));
	    	if(rows.size()==0) {
	    		query = "DELETE FROM optionsets WHERE id="+Integer.toString(id)+";";
	    		jdbc.executeQuery(query);
	    	}
	    }
		//delete empty options
	    itr = optid.iterator();
	    while (itr.hasNext()){
	    	int id = itr.next();
	    	query = "SELECT * FROM models WHERE optid="+id+";";
	    	getResult(jdbc.selectQuery(query));
	    	if(rows.size()==0) {
	    		query = "DELETE FROM options WHERE id="+Integer.toString(id)+";";
	    		jdbc.executeQuery(query);
	    	}
	    }
		jdbc.close();
	}

	/* UpdateAuto */
	//updateOptionSetNameDB - update the OptionSet's name */
	@Override
	public void updateOptionSetNameDB(String modelName, String optionSetName, String newName) {
		JDBCAdapter jdbc = new JDBCAdapter();
		//get autoid for given model
		String query = "SELECT * FROM automobiles WHERE model='"+modelName+"';";
		getResult(jdbc.selectQuery(query));
		int autoid = Integer.parseInt(rows.get(0).split("[(,)]+")[1]);
		//get optsetid for given OptionSet name
		query = "SELECT * FROM optionsets WHERE name='"+optionSetName+"';";
		getResult(jdbc.selectQuery(query));
		int optsetid = Integer.parseInt(rows.get(0).split("[(,)]+")[1]);
		//check to see if overlap with other models
		query = "SELECT DISTINCT autoid FROM models WHERE optsetid="+Integer.toString(optsetid);
		getResult(jdbc.selectQuery(query));
		//if there is overlap, add a new OptionSet with the new OptionSet name
		if(rows.size()!=1) {
			//get next available ID for optset
			int optsetidNew = getID(jdbc,"optionsets");
			//insert new OptionSet name into optionsets
			query = "INSERT INTO optionsets (id, name) VALUES (";
			query+= Integer.toString(optsetidNew)+",'"+newName+"');";
			jdbc.executeQuery(query);
			//update and change to new OptionSetName
			query = "UPDATE models SET optsetid="+Integer.toString(optsetidNew);
			query += " WHERE autoid="+Integer.toString(autoid)
					+" AND optsetid="+Integer.toString(optsetid)+";";
			jdbc.executeQuery(query);
		}
		//no overlap with other models, directly change name value
		else {
			query = "UPDATE optionsets SET name='"+newName+"' WHERE id="+Integer.toString(optsetid)+";";
			jdbc.executeQuery(query);
		}
		jdbc.close();
	}

	//updateOptionPrice - update the Option's price
	@Override
	public void updateOptionPriceDB(String modelName, String optionSetName, String optionName, double oldPrice, double newPrice) {
		JDBCAdapter jdbc = new JDBCAdapter();
		//get autoid for given model
		String query = "SELECT * FROM automobiles WHERE model='"+modelName+"';";
		getResult(jdbc.selectQuery(query));
		int autoid = Integer.parseInt(rows.get(0).split("[(,)]+")[1]);
		//get optsetid for given Option name
		query = "SELECT * FROM optionsets WHERE name='"+optionSetName+"';";
		getResult(jdbc.selectQuery(query));
		int optsetid = Integer.parseInt(rows.get(0).split("[(,)]+")[1]);
		//get optid for given Option name and price
		query = "SELECT * FROM options WHERE name='"+optionName+"' AND price="+Double.toString(oldPrice)+";";
		getResult(jdbc.selectQuery(query));
		int optid = Integer.parseInt(rows.get(0).split("[(,)]+")[1]);
		//check to see if overlap with other models
		query = "SELECT DISTINCT autoid, optsetid FROM models WHERE optid="+Integer.toString(optid);
		getResult(jdbc.selectQuery(query));
		//if there is overlap, add a new OptionSet with the new OptionSet name
		if(rows.size()!=1) {
			//get next available ID for optset
			int optidNew = getID(jdbc,"options");
			//insert new OptionSet name into optionsets
			query = "INSERT INTO options (id, name, price) VALUES (";
			query+= Integer.toString(optidNew)+",'"+optionName+"',"+Double.toString(newPrice)+");";
			jdbc.executeQuery(query);
			//update and change to new OptionSetName
			query = "UPDATE models SET optid="+Integer.toString(optidNew);
			query += " WHERE autoid="+Integer.toString(autoid)
					+" AND optsetid="+Integer.toString(optsetid)
					+" AND optid="+Integer.toString(optid)+";";
			jdbc.executeQuery(query);
		}
		//no overlap with other models, directly change name value
		else {
			query = "UPDATE options SET price="+Double.toString(newPrice)
						+" WHERE id="+Integer.toString(optid)+";";
			jdbc.executeQuery(query);
		}
		jdbc.close();
	}
	
	
	/* getResult - parse ResultSet and put rows and columns into Array<String> */
	public void getResult(ResultSet resultSet) {
		rows = new ArrayList<String>();
		cols = new ArrayList<String>();
		try {
			int numCols = resultSet.getMetaData().getColumnCount();
			int row=0;
			for(int i=1; i<=numCols; i++) {
    			cols.add(resultSet.getMetaData().getColumnName(i));
    		}
			while(resultSet.next()) {
				if(RESULT) System.out.printf("row %d: ",row);
				String result = "(";
		    	for(int i=1; i<=numCols; i++) {
					if(i==numCols) {
						if(RESULT) System.out.printf("%s\n",resultSet.getString(i));
						result += resultSet.getString(i)+")";
					}
					else {
						if(RESULT) System.out.printf("%s, ",resultSet.getString(i));
						result += resultSet.getString(i)+", ";
					}
		    	}
		    	rows.add(result);
		    	row++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* getID - get the next available id */
	public int getID(JDBCAdapter jdbc, String table) {
		getResult(jdbc.selectQuery("SELECT * from "+table));
		return rows.size()+1;
	}
	
}
