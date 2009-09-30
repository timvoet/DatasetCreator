package com.voet.datasetcreator.data.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the meta data for a DB table.
 * @author tvoet
 */
public class TableMapper {
    private String name;
    private List<ColumnMapper> columns;

    /**
     * Default constructor that accepts the table name.
     * @param name
     */
    public TableMapper( String name ){
        this.name = name;
        columns = new ArrayList<ColumnMapper>();
    }


    /**
     * Returns the name of the table
     * @return String the name of the table
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the table.
     * @param name The name of the table
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Adds a new column to the table.
     * @param column The Column definition
     */
    public void addColumn( ColumnMapper column ) {
        column.setTableName( this.getName() );
        this.columns.add( column );
    }
    /**
     * Returns the list of columns that are are in the table.
     * @return List<ColumnMapper> of table columns
     */
    public List<ColumnMapper> getColumms(){
        return this.columns;
    }

    /**
     * returns the list of columns that are foreign keys to another table.
     * @return List<ColumnMapper> the list of columns that are foreign keys to other tables.
     */
    public List<ColumnMapper> getForeignKeys(){
        List<ColumnMapper> fkCols = new ArrayList<ColumnMapper>();
        for ( ColumnMapper column: this.columns ){
            if ( column.isForeignKey() ){
                fkCols.add( column );
            }
        }
        return fkCols;
    }
}
