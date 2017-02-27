package com.example.salva.todolistapp.model;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by salva on 20/02/2017.
 */

public class Note {
    private int id;
    private static int idCount;
    private String titolo;
    private String corpo;
    private String data_Creazione;
    private String data_Ultima_Modifica;
    private String data_Scadenza;
    private Status stato;
    private String isSpecial;

    public Note() {
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String format = formatter.format(date);

        this.data_Creazione=format;
        this.data_Ultima_Modifica=format;


    }

    public Note(String titolo, String corpo, String data_Scadenza,String isSpecial){
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String format = formatter.format(date);
        this.titolo=titolo;
        this.corpo=corpo;
        this.id=idCount++;
        this.data_Creazione=format;
        this.data_Ultima_Modifica=format;
        this.data_Scadenza=data_Scadenza;
        this.isSpecial=isSpecial;


    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getdata_Ultima_Modifica() {
        return data_Ultima_Modifica;
    }

    public void setUltima_Modifica(String data_Ultima_Modifica) {
        this.data_Ultima_Modifica = data_Ultima_Modifica;
    }

    @Override
    public String toString() {
        return "Note{" +
                "titolo='" + titolo + '\'' +
                ", corpo='" + corpo + '\'' +
                ", data_Creazione='" + data_Creazione + '\'' +
                ", data_Ultima_Modifica='" + data_Ultima_Modifica + '\'' +
                ", data_Scadenza='" + data_Scadenza + '\'' +
                ", stato=" + stato +
                '}';
    }

    public String getData_Creazione() {
        return data_Creazione;
    }

    public void setData_Creazione(String data_Creazione) {
        this.data_Creazione = data_Creazione;
    }

    public String getData_Scadenza() {
        return data_Scadenza;
    }


    public void setSpecial(String special) {
        isSpecial = special;
    }

    public String isSpecial() {
        return isSpecial;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStato() {
        return stato;
    }

    public void setStato(Status stato) {
        this.stato = stato;
    }

    public void setData_Scadenza(String data_Scadenza) {
        this.data_Scadenza = data_Scadenza;
    }
}
