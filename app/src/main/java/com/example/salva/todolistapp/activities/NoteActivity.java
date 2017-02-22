package com.example.salva.todolistapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.salva.todolistapp.R;
import com.example.salva.todolistapp.adapter.NoteAdapter;
import com.example.salva.todolistapp.model.Note;

/**
 * Created by salva on 20/02/2017.
 */

public class NoteActivity extends AppCompatActivity {


    public static final String TITLE ="Title";
    public static final String BODY ="Body";
    public static final String DATAS="DateS";
    public static final String DATAC="DateC";
    public static final String DATAU="DateU";

    Intent intent;
    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;
    NoteAdapter adapter;

    FloatingActionButton piu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        recyclerView = (RecyclerView) findViewById(R.id.notes_cards_rv);

        /*Toolbar toolbar=(Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        piu=(FloatingActionButton) findViewById(R.id.add_student);
        layoutManager=new LinearLayoutManager(this);
        adapter=new NoteAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        piu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NoteActivity.this,AddActivity.class);
                startActivityForResult(i, 1);
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Note note = new Note();

                note.setTitolo(data.getExtras().getString(TITLE));
                note.setCorpo(data.getExtras().getString(BODY));
                note.setData_Creazione(data.getExtras().getString(DATAC));
                note.setUltima_Modifica(data.getExtras().getString(DATAU));
                note.setData_Scadenza(data.getExtras().getString(DATAS));
                adapter.addNote(note);
                recyclerView.scrollToPosition(0);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


}
