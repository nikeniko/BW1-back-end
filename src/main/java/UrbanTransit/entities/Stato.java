package UrbanTransit.entities;


import UrbanTransit.enums.Stato_mezzo;
import UrbanTransit.enums.Tipo_mezzo;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "stato")

public class Stato {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Stato_mezzo stato_mezzo;

    private LocalDate data_inizio;
    private LocalDate data_fine;

    @OneToOne
    // mezzo_id
    private Mezzi mezzo;

    @OneToOne(mappedBy = "stato")
    private Percorrenza percorrenza;

    public Stato() {
    }

    public Stato(Stato_mezzo stato_mezzo, LocalDate data_inizio, LocalDate data_fine, Mezzi mezzo) {
        this.stato_mezzo = stato_mezzo;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.mezzo = mezzo;
    }

    public UUID getId() {
        return id;
    }


    public Stato_mezzo getStato_mezzo() {
        return stato_mezzo;
    }

    public void setStato_mezzo(Stato_mezzo stato_mezzo) {
        this.stato_mezzo = stato_mezzo;
    }

    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
    }

    public LocalDate getData_fine() {
        return data_fine;
    }

    public void setData_fine(LocalDate data_fine) {
        this.data_fine = data_fine;
    }

    public Mezzi getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzi mezzo) {
        this.mezzo = mezzo;
    }


    public Percorrenza getPercorrenza() {
        return percorrenza;
    }

    public void setPercorrenza(Percorrenza percorrenza) {
        this.percorrenza = percorrenza;
    }

    @Override
    public String toString() {
        return "Stato{" +
                "id=" + id +
                ", stato_mezzo=" + stato_mezzo +
                ", data_inizio=" + data_inizio +
                ", data_fine=" + data_fine +
                ", mezzo=" + mezzo +
                '}';
    }
}