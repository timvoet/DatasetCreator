/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.xml;

import com.voet.datasetcreator.data.ColumnInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tvoet
 */
public class FileCreator {

    private static final String LINE_SEP = System.getProperty("line.separator");

    public static void createFile(File file, List<String> tableNames, List<ColumnInfo> columns, boolean onlyRequired) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("<?xml version='1.0' encoding='UTF-8'?>" + LINE_SEP);
            writer.write("<dataset>" + LINE_SEP);
            for (String tbl : tableNames) {
                writer.write("\t<" + tbl + " ");
                for (ColumnInfo column : columns) {
                    if (column.getTableName().equals(tbl)) {
                        if (onlyRequired && !column.isRequired()) {
                            continue;
                        } else {
                            writer.write(column.getColumnName() + "=\"\" ");
                        }
                    }
                }
                writer.write( "/>" + LINE_SEP );
            }
            writer.write("</dataset>");
            writer.flush();
        } catch (IOException npe) {
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
