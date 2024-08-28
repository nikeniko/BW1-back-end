package UrbanTransit.DAO;

import UrbanTransit.entities.Stato;
import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class statoDAO {

    public final EntityManager em;


    public statoDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Stato stato) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(stato);
            transaction.commit();
            System.out.println("stato salvato " );
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
