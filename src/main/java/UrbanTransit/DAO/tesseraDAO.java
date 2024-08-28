package UrbanTransit.DAO;

import UrbanTransit.entities.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


public class tesseraDAO {

    public final EntityManager em;

//public static final Logger log = LoggerFactory.getLogger(tesseraDAO.class);

    public tesseraDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Tessera tessera) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(tessera);
            transaction.commit();
            System.out.println("salvataggio avvenuto con successo!!!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

    public Tessera findById (UUID id) {
        return em.find(Tessera.class, id);

    }
}
