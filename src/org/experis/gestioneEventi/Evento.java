package org.experis.gestioneEventi;

import java.time.LocalDate;

public class Evento {
    // attributi
    private String titolo;
    private LocalDate data;
    private int capienza;
    private int postiPrenotati;

    // costruttore
    public Evento (String titolo, LocalDate data, int capienza) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento è già passata");
        }
        if (capienza <= 0) {
            throw new IllegalArgumentException("La capienza deve essere superiore a 0");
        }

        this.titolo = titolo;
        this.data = data;
        this.capienza = capienza;
        this.postiPrenotati = 0;

    }

    // getter e setter
    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non è disponibile");
        }
        this.data = data;
    }

    public int getCapienza() {
        return capienza;
    }
    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    // metodi
    @Override
    public String toString() {
        return "Titolo: " + titolo + "\n" + "Data: " + data + "\n" + "Capienza: " + capienza + "\n" + "Posti prenotati: " + postiPrenotati;
    }

    public void prenota(int postiDaPrenotare) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Non è possibile prenotare per un evento passato");
        }
        if (postiDaPrenotare > (capienza - postiPrenotati)) {
            throw new IllegalArgumentException("Non ci sono abbastanza posti disponibili");
        }
        postiPrenotati += postiDaPrenotare;
    }
    public void disdici(int postiDaDisdire) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Non è possibile disdire un evento passato");
        }
        if (postiDaDisdire > postiPrenotati) {
            throw new IllegalArgumentException("Non ci sono abbastanza prenotazioni da disdire");
        }
        postiPrenotati -= postiDaDisdire;
    }
}
