package UrbanTransit.DAO;

import UrbanTransit.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TrattaDAO {

    private EntityManager entityManager;

    public TrattaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void createTratta(Tratta tratta) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(tratta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}