/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.data;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tvoet
 */
public class DatabaseAccessor {

    public static List<String> getTableNames(ConnectionInfo cInfo) {
        List<String> tableNames = new ArrayList<String>();
        Connection con = null;
        try {
            con = getConnection(cInfo);
            Statement st = con.createStatement();

            DatabaseMetaData dmd = con.getMetaData();
            ResultSet tbrs = dmd.getTables(cInfo.getDbName(), cInfo.getDbName(), null, new String[]{"TABLE"});
            while (tbrs.next()) {
                tableNames.add(tbrs.getString(3));
            }
        } catch (ClassNotFoundException cnfe) {
        } catch (SQLException se) {
        } finally {
            if ( con != null ) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseAccessor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return tableNames;
    }

    public static List<ColumnInfo> getColumnMetaData(ConnectionInfo cInfo, List<String> tableNames) {
        List<ColumnInfo> columnInfo = new ArrayList<ColumnInfo>();
        Connection con = null;
        try {
            con = getConnection(cInfo);
            Statement st = con.createStatement();

            DatabaseMetaData dmd = con.getMetaData();

            for (String tblName : tableNames) {
                ResultSet trs = dmd.getColumns(cInfo.getDbName(), cInfo.getDbName(), tblName, "%");
                while (trs.next()) {

                    String colName = trs.getString("COLUMN_NAME");
                    int type = trs.getInt("DATA_TYPE");
                    int nullableInt = trs.getInt("NULLABLE");
                    boolean nullable = ( nullableInt != 0 );
                    ColumnInfo colInfo = new ColumnInfo(tblName, colName, !nullable, type);
                    columnInfo.add(colInfo);

                }
            }
        } catch (ClassNotFoundException cnfe) {
        } catch (SQLException se) {
        }finally {
            if ( con != null ) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseAccessor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return columnInfo;
    }

    private static Connection getConnection(ConnectionInfo cInfo) throws SQLException, ClassNotFoundException {
        Class.forName(cInfo.getDriverClass());
        Connection con =
                DriverManager.getConnection(cInfo.getUrl(), cInfo.getUserName(), cInfo.getPassword());
        return con;
    }
}
