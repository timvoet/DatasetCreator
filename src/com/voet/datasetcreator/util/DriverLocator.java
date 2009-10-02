/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.util;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev
 */
public class DriverLocator {

    private static void showinfo() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while ( drivers.hasMoreElements() ) {
            Driver driver = drivers.nextElement();
            System.out.println( "driver:" + driver.getClass().getName() );
        }
    }

    public static List<String> locateDrivers() {
        List<String> knownDrivers = new ArrayList<String>();
        System.out.println( "jdbc.drivers:" + System.getProperty( "jdbc.drivers") );
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while ( drivers.hasMoreElements() ) {
            Driver driver = drivers.nextElement();
            if ( ConnectionStringUtil.isKnownDriver( driver.getClass().getName() ) ){
                knownDrivers.add( driver.getClass().getName() );
            }
            System.out.println( "driver:" + driver.getClass().getName() );
        }
        return knownDrivers;
    }

    public static void main( String[] args ) {
        System.out.println( DriverLocator.locateDrivers().toString() );
    }
}
