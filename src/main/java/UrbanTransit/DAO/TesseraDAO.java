package UrbanTransit.DAO;

import UrbanTransit.entities.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class TesseraDAO {
    private EntityManager em;

    public TesseraDAO(EntityManager em){
        this.em = em;
    }

    // Metodo per salvare una nuova tessera
    public void salvaTessera(Tessera tessera) {
        em.getTransaction().begin();
        em.persist(tessera);
        em.getTransaction().commit();
    }

    // Metodo per trovare una tessera per ID
    public Tessera trovaTesseraPerId(UUID id) {
        return em.find(Tessera.class, id);
    }

    // Metodo per trovare tutte le tessere
    public List<Tessera> trovaTutteLeTessere() {
        TypedQuery<Tessera> query = em.createQuery("SELECT t FROM Tessera t", Tessera.class);
        return query.getResultList();
    }

    // Metodo per aggiornare una tessera esistente
    public void aggiornaTessera(Tessera tessera) {
        em.getTransaction().begin();
        em.merge(tessera);
        em.getTransaction().commit();
    }

    // Metodo per eliminare una tessera
    public void eliminaTessera(Tessera tessera) {
        em.getTransaction().begin();
        em.remove(em.contains(tessera) ? tessera : em.merge(tessera));
        em.getTransaction().commit();
    }

    // Metodo per trovare tessere per stato (attiva/inattiva)
    public List<Tessera> trovaTesserePerStato(boolean stato) {
        TypedQuery<Tessera> query = em.createQuery("SELECT t FROM Tessera t WHERE t.stato_tessera = :stato", Tessera.class);
        query.setParameter("stato", stato);
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
