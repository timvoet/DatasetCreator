/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.util;

import com.voet.datasetcreator.util.connection.ConnectionStringBuilder;
import com.voet.datasetcreator.util.connection.provider.DerbyThinConnectionStringBuilder;
import com.voet.datasetcreator.util.connection.provider.HSQLDBConnectionStringBuilder;
import com.voet.datasetcreator.util.connection.provider.MySqlThinConnectionStringBuilder;
import com.voet.datasetcreator.util.connection.provider.OracleThinConnectionStringBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev
 */
public class ConnectionStringFactory {

    private static Map<String, String> impls = new HashMap<String, String>();

    static {
        impls.put( "oracle.jdbc.OracleDriver", OracleThinConnectionStringBuilder.class.getName() );
        impls.put( "org.apache.derby.jdbc.ClientDriver", DerbyThinConnectionStringBuilder.class.getName() );
        impls.put( "com.mysql.jdbc.Driver", MySqlThinConnectionStringBuilder.class.getName() );
        impls.put( "org.hsqldb.jdbc.JDBCDriver", HSQLDBConnectionStringBuilder.class.getName() );
    }

    public static ConnectionStringBuilder getBuilder( String driverClass ) {
        try {
            if ( impls.containsKey( driverClass ) ) {
                Class c = ConnectionStringBuilder.class.getClassLoader().loadClass( impls.get( driverClass ) );
                return (ConnectionStringBuilder) c.newInstance();
            }
            Logger.getLogger( ConnectionStringFactory.class.getName() ).log( Level.WARNING, "No ConnectionStringBuilder found for class" + driverClass);
            return null;
        } catch ( ClassNotFoundException ex ) {
            Logger.getLogger( ConnectionStringFactory.class.getName() ).log( Level.SEVERE, null, ex );
            return null;
        } catch ( IllegalAccessException ie ) {
            return null;
        } catch ( InstantiationException ie ) {
            return null;
        }
    }
}
