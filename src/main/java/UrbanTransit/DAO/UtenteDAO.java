package UrbanTransit.DAO;

import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;

public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO(EntityManager em){
        this.em = em;
    }

    public void salvaUtente(Utente utente){
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }
}
