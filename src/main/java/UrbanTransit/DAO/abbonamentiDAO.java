package UrbanTransit.DAO;

import UrbanTransit.entities.Abbonamento;
import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class abbonamentiDAO {

    public final EntityManager em;


    public abbonamentiDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Abbonamento abbonamento) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(abbonamento);
            transaction.commit();
            System.out.println("abbonamento salvato ");
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
