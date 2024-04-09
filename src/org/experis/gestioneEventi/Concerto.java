package org.experis.gestioneEventi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento{
    // attributi
    private LocalTime ora;
    private BigDecimal prezzo;

    //costruttore
    public Concerto(String titolo, LocalDate data, int capienza, LocalTime ora, BigDecimal prezzo) {
        super(titolo, data, capienza);
        this.ora = ora;
        this.prezzo = prezzo;
    }

    // getter e setter
    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }


    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    // metodi
    public String getOraDataFormattata() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return getData().format(formatter) + " " + ora.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getPrezzoFormattato() {
        return String.format("%.2f€", prezzo);
    }

    @Override
    public String toString() {
        return getTitolo() + "\n" + getOraDataFormattata() + "\n" + getPrezzoFormattato();
    }
}
