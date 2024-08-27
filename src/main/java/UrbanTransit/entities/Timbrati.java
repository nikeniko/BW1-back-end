package UrbanTransit.entities;


import UrbanTransit.enums.Stato_mezzo;
import UrbanTransit.enums.Tipo_mezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "timbrati")

public class Timbrati {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private LocalDate data_timbro;

    @OneToOne
    private Mezzi mezzo;

    @OneToOne
    private Biglietto biglietto;


    public Timbrati() {
    }

    public Timbrati(LocalDate data_timbro, Mezzi mezzo, Biglietto biglietto) {
        this.data_timbro = data_timbro;
        this.mezzo = mezzo;
        this.biglietto = biglietto;
    }

    public UUID getId() {
        return id;
    }


    public Mezzi getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzi mezzo) {
        this.mezzo = mezzo;
    }

    public LocalDate getData_timbro() {
        return data_timbro;
    }

    public void setData_timbro(LocalDate data_timbro) {
        this.data_timbro = data_timbro;
    }

    public Biglietto getBiglietto() {
        return biglietto;
    }

    public void setBiglietto(Biglietto biglietto) {
        this.biglietto = biglietto;
    }

    @Override
    public String toString() {
        return "Timbrati{" +
                "id=" + id +
                ", data_timbro=" + data_timbro +
                ", mezzo=" + mezzo +
                ", biglietto=" + biglietto +
                '}';
    }
}