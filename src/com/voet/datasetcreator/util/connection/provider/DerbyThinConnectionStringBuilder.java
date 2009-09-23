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
public class DerbyThinConnectionStringBuilder implements ConnectionStringBuilder {

    @Override
    public String build(String host, String port, String dbName, String schemaName, String username, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append( "jdbc:derby://");
        sb.append( host );
        sb.append( ":" );
        sb.append( port );
        sb.append( "/");
        sb.append( schemaName );
        return sb.toString();
    }

}
