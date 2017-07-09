package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class DAO {
	protected Connection getConnection() throws SQLException, ClassNotFoundException {
			Class.forName("org.postgresql.Driver");
        Connection con =  DriverManager.getConnection(
        		"jdbc:postgresql://" 
        + Config.Database.HOST 
        + ":" + Config.Database.PORT
        + "/" + Config.Database.DB, 
        Config.Database.USER,
        Config.Database.PASS);
        
        con.setAutoCommit(false);
        
        return con;
    }
}
