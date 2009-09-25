/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.io;

import com.voet.datasetcreator.data.entities.SchemaMapper;
import com.voet.datasetcreator.data.entities.TableMapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev
 */
public class DatasetWriter {
    private final static String LINE_SEP = System.getProperty("line.separator");
    private final SchemaMapper schema;

    public DatasetWriter( SchemaMapper schema ) {
        this.schema = schema;
    }

    public void writeDataset( File outfile, String fieldChoice, int numRows, boolean includeDefaults ) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( new FileWriter( outfile ) );
            // write file header

            for ( TableMapper table : schema.getTables() ) {
                for ( int i = 0; i < numRows; i++ ){
                    writer.write( "<" );
                    writer.write( table.getName() );

                    writer.write( "/>");
                    writer.write( LINE_SEP );
                }
            }

            // write file footer
            writer.flush();
        } catch ( IOException ex ) {
            Logger.getLogger( DatasetWriter.class.getName() ).log( Level.SEVERE, null, ex );
        } finally {
            try {
                writer.close();
            } catch ( IOException ex ) {
                Logger.getLogger( DatasetWriter.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
    }
}
