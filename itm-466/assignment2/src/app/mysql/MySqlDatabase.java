package app.mysql;

import java.sql.*;

/**
 * Class used to virtualize a MySql Database.
 * 
 */
public class MySqlDatabase extends MySqlEntityBaseClass {

	/**
	 * Default Class constructor
	 */
	public MySqlDatabase() {
		super();
	}

	/**
	 * Private getter that returns a string literal containing the sql to drop
	 * the database.
	 * 
	 * @return string value
	 */
	private String getDropSqlStatement() {
		return "DROP DATABASE IF EXISTS `" + this.getName().trim() + "`;";
	}

	/**
	 * Private getter that returns a string literal containing the sql to create
	 * the database.
	 * 
	 * @return string value
	 */
	private String getCreateSqlStatement() {
		return "CREATE DATABASE `" + this.getName().trim()
				+ "` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;";
	}

	/**
	 * Private method used to drop the database if exists. It returns true if
	 * (a) the connection object exists and (b) the drop statement sql is
	 * executed successfully.
	 * 
	 * @return boolean value
	 * @throws SQLException
	 */
	private Boolean dropDatabase() throws SQLException {
		if (this.getMySqlConnection() != null) {
			System.out.println("attempting to drop " + this.getName()
					+ " database ...");
			Statement _stmt = this.getMySqlConnection().getConnection()
					.createStatement();
			String _dropSql = this.getDropSqlStatement();
			System.out.println(_dropSql);
			_stmt.executeUpdate(_dropSql);
			return true;
		}
		return false;
	}

	/**
	 * Private method used to create the database It returns true if (a) the
	 * connection object exists, (b) the dropDatabase method returns true and
	 * (c) the create statement sql executes successfully. Otherwise, it returns
	 * false.
	 * 
	 * @return boolean value
	 * @throws SQLException
	 */
	private Boolean createDatabase() throws SQLException {
		if (this.getMySqlConnection() != null && this.dropDatabase()) {
			System.out.println("attempting to create " + this.getName()
					+ " database ...");
			Statement _stmt = this.getMySqlConnection().getConnection()
					.createStatement();
			String _createSql = this.getCreateSqlStatement();
			System.out.println(_createSql);
			_stmt.executeUpdate(_createSql);
			return true;
		}
		return false;
	}

	/**
	 * Public value function that can be used to create a database using the
	 * entities connection object. it returns true if the database was created
	 * successfully. Otherwise it returns false. NOTE - If the database already
	 * exists, calling this method will result in the database first being
	 * dropped.
	 * 
	 * @return boolean value
	 */
	public boolean create() {

		if (this.getMySqlConnection() != null) {
			try {
				return this.createDatabase();
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(2);
			}
		}
		return false;
	}

}
