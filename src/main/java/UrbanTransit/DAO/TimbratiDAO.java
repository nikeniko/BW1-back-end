package UrbanTransit.DAO;

import UrbanTransit.entities.Timbrati;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TimbratiDAO {

    private EntityManager entityManager;

    public TimbratiDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createTimbrati(Timbrati timbrati) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(timbrati);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}