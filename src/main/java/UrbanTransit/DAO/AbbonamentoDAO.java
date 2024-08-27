package UrbanTransit.DAO;

import UrbanTransit.entities.Abbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AbbonamentoDAO {

    private EntityManager entityManager;

    public AbbonamentoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void createAbbonamento(Abbonamento abbonamento) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(abbonamento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}