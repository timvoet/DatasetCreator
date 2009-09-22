/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voet.datasetcreator.util;

import com.voet.datasetcreator.util.connection.ConnectionStringBuilder;
import com.voet.datasetcreator.util.connection.provider.OracleThinConnectionStringBuilder;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev
 */
public class ConnectionStringFactory {

    private static Map<String, String> impls = new HashMap<String,String>();

    static{
        impls.put("oracle.jdbc.driver.OracleDriver", OracleThinConnectionStringBuilder.class.getName());
    }

    public static ConnectionStringBuilder getBuilder( String driverClass ){
        try {
            Class c = ConnectionStringBuilder.class.getClassLoader().loadClass( impls.get( driverClass ));
            return (ConnectionStringBuilder)c.newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionStringFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch ( IllegalAccessException ie ) {
            return null;
        } catch ( InstantiationException ie ) {
            return null;
        }
    }
}
