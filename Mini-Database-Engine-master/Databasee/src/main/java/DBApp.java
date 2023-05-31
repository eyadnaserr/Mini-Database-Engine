package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

public class DBApp {
	static String csvFilePath = "src/main/resources/metadata.csv"; // specify
																	// the file
	// path
	Vector<Table> tableList = new Vector<Table>();

	private static void createCoursesTable(DBApp dbApp) throws Exception {
		// Date CK
		String tableName = "with";

		Hashtable<String, String> htblColNameType = new Hashtable<String, String>();
		htblColNameType.put("date_added", "java.util.Date");
		htblColNameType.put("course_id", "java.lang.String");
		htblColNameType.put("course_name", "java.lang.String");
		htblColNameType.put("hours", "java.lang.Integer");

		Hashtable<String, String> minValues = new Hashtable<>();
		minValues.put("date_added", "1901-01-01");
		minValues.put("course_id", "0000");
		minValues.put("course_name", "AAAAAA");
		minValues.put("hours", "1");

		Hashtable<String, String> maxValues = new Hashtable<>();
		maxValues.put("date_added", "2020-12-31");
		maxValues.put("course_id", "9999");
		maxValues.put("course_name", "zzzzzz");
		maxValues.put("hours", "24");

		dbApp.createTable(tableName, "date_added", htblColNameType, minValues,
				maxValues);

	}

	private static void insertCoursesRecords(DBApp dbApp, int limit)
			throws Exception {
		BufferedReader coursesTable = new BufferedReader(new FileReader(
				"src/main/resources/courses_table.csv"));
		String record;
		Hashtable<String, Object> row = new Hashtable<>();
		int c = limit;
		if (limit == -1) {
			c = 1;
		}
		while ((record = coursesTable.readLine()) != null && c > 0) {
			String[] fields = record.split(",");

			int year = Integer.parseInt(fields[0].trim().substring(0, 4));
			int month = Integer.parseInt(fields[0].trim().substring(5, 7));
			int day = Integer.parseInt(fields[0].trim().substring(8));

			Date dateAdded = new Date(year - 1900, month - 1, day);

			row.put("date_added", dateAdded);

			row.put("course_id", fields[1]);
			row.put("course_name", fields[2]);
			row.put("hours", Integer.parseInt(fields[3]));

			dbApp.insertIntoTable("with", row);
			row.clear();

			if (limit != -1) {
				c--;
			}
		}

		coursesTable.close();
	}

