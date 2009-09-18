/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.voet.datasetcreator.data.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tvoet
 */
public class TableMapper {

    private String name;
    private List<ColumnMapper> columns;
    public TableMapper(){
        columns = new ArrayList<ColumnMapper>();
    }

    public void addColumn( ColumnMapper column ) {
        this.columns.add( column );
    }


}
