/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.data;

import com.voet.datasetcreator.data.entities.ColumnMapper;
import com.voet.datasetcreator.data.entities.SchemaMapper;
import com.voet.datasetcreator.data.entities.TableMapper;
import com.voet.datasetcreator.util.ConnectionStringUtil;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tvoet
 */
public class MetaDataAccessor {

    /**
     * Returns the Table information for the desired schema.
     * @param driverClass The driver class used to connect
     * @param connectionString The connection string to connect with
     * @param dbName The database name to connect to.  Some Database engines don't use this.
     * @param schemaName The schema to connect to.  Some database engines automatically connect you based on username.
     * @param username The username to connect with
     * @param password The password to connect with
     * @return SchemaMapper containing all the tables that are available in the specified schema/dbname combination.
     */
    public static SchemaMapper getTableNames( String driverClass,
            String connectionString, String dbName, String schemaName,
            String username, String password ) {
        SchemaMapper mapper = new SchemaMapper( dbName, schemaName );
        Connection connection = null;
        try {
            connection = getConnection( driverClass, connectionString, username,
                    password );
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet tbrs = dmd.getTables( schemaName, schemaName, null,
                    new String[]{ "TABLE" } );
            while ( tbrs.next() ) {
                mapper.add( tbrs.getString( 3 ) );
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

    /**
     * Returns the column information for the selected tables from the desired schema.
     * @param schema The Schema mapper class
     * @param driverClass The driver class used to connect
     * @param connectionString The connection string to connect with
     * @param dbName The database name to connect to.  Some Database engines don't use this.
     * @param schemaName The schema to connect to.  Some database engines automatically connect you based on username.
     * @param username The username to connect with
     * @param password The password to connect with
     * @return SchemaMapper the meta data information from the DB including the tables and columns selected.
     */
    public static SchemaMapper getColumnInfo( SchemaMapper schema,
            String driverClass, String connectionString, String dbName,
            String schemaName, String username, String password ) {

        SchemaMapper mapper = new SchemaMapper( schema.getDbName(), schema.
                getSchemaName() );
        Connection connection = null;
        try {
            connection = getConnection( driverClass, connectionString, username,
                    password );
            DatabaseMetaData dmd = connection.getMetaData();
            for ( TableMapper tbl : schema.getTables() ) {
                TableMapper newTable = new TableMapper( tbl.getName() );
                Set<String> primaryKeys = new HashSet<String>();
                try {
                    ResultSet prs = dmd.getPrimaryKeys( schema.getSchemaName(),
                            schema.getSchemaName(), tbl.getName() );

                    while ( prs.next() ) {
                        primaryKeys.add( prs.getString( 4 ) );
                    }
                } catch ( Throwable t ) {
                    // Method not implemented by driver so ignore.
                }

                mapper.add( newTable );
                ResultSet trs = dmd.getColumns( schema.getSchemaName(), schema.
                        getSchemaName(), tbl.getName(), "%" );
                while ( trs.next() ) {

                    String colName = trs.getString( "COLUMN_NAME" );
                    int type = trs.getInt( "DATA_TYPE" );
                    int nullableInt = trs.getInt( "NULLABLE" );
                    boolean nullable = ( nullableInt != 0 );
                    ColumnMapper cMapper = null;
                    if ( primaryKeys.contains( colName ) ) {
                        cMapper = new ColumnMapper( newTable.getName(),
                                colName, !nullable,
                                type, true );
                    } else {
                        cMapper = new ColumnMapper( newTable.getName(),
                                colName, !nullable,
                                type );
                    }
                    newTable.addColumn( cMapper );
                }
            }
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

    private static Connection getConnection( String driverClass,
            String connectionString, String username, String password ) {
        return ConnectionStringUtil.getConnection( driverClass, connectionString,
                username, password );
    }
}
