package com.example.salva.todolistapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.salva.todolistapp.model.Note;

import java.util.ArrayList;

/**
 * Created by salva on 23/02/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Notes Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_BODY = "body";
    private static final String KEY_DATETERM = "dateterm";
    private static final String KEY_SPECIAL = "isSpecial";
    private static final String KEY_COLOR = "color";

    private static final int DATABASE_VERSION = 7;
    // Database Name
    private static final String DATABASE_NAME = "notes";
    // Contacts table name
    private static final String TABLE_NOTES = "contacts";

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_NOTE_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_TITLE + " TEXT, "
                + KEY_BODY + " TEXT, " + KEY_DATETERM + " TEXT, " + KEY_SPECIAL + " TEXT, " + KEY_COLOR + " INTEGER " + ")";
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);

    }

    public void addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.getTitolo());
        values.put(KEY_BODY, note.getCorpo());
        values.put(KEY_DATETERM, note.getData_Scadenza());
        values.put(KEY_SPECIAL, note.isSpecial());
        values.put(KEY_COLOR, note.getColor());
        // Inserting Row
        long id = db.insert(TABLE_NOTES, null, values);
        note.setId((int)id);
        db.close(); // Closing database connection
    }

        public ArrayList<Note> getAllNotes(){

            ArrayList<Note> notesList = new ArrayList<>();
            String selectQuery = "SELECT  * FROM " + TABLE_NOTES;
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    Note note = new Note();
                    note.setId(Integer.parseInt(cursor.getString(0)));
                    note.setTitolo(cursor.getString(1));
                    note.setCorpo(cursor.getString(2));
                    note.setData_Scadenza(cursor.getString(3));
                    note.setSpecial(cursor.getString(4));
                    note.setColor(Integer.parseInt(cursor.getString(5)));
                    Log.d("getnotecolor",cursor.getString(5));
                    // Adding note to list
                    notesList.add(note);
                } while (cursor.moveToNext());
            }

            return notesList;


        }
    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.getTitolo());
        values.put(KEY_BODY, note.getCorpo());
        values.put(KEY_DATETERM, note.getData_Scadenza());
        values.put(KEY_SPECIAL, note.isSpecial());
        values.put(KEY_COLOR,note.getColor());
        Log.d("dbcolor",String.valueOf(note.getColor()));
        // updating row
        return db.update(TABLE_NOTES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }
    public void deletNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, KEY_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }



}
