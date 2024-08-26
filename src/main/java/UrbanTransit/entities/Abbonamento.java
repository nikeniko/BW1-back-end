package UrbanTransit.entities;


import UrbanTransit.enums.Periodicita_abbonamento;
import jakarta.persistence.*;

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

    @ManyToOne
    private Rivenditori rivenditore;

    @ManyToOne
    private Tessera tessera;

    @ManyToOne
    private Distributori distributore;

    @ManyToMany(mappedBy = "abbonamenti")
    private List<Mezzi> mezzi = new ArrayList<>();

    public Abbonamento() {
    }

    public Abbonamento(Periodicita_abbonamento periodicita_abbonamento, Tessera tessera) {
        this.periodicita_abbonamento = periodicita_abbonamento;
        this.tessera = tessera;
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


    @Override
    public String toString() {
        return "Abbonamento{" +
                "id=" + id +
                ", periodicita_abbonamento=" + periodicita_abbonamento +

                '}';
    }
}
