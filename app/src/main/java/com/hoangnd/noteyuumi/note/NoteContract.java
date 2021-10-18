package com.hoangnd.noteyuumi.note;

import com.hoangnd.noteyuumi.data.entity.Note;
import java.util.List;

public interface NoteContract {
    interface View{
        void displayAllNote(List<Note> listNote);
        void displayAddNewNote();
        void NoTasks(List<Note> listNote);
    }

    interface Presenter{
        void loadDataSuccess(List<Note> listNote);
        void addNewNote();
    }
}
