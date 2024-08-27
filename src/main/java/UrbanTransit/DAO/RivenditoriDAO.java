package UrbanTransit.DAO;

import UrbanTransit.entities.Rivenditori;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class RivenditoriDAO {

    private final EntityManager em;

    public RivenditoriDAO(EntityManager em) {
        this.em = em;
    }

    public void createRivenditori(Rivenditori rivenditore) {
        em.getTransaction().begin();
        em.persist(rivenditore);
        em.getTransaction().commit();
    }

    public Rivenditori findRivenditoreById(UUID id) {
        return em.find(Rivenditori.class, id);
    }

    public void deleteRivenditori(UUID id) {
        em.getTransaction().begin();
        Rivenditori rivenditore = em.find(Rivenditori.class, id);
        if (rivenditore != null) {
            em.remove(rivenditore);
        }
        em.getTransaction().commit();
    }
}