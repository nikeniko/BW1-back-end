package UrbanTransit.DAO;

import UrbanTransit.entities.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TesseraDAO {

    private EntityManager entityManager;

    public TesseraDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void createTessera(Tessera tessera) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(tessera);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
