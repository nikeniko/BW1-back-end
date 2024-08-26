package UrbanTransit;

import UrbanTransit.entities.*;
import UrbanTransit.enums.Periodicita_abbonamento;
import UrbanTransit.enums.Stato_Distribrutori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-UrbanTransit");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();


        Utente Nikeneko = new Utente("nike", "neko", LocalDate.of(1999, 12, 4));
        Tessera tessera1 = new Tessera(LocalDate.of(2024, 8, 25), true, Nikeneko);


        tessera1.setStato_tessera(false);

        Rivenditori rivenditore1 = new Rivenditori("Via Roma, 10", "Tabaccheria Rossi");
        Rivenditori rivenditore2 = new Rivenditori("Piazza Duomo, 5", "Edicola Centrale");
        Rivenditori rivenditore3 = new Rivenditori("Corso Italia, 20", "Bar Stazione");
        Rivenditori rivenditore4 = new Rivenditori("Via Verdi, 15", "Negozio di Souvenir");
        Rivenditori rivenditore5 = new Rivenditori("Viale Mazzini, 50", "Libreria L'Angolo");

        Distributori distributore1 = new Distributori(Stato_Distribrutori.ATTIVO, "Via Roma, 25");
        Distributori distributore2 = new Distributori(Stato_Distribrutori.FUORI_SERVIZIO, "Piazza Duomo, 15");
        Distributori distributore3 = new Distributori(Stato_Distribrutori.FUORI_SERVIZIO, "Corso Italia, 50");
        Distributori distributore4 = new Distributori(Stato_Distribrutori.FUORI_SERVIZIO, "Viale Mazzini, 10");
        Distributori distributore5 = new Distributori(Stato_Distribrutori.ATTIVO, "Via Verdi, 5");




        Biglietto b1 = new Biglietto(LocalDate.of(2024, 8, 26), true, tessera1, rivenditore1 );
        Biglietto b2 = new Biglietto(LocalDate.of(2024, 8, 26), true, tessera1, distributore2 );

        Abbonamento abb1 = new Abbonamento(Periodicita_abbonamento.MENSILI, tessera1);

        System.out.println("Hello World!");
    }
}
