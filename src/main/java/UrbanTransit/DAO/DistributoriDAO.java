package UrbanTransit.DAO;

import UrbanTransit.entities.Distributori;
import UrbanTransit.entities.Stato;
import UrbanTransit.enums.Stato_Distribrutori;
import UrbanTransit.enums.Stato_mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.UUID;

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

    public Distributori getDistributoreById(UUID id) {
        return entityManager.find(Distributori.class, id);
    }


    public void updateDistributori(UUID id, String nuovoIndirizzo, String nuovoStato_distribrutori, LocalDate nuovoData_fine) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Distributori distributori = entityManager.find(Distributori.class, id);
            if (distributori != null) {
                distributori.setIndirizzo(nuovoIndirizzo);
                distributori.setStato_distribrutori(Stato_Distribrutori.valueOf(nuovoStato_distribrutori));
                entityManager.merge(distributori);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void deleteDistributori(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Distributori distributore = entityManager.find(Distributori.class, id);
            if (distributore != null) {
                entityManager.remove(distributore);
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
