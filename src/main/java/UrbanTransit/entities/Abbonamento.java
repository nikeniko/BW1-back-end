package UrbanTransit.entities;


import UrbanTransit.enums.Periodicita_abbonamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "abbonamento")

public class Abbonamento {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    @Enumerated (EnumType.STRING)
    private Periodicita_abbonamento periodicita_abbonamento;

    @Column
    private LocalDate data_inizio;

    @ManyToOne
    private Rivenditori rivenditore;

    @ManyToOne
    private Distributori distributore;

    @ManyToOne
    private Tessera tessera;

    public Abbonamento() {
    }

    public Abbonamento(Periodicita_abbonamento periodicita_abbonamento, LocalDate data_inizio, Distributori distributore, Tessera tessera) {
        this.periodicita_abbonamento = periodicita_abbonamento;
        this.data_inizio = data_inizio;
        this.distributore = distributore;
        this.tessera = tessera;
        this.rivenditore = null;
    }


    public Abbonamento(Periodicita_abbonamento periodicita_abbonamento, LocalDate data_inizio, Rivenditori rivenditore, Tessera tessera) {
        this.periodicita_abbonamento = periodicita_abbonamento;
        this.data_inizio = data_inizio;
        this.rivenditore = rivenditore;
        this.tessera = tessera;
        this.distributore = null;
    }

    public UUID getId() {
        return id;
    }

    public Periodicita_abbonamento getPeriodicita_abbonamento() {
        return periodicita_abbonamento;
    }

    public void setPeriodicita_abbonamento(Periodicita_abbonamento periodicita_abbonamento) {
        this.periodicita_abbonamento = periodicita_abbonamento;
    }

    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
    }

    public Rivenditori getRivenditore() {
        return rivenditore;
    }

    public void setRivenditore(Rivenditori rivenditore) {
        this.rivenditore = rivenditore;
    }

    public Distributori getDistributore() {
        return distributore;
    }

    public void setDistributore(Distributori distributore) {
        this.distributore = distributore;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "id=" + id +
                ", periodicita_abbonamento=" + periodicita_abbonamento +
                ", data_inizio=" + data_inizio +
                ", rivenditore=" + rivenditore +
                ", distributore=" + distributore +
                ", tessera=" + tessera +
                '}';
    }
}
