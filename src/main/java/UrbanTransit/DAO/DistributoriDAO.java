package UrbanTransit.DAO;

import UrbanTransit.entities.Distributori;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class DistributoriDAO {

    private final EntityManager em;

    public DistributoriDAO(EntityManager em) {
        this.em = em;
    }

    public void createDistributori(Distributori distributore) {
        em.getTransaction().begin();
        em.persist(distributore);
        em.getTransaction().commit();
    }

    public Distributori findDistributoreById(UUID id) {
        return em.find(Distributori.class, id);
    }

    public void deleteDistributori(UUID id) {
        em.getTransaction().begin();
        Distributori distributore = em.find(Distributori.class, id);
        if (distributore != null) {
            em.remove(distributore);
        }
        em.getTransaction().commit();
    }
}
