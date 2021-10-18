package com.hoangnd.noteyuumi.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.hoangnd.noteyuumi.data.constant.Constant;
import com.hoangnd.noteyuumi.data.database.DatabaseHelper;
import com.hoangnd.noteyuumi.data.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class DAONote implements Constant {

    DatabaseHelper databaseHelper;

    public DAONote(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertNote(String title, String content, int completed){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CONTENT, content);
        values.put(COLUMN_COMPLETED, completed);

        long result = database.insert(TABLE_NOTE, null, values);
        database.close();
        return result;
    }

    public Note getNote(int id){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.query(TABLE_NOTE, new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_COMPLETED},
                COLUMN_ID+"=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Note note = null;
        if (cursor!=null){
            cursor.moveToFirst();
            note = new Note(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_COMPLETED)));
        }
        database.close();
        return note;
    }

    public List<Note> getAllNote(){
        List<Note> listNote = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_NOTE;

        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do{

                Note note = new Note(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_COMPLETED)));
                listNote.add(note);

            }while(cursor.moveToNext());
        }
        database.close();
        return listNote;
    }

    public List<Note> getAllNoteCompleted(){
        List<Note> listNote = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_NOTE+" WHERE "+COLUMN_COMPLETED+" = 1";

        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do{

                Note note = new Note(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_COMPLETED)));
                listNote.add(note);

            }while(cursor.moveToNext());
        }
        database.close();
        return listNote;
    }

    public List<Note> getAllNoteNotCompleted(){
        List<Note> listNote = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_NOTE+" WHERE "+COLUMN_COMPLETED+" = 0";

        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do{

                Note note = new Note(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_COMPLETED)));
                listNote.add(note);

            }while(cursor.moveToNext());
        }
        database.close();
        return listNote;
    }

    public int updateNote(Note note){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, note.getTitle());
        values.put(COLUMN_CONTENT, note.getContent());
        values.put(COLUMN_COMPLETED, note.getCompleted());
        return database.update(TABLE_NOTE, values, COLUMN_ID+"=?", new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(Note note){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NOTE, COLUMN_ID+"=?", new String[]{String.valueOf(note.getId())});
    }

    public void deleteNoteCompleted(int completed){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(TABLE_NOTE, COLUMN_COMPLETED+"=" +completed, null);
    }

}
