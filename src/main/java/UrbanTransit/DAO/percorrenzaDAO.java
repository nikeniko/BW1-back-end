package UrbanTransit.DAO;

import UrbanTransit.entities.Percorrenza;
import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class percorrenzaDAO {

    public final EntityManager em;


    public percorrenzaDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Percorrenza percorrenza) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(percorrenza);
            transaction.commit();
            System.out.println("percorrenza salvata ");
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
