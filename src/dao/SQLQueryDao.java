package dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import beans.SQLResultBean;

public class SQLQueryDao extends DAO {
	
	/**
	 * Executes a query entered by the user. For security reasons, only read-only queries are allowed.
	 * 
	 * @param query the query string
	 * @param sqlResultBean the bean to be filled with the query result
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void executeQuery(String query, SQLResultBean sqlResultBean) 
			throws ClassNotFoundException, SQLException {
		
		connection.setReadOnly(true);
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		// Retrieve the column names of the result
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		LinkedList<String> columnNames = new LinkedList<>();
		
		for (int i=1; i <= rsMetaData.getColumnCount(); i++) {
			columnNames.add(rsMetaData.getColumnLabel(i));
		}
		
		sqlResultBean.setColumnNames(columnNames);
		
		// Retrieve the result of query
		LinkedList<LinkedList<String>> columnValues = new LinkedList<>();
		
		while(rs.next()) {
			LinkedList<String> currentRow = new LinkedList<>();
			
			// For each column of the current row in the result set,
			// get the value and write it to the row data
			for (int i=1; i <= rsMetaData.getColumnCount(); i++) {
				currentRow.add(rs.getString(i));
			}
			
			columnValues.add(currentRow);
		}
		
		sqlResultBean.setColumnValues(columnValues);
		
		rs.close();
		stmt.close();
	}
}
