package UrbanTransit.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "biglietto")

public class Biglietto {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private LocalDate data_emissione;

    @ManyToOne
    private Tessera tessera;

    @ManyToOne
    private Rivenditori rivenditore;

    @ManyToOne
    private Distributori distributore;

    @OneToOne(mappedBy = "biglietto")
    private Timbrati timbrati;

    public Biglietto() {
    }

    public Biglietto(LocalDate data_emissione,  Tessera tessera, Rivenditori rivenditore) {
        this.data_emissione = data_emissione;

        this.tessera = tessera;
        this.rivenditore = rivenditore;
    }

    public Biglietto(LocalDate data_emissione,  Tessera tessera, Distributori distributore) {
        this.data_emissione = data_emissione;

        this.tessera = tessera;
        this.distributore = distributore;
    }


    public LocalDate getData_emissione() {
        return data_emissione;
    }

    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
    }

    public UUID getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", data_emissione=" + data_emissione +
                ", tessera=" + tessera +
                ", rivenditore=" + rivenditore +
                ", distributore=" + distributore +
                ", timbrati=" + timbrati +
                '}';
    }
}
