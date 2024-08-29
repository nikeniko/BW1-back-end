package UrbanTransit.DAO;

import UrbanTransit.entities.Percorrenza;
import UrbanTransit.entities.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.UUID;

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

    public Percorrenza getPercorrenzaById(UUID id) {
        return entityManager.find(Percorrenza.class, id);
    }

    public void updatePercorrenza(UUID id, int nuovoTempo_effettivo) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Percorrenza percorrenza = entityManager.find(Percorrenza.class, id);
            if (percorrenza != null) {
                percorrenza.setTempo_effettivo(nuovoTempo_effettivo);
                entityManager.merge(percorrenza);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletePercorrenza(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Percorrenza percorrenza = entityManager.find(Percorrenza.class, id);
            if (percorrenza != null) {
                entityManager.remove(percorrenza);
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