	public static void main(String[] args) throws Exception {
		// ClassNotFoundException, IOException, ParseException {
		// init();
		String strTableName = "noIndex";
		String strTableNameWith = "hasIndex";
		DBApp dbApp = new DBApp();
		dbApp.init();
		Hashtable htblColNameType1 = new Hashtable();
		Hashtable htblColNameMin1 = new Hashtable();
		Hashtable htblColNameMax1 = new Hashtable();
		htblColNameType1.put("id", "java.lang.Integer");
		htblColNameType1.put("name", "java.lang.String");
		htblColNameType1.put("gpa", "java.lang.Double");
		htblColNameType1.put("phone", "java.lang.Integer");
		// htblColNameType1.put("date", "java.util.Date");
		// htblColNameType1.put("email", "java.lang.String");
		htblColNameMin1.put("id", "0");
		htblColNameMin1.put("name", "a");
		htblColNameMin1.put("gpa", "1.0");
		htblColNameMin1.put("phone", "0");
		// htblColNameMin1.put("date", "1903-03-01");
		// htblColNameMin1.put("email", "a");

		htblColNameMax1.put("id", "30");
		htblColNameMax1.put("name", "zzzzzz");
		htblColNameMax1.put("gpa", "5.0");
		htblColNameMax1.put("phone", "99999999");
		// createCoursesTable(dbApp);
		// insertCoursesRecords(dbApp, 500);
		String[] a = { "date_added", "course_name", "hours" };
		// dbApp.createIndex("with", a);
		// dbApp.createTable("lila", "id",
		// htblColNameType1,htblColNameMin1, htblColNameMax1);
		//dbApp.createTable(strTableNameWith, "id",
		// htblColNameType1,htblColNameMin1, htblColNameMax1);
	    String[] aa = { "name", "gpa", "phone" };
		 //dbApp.createIndex("lila", aa);
		// dbApp.validateIndexColms(strTableName, a);
		Hashtable<String, Object> rowww = new Hashtable();
		rowww.put("name", "yes");
		rowww.put("id", new Integer(0));
		rowww.put("gpa", new Double(3.0));
		rowww.put("phone", new Integer(6));
		//dbApp.insertIntoTable("lila", rowww);
		//dbApp.insertIntoTable(strTableNameWith, rowww);
			Table t = deserialize("lila");
			t.trees.get(0).octTreeIndex.print();
		//	tree.octTreeIndex.print();
	DBApp.printTableContent("lila");
		System.out.println("I am the table with the index");
	//	DBApp.printTableContent(strTableNameWith);
		System.out.println("I SEE THIS LINE");
		SQLTerm[] arrSQLTerms;
		arrSQLTerms = new SQLTerm[3];
		arrSQLTerms[0] = new SQLTerm();
		arrSQLTerms[1] = new SQLTerm();
		arrSQLTerms[2] = new SQLTerm();
		// arrSQLTerms[3]= new SQLTerm();
		// arrSQLTerms[4]= new SQLTerm();
		// arrSQLTerms[5]= new SQLTerm();
		// arrSQLTerms[6]= new SQLTerm();
		arrSQLTerms[0]._strTableName = strTableName;
		arrSQLTerms[0]._strColumnName = "name";
		arrSQLTerms[0]._strOperator = "!=";
		arrSQLTerms[0]._objValue = "LLLLLL";
		arrSQLTerms[1]._strTableName = strTableName;
		arrSQLTerms[1]._strColumnName = "phone";
		arrSQLTerms[1]._strOperator = "<";
		arrSQLTerms[1]._objValue = 90;
		arrSQLTerms[2]._strTableName = strTableName;
		arrSQLTerms[2]._strColumnName = "gpa";
		arrSQLTerms[2]._strOperator = "=";
		arrSQLTerms[2]._objValue = 3.0;

//		SQLTerm[] arrSQLTermsWith;
//		arrSQLTermsWith = new SQLTerm[3];
//		arrSQLTermsWith[0] = new SQLTerm();
//		arrSQLTermsWith[1] = new SQLTerm();
//		arrSQLTermsWith[2] = new SQLTerm();
//		arrSQLTermsWith[0]._strTableName = strTableNameWith;
//		arrSQLTermsWith[0]._strColumnName = "name";
//		arrSQLTermsWith[0]._strOperator = "!=";
//		arrSQLTermsWith[0]._objValue = "llllll";
//		arrSQLTermsWith[1]._strTableName = strTableNameWith;
//		arrSQLTermsWith[1]._strColumnName = "phone";
//		arrSQLTermsWith[1]._strOperator = "<";
//		arrSQLTermsWith[1]._objValue = 90;
//		arrSQLTermsWith[2]._strTableName = strTableNameWith;
//		arrSQLTermsWith[2]._strColumnName = "gpa";
//		arrSQLTermsWith[2]._strOperator = "=";
//		arrSQLTermsWith[2]._objValue = 3.0;
//		System.out.println("I SEE THIS LINE");
//		System.out.println("I SEE THIS LINE888");

//		  arrSQLTerms[3]._strTableName = "lila";
//		  arrSQLTerms[3]._strColumnName= "name";
//		  arrSQLTerms[3]._strOperator = "=";
//		  arrSQLTerms[3]._objValue = "lessgo";
//		  arrSQLTerms[4]._strTableName = "lila";
//		  arrSQLTerms[4]._strColumnName= "name";
//		  arrSQLTerms[4]._strOperator = "=";
//		  arrSQLTerms[4]._objValue = "lm";
//		  arrSQLTerms[5]._strTableName = "lila";
//		  arrSQLTerms[5]._strColumnName= "phone";
//		  arrSQLTerms[5]._strOperator = "=";
//		  arrSQLTerms[5]._objValue = 1555;
//		  arrSQLTerms[6]._strTableName = "lila";
//		  arrSQLTerms[6]._strColumnName= "gpa";
//		  arrSQLTerms[6]._strOperator = "=";
//		  arrSQLTerms[6]._objValue = new Double(3.0);
		String[] strarrOperators = new String[2];
		strarrOperators[0] = "AND";
		strarrOperators[1] = "AND";
//		System.out.println("I AM HERE");
		 SQLTerm[] arrSQLTermsWith;
		 arrSQLTermsWith = new SQLTerm[3];
		 arrSQLTermsWith[0]= new SQLTerm();
		 arrSQLTermsWith[1]= new SQLTerm();
		 arrSQLTermsWith[2]= new SQLTerm();
		 arrSQLTermsWith[0]._strTableName = "lila";
		 arrSQLTermsWith[0]._strColumnName= "phone";
		 arrSQLTermsWith[0]._strOperator = "<=";
		 arrSQLTermsWith[0]._objValue = 150;
		 arrSQLTermsWith[1]._strTableName = "lila";
		 arrSQLTermsWith[1]._strColumnName= "name";
		 arrSQLTermsWith[1]._strOperator = "!=";
		 arrSQLTermsWith[1]._objValue = "lo";
		 arrSQLTermsWith[2]._strTableName = "lila";
		 arrSQLTermsWith[2]._strColumnName= "gpa";
		 arrSQLTermsWith[2]._strOperator = ">" ;
		 arrSQLTermsWith[2]._objValue = new Double(3.0);
	 long startTimee = System.nanoTime();

		 Iterator resultSet1 = dbApp.selectFromTable(arrSQLTermsWith,
				strarrOperators);
//		 Table table = deserialize("coursesWithoutIndex");
//		 dbApp.selectFromTable(arrSQLTermsWith, strarrOperators);
		 long endTimee = System.nanoTime();
		 long elapsedTimee = endTimee - startTimee;
		
		 double millisecondss = (double) elapsedTimee / 1_000_000;
		
		 System.out.println("dh kda index:  "
		 + millisecondss + " ms");
//		
		 System.out.println("The result is here");
		if (!resultSet1.hasNext()) {
			System.out.println("Result set is empty.");
		}
		while (resultSet1.hasNext()) {
			Tuples element = (Tuples) resultSet1.next();
			Hashtable hashtable = element.tuple;
			Enumeration<String> keys = hashtable.keys();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				Object value = hashtable.get(key);
				System.out.println(key + " = " + value);

			}
			System.out.println("**");
		}
//		System.out.println("Results without index");
//		Iterator resultSet = dbApp
//				.selectFromTable(arrSQLTerms, strarrOperators);
//		if (!resultSet.hasNext()) {
//			System.out.println("Result set is empty.");
//		}
//		while (resultSet.hasNext()) {
//			Tuples element = (Tuples) resultSet.next();
//			Hashtable hashtable = element.tuple;
//			Enumeration<String> keys = hashtable.keys();
//			while (keys.hasMoreElements()) {
//				String key = keys.nextElement();
//				Object value = hashtable.get(key);
//				System.out.println(key + " = " + value);
//
//			}
//			System.out.println("**");
//		}
//		 long startTimee = System.nanoTime();
//		 Table table = deserialize("coursesWithoutIndex");
//		 dbApp.selectFromTable(arrSQLTermsWith, strarrOperators);
//		 long endTimee = System.nanoTime();
//		
//		 long elapsedTimee = endTimee - startTimee;
//		
//		 double millisecondss = (double) elapsedTimee / 1_000_000;
//		
//		 System.out.println("Method execution time Linearly:  "
//		 + millisecondss + " ms");
//		
//		 long startTimee1 = System.nanoTime();
//		 Table t = deserialize("with");
//		 OctTree ot = t.trees.get(0);
//		 OctTreeIndex index = ot.octTreeIndex;
//		 t.chooseSelect (arrSQLTermsWith, strarrOperators);
//		 t.convertObjectsSelected(arrSQLTerms, ot);
//
//		 dbApp.selectFromTable(arrSQLTermsWith, strarrOperators);
//		 long endTimee1 = System.nanoTime();
//		
//		 long elapsedTimee1 = endTimee1 - startTimee1;
//		
//		 double millisecondss1 = (double) elapsedTimee1 / 1_000_000;
//		
//		 System.out.println("Method execution without octTree:  "
//		 + millisecondss1 + " ms");
		

