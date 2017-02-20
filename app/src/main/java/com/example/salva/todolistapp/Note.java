package com.example.salva.todolistapp;

/**
 * Created by salva on 20/02/2017.
 */

public class Note {
    private String titolo;
    private String corpo;
    private String data_Creazione;
    private String ultima_Modifica;
    private String data_Scadenza;

    public Note(String titolo, String corpo, String data_Creazione, String ultima_Modifica, String data_Scadenza){
        this.titolo=titolo;
        this.corpo=corpo;
        this.data_Creazione=data_Creazione;
        this.ultima_Modifica=ultima_Modifica;
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

    public String getUltima_Modifica() {
        return ultima_Modifica;
    }

    public void setUltima_Modifica(String ultima_Modifica) {
        this.ultima_Modifica = ultima_Modifica;
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

    public void setData_Scadenza(String data_Scadenza) {
        this.data_Scadenza = data_Scadenza;
    }
}
