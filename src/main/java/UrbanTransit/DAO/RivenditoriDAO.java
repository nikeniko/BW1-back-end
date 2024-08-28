package UrbanTransit.DAO;

import UrbanTransit.entities.Abbonamento;
import UrbanTransit.entities.Biglietto;
import UrbanTransit.entities.Rivenditori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RivenditoriDAO {
    private EntityManager em;

    public RivenditoriDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo per salvare un nuovo rivenditore
    public void salvaRivenditore(Rivenditori rivenditore) {
        em.getTransaction().begin();
        em.persist(rivenditore);
        em.getTransaction().commit();
    }

    // Metodo per trovare tutti i rivenditori
    public List<Rivenditori> trovaTuttiRivenditori() {
        TypedQuery<Rivenditori> query = em.createQuery("SELECT r FROM Rivenditori r", Rivenditori.class);
        return query.getResultList();
    }

    // Metodo per trovare un rivenditore per ID
    public Rivenditori trovaRivenditorePerId(UUID id) {
        return em.find(Rivenditori.class, id);
    }

    // Metodo per aggiornare un rivenditore esistente
    public void aggiornaRivenditore(Rivenditori rivenditore) {
        em.getTransaction().begin();
        em.merge(rivenditore);
        em.getTransaction().commit();
    }

    // Metodo per eliminare un rivenditore
    public void eliminaRivenditore(Rivenditori rivenditore) {
        em.getTransaction().begin();
        em.remove(em.contains(rivenditore) ? rivenditore : em.merge(rivenditore));
        em.getTransaction().commit();
    }

    // Metodo per trovare abbonamenti emessi da un rivenditore in un determinato intervallo di tempo
    public List<Abbonamento> trovaAbbonamentiEmessi(UUID rivenditoreId, LocalDate dataInizio, LocalDate dataFine) {
        TypedQuery<Abbonamento> query = em.createQuery(
                "SELECT a FROM Abbonamento a WHERE a.rivenditore.id = :rivenditoreId AND a.data_inizio BETWEEN :dataInizio AND :dataFine",
                Abbonamento.class
        );
        query.setParameter("rivenditoreId", rivenditoreId);
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);
        return query.getResultList();
    }


    public List<Biglietto> trovaBigliettiEmessi(UUID rivenditoreId, LocalDate dataInizio, LocalDate dataFine) {
        TypedQuery<Biglietto> query = em.createQuery(
                "SELECT b FROM Biglietto b WHERE b.rivenditore.id = :rivenditoreId AND b.data_emissione BETWEEN :dataInizio AND :dataFine",
                Biglietto.class
        );
        query.setParameter("rivenditoreId", rivenditoreId);
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);
        return query.getResultList();
    }
}
