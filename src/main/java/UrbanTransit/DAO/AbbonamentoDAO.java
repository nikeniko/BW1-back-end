package UrbanTransit.DAO;

import UrbanTransit.entities.Abbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AbbonamentoDAO {
    private EntityManager em;

    public AbbonamentoDAO(EntityManager em){
        this.em = em;
    }

    //Ottieni un nuovo abbonamento
    public void salvaAbbonamento(Abbonamento abbonamento){
        em.getTransaction().begin();
        em.persist(abbonamento);
        em.getTransaction().commit();
    }

    //Aggiorna abbonamento
    public void aggiornaAbbonamento(Abbonamento abbonamento){
        em.getTransaction().begin();
        em.merge(abbonamento);
        em.getTransaction().commit();
    }

    //Elimina abbonamento
    public void eliminaAbbonamento(Abbonamento abbonamento){
        em.getTransaction().begin();
        em.remove(em.contains(abbonamento) ? abbonamento : em.merge(abbonamento));
        em.getTransaction().commit();
    }

    // Metodo per trovare un abbonamento per ID
    public Abbonamento trovaAbbonamentoPerId(UUID id) {
        return em.find(Abbonamento.class, id);
    }


    //Metodo per trovare abbonamenti venduti da un distributore in un determinato intervallo di tempo
    public List<Abbonamento> trovaAbbonamentiDistributore(UUID distributoreId, LocalDate dataInizio, LocalDate dataFine){
        TypedQuery<Abbonamento> query = em.createQuery(
                "SELECT a FROM Abbonamento a WHERE a.distributore.id = :distributoreId AND a.data_inizio BETWEEN :dataInizio AND :dataFine", Abbonamento.class);
        query.setParameter("distributoreId", distributoreId);
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);
        return query.getResultList();
    }

    //Metodo per trovare abbonamenti venduti da un rivenditore in un determinato intervallo di tempo
    public List<Abbonamento> trovaAbbonamentiEmessiDaRivenditore(UUID rivenditoreId, LocalDate dataInizio, LocalDate dataFine) {
        TypedQuery<Abbonamento> query = em.createQuery(
                "SELECT a FROM Abbonamento a WHERE a.rivenditore.id = :rivenditoreId AND a.data_inizio BETWEEN :dataInizio AND :dataFine",
                Abbonamento.class
        );
        query.setParameter("rivenditoreId", rivenditoreId);
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);
        return query.getResultList();
    }


}
