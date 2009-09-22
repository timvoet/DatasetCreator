package com.voet.datasetcreator.util;

import com.voet.datasetcreator.util.connection.ConnectionStringBuilder;
import java.util.HashMap;
import java.util.Map;

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
     * Is the driver a known driver.
     * @param driverClass
     * @return
     */
    public static boolean isKnownDriver( String driverClass ) {
        return knownDrivers.containsKey( driverClass );
    }

    /**
     * Returns a properly formatted connection string.  Uses the input parameters and
     * driver type to build a smart connection string.analyze javadoc
     * @param driverClass
     * @param dbName
     * @param username
     * @param password
     * @return
     */
    public static String getConnectionString( String host, String port, String driverClass, String dbName, String username, String password ){
        ConnectionStringBuilder builder = ConnectionStringFactory.getBuilder( driverClass );
        return builder.build(host, port, dbName, username, password);
    }
}
