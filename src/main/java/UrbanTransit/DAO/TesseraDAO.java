package UrbanTransit.DAO;

import UrbanTransit.entities.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class TesseraDAO {

    private EntityManager entityManager;

    public TesseraDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createTessera(Tessera tessera) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(tessera);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Tessera getTesseraById(UUID id) {
        return entityManager.find(Tessera.class, id);
    }

    public void updateTessera(Tessera tessera) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(tessera);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTessera(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Tessera tessera = entityManager.find(Tessera.class, id);
            if (tessera != null) {
                entityManager.remove(tessera);
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
