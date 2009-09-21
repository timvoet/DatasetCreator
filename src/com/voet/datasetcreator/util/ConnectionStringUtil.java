package com.voet.datasetcreator.util;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a utility class designed to help build connection strings for databases.
 * @author tvoet
 */
public final class ConnectionStringUtil {

    private static Map<String, String> knownDrivers = new HashMap<String, String>();

    static {
        knownDrivers.put( "oracle.jdbc.driver.OracleDriver", "Oracle - thin" );
        knownDrivers.put( "com.mysql.jdbc.Driver", "MySQL" );
        knownDrivers.put( "org.apache.derby.jdbc.EmbeddedDriver", "Derby - Embedded" );
        knownDrivers.put( "org.apache.derby.jdbc.CientDriver", "Derby - Client" );
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
}
