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

    @Column
    private boolean valido;

    @ManyToOne
    private Tessera tessera;

    @ManyToOne
    private Rivenditori rivenditore;

    @ManyToOne
    private Distributori distributore;

    @OneToOne(mappedBy = "biglietto")
    private Timbrati timbrati;

    public Biglietto() {
        this.valido = true;
    }

    public Biglietto(LocalDate data_emissione,  Tessera tessera, Rivenditori rivenditore) {
        this.data_emissione = data_emissione;
        this.tessera = tessera;
        this.rivenditore = rivenditore;
        this.valido = true;

    }

    public Biglietto(LocalDate data_emissione,  Tessera tessera, Distributori distributore) {
        this.data_emissione = data_emissione;
        this.tessera = tessera;
        this.distributore = distributore;
        this.valido = true;
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

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
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

    public Timbrati getTimbrati() {
        return timbrati;
    }

    public void setTimbrati(Timbrati timbrati) {
        this.timbrati = timbrati;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", data_emissione=" + data_emissione +
                ", valido=" + valido +
                ", tessera=" + tessera +
                ", rivenditore=" + rivenditore +
                ", distributore=" + distributore +
                ", timbrati=" + timbrati +
                '}';
    }
}
