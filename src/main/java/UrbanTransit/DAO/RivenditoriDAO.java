package UrbanTransit.DAO;

import UrbanTransit.entities.Rivenditori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class RivenditoriDAO {

    private EntityManager entityManager;

    public RivenditoriDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void createRivenditori(Rivenditori rivenditore) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(rivenditore);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}