package com.example.salva.todolistapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.salva.todolistapp.R;
import com.example.salva.todolistapp.adapter.NoteAdapter;
import com.example.salva.todolistapp.database.DatabaseHandler;
import com.example.salva.todolistapp.model.Note;

/**
 * Created by salva on 20/02/2017.
 */

public class NoteActivity extends AppCompatActivity {

    Note editingNote;

    private static final int REQUEST_ADD = 1001;
    public static final int REQUEST_EDIT = 1002;

    public static final String TITLE ="Title";
    public static final String BODY ="Body";
    public static final String DATAS="DateS";

    public static final String SPECIAL="special";



    ImageView prefer;

    Intent intent;
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    NoteAdapter adapter;

    FloatingActionButton piu;

    DatabaseHandler dbHandler;

    private static final String LAYOUT_MANAGER_KEY = "LAYOUT_MANAGER_KEY";
    private int STAGGERED_LAYOUT = 20;
    private int LINEAR_LAYOUT = 21;
    private int layoutManagerType = LINEAR_LAYOUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        recyclerView = (RecyclerView) findViewById(R.id.notes_cards_rv);

        Toolbar toolbar=(Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        piu=(FloatingActionButton) findViewById(R.id.add_student);
        layoutManager=getSavedLayoutManager();
        adapter=new NoteAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        /*registerForContextMenu(recyclerView);*/

        piu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NoteActivity.this,AddActivity.class);
                startActivityForResult(i, REQUEST_ADD);
            }
        });

        if(getIntent() != null){
            if (getIntent().getAction() != null) {
                if (getIntent().getAction().equals(Intent.ACTION_SEND)) {
                    Intent i = new Intent(NoteActivity.this, AddActivity.class);
                    i.putExtra(BODY,getIntent().getStringExtra(Intent.EXTRA_TEXT));
                    Log.d("NoteActivity",getIntent().getStringExtra(Intent.EXTRA_TEXT));
                    startActivityForResult(i, REQUEST_ADD);
                }
            }
        }

        dbHandler = new DatabaseHandler(this);
        adapter.setData(dbHandler.getAllNotes());


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences layoutPreferences = getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = layoutPreferences.edit();
        editor.putInt(LAYOUT_MANAGER_KEY,getLayoutManagerTyper());
        editor.apply();

    }

    private RecyclerView.LayoutManager getSavedLayoutManager() {
        SharedPreferences sharedPrefs = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        int layoutManager = sharedPrefs.getInt(LAYOUT_MANAGER_KEY, -1);
        if (layoutManager == STAGGERED_LAYOUT) {
            setLayoutManagerType(layoutManager);
            return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        }
        if (layoutManager == LINEAR_LAYOUT) {
            setLayoutManagerType(layoutManager);
            return new LinearLayoutManager(this);
        }
        return new LinearLayoutManager(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD) {
            if(resultCode == Activity.RESULT_OK){
                Note note = new Note();

                note.setTitolo(data.getStringExtra(TITLE));
                note.setCorpo(data.getStringExtra(BODY));
                note.setSpecial(data.getBooleanExtra(SPECIAL,false)? "special" : "nospecial");
               /* note.setData_Creazione(data.getExtras().getString(DATAC));
                note.setUltima_Modifica(data.getExtras().getString(DATAU));*/
                note.setData_Scadenza(data.getStringExtra(DATAS));
                dbHandler.addNote(note);
                adapter.addNote(note);
                recyclerView.scrollToPosition(0);
            }

        }
        if (requestCode == REQUEST_EDIT){
            if(resultCode == Activity.RESULT_OK){
                editingNote.setTitolo(data.getStringExtra(TITLE));
                editingNote.setCorpo(data.getStringExtra(BODY));
                editingNote.setData_Scadenza(data.getStringExtra(DATAS));
                dbHandler.updateNote(editingNote);
                adapter.updateDataSet(editingNote,adapter.getPosition());
            }
        }
    }

    public ActionMode actionMode;
     public ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.note_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.id_delete_menu:
                    //remove record
                    dbHandler.deletNote(adapter.getNote(adapter.getPosition()));
                    // remove from adapter
                    adapter.removeNote(adapter.getPosition());
                    break;

                case R.id.id_edit_menu:

                    editingNote = adapter.getNote(adapter.getPosition());
                    Intent i = new Intent(NoteActivity.this,AddActivity.class);
                    i.putExtra(TITLE,editingNote.getTitolo());
                    i.putExtra(BODY,editingNote.getCorpo());
                    i.putExtra(DATAS,editingNote.getData_Scadenza());
                    startActivityForResult(i,REQUEST_EDIT);
                    break;

                /*case R.id.id_prefer_menu:
                    if(prefer.getVisibility()==View.INVISIBLE){

                        prefer.setVisibility(View.VISIBLE);
                    }
                    else
                        prefer.setVisibility(View.INVISIBLE);*/


            }
            return false;
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };
    public void setLayoutManagerType(int layoutManagerType) {
        this.layoutManagerType = layoutManagerType;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_change_menu) {
            if (getLayoutManagerTyper() == STAGGERED_LAYOUT) {
                setLayoutManagerType(LINEAR_LAYOUT);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));



            }else{
                setLayoutManagerType(STAGGERED_LAYOUT);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


            }

        }

        return super.onOptionsItemSelected(item);
    }

    public int getLayoutManagerTyper() {

        return layoutManagerType;
    }
    /*@Override
    public boolean onContextItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.id_delete_menu:
                //remove record
                dbHandler.deletNote(adapter.getNote(adapter.getPosition()));
                // remove from adapter
                adapter.removeNote(adapter.getPosition());
                break;

            case R.id.id_edit_menu:

                editingNote = adapter.getNote(adapter.getPosition());
                Intent i = new Intent(this,AddActivity.class);
                i.putExtra(TITLE,editingNote.getTitolo());
                i.putExtra(BODY,editingNote.getCorpo());
                i.putExtra(DATAS,editingNote.getData_Scadenza());
                startActivityForResult(i,REQUEST_EDIT);
                break;

            case R.id.id_note_prefer:

                editingNote = adapter.getNote(adapter.getPosition());

        }

        return super.onContextItemSelected(item);
    }*/




}
