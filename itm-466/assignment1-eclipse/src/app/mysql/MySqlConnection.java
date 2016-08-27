package app.mysql;

import java.sql.*;


/**
 * Wrapper class for a MySQL Server
 * connection.
 *
 * @author Brady Houseknecht
 */
public class MySqlConnection {
    private String
            m_str_server,
            m_str_database,
            m_str_username,
            m_str_password,
            m_str_portNumber;

    private EntityType m_enum_entityType;

    public enum EntityType {
        DATABASE,
        TABLE
    }

    /**
     * Default class constructor.
     */
    public MySqlConnection() {
        this.m_str_server = "localhost";
        this.m_str_database = "assignment1";
        this.m_str_username = "root";
        this.m_str_password = "root";
        this.m_str_portNumber = "8889";
    }

    /**
     * Overloaded constructor that can be used
     * to assign the defining attributes of the
     * connection during initialization of the class.
     *
     * @param server   string value equal to the target SQL Server
     * @param database string value equal to the target database
     * @param username string value equal to the user name to use for the connection
     * @param password string value equal to the password to use for the connection
     */
    public MySqlConnection(String server, String database, String port, String username, String password) {

        this.m_str_server = server;
        this.m_str_database = database;
        this.m_str_portNumber = port;
        this.m_str_username = username;
        this.m_str_password = password;

    }

    /**
     * @return the server
     */
    public String getServer() {
        return this.m_str_server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
        this.m_str_server = server;
    }

    /**
     * @return the database
     */
    public String getDatabase() {
        return m_str_database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(String database) {
        this.m_str_database = database;
    }

    /**
     * @return the user name
     */
    public String getUsername() {
        return m_str_username;
    }

    /**
     * @param username the user name to set
     */
    public void setUsername(String username) {
        this.m_str_username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.m_str_password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.m_str_password = password;
    }

    /**
     * Port number getter.
     *
     * @return string
     */
    public String getPortNumber() {
        return m_str_portNumber;
    }

    /**
     * Port number setter.
     *
     * @param portNumber string
     */
    public void setPortNumber(String port) {
        this.m_str_portNumber = port;
    }

    /**
     * Entity Type getter
     *
     * @return Enum value
     */
    public EntityType getEntityType() {
        return m_enum_entityType;
    }

    /**
     * Entity type setter
     *
     * @param type string value equal to target enum Entity Type
     */
    public void setEntityType(String type) {
        String value = type.toUpperCase().trim();
        this.m_enum_entityType = EntityType.valueOf(value);
    }

    /**
     * Value function that will return a boolean
     * to indicate whether or not the connection
     * can be established using configured attributes
     * of the class instance.
     *
     * @return boolean value indicating whether connection could be established.
     */
    public boolean test() {

        try {
            /// Verify that the MySql Driver class exists
            Class.forName("com.mysql.jdbc.Driver");
            /// Build the connection string
            String _url = "jdbc:mysql://" + this.m_str_server + ":" + this.m_str_portNumber + "/" + this.m_str_database;
            /// Try establishing a connection
            Connection _connection = DriverManager.getConnection(_url, this.m_str_username, this.m_str_password);
            return _connection != null ? true : false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return false;
    }

    /**
     * Connection getter.
     *
     * @return MySql connection object
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {

        Connection _conn = null;
        try {
            /// Verify that the MySql Driver class exists
            Class.forName("com.mysql.jdbc.Driver");
            /// Build the connection string
            String _url = "jdbc:mysql://" + this.m_str_server + ":" + this.m_str_portNumber + "/" + this.m_str_database;
            /// Try establishing a connection
            _conn = DriverManager.getConnection(_url, this.m_str_username, this.m_str_password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return _conn;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "jdbc:mysql://" + this.m_str_server + ":" + this.m_str_portNumber + "/" + this.m_str_database;
    }

}
