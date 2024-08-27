package UrbanTransit.DAO;

import UrbanTransit.entities.Distributori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DistributoriDAO {

    private EntityManager entityManager;

    public DistributoriDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void createDistributori(Distributori distributore) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(distributore);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
