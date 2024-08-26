package UrbanTransit.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "biglietto")

public class Biglietto {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private LocalDate data_emissione;

    @Column
    private boolean stato_biglietto;

    @ManyToOne
    private Tessera tessera;

    @ManyToOne
    private Rivenditori rivenditore;

    @ManyToOne
    private Distributori distributore;

    @ManyToOne
    private Mezzi mezzo;


    public Biglietto() {
    }

    public Biglietto(LocalDate data_emissione, boolean stato_biglietto) {
        this.data_emissione = data_emissione;
        this.stato_biglietto = stato_biglietto;
    }


    public LocalDate getData_emissione() {
        return data_emissione;
    }

    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
    }

    public boolean isStato_biglietto() {
        return stato_biglietto;
    }

    public void setStato_biglietto(boolean stato_biglietto) {
        this.stato_biglietto = stato_biglietto;
    }

    public UUID getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", data_emissione=" + data_emissione +
                ", stato_biglietto=" + stato_biglietto +

                '}';
    }
}
