package UrbanTransit;

import UrbanTransit.DAO.*;
import UrbanTransit.entities.*;
import UrbanTransit.enums.Stato_mezzo;
import UrbanTransit.enums.Tipo_mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

import static UrbanTransit.enums.Stato_Distribrutori.ATTIVO;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-UrbanTransit");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        RivenditoriDAO rivenditoriDAO = new RivenditoriDAO(em);
        DistributoriDAO distributoriDAO = new DistributoriDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TimbratiDAO timbratiDAO = new TimbratiDAO(em);
        MezziDAO mezziDAO = new MezziDAO(em);
        StatoDAO statoDAO = new StatoDAO(em);
        PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);


        try {

            Utente nuovoUtente = new Utente("Nike","niko", LocalDate.of(1999,12,4));
            utenteDAO.createUtente(nuovoUtente);

            LocalDate data_inizio = LocalDate.now();
            LocalDate data_scadenza = data_inizio.plusYears(1);
            Tessera nuovaTessera = new Tessera(data_inizio,data_scadenza,true,nuovoUtente);
            tesseraDAO.createTessera(nuovaTessera);

            Rivenditori nuovoRivenditore = new Rivenditori("via calzoni", "tabacchi");
            rivenditoriDAO.createRivenditori(nuovoRivenditore);

            Distributori nuovoDistributore = new Distributori(ATTIVO, "via dalle scatole");
            distributoriDAO.createDistributori(nuovoDistributore);

            Biglietto nuovoBiglietto = new Biglietto(LocalDate.now(), nuovaTessera, nuovoDistributore);
            bigliettoDAO.createBiglietto(nuovoBiglietto);

            Mezzi nuovoMezzo = new Mezzi(34, Tipo_mezzo.AUTOBUS, 5);
            mezziDAO.createMezzi(nuovoMezzo);

            Timbrati nuovoTimbrato = new Timbrati(LocalDate.now(), nuovoMezzo,nuovoBiglietto);
            timbratiDAO.createTimbrati(nuovoTimbrato);

            Stato nuovoStato = new Stato(Stato_mezzo.SERVIZIO,LocalDate.of(2024,8,14),LocalDate.now(),nuovoMezzo);
            statoDAO.createStato(nuovoStato);

            Tratta nuovoTratta = new Tratta("via dalle scatole", "via costanzo", 36);
            trattaDAO.createTratta(nuovoTratta);

            Percorrenza  nuovoPercorrenza = new Percorrenza(37 ,nuovoStato , nuovoTratta);
            percorrenzaDAO.createPercorrenza(nuovoPercorrenza);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}