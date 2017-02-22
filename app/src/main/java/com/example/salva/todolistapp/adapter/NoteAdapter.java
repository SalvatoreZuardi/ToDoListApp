package com.example.salva.todolistapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.salva.todolistapp.R;
import com.example.salva.todolistapp.activities.NoteActivity;
import com.example.salva.todolistapp.model.Note;

import java.util.ArrayList;

/**
 * Created by salva on 20/02/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {

    private final Context context;
    private ArrayList<Note> dataSet = new ArrayList<>();

    public NoteAdapter(Context c) {
        context=c;
    }


    public void addNote(Note note){
        dataSet.add(0,note);
        notifyItemInserted(0);
    }

    public void setDataSet(ArrayList<Note> dataset){
        this.dataSet=dataset;
        notifyDataSetChanged();
    }

    @Override
    public NoteAdapter.NoteVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_note,parent,false);
        return new NoteVH(view);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.NoteVH holder, int position) {

        Note note = dataSet.get(position);
        holder.TitleEt.setText(note.getTitolo());
        holder.BodyEt.setText(note.getCorpo());
        holder.DateCreationEt.setText(note.getData_Creazione());
        holder.DateUpdateEt.setText(note.getdata_Ultima_Modifica());
        holder.DateTermEt.setText(note.getData_Scadenza());




    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class NoteVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView TitleEt,BodyEt,DateTermEt,DateUpdateEt,DateCreationEt;

        public NoteVH(View itemView) {
            super(itemView);
            TitleEt=(TextView) itemView.findViewById(R.id.note_title);
            BodyEt=(TextView) itemView.findViewById(R.id.note_body);
            DateTermEt=(TextView)itemView.findViewById(R.id.note_term_date);
            DateUpdateEt=(TextView)itemView.findViewById(R.id.note_update_date);
            DateCreationEt=(TextView)itemView.findViewById(R.id.note_creation_date);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
