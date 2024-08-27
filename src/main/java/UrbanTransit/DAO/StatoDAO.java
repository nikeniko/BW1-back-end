package UrbanTransit.DAO;

import UrbanTransit.entities.Stato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

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

    public Stato getStatoById(UUID id) {
        return entityManager.find(Stato.class, id);
    }

    public void updateStato(Stato stato) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(stato);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteStato(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Stato stato = entityManager.find(Stato.class, id);
            if (stato != null) {
                entityManager.remove(stato);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
