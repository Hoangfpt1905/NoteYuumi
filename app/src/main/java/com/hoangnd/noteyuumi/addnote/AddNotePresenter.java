package com.hoangnd.noteyuumi.addnote;

import android.content.Context;
import com.hoangnd.noteyuumi.data.dao.DAONote;
import com.hoangnd.noteyuumi.data.database.DatabaseHelper;

public class AddNotePresenter implements AddNoteContract.Presenter {

    private AddNoteContract.View addNoteContractView;

    public AddNotePresenter(AddNoteContract.View addNoteContractView) {
        this.addNoteContractView = addNoteContractView;
    }

    public void addDataToDatabase(Context context,DatabaseHelper databaseHelper, DAONote daoNote, String title, String content ){
        databaseHelper = new DatabaseHelper(context);
        daoNote = new DAONote(databaseHelper);
        daoNote.insertNote(title, content, 0);
    }

    @Override
    public void checkData(String title, String content) {
        if (!(title.equals("")&&content.equals(""))){
            addNoteContractView.checkSuccessData();
        }else{
            addNoteContractView.checkFailureData();
        }
    }
}
