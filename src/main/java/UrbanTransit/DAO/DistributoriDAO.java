package UrbanTransit.DAO;

import UrbanTransit.entities.Abbonamento;
import UrbanTransit.entities.Biglietto;
import UrbanTransit.entities.Distributori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DistributoriDAO {
    private EntityManager em;

    public DistributoriDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo per salvare un nuovo distributore
    public void salvaDistributore(Distributori distributore) {
        em.getTransaction().begin();
        em.persist(distributore);
        em.getTransaction().commit();
    }

    // Metodo per trovare un distributore per ID
    public Distributori trovaDistributorePerId(UUID id) {
        return em.find(Distributori.class, id);
    }


    // Metodo per trovare tutti i distributori
    public List<Distributori> trovaTuttiDistributori() {
        TypedQuery<Distributori> query = em.createQuery("SELECT d FROM Distributori d", Distributori.class);
        return query.getResultList();
    }

    // Metodo per aggiornare un distributore esistente
    public void aggiornaDistributore(Distributori distributore) {
        em.getTransaction().begin();
        em.merge(distributore);
        em.getTransaction().commit();
    }

    // Metodo per eliminare un distributore
    public void eliminaDistributore(Distributori distributore) {
        em.getTransaction().begin();
        em.remove(em.contains(distributore) ? distributore : em.merge(distributore));
        em.getTransaction().commit();
    }

    // Metodo per trovare abbonamenti emessi da un distributore in un determinato intervallo di tempo
    public List<Abbonamento> trovaAbbonamentiEmessi(UUID distributoreId, LocalDate dataInizio, LocalDate dataFine) {
        TypedQuery<Abbonamento> query = em.createQuery(
                "SELECT a FROM Abbonamento a WHERE a.distributore.id = :distributoreId AND a.data_inizio BETWEEN :dataInizio AND :dataFine",
                Abbonamento.class
        );
        query.setParameter("distributoreId", distributoreId);
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);
        return query.getResultList();
    }

    public List<Biglietto> trovaBigliettiEmessi(UUID distributoreId, LocalDate dataInizio, LocalDate dataFine) {
        TypedQuery<Biglietto> query = em.createQuery(
                "SELECT b FROM Biglietto b WHERE b.distributore.id = :distributoreId AND b.data_emissione BETWEEN :dataInizio AND :dataFine",
                Biglietto.class
        );
        query.setParameter("distributoreId", distributoreId);
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);
        return query.getResultList();
    }
}
