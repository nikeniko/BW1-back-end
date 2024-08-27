package UrbanTransit.DAO;

import UrbanTransit.entities.Stato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class StatoDAO {

    private EntityManager entityManager;

    public StatoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createStato(Stato stato) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(stato);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
