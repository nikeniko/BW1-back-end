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
    private Stato stato;

    @OneToOne
    private Tratta tratta;



    public Percorrenza() {
    }

    public Percorrenza(int tempo_effettivo, Stato stato ) {
        this.tempo_effettivo = tempo_effettivo;
        this.stato = stato;
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

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }


    @Override
    public String toString() {
        return "Percorrenza{" +
                "id=" + id +
                ", tempo_effettivo=" + tempo_effettivo +
                ", stato=" + stato +
                '}';
    }
}
