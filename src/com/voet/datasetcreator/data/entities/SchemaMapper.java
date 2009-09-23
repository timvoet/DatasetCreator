package com.voet.datasetcreator.data.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tvoet
 */
public class SchemaMapper {
    private List<TableMapper> tables = new ArrayList<TableMapper>();
    private String dbName;

    public SchemaMapper( String dbName ){
        this.dbName = dbName;

    }

    public void add( String tableName ){
        this.tables.add( new TableMapper( tableName ) );
    }
    public void add( TableMapper tableMapper ) {
        this.tables.add( tableMapper );
    }
    public List<TableMapper> getTables(){
        return this.tables;
    }
}
