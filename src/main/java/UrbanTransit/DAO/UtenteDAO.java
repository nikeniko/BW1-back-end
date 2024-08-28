package UrbanTransit.DAO;

import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO(EntityManager em){
        this.em = em;
    }

    public void salvaUtente(Utente utente){
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
        System.out.println("utente salvato!");
    }

    public Utente trovaUtentePerId(UUID id){
        return em.find(Utente.class, id);
    }

    public List<Utente> trovaTuttiGliUtenti(){
        TypedQuery<Utente> query = em.createQuery("SELECT u FROM Utente u", Utente.class);
        return query.getResultList();
    }

    public void aggiornaUtente(Utente utente){
        em.getTransaction().begin();
        em.merge(utente);
        em.getTransaction().commit();
        System.out.println("utente modificato");
    }

    public void eliminaUtente(Utente utente){
        em.getTransaction().begin();
        em.remove(em.contains(utente) ? utente : em.merge(utente));
        em.getTransaction().commit();
        System.out.println("utente eliminato" + utente.getId());


    }
}