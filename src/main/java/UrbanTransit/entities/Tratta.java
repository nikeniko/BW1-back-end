package UrbanTransit.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tratta")

public class Tratta {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String zona_partenza;

    @Column
    private String capolinea;

    @Column
    private int tempo_percorrenza;

    @OneToOne(mappedBy = "tratta")
    private Percorrenza percorrenza;


    public Tratta() {
    }

    public Tratta(String zona_partenza, String capolinea, int tempo_percorrenza, Percorrenza percorrenza) {
        this.zona_partenza = zona_partenza;
        this.capolinea = capolinea;
        this.tempo_percorrenza = tempo_percorrenza;
        this.percorrenza = percorrenza;
    }

    public UUID getId() {
        return id;
    }

    public String getZona_partenza() {
        return zona_partenza;
    }

    public void setZona_partenza(String zona_partenza) {
        this.zona_partenza = zona_partenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public int getTempo_percorrenza() {
        return tempo_percorrenza;
    }

    public void setTempo_percorrenza(int tempo_percorrenza) {
        this.tempo_percorrenza = tempo_percorrenza;
    }

    public Percorrenza getPercorrenza() {
        return percorrenza;
    }

    public void setPercorrenza(Percorrenza percorrenza) {
        this.percorrenza = percorrenza;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zona_partenza='" + zona_partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempo_percorrenza=" + tempo_percorrenza +
                // ", percorrenza=" + percorrenza +
                '}';
    }
}
