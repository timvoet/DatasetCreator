/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voet.datasetcreator.data.entities;

/**
 *
 * @author tvoet
 */
public class ColumnMapper {

    private String tableName;
    private String columnName;
    private boolean required;
    private Integer type;

    public ColumnMapper( String tableName, String columnName, boolean required,
            Integer type ) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.required = required;
        this.type = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName( String columnName ) {
        this.columnName = columnName;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired( boolean required ) {
        this.required = required;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName( String tableName ) {
        this.tableName = tableName;
    }

    public Integer getType() {
        return type;
    }

    public void setType( Integer type ) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( this.getClass().getName() );
        sb.append( ":[" );
        sb.append( "TableName:" );
        sb.append( getTableName() );
        sb.append( ", " );
        sb.append( "ColName:" );
        sb.append( getColumnName() );
        sb.append( ", " );
        sb.append( "Nullable:" );
        sb.append( isRequired() );
        sb.append( "," );
        sb.append( "Type:" );
        sb.append( getType() );
        sb.append( "]" );
        return sb.toString();
    }
}
