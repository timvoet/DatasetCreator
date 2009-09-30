package com.voet.datasetcreator.data.entities;

/**
 * This class represents the meta data about a specific column
 * @author tvoet
 */
public class ColumnMapper {

    private String tableName;
    private String columnName;
    private boolean required;
    private Integer type;
    private boolean primaryKey;
    private boolean foreignKey;
    private String foreignKeyTable;
    private String foreignKeyColumn;



    /**
     * The default constructor accepting all the necessary fields.
     * @param tableName
     * @param columnName
     * @param required
     * @param type
     */
    public ColumnMapper( String tableName, String columnName, boolean required,
            Integer type ) {
        this( tableName, columnName, required, type, false, false, null, null );

    }

    public ColumnMapper( String tableName, String columnName, boolean required,
            Integer type, boolean primaryKey ){
        this( tableName, columnName, required, type, primaryKey, false, null, null );
    }
    /**
     * The default constructor accepting all the necessary fields.
     * @param tableName
     * @param columnName
     * @param required
     * @param type
     * @param primaryKey
     */
    public ColumnMapper( String tableName, String columnName, boolean required,
            Integer type, boolean primaryKey, boolean foreignKey, String foreignKeyTable, String foreignKeyColumn ) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.required = required;
        this.type = type;
        this.primaryKey = primaryKey;
        this.foreignKey = foreignKey;
        this.foreignKeyTable = foreignKeyTable;
        this.foreignKeyColumn = foreignKeyColumn;
    }

    /**
     * Returns the column name.
     * @return
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Sets the column name.
     * @param columnName
     */
    public void setColumnName( String columnName ) {
        this.columnName = columnName;
    }

    /**
     * Returns if this field is nullable or not.  True means the field is non nullable ( required )
     * @return
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * Sets if the field is required.  Required for these purposes is based on if
     * the field is nullable or not.  If nullable the field is considered not required,
     * if the field is NON NULLABLE then it is considered required.
     * @param required
     */
    public void setRequired( boolean required ) {
        this.required = required;
    }

    /**
     * The table to which the column is associated.
     * @return
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the table name of the column.
     * @param tableName
     */
    public void setTableName( String tableName ) {
        this.tableName = tableName;
    }

    /**
     * Returns the type of the column.  this maps to the {@link java.sql.Types} class.
     * @return
     */
    public Integer getType() {
        return type;
    }

    /**
     * Sets the type of the column.  this maps to the {@link java.sql.Types} class.
     * @param type
     */
    public void setType( Integer type ) {
        this.type = type;
    }
    /**
     *
     * @return
     */
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    /**
     * 
     * @param primaryKey
     */
    public void setPrimaryKey( boolean primaryKey ) {
        this.primaryKey = primaryKey;
    }

    public String getForeignKeyTable() {
        return foreignKeyTable;
    }

    public void setForeignKeyTable( String foreignKeyTable ) {
        this.foreignKeyTable = foreignKeyTable;
    }

    public boolean isForeignKey() {
        return foreignKey;
    }

    public void setForeignKey( boolean isForeignKey ) {
        this.foreignKey = isForeignKey;
    }

    public String getForeignKeyColumn() {
        return foreignKeyColumn;
    }

    public void setForeignKeyColumn( String foreignKeyColumn ) {
        this.foreignKeyColumn = foreignKeyColumn;
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
