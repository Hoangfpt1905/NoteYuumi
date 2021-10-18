package com.hoangnd.noteyuumi.addnote;

public interface AddNoteContract {
    interface View{
        void checkSuccessData();
        void checkFailureData();
    }

    interface Presenter{
        void checkData(String title, String content);
    }
}
