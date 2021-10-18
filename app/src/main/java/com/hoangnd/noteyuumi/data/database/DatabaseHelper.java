package com.hoangnd.noteyuumi.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hoangnd.noteyuumi.data.constant.Constant;


public class DatabaseHelper extends SQLiteOpenHelper implements Constant {
    public DatabaseHelper(Context context) {
        super(context, "note.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NOTE);
        onCreate(db);
    }
}
