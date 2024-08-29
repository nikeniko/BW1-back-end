package UrbanTransit.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "percorrenza")
public class Percorrenza {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private int tempo_effettivo;

    @OneToOne
    //stato_id
    private Stato stato;

    @OneToOne
    //tratta_id
    private Tratta tratta;



    public Percorrenza() {
    }

    public Percorrenza(int tempo_effettivo, Stato stato, Tratta tratta ) {
        this.tempo_effettivo = tempo_effettivo;
        this.stato = stato;
        this.tratta = tratta;
    }

    public Percorrenza(Stato stato, Tratta tratta) {
        this.stato = stato;
        this.tratta = tratta;
    }

    public UUID getId() {
        return id;
    }


    public int getTempo_effettivo() {
        return tempo_effettivo;
    }

    public void setTempo_effettivo(int tempo_effettivo) {
        this.tempo_effettivo = tempo_effettivo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }


    @Override
    public String toString() {
        return "Percorrenza{" +
                "stato=" + stato +
                ", tempo_effettivo=" + tempo_effettivo +
                ", id=" + id +
                ", tratta=" + tratta +
                '}';
    }
}
