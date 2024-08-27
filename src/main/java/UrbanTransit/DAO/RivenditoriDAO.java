package UrbanTransit.DAO;

import UrbanTransit.entities.Rivenditori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

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

    public Rivenditori getRivenditoreById(UUID id) {
        return entityManager.find(Rivenditori.class, id);
    }

    public void updateRivenditori(Rivenditori rivenditore) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(rivenditore);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteRivenditori(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Rivenditori rivenditore = entityManager.find(Rivenditori.class, id);
            if (rivenditore != null) {
                entityManager.remove(rivenditore);
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
