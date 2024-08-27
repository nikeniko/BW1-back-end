package UrbanTransit.DAO;

import UrbanTransit.entities.Percorrenza;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PercorrenzaDAO {

    private EntityManager entityManager;

    public PercorrenzaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void createPercorrenza(Percorrenza percorrenza) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(percorrenza);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}