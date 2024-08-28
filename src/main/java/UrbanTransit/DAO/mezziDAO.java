package UrbanTransit.DAO;

import UrbanTransit.entities.Mezzi;
import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class mezziDAO {

    public final EntityManager em;


    public mezziDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Mezzi mezzi) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(mezzi);
            transaction.commit();
            System.out.println("mezzo salvato");
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
