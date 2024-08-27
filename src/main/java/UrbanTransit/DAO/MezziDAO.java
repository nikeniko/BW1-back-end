package UrbanTransit.DAO;

import UrbanTransit.entities.Mezzi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MezziDAO {

    private EntityManager entityManager;

    public MezziDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void createMezzi(Mezzi mezzo) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(mezzo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}