/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voet.datasetcreator.util.connection;

/**
 *
 * @author dev
 */
public interface ConnectionStringBuilder {

    /**
     * Builds a connection string based on all the incoming parameters.
     * <p>
     * This function is specific to each DB provider type.
     * @param host
     * @param port
     * @param dbName
     * @param username
     * @param password
     * @return
     */
    String build( String host, String port, String dbName, String username, String password );
}
