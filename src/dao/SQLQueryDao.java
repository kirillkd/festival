package dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import beans.SQLResultBean;

public class SQLQueryDao extends DAO {
	
	public void executeQuery(String query, SQLResultBean sqlResultBean) 
			throws ClassNotFoundException, SQLException {
		
		connection.setReadOnly(true);
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		LinkedList<String> columnNames = new LinkedList<>();
		LinkedList<LinkedList<String>> columnValues = new LinkedList<>();
		
		for (int i=1; i <= rsMetaData.getColumnCount(); i++) {
			columnNames.add(rsMetaData.getColumnLabel(i));
		}
		
		sqlResultBean.setColumnNames(columnNames);
		
		while(rs.next()) {
			LinkedList<String> currentRow = new LinkedList<>();
			
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
