package beans;

import java.util.LinkedList;

public class SQLResultBean {
	
	private LinkedList<String> columnNames;
	private LinkedList<LinkedList<String>> columnValues;
	
	
	public SQLResultBean() {
	}


	public LinkedList<String> getColumnNames() {
		return columnNames;
	}


	public void setColumnNames(LinkedList<String> columnNames) {
		this.columnNames = columnNames;
	}


	public LinkedList<LinkedList<String>> getColumnValues() {
		return columnValues;
	}


	public void setColumnValues(LinkedList<LinkedList<String>> columnValues) {
		this.columnValues = columnValues;
	}	
}
