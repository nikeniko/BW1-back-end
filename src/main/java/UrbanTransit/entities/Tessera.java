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

    @OneToMany(mappedBy = "tessera", cascade = CascadeType.ALL)
    private List<Abbonamento> abbonamenti;

    @OneToMany(mappedBy = "tessera", cascade = CascadeType.ALL)
    private List<Biglietto> biglietti;


    public Tessera() {
    }

    public Tessera(LocalDate data_inizio, LocalDate data_scadenza, boolean stato_tessera, Utente utente) {
        this.data_inizio = data_inizio;
        this.data_scadenza = data_scadenza;
        this.stato_tessera = stato_tessera;
        this.utente = utente;
    }

    public Tessera(LocalDate data_inizio, LocalDate data_scadenza, Utente utente, boolean stato_tessera, List<Abbonamento> abbonamenti) {
        this.data_inizio = data_inizio;
        this.data_scadenza = data_scadenza;
        this.utente = utente;
        this.stato_tessera = stato_tessera;
        this.abbonamenti = abbonamenti;
    }

    public Tessera(LocalDate data_inizio, LocalDate data_scadenza, boolean stato_tessera, Utente utente, List<Biglietto> biglietti) {
        this.data_inizio = data_inizio;
        this.data_scadenza = data_scadenza;
        this.stato_tessera = stato_tessera;
        this.utente = utente;
        this.biglietti = biglietti;
    }

    public Tessera(LocalDate data_inizio, LocalDate data_scadenza, boolean stato_tessera, Utente utente, List<Abbonamento> abbonamenti, List<Biglietto> biglietti) {
        this.data_inizio = data_inizio;
        this.data_scadenza = data_scadenza;
        this.stato_tessera = stato_tessera;
        this.utente = utente;
        this.abbonamenti = abbonamenti;
        this.biglietti = biglietti;
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

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public List<Abbonamento> getAbbonamenti() {
        return abbonamenti;
    }

    public void setAbbonamenti(List<Abbonamento> abbonamenti) {
        this.abbonamenti = abbonamenti;
    }

    public List<Biglietto> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(List<Biglietto> biglietti) {
        this.biglietti = biglietti;
    }


    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", data_inizio=" + data_inizio +
                ", data_scadenza=" + data_scadenza +
                ", stato_tessera=" + stato_tessera +
                ", utente=" + utente +
                ", abbonamenti=" + abbonamenti +
                ", biglietti=" + biglietti +
                '}';
    }
}
