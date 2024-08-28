package UrbanTransit.DAO;

import UrbanTransit.entities.Distributori;
import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class distributoriDAO {


    public final EntityManager em;


    public distributoriDAO(EntityManager em) {
        this.em = em;
    }

    //public static final Logger log = LoggerFactory.getLogger(distributoriDAO.class);


    public void save(Distributori distributore) {

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(distributore);
            transaction.commit();
            System.out.println("distributore salvato!!! " + distributore.getIndirizzo());
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }

    }
 }
