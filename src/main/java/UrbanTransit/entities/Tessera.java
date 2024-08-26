package UrbanTransit.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "tessera")
public class Tessera {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private LocalDate data_inizio;

    @Column
    private LocalDate data_scadenza;

    @Column
    private boolean stato_tessera;

    @OneToOne
    private Utente utente;

    @OneToMany(mappedBy = "tessera")
    private List<Abbonamento> abbonamenti;

    @OneToMany(mappedBy = "tessera")
    private List<Biglietto> biglietti;


    public Tessera() {
    }

    public Tessera(LocalDate data_inizio, boolean stato_tessera, Utente utente) {
        this.data_inizio = data_inizio;
        this.data_scadenza = data_inizio.plusYears(1);
        this.stato_tessera = stato_tessera;
        this.utente = utente;
    }

    public UUID getId() {
        return id;
    }


    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
    }

    public LocalDate getData_scadenza() {
        return data_scadenza;
    }

    public void setData_scadenza(LocalDate data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    public boolean isStato_tessera() {
        return stato_tessera;
    }

    public void setStato_tessera(boolean stato_tessera) {
        this.stato_tessera = stato_tessera;
    }


    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", data_inizio=" + data_inizio +
                ", data_scadenza=" + data_scadenza +
                ", stato_tessera=" + stato_tessera +
                '}';
    }
}
