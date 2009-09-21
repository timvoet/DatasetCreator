package com.voet.datasetcreator.data.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the meta data for a DB table.
 * @author tvoet
 */
public class TableMapper {
    private boolean includeOnlyRequired = false;
    private String name;
    private List<ColumnMapper> columns;
    private int rowCount = 1;

    /**
     * Default constructor that accepts the table name.
     * @param name
     */
    public TableMapper( String name ){
        this.name = name;
        columns = new ArrayList<ColumnMapper>();
    }
    /**
     * Returns if only required fields should be persisted to the dataset.
     * @return
     */
    public boolean isIncludeOnlyRequired() {
        return includeOnlyRequired;
    }

    /**
     * Set whether only the required fields should be included in the dataset
     * @param includeOnlyRequired
     */
    public void setIncludeOnlyRequired( boolean includeOnlyRequired ) {
        this.includeOnlyRequired = includeOnlyRequired;
    }

    /**
     * Returns the name of the table
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the table.
     * @param name
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * returns the Row count of how many rows to create in the dataset
     * @return
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Sets how many rows to create in the data set
     * @param rowCount
     */
    public void setRowCount( int rowCount ) {
        this.rowCount = rowCount;
    }

    /**
     * Adds a new column to the table.
     * @param column
     */
    public void addColumn( ColumnMapper column ) {
        column.setTableName( this.getName() );
        this.columns.add( column );
    }


}
