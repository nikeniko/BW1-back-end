package UrbanTransit;

import UrbanTransit.DAO.*;
import UrbanTransit.entities.*;
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


        try {

            Utente nuovoUtente = new Utente("Nike","niko", LocalDate.of(1999,12,4));
            utenteDAO.createUtente(nuovoUtente);

            Tessera nuovaTessera = new Tessera();
            nuovaTessera.setUtente(nuovoUtente);
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}