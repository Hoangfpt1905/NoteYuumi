package com.hoangnd.noteyuumi.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoangnd.noteyuumi.R;
import com.hoangnd.noteyuumi.data.dao.DAONote;
import com.hoangnd.noteyuumi.data.database.DatabaseHelper;
import com.hoangnd.noteyuumi.data.entity.Note;
import com.hoangnd.noteyuumi.notedetail.NoteDetailActivity;

import java.util.List;

public class AdapterNote extends RecyclerView.Adapter<AdapterNote.ViewHolder> {

    private List<Note> listNote;

    private DatabaseHelper databaseHelper;
    private DAONote daoNote;

    public AdapterNote(List<Note> listNote) {
        this.listNote = listNote;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_note, viewGroup, false);
        databaseHelper = new DatabaseHelper(viewGroup.getContext());
        daoNote = new DAONote(databaseHelper);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Note note = listNote.get(i);
        viewHolder.tvTitleNote.setText(note.getTitle());
        if (note.getCompleted()==1){
            viewHolder.cbNote.setChecked(true);
        }else{
            viewHolder.cbNote.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cbNote;
        private TextView tvTitleNote;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            cbNote = (CheckBox) itemView.findViewById(R.id.cbNote);
            tvTitleNote = (TextView) itemView.findViewById(R.id.tvTitleNote);
            cbNote.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Note note = listNote.get(getAdapterPosition());
                    if (isChecked){
                        note.setCompleted(1);
                        itemView.setBackgroundResource(R.color.colorSelectedItem);
                        daoNote.updateNote(note);
                    }else{
                        note.setCompleted(0);
                        itemView.setBackgroundResource(R.color.colorTitleToolBar);
                        daoNote.updateNote(note);
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note note = listNote.get(getAdapterPosition());
                    Intent intent = new Intent(itemView.getContext(), NoteDetailActivity.class);
                    intent.putExtra("completed", note.getCompleted());
                    intent.putExtra("title", note.getTitle());
                    intent.putExtra("content", note.getContent());
                    intent.putExtra("id", note.getId());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
