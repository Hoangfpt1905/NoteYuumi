package com.hoangnd.noteyuumi.notedetail;


import android.content.Context;
import android.util.Log;

import com.hoangnd.noteyuumi.data.dao.DAONote;
import com.hoangnd.noteyuumi.data.database.DatabaseHelper;
import com.hoangnd.noteyuumi.data.entity.Note;

public class NoteDetailPresenter implements NoteDetailContract.Presenter {
    private NoteDetailContract.View noteDetailContractView;

    public NoteDetailPresenter(NoteDetailContract.View noteDetailContractView) {
        this.noteDetailContractView = noteDetailContractView;
    }

    public void checkNote(Context context, DatabaseHelper databaseHelper, DAONote daoNote,int id, boolean isChecked){
        databaseHelper = new DatabaseHelper(context);
        daoNote = new DAONote(databaseHelper);
        Note note = daoNote.getNote(id);
        if (isChecked){
            note.setCompleted(1);
            daoNote.updateNote(note);
        }else{
            note.setCompleted(0);
            daoNote.updateNote(note);
        }
    }

    @Override
    public void deleteNote(Context context, DatabaseHelper databaseHelper, DAONote daoNote, Note note) {
        databaseHelper = new DatabaseHelper(context);
        daoNote = new DAONote(databaseHelper);
        daoNote.deleteNote(note);
        noteDetailContractView.deleteSuccessNote();
    }

    @Override
    public void editNote(Context context, DatabaseHelper databaseHelper, DAONote daoNote,int id,String title, String content) {
        if (!(title.equals("") && title.equals(""))){
            databaseHelper = new DatabaseHelper(context);
            daoNote = new DAONote(databaseHelper);
            Note note = daoNote.getNote(id);
            note.setTitle(title);
            note.setContent(content);
            daoNote.updateNote(note);

            Log.e("title", daoNote.getNote(id).getTitle()+"");
            Log.e("content", daoNote.getNote(id).getContent()+"");
            noteDetailContractView.editSuccessNote();
        }else{
            noteDetailContractView.editFailure();
        }

    }
}
