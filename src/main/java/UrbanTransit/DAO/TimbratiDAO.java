package UrbanTransit.DAO;

import UrbanTransit.entities.Timbrati;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.UUID;

public class TimbratiDAO {

    private EntityManager entityManager;

    public TimbratiDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createTimbrati(Timbrati timbrati) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(timbrati);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Timbrati getTimbratiById(UUID id) {
        return entityManager.find(Timbrati.class, id);
    }

    public void updateTimbrati(UUID id, LocalDate nuovoData_timbro) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Timbrati timbrati = entityManager.find(Timbrati.class, id);
            if (timbrati != null) {
                timbrati.setData_timbro(nuovoData_timbro);
                entityManager.merge(timbrati);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTimbrati(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Timbrati timbrati = entityManager.find(Timbrati.class, id);
            if (timbrati != null) {
                entityManager.remove(timbrati);
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
