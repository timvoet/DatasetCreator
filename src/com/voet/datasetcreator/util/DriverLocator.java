/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.util;

import java.io.File;
import java.io.IOException;
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

    public static List<String> locateDrivers() {
        List<String> drivers = new ArrayList<String>();
        String path = System.getProperty( "java.class.path" );
        System.out.println( "path:" + path );
        String[] split = path.split( System.getProperty( "path.separator" ) );
        ClassLoader clsLoader = DriverLocator.class.getClassLoader();
        for ( String element : split ) {
            File file = new File( element );
            if ( file.isDirectory() ) {
                continue;
            }
            if ( file.exists() ) {
                try {
                    JarFile jarFile = new JarFile( file );
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while ( entries.hasMoreElements() ) {
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        name = name.replaceAll( "/", "." );
                        if ( name.endsWith( ".class" ) ) {
                            name = name.substring( 0, name.length() - 6 );
                        }

                        Class clazz;
                        try {
                            clazz = clsLoader.loadClass( name );
                            Class[] interfaces = clazz.getInterfaces();
                            for ( Class cls : interfaces ) {
                                if ( cls.getName().equals( "java.sql.Driver" ) ) {
                                    System.out.println( "driver class:" + name );
                                    drivers.add( name );
                                }
                            }
                        } catch ( ClassNotFoundException ex ) {
                            // we don't care since this class can't be found, it can't be a driver
                        } catch ( NoClassDefFoundError ncdfe ) {
                            // we don't care since this class can't be found, it can't be a driver
                        }

                    }
                } catch ( IOException ex ) {
                    Logger.getLogger( DriverLocator.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        }
        return drivers;
    }

    public static void main( String[] args ) {
        System.out.println( DriverLocator.locateDrivers().toString() );
    }
}
