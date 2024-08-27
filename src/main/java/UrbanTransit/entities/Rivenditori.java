package UrbanTransit.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "rivenditori")

public class Rivenditori {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String indirizzo;

    @Column
    private String denominazione;



    public Rivenditori() {
    }

    public Rivenditori(String indirizzo, String denominazione) {
        this.indirizzo = indirizzo;
        this.denominazione = denominazione;
    }

    public UUID getId() {
        return id;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    @Override
    public String toString() {
        return "Rivenditori{" +
                "id=" + id +
                ", indirizzo='" + indirizzo + '\'' +
                ", denominazione='" + denominazione + '\'' +
                '}';
    }
}
