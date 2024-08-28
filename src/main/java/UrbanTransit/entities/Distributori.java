package UrbanTransit.entities;


import UrbanTransit.enums.Stato_Distributori;
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
    private Stato_Distributori stato_distributori;

    @Column
    private String indirizzo;

    public Distributori() {
    }

    public Distributori(Stato_Distributori stato_distributori, String indirizzo) {
        this.stato_distributori = stato_distributori;
        this.indirizzo = indirizzo;
    }


    public UUID getId() {
        return id;
    }

    public Stato_Distributori getStato_distribrutori() {
        return stato_distributori;
    }

    public void setStato_distribrutori(Stato_Distributori stato_distributori) {
        this.stato_distributori = stato_distributori;
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
                ", stato_distribrutori=" + stato_distributori +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
