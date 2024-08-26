package UrbanTransit.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "tratta")

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

    @Column
    private int num_giri;

    @Column
    private int tempo_effettivo;

    public Tratta() {
    }

    public Tratta(String zona_partenza, String capolinea, int tempo_percorrenza, int num_giri, int tempo_effettivo) {
        this.zona_partenza = zona_partenza;
        this.capolinea = capolinea;
        this.tempo_percorrenza = tempo_percorrenza;
        this.num_giri = num_giri;
        this.tempo_effettivo = tempo_effettivo;
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

    public int getNum_giri() {
        return num_giri;
    }

    public void setNum_giri(int num_giri) {
        this.num_giri = num_giri;
    }

    public int getTempo_effettivo() {
        return tempo_effettivo;
    }

    public void setTempo_effettivo(int tempo_effettivo) {
        this.tempo_effettivo = tempo_effettivo;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zona_partenza='" + zona_partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempo_percorrenza=" + tempo_percorrenza +
                ", num_giri=" + num_giri +
                ", tempo_effettivo=" + tempo_effettivo +
                '}';
    }
}
