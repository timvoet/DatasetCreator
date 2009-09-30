/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.io;

import com.voet.datasetcreator.data.entities.ColumnMapper;
import com.voet.datasetcreator.data.entities.SchemaMapper;
import com.voet.datasetcreator.data.entities.TableMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.DocumentHelper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author dev
 */
public class DatasetWriter {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:SS" );
    private final static String LINE_SEP = System.getProperty( "line.separator" );
    private final SchemaMapper schema;

    public DatasetWriter( SchemaMapper schema ) {
        this.schema = schema;
    }

    public void writeDataset( File outfile, String fieldChoice, int numRows,
            boolean includeDefaults, boolean respectConstraints ) {
        XMLWriter writer = null;
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            Document doc = buildDocument( fieldChoice, numRows, includeDefaults, respectConstraints );
            writer = new XMLWriter( new FileWriter( outfile ), format );
            writer.write( doc );
            writer.flush();
        } catch ( IOException ex ) {
            Logger.getLogger( DatasetWriter.class.getName() ).log( Level.SEVERE,
                    null, ex );
        } finally {
            if ( writer != null ) {
                try {
                    writer.close();
                } catch ( IOException ex ) {
                    Logger.getLogger( DatasetWriter.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        }
    }

    private String getDefault( Integer type ) {
        switch ( type ) {
            case Types.VARCHAR:
                return "";
            case Types.INTEGER:
                return String.valueOf( 1 );
            case Types.DECIMAL:
                return String.valueOf( 1 );
            case Types.NUMERIC:
                return String.valueOf( 1 );
            case Types.DATE:
                return DATE_FORMAT.format( new Date() );
            case Types.TIMESTAMP:
                return DATE_FORMAT.format( new Date() );
            case Types.BOOLEAN:
                return Boolean.TRUE.toString();
            case Types.BIT:
                return "1";
            case Types.DOUBLE:
                return Double.valueOf( "0" ).toString();
            case Types.FLOAT:
                return Float.valueOf( "0" ).toString();
            default:
                return type.toString();
        }
    }

    private Document buildDocument( String fieldChoice, int numRows,
            boolean includeDefaults, boolean respectConstraints ) {
        Element datasetElement = DocumentHelper.createElement( "dataset" );
        Document doc = DocumentHelper.createDocument( datasetElement );

        for ( TableMapper table : schema.getTables() ){
            int id = 0;
            for ( int i = 0 ; i < numRows; i++ ) {
                id++;

                Element tableElement = datasetElement.addElement( table.getName() );
                for ( ColumnMapper column: table.getColumms() ) {
                    if ( fieldChoice.equals( "ALL" ) ){
                        if ( column.isPrimaryKey() ) {
                            tableElement.addAttribute( column.getColumnName(), String.valueOf( id ) );
                        } else {
                            tableElement.addAttribute( column.getColumnName(), getDefault( column.getType() ) );
                        }

                    } else if ( fieldChoice.equals( "REQ" ) ) {
                        if ( column.isPrimaryKey() ) {
                            tableElement.addAttribute( column.getColumnName(), String.valueOf( id ) );
                        } else if ( column.isRequired() ) {
                            tableElement.addAttribute( column.getColumnName(), getDefault( column.getType() ) );
                        }

                    } else if ( fieldChoice.equals( "NONE" ) ) {

                    }
                }
            }
        }

        return doc;
//        for ( TableMapper table : schema.getTables() ) {
//                int id = 0;
//                for ( int i = 0; i < numRows; i++ ) {
//                    id++;
//                    writer.write( "\t<" );
//                    writer.write( table.getName() );
//
//                    for ( ColumnMapper column : table.getColumms() ) {
//
//                        if ( fieldChoice.equals( "ALL" ) ) {
//                            writer.write( " " );
//                            writer.write( column.getColumnName() );
//                            writer.write( "=\"" );
//                            if ( column.isPrimaryKey() ) {
//                                writer.write( String.valueOf( id ) );
//                            } else if ( includeDefaults ) {
//                                writer.append( getDefault( column.getType() ) );
//                            }
//                            writer.write( "\"" );
//
//                        } else if ( fieldChoice.equals( "REQ" ) ) {
//                            if ( column.isRequired() ) {
//                                writer.write( " " );
//                                writer.write( column.getColumnName() );
//                                writer.write( "=\"" );
//                                if ( column.isPrimaryKey() ) {
//                                    writer.write( String.valueOf( id ) );
//                                } else if ( includeDefaults ) {
//                                    writer.append( getDefault( column.getType() ) );
//                                }
//                                writer.write( "\"" );
//                            }
//                        } else if ( fieldChoice.equals( "NONE" ) ) {
//                        }
//                    }
//                    writer.write( "/>" );
//                    writer.write( LINE_SEP );
//                }
    }
}
