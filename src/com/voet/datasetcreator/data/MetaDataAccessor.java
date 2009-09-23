/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.data;

import com.voet.datasetcreator.data.entities.SchemaMapper;
import com.voet.datasetcreator.util.ConnectionStringUtil;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tvoet
 */
public class MetaDataAccessor {

    public static SchemaMapper getTableNames( String driverClass, String connectionString, String dbName, String schemaName, String username, String password ) {
        SchemaMapper mapper = new SchemaMapper( dbName );
        Connection connection = null;
        try {
            connection = getConnection( driverClass, connectionString, username, password );
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet tbrs = dmd.getTables( schemaName, schemaName, null,
                    new String[]{ "TABLE" } );
            while (tbrs.next()) {
                mapper.add(tbrs.getString(3));
            }
            tbrs.close();
        } catch ( SQLException ex ) {
            Logger.getLogger( MetaDataAccessor.class.getName() ).
                    log( Level.SEVERE, null, ex );
        } finally {
            try {
                connection.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( MetaDataAccessor.class.getName() ).
                        log( Level.SEVERE, null, ex );
            }
        }
        return mapper;
    }

    private static Connection getConnection( String driverClass, String connectionString, String username, String password ){
        return ConnectionStringUtil.getConnection( driverClass, connectionString,
                username, password );
    }
}
