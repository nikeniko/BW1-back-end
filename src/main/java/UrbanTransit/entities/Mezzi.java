package UrbanTransit.entities;


import UrbanTransit.enums.Stato_mezzo;
import UrbanTransit.enums.Tipo_mezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "mezzi")

public class Mezzi {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private int capienza;

    @Column
    private LocalDate inizio_stato;

    @Column
    private LocalDate fine_stato;

    @Column
    @Enumerated (EnumType.STRING)
    private Stato_mezzo stato_mezzo;

    @Column
    @Enumerated (EnumType.STRING)
    private Tipo_mezzo tipo_mezzo;

    @ManyToOne
    private Tratta tratta;


    @ManyToMany
    @JoinTable(
            name = "mezzi_abbonamenti",
            joinColumns = @JoinColumn(name = "mezzi_id"),
            inverseJoinColumns = @JoinColumn(name = "abbonamenti_id")
    )


    private List<Abbonamento> abbonamenti = new ArrayList<>();







    public Mezzi() {
    }

    public Mezzi(int capienza, LocalDate inizio_stato, LocalDate fine_stato, Stato_mezzo stato_mezzo, Tipo_mezzo tipo_mezzo) {
        this.capienza = capienza;
        this.inizio_stato = inizio_stato;
        this.fine_stato = fine_stato;
        this.stato_mezzo = stato_mezzo;
        this.tipo_mezzo = tipo_mezzo;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public LocalDate getInizio_stato() {
        return inizio_stato;
    }

    public void setInizio_stato(LocalDate inizio_stato) {
        this.inizio_stato = inizio_stato;
    }

    public LocalDate getFine_stato() {
        return fine_stato;
    }

    public void setFine_stato(LocalDate fine_stato) {
        this.fine_stato = fine_stato;
    }

    public Stato_mezzo getStato_mezzo() {
        return stato_mezzo;
    }

    public void setStato_mezzo(Stato_mezzo stato_mezzo) {
        this.stato_mezzo = stato_mezzo;
    }

    public Tipo_mezzo getTipo_mezzo() {
        return tipo_mezzo;
    }

    public void setTipo_mezzo(Tipo_mezzo tipo_mezzo) {
        this.tipo_mezzo = tipo_mezzo;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Mezzi{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", inizio_stato=" + inizio_stato +
                ", fine_stato=" + fine_stato +
                ", stato_mezzo=" + stato_mezzo +
                ", tipo_mezzo=" + tipo_mezzo +
                // ", tratta=" + tratta +
                '}';
    }
}
