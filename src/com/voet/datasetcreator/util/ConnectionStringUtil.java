package com.voet.datasetcreator.util;

import com.voet.datasetcreator.util.connection.ConnectionStringBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a utility class designed to help build connection strings for databases.
 * @author tvoet
 */
public final class ConnectionStringUtil {

    /**
     * This is the list of known driver types that the application can process.
     */
    private static Map<String, String> knownDrivers = new HashMap<String, String>();

    static {
        knownDrivers.put( "oracle.jdbc.driver.OracleDriver", "Oracle - thin" );
        knownDrivers.put( "com.mysql.jdbc.Driver", "MySQL" );
        knownDrivers.put( "org.apache.derby.jdbc.EmbeddedDriver", "Derby - Embedded" );
        knownDrivers.put( "org.apache.derby.jdbc.ClientDriver", "Derby - Client" );
        knownDrivers.put( "org.hsqldb.jdbcDriver", "HSQLDB" );
    }

    /**
     * Returns the "friendly name of the JDBC driver to display
     * @param driverClass
     * @return
     */
    public static String getDriverName( String driverClass ) {
        return knownDrivers.get( driverClass );
    }

    /**
     * Checks an internal map of known drivers.
     * <p>
     * the purpose behind this, is that different drivers require different formats
     * of connection strings.  We dynamically scan the classpath for drivers available
     * but those drivers must be understood, and be able to generate the
     * appropriate information to connect to the db.
     * @param driverClass The class we are trying to connect using.
     * @return
     */
    public static boolean isKnownDriver( String driverClass ) {
        return knownDrivers.containsKey( driverClass );
    }

    /**
     * Returns a properly formatted connection string.  Uses the input parameters and
     * driver type to build a smart connection string.
     * @param host The hostname where the database server resides
     * @param port The port number to which it accepts connections
     * @param driverClass The class to use to connect.  This has an impact on the connection string
     * @param dbName The database name to connect to
     * @param username The username to connect
     * @param password The password to connect
     * @return
     */
    public static String getConnectionString( String host, String port, String driverClass, String dbName, String schemaName, String username, String password ){
        ConnectionStringBuilder builder = ConnectionStringFactory.getBuilder( driverClass );
        return builder.build(host, port, dbName, schemaName, username, password);
    }

    /**
     * Creates a database connection using the provided information and returns it for use.
     * @param host The server host to connect to.
     * @param port The port the database is listening on.
     * @param driverClass The driver class to connect with.
     * @param dbName The database name to conncect to.
     * @param username The username to connect with.
     * @param password The password to connect with.
     * @return A database connection.
     */
    public static Connection getConnection( String host, String port, String driverClass, String dbName, String schemaName, String username, String password ){
            ConnectionStringBuilder builder = ConnectionStringFactory.getBuilder( driverClass );
            String connectionString = builder.build( host, port, dbName, schemaName,
                    username, password );
            return getConnection( driverClass, connectionString, username,
                    password );
    }

    /**
     * Creates a database connection using the provided information and returns it for use.
     * @param driverClass The driver class to connect with.
     * @param connectionString The connection string to use to connect to the database.
     * @param username The username to connect with.
     * @param password The password to connect with.
     * @return A database connect.
     */
    public static Connection getConnection( String driverClass, String connectionString, String username, String password ){
        try {
            Class.forName( driverClass );
            Connection con = DriverManager.getConnection( connectionString,
                    username, password );
            return con;
        } catch ( SQLException ex ) {
            Logger.getLogger( ConnectionStringUtil.class.getName() ).
                    log( Level.SEVERE, null, ex );
        } catch ( ClassNotFoundException ex ) {
            Logger.getLogger( ConnectionStringUtil.class.getName() ).
                    log( Level.SEVERE, null, ex );
        }
        return null;

    }
}
