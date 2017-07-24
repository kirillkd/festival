package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO {

	protected Connection connection;

	/** 
	 * Establishes the connection to the PostgreSQL database. The parameters
	 * can be changed in Config.java.
	 * By default, auto-commit of the connection is disabled.
	 *
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see Config
	 */
	public void getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");

		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://" + Config.Database.HOST + ":"
						+ Config.Database.PORT + "/" + Config.Database.DB,
				Config.Database.USER, Config.Database.PASS);

		connection.setAutoCommit(false);

		this.connection = connection;
	}

	/**
	 * Close the connection to the database.
	 *
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException {
		connection.close();
	}
}
