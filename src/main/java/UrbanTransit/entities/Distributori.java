package UrbanTransit.entities;


import UrbanTransit.enums.Stato_Distribrutori;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "distributori")

public class Distributori {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    @Enumerated (EnumType.STRING)
    private Stato_Distribrutori stato_distribrutori;

    @Column
    private String indirizzo;

    public Distributori() {
    }

    public Distributori(Stato_Distribrutori stato_distribrutori, String indirizzo) {
        this.stato_distribrutori = stato_distribrutori;
        this.indirizzo = indirizzo;
    }


    public UUID getId() {
        return id;
    }

    public Stato_Distribrutori getStato_distribrutori() {
        return stato_distribrutori;
    }

    public void setStato_distribrutori(Stato_Distribrutori stato_distribrutori) {
        this.stato_distribrutori = stato_distribrutori;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }


    @Override
    public String toString() {
        return "Distributori{" +
                "id=" + id +
                ", stato_distribrutori=" + stato_distribrutori +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
