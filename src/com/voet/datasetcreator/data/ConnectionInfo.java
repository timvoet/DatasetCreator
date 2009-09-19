/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voet.datasetcreator.data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tvoet
 */
public class ConnectionInfo {

    private static Map<String, String> driverMap = new HashMap<String, String>();
    private String driverName;
    private String dbName;
    private String url;
    private String userName;
    private String password;

    static {
        driverMap.put( "oracle" , "oracle.jdbc.driver.OracleDriver" );
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDriverName(){
        return driverName;
    }
    public String getDriverClass() {
        return driverMap.get(this.driverName);
    }

    public void setDriverName(String driver) {
        this.driverName = driver;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl(){
        return this.url;
    }
    public void setUrl( String url){
        this.url = url;
    }
}
