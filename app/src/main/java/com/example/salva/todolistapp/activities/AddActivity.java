package com.example.salva.todolistapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.salva.todolistapp.R;
import com.example.salva.todolistapp.model.Note;

/**
 * Created by salva on 20/02/2017.
 */

public class AddActivity extends AppCompatActivity{

    EditText title,body,dateTerm,dateCreation,dateUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
        Toolbar toolbar=(Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title = (EditText) findViewById(R.id.id_add_title);
        body = (EditText) findViewById(R.id.id_add_body);
        dateTerm = (EditText) findViewById(R.id.id_add_term_date);
        dateCreation = (EditText) findViewById(R.id.id_add_date_creation);
        dateUpdate = (EditText) findViewById(R.id.id_add_date_update);



    }

        //Note note = new Note(title.getText().toString(),body.getText().toString(),dateTerm.getText().toString(),dateCreation.getText().toString(),dateUpdate.getText().toString());
        public void confirmNote() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(NoteActivity.TITLE,title.getText().toString());
        returnIntent.putExtra(NoteActivity.BODY,body.getText().toString());
        returnIntent.putExtra(NoteActivity.DATAS,dateTerm.getText().toString());
        returnIntent.putExtra(NoteActivity.DATAC, dateCreation.getText().toString());
        returnIntent.putExtra(NoteActivity.DATAU, dateUpdate.getText().toString());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.id_add_menu){
            confirmNote();
            return true;
        }
        if(id == android.R.id.home){
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
