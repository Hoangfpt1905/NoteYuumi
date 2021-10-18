package com.hoangnd.noteyuumi.notedetail;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.hoangnd.noteyuumi.R;
import com.hoangnd.noteyuumi.data.dao.DAONote;
import com.hoangnd.noteyuumi.data.database.DatabaseHelper;
import com.hoangnd.noteyuumi.note.NoteActivity;


public class NoteDetailActivity extends AppCompatActivity implements NoteDetailContract.View {

    private Toolbar toolbarNoteDetail;
    private CheckBox cbNoteDetail;
    private TextView tvNoteTitle;
    private FloatingActionButton fabEditNote;
    private RelativeLayout noteDetail;
    private EditText edTitle;
    private EditText edContent;
    private DatabaseHelper databaseHelper;
    private DAONote daoNote;
    private NoteDetailPresenter noteDetailPresenter = new NoteDetailPresenter(this);
    int completed;
    int id;
    String title;
    String content;


    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        initViews();
        databaseHelper = new DatabaseHelper(this);
        daoNote = new DAONote(databaseHelper);
        toolbarNoteDetail.setNavigationIcon(R.drawable.ic_back);
        toolbarNoteDetail.setTitle("Note Detail");
        setSupportActionBar(toolbarNoteDetail);
        toolbarNoteDetail.setTitleTextColor(getColor(R.color.colorTitleToolBar));
        toolbarNoteDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoteDetailActivity.this, NoteActivity.class));
                finish();
            }
        });
        Intent intent = getIntent();
        completed = intent.getIntExtra("completed", 0);
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        id = intent.getIntExtra("id", 0);
        Log.e("title", title + "");
        Log.e("completed", completed + "");
        if (completed == 1) {
            cbNoteDetail.setChecked(true);
        } else {
            cbNoteDetail.setChecked(false);
        }
        tvNoteTitle.setText(title);

        fabEditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NoteDetailActivity.this);
                builder.setTitle("NOTE EDIT");
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View viewDialogEditNote = inflater.inflate(R.layout.dialog_note_edit, null);
                edTitle = viewDialogEditNote.findViewById(R.id.edTitle);
                edContent = viewDialogEditNote.findViewById(R.id.edContent);
                edTitle.setText(title);
                edContent.setText(content);

                builder.setView(viewDialogEditNote);
                builder.setNegativeButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = edTitle.getText().toString().trim();
                        String content = edContent.getText().toString().trim();
                        noteDetailPresenter.editNote(NoteDetailActivity.this, databaseHelper, daoNote, id, title, content);
                        tvNoteTitle.setText(title);
                    }
                });
                builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        cbNoteDetail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                noteDetailPresenter.checkNote(NoteDetailActivity.this, databaseHelper, daoNote, id, isChecked);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete_note_detail:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("DELETE");
                builder.setMessage("Do you want delete?");
                builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        noteDetailPresenter.deleteNote(NoteDetailActivity.this, databaseHelper, daoNote, daoNote.getNote(id));
                    }
                });
                builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void deleteSuccessNote() {
        startActivity(new Intent(NoteDetailActivity.this, NoteActivity.class));
        finish();
    }

    @Override
    public void editSuccessNote() {
        Snackbar.make(noteDetail, "Edit Success!", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void editFailure() {
        Snackbar.make(noteDetail, "Edit Failure!", Snackbar.LENGTH_SHORT).show();
    }

    private void initViews() {
        toolbarNoteDetail = findViewById(R.id.toolbarNoteDetail);
        cbNoteDetail = findViewById(R.id.cbNoteDetail);
        tvNoteTitle = findViewById(R.id.tvNoteTitle);
        fabEditNote = findViewById(R.id.fabEditNote);
        noteDetail = findViewById(R.id.noteDetai);
    }
}
