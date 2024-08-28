package UrbanTransit.DAO;

import UrbanTransit.entities.Timbrati;
import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class timbratiDAO {

    public final EntityManager em;


    public timbratiDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Timbrati timbrati) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(timbrati);
            transaction.commit();
            System.out.println("andata bene!!!");
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