	//	long startTime = System.nanoTime();

		// Table t = deserialize("with");
		// OctTree ott = t.trees.get(0);
		// t.convertObjectsSelected(arrSQLTerms, ott);
//		long endTime = System.nanoTime();
//
//		long elapsedTime = endTime - startTime;
//
//		double milliseconds = (double) elapsedTime / 1_000_000;
//
//		System.out.println("Method execution with octTree:  " + milliseconds
//				+ " ms");
		//
		//
	}

	// //
	// // }
	//
	// }

	public static void init() {
		String header = "Table Name, Column Name, Column Type, ClusteringKey, IndexName,IndexType, min, max";
		String path = "src/main/resources/data";
		File file = new File(path);
		file.mkdir();
		// FileWriter fileWriter;
		// try {
		// fileWriter = new FileWriter(csvFilePath, true);
		// BufferedWriter bw = new BufferedWriter(fileWriter);
		// bw.write(header);
		// bw.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public static void printTableContent(String tableName)
			throws ClassNotFoundException, IOException, DBAppException {
		boolean isExists = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(
				"src/main/resources/" + "metadata.csv"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] columns = line.split(",");
				if (columns[0].trim().equals(tableName)) {
					isExists = true;

					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!isExists) {
			throw new DBAppException("Table not found");
		}

		Table table = deserialize(tableName);
		if (table.tablePages.size() != 0)
			table.printTable();
		else
			System.out.println("Table is Empty!");
		serialize(table);
	}

	public Iterator selectFromTable(SQLTerm[] arrSQLTerms,
			String[] strarrOperators) throws DBAppException {
		Iterator result = null;
		try {
			System.out.println("i entered");
			DBApp.validateSelection(arrSQLTerms, strarrOperators);

			Table table = deserialize(arrSQLTerms[0]._strTableName);
			result = table.chooseSelect(arrSQLTerms, strarrOperators);
			System.out.println(result.hasNext());
	


			serialize(table);
			table = null;
			System.gc();
			return result;

		} catch (ClassNotFoundException | IOException | NumberFormatException
				| ParseException e) {
			throw new DBAppException();

		}
	}

	public static void validateSelection(SQLTerm[] arrSQLTerms,
			String[] strarrOperators) throws DBAppException, IOException,
			ClassNotFoundException {
		// check sizes

		if (arrSQLTerms.length - strarrOperators.length != 1)
			throw new DBAppException(
					"Invalid query due to operators length mismatch");

		for (int i = 0; i < arrSQLTerms.length - 1; i++) {

			// Selecting from different tables
			if (!(arrSQLTerms[i]._strTableName
					.equals(arrSQLTerms[i + 1]._strTableName)))
				throw new DBAppException("Can't select from multiple tables");
			// Table not found
			boolean isExists = false;
			BufferedReader reader = new BufferedReader(new FileReader(
					"src/main/resources/" + "metadata.csv"));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] columns = line.split(",");
				if (columns[0].trim().equals(arrSQLTerms[0]._strTableName)) {
					isExists = true;

					break;
				}
			}
			reader.close();

			if (!isExists) {
				throw new DBAppException(
						"Table you're selecting from doesn't exist");
			}

			// Invalid operator
			if (!arrSQLTerms[i]._strOperator.equals(">")
					&& !arrSQLTerms[i]._strOperator.equals(">=")
					&& !arrSQLTerms[i]._strOperator.equals("<")
					&& !arrSQLTerms[i]._strOperator.equals("<=")
					&& !arrSQLTerms[i]._strOperator.equals("!=")
					&& !arrSQLTerms[i]._strOperator.equals("="))
				throw new DBAppException("Invalid Operator");
			// Check dataType mismatch
			long startTimee = System.nanoTime();
			Table table = DBApp.deserialize(arrSQLTerms[i]._strTableName);

			if (colDataType(table,arrSQLTerms[i]._strTableName,
					arrSQLTerms[i]._strColumnName).equals("java.lang.Integer")
					&& !(arrSQLTerms[i]._objValue instanceof Integer)) {
				throw new DBAppException("Data type mismatch int");
			} else if (colDataType(table,arrSQLTerms[i]._strTableName,
					arrSQLTerms[i]._strColumnName).equals("java.lang.Double")
					&& !(arrSQLTerms[i]._objValue instanceof Double))
				throw new DBAppException("Data type mismatch double");
			else if (colDataType(table,arrSQLTerms[i]._strTableName,
					arrSQLTerms[i]._strColumnName).equals("java.lang.String")
					&& !(arrSQLTerms[i]._objValue instanceof String))
				throw new DBAppException("Data type mismatch string");
			else if (colDataType(table,arrSQLTerms[i]._strTableName,
					arrSQLTerms[i]._strColumnName).equals("java.util.Date")
					&& !(arrSQLTerms[i]._objValue instanceof Date))
				throw new DBAppException("Data type mismatch date");

		}

		// check and or xor operators
		for (int i = 0; i < strarrOperators.length; i++) {
			if (!(strarrOperators[i].equals("OR")
					|| strarrOperators[i].equals("AND") || strarrOperators[i]
						.equals("XOR"))) {
				throw new DBAppException("Invalid Operation");
			}
		}

	}

	public static String colDataType(Table table,String tableName, String colName)
			throws IOException, ClassNotFoundException, DBAppException {

		ArrayList colData = Table.readCsvCKDataType(tableName, colName);

		// if (colData.isEmpty() && colName != null)
		// throw new DBAppException(
		// "Column you're selecting from doesn't exist");
		// System.out.println(colData.toString());
		String dataType = (String) colData.get(0);

		// System.out.println(dataType);
		return dataType;

	}

	public void createIndex(String strTableName, String[] strarrColName)
			throws DBAppException {
		Object[] min = new Object[3];
		Object[] max = new Object[3];

		if (!checkTable(strTableName))
			throw new DBAppException("Table name is not exist in the database");

		try {
			validateIndexColms(strTableName, strarrColName);
		} catch (ClassNotFoundException | IOException e) {
			throw new DBAppException(e.getMessage());

		}
		int i = 0;
		for (String s : strarrColName) {
			ArrayList a;

			try {
				a = Table.readCsvCKDataType(strTableName, s);
				min[i] = Table.changeClusterKey(strTableName, s,
						(String) a.get(4));
				max[i++] = Table.changeClusterKey(strTableName, s,
						(String) a.get(5));
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		OctTree<Object> tree = new OctTree<Object>(strarrColName[0],
				strarrColName[1], strarrColName[2], min[0], min[1], min[2],
				max[0], max[1], max[2], getconfigindex());
		// set coloumns to yes indexed and what index name and type
		setIndexTrue(strTableName, strarrColName);
		// check if table not empty we have to insert what have inserted before
		try {
			Table t = deserialize(strTableName);
			t.trees.add(tree);
			t.beforeIndexInsert(tree, strarrColName);
			serialize(t);
			t = null;

		} catch (ClassNotFoundException | IOException e) {
			throw new DBAppException(e.getMessage());
		}
		tree.octTreeIndex.print();

	}

	public static int getconfigindex() {
		int noOfentries;

		Properties config = new Properties();
		try {
			InputStream input = new FileInputStream(new File(
					"src/main/resources/DBApp.config"));
			config.load(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		noOfentries = Integer.parseInt(config
				.getProperty("MaximumKeysCountinIndexBucket"));

		return noOfentries;
	}

	public static void setIndexTrue(String strTableName, String[] strarrColName) {
		String filePath = "src/main/resources/metadata.csv";
		// specify the line number to change

		try {
			// create a new file reader
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			// create a new file writer
			FileWriter fw = new FileWriter(filePath + ".tmp");
			BufferedWriter bw = new BufferedWriter(fw);

			// loop through each line in the file
			String line;
			int i = 1;
			while ((line = br.readLine()) != null) {
				// check if this is the line to change
				String[] lines = line.split(",");

				if (lines[0].equals(strTableName)
						&& ((lines[1].toString()).replaceAll("\\s", "").equals(
								strarrColName[0])
								|| (lines[1].toString()).replaceAll("\\s", "")
										.equals(strarrColName[1]) || (lines[1]
									.toString()).replaceAll("\\s", "").equals(
								strarrColName[2]))) {
					// write the new values to the line
					lines[4] = "" + strarrColName[0] + strarrColName[1]
							+ strarrColName[2] + "Index";
					lines[5] = "Octree";
					String newLine = String.join(",", lines);
					bw.write(newLine);
					bw.newLine();
				} else {
					// write the original line to the file
					bw.write(line);
					bw.newLine();
				}
				i++;
			}

			// close the file reader and writer
			br.close();
			fr.close();
			bw.close();
			fw.close();

			// delete the original file
			File oldFile = new File(filePath);
			oldFile.delete();

			// rename the temporary file to the original file name
			File newFile = new File(filePath + ".tmp");
			newFile.renameTo(oldFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void validateIndexColms(String strTableName, String[] strarrColName)
			throws DBAppException, IOException, ClassNotFoundException {
		if (strarrColName.length != 3)
			throw new DBAppException("Please enter a table with 3 columns");

		Table t = (Table) deserialize(strTableName);
		for (String s : strarrColName) {
			ArrayList a = t.readCsvCKDataType(strTableName, s);

			if (a == null || a.size() == 0)
				throw new DBAppException(
						"No column with this name in this Table");
			else

			if (!a.get(2).equals("null"))
				throw new DBAppException("there is exist index on this coloumn");
		}
		serialize(t);
		t = null;

	}

	public void createTable(String strTableName, String strClusteringKeyColumn,
			Hashtable<String, String> htblColNameType,
			Hashtable<String, String> htblColNameMin,
			Hashtable<String, String> htblColNameMax) throws DBAppException {
		try {
			// create metadata if not exists
			File metaData = new File("src/main/resources/metadata.csv");
			if (!metaData.exists()) {
				try {
					FileWriter filewrite = new FileWriter(metaData);
					BufferedWriter b = new BufferedWriter(filewrite);
					b.write("Table Name, Column Name, Column Type, ClusteringKey, IndexName,IndexType, min, max");
					b.newLine();
					b.flush();
					b.close();
					filewrite.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// check if table already exists and not null
			if (checkTable(strTableName))
				throw new DBAppException(
						"Table name already exists in the database");
			if (strTableName == null || strTableName == "")
				throw new DBAppException("Table should have a name ");
			else {
				// check if no clustering key
				if (strClusteringKeyColumn == null)
					throw new DBAppException("Table should have clusteringKey");
				// check if datatype is correct and no min and max is empty
				Enumeration<String> colNames = htblColNameType.keys();
				while (colNames.hasMoreElements()) {
					String currColumn = colNames.nextElement();

					String checkType = htblColNameType.get(currColumn);

					if ((!checkType.equals("java.lang.Integer"))
							&& (!checkType.equals("java.lang.String"))
							&& (!checkType.equals("java.lang.Double"))
							&& (!checkType.equals("java.util.Date")))
						throw new DBAppException("Invalid Datatype for column");

					// if (htblColNameMax.get(currColumn) == null ||
					// htblColNameMin.get(currColumn) == null
					// || checkType != checkType(htblColNameMin.get(currColumn))
					// || checkType !=
					// checkType(htblColNameMax.get(currColumn)))
					// throw new
					// DBAppException("Invalid Datatype or empty column of "+currColumn);

					if (!htblColNameMin.containsKey(currColumn)
							|| !htblColNameMax.containsKey(currColumn)) {
						System.out
								.print(htblColNameMin.containsKey(currColumn));
						throw new DBAppException(
								"MIN AND MAX SHOULD NOT BE EMPTY!");

					}
					determineType(htblColNameMax.get(currColumn), checkType);
					determineType(htblColNameMin.get(currColumn), checkType);

				}
				// write to csv file
				colNames = htblColNameType.keys();
				while (colNames.hasMoreElements()) {
					boolean clustK;
					String currColumn = colNames.nextElement();
					if (currColumn.equals(strClusteringKeyColumn))
						clustK = true;
					else
						clustK = false;

					String inputLine = strTableName + ", " + currColumn + ", "
							+ htblColNameType.get(currColumn) + ", " + clustK
							+ ", " + null + ", " + null + ", "
							+ String.valueOf(htblColNameMin.get(currColumn))
							+ ", "
							+ String.valueOf(htblColNameMax.get(currColumn));
					// if
					// (!htblColNameType.get(currColumn).equals("java.util.Date"))
					// {
					// inputLine +=
					// String.valueOf(htblColNameMin.get(currColumn)) + ", "
					// + String.valueOf(htblColNameMax.get(currColumn));
					// } else {
					// inputLine += new
					// SimpleDateFormat("yyyy-MM-dd").format(htblColNameMin.get(currColumn))
					// + ", ";
					// inputLine += new
					// SimpleDateFormat("yyyy-MM-dd").format(htblColNameMax.get(currColumn));
					// }

					writeToCSV(inputLine);
				}

				Table newTable = new Table(getconfig(), strTableName);
				tableList.add(newTable);
				String path = "src/main/resources/data/" + strTableName;
				File file = new File(path);
				file.mkdir();
				file = new File(path + "/pages");
				file.mkdir();
				// Serializer.serialize(path + "/" + tableName + ".ser", table);
				serialize(newTable);

			}
			System.gc();
		} catch (IOException e) {

		}
	}

	public static void determineType(String str, String dataType)
			throws DBAppException {
		// Check if the string can be parsed as an integer
		switch (dataType) {
		case "java.lang.Integer":
			try {
				Integer.parseInt(str);
				break;
			} catch (Exception e) {
				throw new DBAppException(
						"MIN AND MAX SHOULD BE OF THE SAME DATA TYPE PLEASE!");
			}
		case "java.lang.Double":
			try {
				Double.parseDouble(str);
				break;
			} catch (Exception e) {
				throw new DBAppException(
						"MIN AND MAX SHOULD BE OF THE SAME DATA TYPE PLEASE!");
			}
		case "java.lang.String":
			try {
				//
				break;
			} catch (Exception e) {
				throw new DBAppException(
						"MIN AND MAX SHOULD BE OF THE SAME DATA TYPE PLEASE!");
			}
		case "java.util.Date":
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = dateFormat.parse(str);
				break;
			} catch (Exception e) {
				throw new DBAppException(
						"MIN AND MAX SHOULD BE OF THE SAME DATA TYPE PLEASE!");
			}
		}
	}

	public void writeToCSV(String input) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(csvFilePath, true);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(input);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean checkTable(String name) {
		boolean isExists = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(
				csvFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] columns = line.split(",");
				if (columns[0].trim().equals(name)) {
					isExists = true;
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isExists;
	}

	public void insertIntoTable(String strTableName,
			Hashtable<String, Object> htblColNameValue) throws DBAppException {
		// check if table exists
		try {
			boolean isExists = checkTable(strTableName);

			if (!isExists) {
				throw new DBAppException("Table not found");
			}
			// if exists deserialize --> insert --> serialize
			Table t;

			t = (Table) deserialize(strTableName);
			t.insertIntoTable(strTableName, htblColNameValue);
			serialize(t);
			t = null;

			System.gc();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBAppException("Table not found");
		}
	}

	public static String checkType(Object key) throws ParseException {

		String type = key.getClass().toString();
		// System.out.println(primaryKey);

		String typeString = "";
		if (type.substring(16).equals("Integer")) {
			typeString = "java.lang.Integer";
		}
		if (type.substring(16).equals("Double")) {
			typeString = "java.lang.Double";
		}

		if (type.substring(16).equals("String")) {
			typeString = "java.lang.String";
		}
		if (type.substring(16).equals("Date")) {
			typeString = "java.util.Date";
		}
		return typeString;
	}

	public static Table deserialize(String tableName) throws IOException,
			ClassNotFoundException {
		String fileName = "src/main/resources/data/" + tableName + "/"
				+ tableName + ".ser";
		Table t = null;
		File file = new File(fileName);
		if (file.exists()) {
			FileInputStream fileStream = new FileInputStream(fileName);
			ObjectInputStream is = new ObjectInputStream(fileStream);
			t = (Table) is.readObject();
			fileStream.close();
			is.close();
			return t;
		}
		return t;
	}

	public static void serialize(Table table) throws IOException {
		// String fileName = "src/main/resources/" + table.tableName + ".ser";
		String fileName = "src/main/resources/data/" + table.tableName + "/"
				+ table.tableName + ".ser";
		FileOutputStream fileOut = new FileOutputStream(fileName);

		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

		try {
			objOut.writeObject(table);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		objOut.close();
		fileOut.close();
	}

	public static int getconfig() {
		int noOfRows;

		Properties config = new Properties();
		try {
			InputStream input = new FileInputStream(new File(
					"src/main/resources/DBApp.config"));
			config.load(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		noOfRows = Integer.parseInt(config
				.getProperty("MaximumRowsCountinPage"));

		return noOfRows;
	}

	public void updateTable(String strTableName, String strClusteringKeyValue,
			Hashtable<String, Object> htblColNameValue) throws DBAppException {
		try {
			boolean isExists = checkTable(strTableName);
			if (!isExists) {
				throw new DBAppException("Table not found");
			}
			// if exists deserialize --> insert --> serialize
			Table t;
			ArrayList<OctPoint<String>> temp = new ArrayList<OctPoint<String>>();
			t = (Table) deserialize(strTableName);
			try {
				t.validateData(htblColNameValue);
			} catch (DBAppException | ParseException e1) {
				throw new DBAppException();
			}
			for (int i = 0; i < t.trees.size(); i++) {
				try {
					if (t.trees.get(i).xname.equals(t.clusteringKey)) {

						temp = t.trees.get(i).octTreeIndex
								.partialQuery(
										t.changeClusterKey(strTableName,
												t.clusteringKey,
												strClusteringKeyValue), null,
										null);

					} else if (t.trees.get(i).yname.equals(t.clusteringKey)) {

						temp = t.trees.get(i).octTreeIndex
								.partialQuery(
										t.changeClusterKey(strTableName,
												t.clusteringKey,
												strClusteringKeyValue), null,
										null);

					} else if (t.trees.get(i).zname.equals(t.clusteringKey)) {

						temp = t.trees.get(i).octTreeIndex
								.partialQuery(
										t.changeClusterKey(strTableName,
												t.clusteringKey,
												strClusteringKeyValue), null,
										null);

					}

				} catch (ParseException | DBAppException e) {
					throw new DBAppException();
				}
				if (temp.size() != 0) {
					String[] Temppath = ((String) temp.get(0).object)
							.split(" ");
					t.tablePages.get(Integer.parseInt(Temppath[0]) - 1).rows = Table
							.deserializable(strTableName,
									Integer.parseInt(Temppath[0]));

					try {
						Object clusteringkeywithType = t.changeClusterKey(
								strTableName, t.clusteringKey,
								strClusteringKeyValue);
						System.out.println(clusteringkeywithType.getClass());
						htblColNameValue.put(t.clusteringKey,
								clusteringkeywithType);
						t.tablePages.get(Integer.parseInt(Temppath[0]) - 1)
								.updateExistingRecord(htblColNameValue,
										t.clusteringKey);
						System.out.println("I've Updated Succesfully");
					} catch (NumberFormatException | ParseException
							| DBAppException e) {
						// TODO Auto-generated catch block
						throw new DBAppException();
					}
					serialize(t);
					t = null;
					return;
				}
			}
			t.updateInTable(strTableName, strClusteringKeyValue,
					htblColNameValue);
			serialize(t);
			t = null;

			System.gc();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBAppException();
		}
	}

	public void deleteFromTable(String strTableName,
			Hashtable<String, Object> htblColNameValue) throws DBAppException {
		try {
			boolean isExists = false;
			BufferedReader reader = new BufferedReader(new FileReader(
					"src/main/resources/" + "metadata.csv"));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] columns = line.split(",");
				if (columns[0].trim().equals(strTableName)) {
					isExists = true;

					break;
				}
			}
			reader.close();

			if (!isExists) {
				throw new DBAppException("Table not found");
			}

			Table table = deserialize(strTableName);
			try {
				table.validateData(htblColNameValue);
			} catch (DBAppException | ParseException e1) {
				throw new DBAppException();
			}
			int countCol = 0;
			int maximum = 0;
			OctTree bestTree = null;
			for (int i = 0; i < table.trees.size(); i++) {
				countCol = 0;
				Object tempx = htblColNameValue.get(table.trees.get(i).xname);
				Object tempy = htblColNameValue.get(table.trees.get(i).yname);
				Object tempz = htblColNameValue.get(table.trees.get(i).zname);
				if (tempx != null)
					countCol++;
				if (tempy != null)
					countCol++;
				if (tempz != null)
					countCol++;
				if (countCol > maximum) {
					maximum = countCol;
					bestTree = table.trees.get(i);
				}
			}
			System.out.println(bestTree);
			if (bestTree != null) {
				System.out.println("I'm in the if condition");
				Object tempx = htblColNameValue.get(bestTree.xname);
				Object tempy = htblColNameValue.get(bestTree.yname);
				Object tempz = htblColNameValue.get(bestTree.zname);
				// get array list of points to remove
				ArrayList<OctPoint<String>> temp = bestTree.octTreeIndex
						.partialQuery(tempx, tempy, tempz);
				System.out.println(temp.size());
				System.out.println(temp.get(0).duplicates.size());

				// / System.out.print("I have temp of kaza");

				for (int j = 0; j < temp.size(); j++) {

					Iterator<OctPoint> iterator = temp.get(j).duplicates
							.iterator();

					ArrayList<String[]> itemsToRemove = new ArrayList<>();
					while (iterator.hasNext()) {
						OctPoint p = iterator.next();
						System.out.print(p);
						String[] Temppath = ((String) p.object).split(" ");
						itemsToRemove.add(Temppath);
						iterator.remove();
					}

					for (int l = 0; l < itemsToRemove.size(); l++) {
						System.out.println("Items to remove : ");
						System.out.println(itemsToRemove.size());
						String[] Temppath = itemsToRemove.get(l);
						table.tablePages.get(Integer.parseInt(Temppath[0]) - 1).rows = Table
								.deserializable(strTableName,
										Integer.parseInt(Temppath[0]));
						if (htblColNameValue.get(table.clusteringKey) != null) {
							table.tablePages.get(
									Integer.parseInt(Temppath[0]) - 1)
									.deleteRecordWithClusteringKey(
											htblColNameValue);
						} else {
							htblColNameValue.put(table.clusteringKey, Table
									.changeClusterKey(strTableName,
											table.clusteringKey, Temppath[1]));
							System.out.println(htblColNameValue.get("id"));
							System.out.println(Table.changeClusterKey(
									strTableName, table.clusteringKey,
									Temppath[1]));
							table.tablePages.get(
									Integer.parseInt(Temppath[0]) - 1)
									.deleteRecordWithClusteringKey(
											htblColNameValue);
							htblColNameValue.remove(table.clusteringKey);
							System.out.println(htblColNameValue.get("id"));
						}

					}
					String[] Temppath = temp.get(j).object.split(" ");
					System.out.println(Temppath[0]);
					table.tablePages.get(Integer.parseInt(Temppath[0]) - 1).rows = Table
							.deserializable(strTableName,
									Integer.parseInt(Temppath[0]));
					if (htblColNameValue.get(table.clusteringKey) != null) {
						table.tablePages
								.get(Integer.parseInt(Temppath[0]) - 1)
								.deleteRecordWithClusteringKey(htblColNameValue);
					} else {
						htblColNameValue.put(table.clusteringKey, Table
								.changeClusterKey(strTableName,
										table.clusteringKey, Temppath[1]));
						System.out.println(htblColNameValue.get("id"));
						table.tablePages
								.get(Integer.parseInt(Temppath[0]) - 1)
								.deleteRecordWithClusteringKey(htblColNameValue);
						htblColNameValue.remove(table.clusteringKey);
						System.out.println(htblColNameValue.get("id"));
					}
				}
				table.removeEmptyPages(table.tablePages);
				serialize(table);
				table = null;
				return;

			} else {// linear execution
				System.out.println("I'm deleting linearly");
				table.deleteFromTable(htblColNameValue);

				serialize(table);
				table = null;
				System.gc();
			}

		} catch (ClassNotFoundException | IOException | ParseException e) {
			e.printStackTrace();
			throw new DBAppException();
		}
	}
}
