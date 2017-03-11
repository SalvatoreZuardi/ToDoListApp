package com.example.salva.todolistapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salva.todolistapp.R;
import com.example.salva.todolistapp.activities.AddActivity;
import com.example.salva.todolistapp.activities.NoteActivity;
import com.example.salva.todolistapp.database.DatabaseHandler;
import com.example.salva.todolistapp.model.Note;

import java.util.ArrayList;

import static com.example.salva.todolistapp.activities.NoteActivity.REQUEST_EDIT;

/**
 * Created by salva on 20/02/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {

    public static final String TITLE ="Title";
    public static final String BODY ="Body";
    public static final String DATAS="DateS";

    private final Context context;
    private ArrayList<Note> dataSet = new ArrayList<>();
    int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public NoteAdapter(Context c) {
        context=c;
    }


    public void addNote(Note note){
        dataSet.add(0,note);
        notifyItemInserted(0);
    }

    public void updateDataSet(Note note,int position){
        dataSet.set(position,note);
        notifyItemChanged(position);
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
        holder.Special.setVisibility(note.isSpecial().equals("special")?View.VISIBLE:View.INVISIBLE );
        holder.layout.setBackgroundColor(context.getResources().getColor(note.getColor()));
        Log.d("adaptercolor",String.valueOf(note.getColor()));

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void removeNote(int position) {
        dataSet.remove(position);
        notifyItemRemoved(position);

    }

    public Note getNote(int position) {
        return dataSet.get(position);
    }

    public void setData(ArrayList<Note>dataSet){
        this.dataSet=dataSet;
        notifyDataSetChanged();
    }


    public class NoteVH extends RecyclerView.ViewHolder {

        ActionMode mActionMode;
        ActionMode.Callback mActionModeCallBack;

        TextView TitleEt,BodyEt,DateTermEt,DateUpdateEt,DateCreationEt;
        ImageView Special;
        LinearLayout layout;
        View view;

        public NoteVH(final View itemView) {
            super(itemView);
            this.view=itemView;
            TitleEt=(TextView) itemView.findViewById(R.id.note_title);
            BodyEt=(TextView) itemView.findViewById(R.id.note_body);
            DateTermEt=(TextView)itemView.findViewById(R.id.note_term_date);
            DateUpdateEt=(TextView)itemView.findViewById(R.id.note_update_date);
            DateCreationEt=(TextView)itemView.findViewById(R.id.note_creation_date);
            Special=(ImageView)itemView.findViewById(R.id.id_note_prefer);
            layout=(LinearLayout)itemView.findViewById(R.id.layout_id);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mActionMode=((NoteActivity)view.getContext()).actionMode;
                    mActionModeCallBack=((NoteActivity)view.getContext()).mActionModeCallback;

                    if(mActionMode != null){
                    return false;
                    }
                    setPosition(getAdapterPosition());
                    mActionMode = ((NoteActivity)view.getContext()).startSupportActionMode(mActionModeCallBack);
                    return true;
                }
            });

        }




       /* @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = ((NoteActivity)context).getMenuInflater();
            inflater.inflate(R.menu.note_menu,menu);
        }*/


    }
}
