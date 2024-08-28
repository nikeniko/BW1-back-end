package UrbanTransit.DAO;

import UrbanTransit.entities.Biglietto;
import UrbanTransit.entities.Stato;
import UrbanTransit.enums.Stato_mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.UUID;

public class BigliettoDAO {

    private EntityManager entityManager;

    public BigliettoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void createBiglietto(Biglietto biglietto) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(biglietto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Biglietto getBigliettoById(UUID id) {
        return entityManager.find(Biglietto.class, id);
    }


    public void updateBiglietto(UUID id, LocalDate nuovoData_emissione) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Biglietto biglietto = entityManager.find(Biglietto.class, id);
            if (biglietto != null) {
                biglietto.setData_emissione(nuovoData_emissione);
                entityManager.merge(biglietto);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteBiglietto(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Biglietto biglietto = entityManager.find(Biglietto.class, id);
            if (biglietto != null) {
                entityManager.remove(biglietto);
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
