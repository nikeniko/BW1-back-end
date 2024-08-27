package UrbanTransit;

import UrbanTransit.DAO.BigliettoDAO;
import UrbanTransit.DAO.DistributoriDAO;
import UrbanTransit.DAO.RivenditoriDAO;
import UrbanTransit.entities.*;
import UrbanTransit.enums.Periodicita_abbonamento;
import UrbanTransit.enums.Stato_Distribrutori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static UrbanTransit.enums.Stato_Distribrutori.ATTIVO;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-UrbanTransit");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        RivenditoriDAO rivenditoriDAO = new RivenditoriDAO(em);
        DistributoriDAO distributoriDAO = new DistributoriDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);

        Rivenditori nuovoRivenditore = new Rivenditori("via calzoni","tabbacchi" );
        rivenditoriDAO.createRivenditori(nuovoRivenditore);

        Distributori nuovoDistributore = new Distributori(ATTIVO,"via dalle scatole");
        distributoriDAO.createDistributori(nuovoDistributore);

        Tessera tessera = new Tessera();
        Biglietto nuovoBiglietto = new Biglietto(LocalDate.now(), tessera, nuovoRivenditore);

        bigliettoDAO.createBiglietto(nuovoBiglietto);

        em.close();
        emf.close();
    }
}
