package UrbanTransit.DAO;

import UrbanTransit.entities.Biglietto;
import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class bigliettiDAO {

    public final EntityManager em;


    public bigliettiDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Biglietto biglietto) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(biglietto);
            transaction.commit();
            System.out.println("biglietto salvato " + biglietto.getId());
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
