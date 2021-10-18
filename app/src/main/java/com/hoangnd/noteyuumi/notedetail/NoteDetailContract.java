package com.hoangnd.noteyuumi.notedetail;

import android.content.Context;

import com.hoangnd.noteyuumi.data.dao.DAONote;
import com.hoangnd.noteyuumi.data.database.DatabaseHelper;
import com.hoangnd.noteyuumi.data.entity.Note;

public interface NoteDetailContract {
    interface View {
        void deleteSuccessNote();

        void editSuccessNote();

        void editFailure();
    }

    interface Presenter {
        void deleteNote(Context context, DatabaseHelper databaseHelper, DAONote daoNote, Note note);

        void editNote(Context context, DatabaseHelper databaseHelper, DAONote daoNote, int id, String title, String content);

    }
}
