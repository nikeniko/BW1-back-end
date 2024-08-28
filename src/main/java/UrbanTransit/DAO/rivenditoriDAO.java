package UrbanTransit.DAO;

import UrbanTransit.entities.Distributori;
import UrbanTransit.entities.Rivenditori;
import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class rivenditoriDAO {


    public final EntityManager em;


    public rivenditoriDAO(EntityManager em) {
        this.em = em;
    }

    //public static final Logger log = LoggerFactory.getLogger(rivenditoriDAO.class);


    public void save(Rivenditori rivenditori) {

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(rivenditori);
            transaction.commit();
            System.out.println("rivenditore salvato. ");
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
        }

    }
}
