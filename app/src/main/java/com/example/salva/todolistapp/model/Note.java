package com.example.salva.todolistapp.model;

/**
 * Created by salva on 20/02/2017.
 */

public class Note {
    private String titolo;
    private String corpo;
    private String data_Creazione;
    private String data_Ultima_Modifica;
    private String data_Scadenza;
    private Status stato;

    public Note() {
    }

    public Note(String titolo, String corpo, String data_Creazione, String ultima_Modifica, String data_Scadenza){
        this.titolo=titolo;
        this.corpo=corpo;

        this.data_Creazione=data_Creazione;
        this.data_Ultima_Modifica=ultima_Modifica;
        this.data_Scadenza=data_Scadenza;

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

    public String getData_Creazione() {
        return data_Creazione;
    }

    public void setData_Creazione(String data_Creazione) {
        this.data_Creazione = data_Creazione;
    }

    public String getData_Scadenza() {
        return data_Scadenza;
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
