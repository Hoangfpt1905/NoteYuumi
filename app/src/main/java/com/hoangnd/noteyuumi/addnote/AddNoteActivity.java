package com.hoangnd.noteyuumi.addnote;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoangnd.noteyuumi.R;
import com.hoangnd.noteyuumi.data.dao.DAONote;
import com.hoangnd.noteyuumi.data.database.DatabaseHelper;
import com.hoangnd.noteyuumi.note.NoteActivity;

public class AddNoteActivity extends AppCompatActivity implements AddNoteContract.View{

    private Toolbar toolbarAddNote;
    private EditText edTitle;
    private EditText edContent;
    private FloatingActionButton fabSave;

    private DatabaseHelper databaseHelper;
    private DAONote daoNote;
    private AddNotePresenter addNotePresenter = new AddNotePresenter(this);

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edTitle.getText().toString().trim();
                String content = edContent.getText().toString().trim();
                addNotePresenter.checkData(title, content);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initViews(){
        toolbarAddNote = (Toolbar) findViewById(R.id.toolbarAddNote);
        edTitle = (EditText) findViewById(R.id.edTitle);
        edContent = (EditText) findViewById(R.id.edContent);
        fabSave = (FloatingActionButton) findViewById(R.id.fabSave);
        databaseHelper = new DatabaseHelper(this);
        daoNote = new DAONote(databaseHelper);
        toolbarAddNote.setTitle("Add Note");
        setSupportActionBar(toolbarAddNote);
        toolbarAddNote.setTitleTextColor(getColor(R.color.colorTitleToolBar));
        toolbarAddNote.setNavigationIcon(R.drawable.ic_back);
        toolbarAddNote.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void checkSuccessData() {
        String title = edTitle.getText().toString().trim();
        String content = edContent.getText().toString().trim();
        addNotePresenter.addDataToDatabase(AddNoteActivity.this, databaseHelper, daoNote, title, content);
        Log.e("datababseSize", daoNote.getAllNote().size()+"");
        startActivity(new Intent(AddNoteActivity.this, NoteActivity.class));
        finish();
    }

    @Override
    public void checkFailureData() {

    }
}
