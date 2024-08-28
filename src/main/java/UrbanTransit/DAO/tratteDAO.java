package UrbanTransit.DAO;

import UrbanTransit.entities.Tratta;
import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class tratteDAO {

    public final EntityManager em;


    public tratteDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Tratta tratta) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(tratta);
            transaction.commit();
            System.out.println("andata bene!!!");
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
