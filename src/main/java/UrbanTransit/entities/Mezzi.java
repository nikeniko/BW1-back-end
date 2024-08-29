package UrbanTransit.entities;


import UrbanTransit.enums.Stato_mezzo;
import UrbanTransit.enums.Tipo_mezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mezzi")

public class Mezzi {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private int capienza;

    @Column
    @Enumerated(EnumType.STRING)
    private Tipo_mezzo tipo_mezzo;

    private int num_giri;

    @OneToOne(mappedBy = "mezzo", cascade = CascadeType.ALL)
    private Timbrati timbrati;

    @OneToOne(mappedBy = "mezzo", cascade = CascadeType.ALL)
    private Stato stato;


    public Mezzi() {
    }

    public Mezzi(int capienza, Tipo_mezzo tipo_mezzo, int num_giri) {
        this.capienza = capienza;
        this.tipo_mezzo = tipo_mezzo;
        this.num_giri = num_giri;

    }

    public Mezzi(int capienza, Tipo_mezzo tipo_mezzo, int num_giri, Stato stato) {
        this.capienza = capienza;
        this.tipo_mezzo = tipo_mezzo;
        this.num_giri = num_giri;
        this.stato = stato;
    }

    public Tipo_mezzo getTipo_mezzo() {
        return tipo_mezzo;
    }

    public void setTipo_mezzo(Tipo_mezzo tipo_mezzo) {
        this.tipo_mezzo = tipo_mezzo;
    }

    public Timbrati getTimbrati() {
        return timbrati;
    }

    public void setTimbrati(Timbrati timbrati) {
        this.timbrati = timbrati;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public UUID getId() {
        return id;
    }

    public int getNum_giri() {
        return num_giri;
    }

    public void setNum_giri(int num_giri) {
        this.num_giri = num_giri;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Mezzi{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", tipo_mezzo=" + tipo_mezzo +
                ", num_giri=" + num_giri +
                ", timbrati=" + timbrati +
                '}';
    }
}
