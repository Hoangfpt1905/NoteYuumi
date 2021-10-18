package com.hoangnd.noteyuumi.data.constant;

public interface Constant {

    String TABLE_NOTE = "Note";
    String COLUMN_ID = "ID";
    String COLUMN_TITLE = "Title";
    String COLUMN_CONTENT = "Content";
    String COLUMN_COMPLETED = "Completed";

    String CREATE_TABLE_NOTE = "CREATE TABLE "+TABLE_NOTE+"("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_TITLE+" VARCHAR(50),"
            +COLUMN_CONTENT+" VARCHAR(1000),"
            +COLUMN_COMPLETED+" INTEGER)";
}
