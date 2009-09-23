/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voet.datasetcreator.swing;

import com.voet.datasetcreator.util.Tuple;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tvoet
 */
public class MyTableModel extends AbstractTableModel {

    List<Tuple<Boolean,String>> table = new ArrayList<Tuple<Boolean,String>>();
    List<String> columnNames = new ArrayList<String>();
    public MyTableModel(){
        columnNames.add("");
        columnNames.add("Table Name" );
    }
    @Override
    public String getColumnName( int col ) {
        return columnNames.get( col );
    }

    @Override
    public int getRowCount() {
        return this.table.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    @Override
    public boolean isCellEditable(int row, int col) {
         if ( col == 0 ){
             return true;
         }
         return false;
    }

    @Override
    public Object getValueAt( int rowIndex, int columnIndex ) {
        if ( rowIndex < 0 || rowIndex > this.table.size() -1 ){
            return null;
        } else if ( columnIndex < 0 || columnIndex > 1 ){
            return null;
        }
        Tuple<Boolean, String> row = this.table.get( rowIndex );
        if ( columnIndex == 0 ){
            return (Boolean)row.getFirst();
        } else if ( columnIndex == 1 ) {
            return (String)row.getSecond();
        } else {
            return null;
        }

    }

    public void setValueAt(Object value, int row, int col) {
        Tuple<Boolean, String> rowData = this.table.get( row );
        rowData.setFirst( (Boolean)value );
        fireTableCellUpdated(row, col);
    }

    public void add( Tuple<Boolean, String> row ) {
        this.table.add( row );
    }

}
