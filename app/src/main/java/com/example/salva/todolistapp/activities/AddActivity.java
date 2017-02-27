package com.example.salva.todolistapp.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.salva.todolistapp.R;
import com.example.salva.todolistapp.model.Note;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by salva on 20/02/2017.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    EditText title,body,dateTerm;
    ImageView prefer;
    Intent intent;
    boolean stateSpecial;

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
        prefer = (ImageView) findViewById(R.id.id_note_prefer);
        dateTerm = (EditText) findViewById(R.id.id_add_term_date);
        dateTerm.setOnClickListener(this);

       /* dateCreation = (EditText) findViewById(R.id.id_add_date_creation);
        dateUpdate = (EditText) findViewById(R.id.id_add_date_update);*/
        intent=getIntent();
    }
    Calendar myCalendar = Calendar.getInstance();


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);

        dateTerm.setText(sdf.format(myCalendar.getTime()));
    }

        //Note note = new Note(title.getText().toString(),body.getText().toString(),dateTerm.getText().toString(),dateCreation.getText().toString(),dateUpdate.getText().toString());
        public void confirmNote() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(NoteActivity.TITLE,title.getText().toString());
        returnIntent.putExtra(NoteActivity.BODY,body.getText().toString());
        returnIntent.putExtra(NoteActivity.DATAS,dateTerm.getText().toString());
        returnIntent.putExtra(NoteActivity.SPECIAL,stateSpecial);
        /*returnIntent.putExtra(NoteActivity.DATAC, dateCreation.getText().toString());
        returnIntent.putExtra(NoteActivity.DATAU, dateUpdate.getText().toString());*/
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
        if(id == R.id.id_prefer_menu){
            setBookmark();
        }

        return super.onOptionsItemSelected(item);
    }

    public void setBookmark(){
        stateSpecial = !stateSpecial;
        prefer.setVisibility(stateSpecial ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.id_add_term_date){
            new DatePickerDialog(AddActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }
}
