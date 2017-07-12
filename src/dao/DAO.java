package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class DAO {
	
	protected Connection connection;
	
	
	protected void getConnection() throws SQLException, ClassNotFoundException {
			Class.forName("org.postgresql.Driver");
        Connection connection =  DriverManager.getConnection(
        		"jdbc:postgresql://" 
        + Config.Database.HOST 
        + ":" + Config.Database.PORT
        + "/" + Config.Database.DB, 
        Config.Database.USER,
        Config.Database.PASS);
        
        connection.setAutoCommit(false);
        
        this.connection = connection;        
    }
	
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
}
