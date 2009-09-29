/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voet.datasetcreator.util.connection.provider;

import com.voet.datasetcreator.util.connection.ConnectionStringBuilder;

/**
 *
 * @author dev
 */
 public class HSQLDBConnectionStringBuilder implements ConnectionStringBuilder {

    @Override
    public String build(String host, String port, String dbName, String schemaName, String username, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append( "jdbc:hsql:");

        if ( "mem".equals( host ) ) {
            sb.append( "mem:" );
            return sb.toString();
        } else if ( host.startsWith( "file" ) ){
            sb.append( "file:/" );
            sb.append( dbName );
            return sb.toString();
        }

        sb.append( host );
        sb.append( ":" );
        sb.append( port );
        sb.append( "/");
        sb.append( dbName );
        return sb.toString();
    }

}
