package UrbanTransit.DAO;

import UrbanTransit.entities.Stato;
import UrbanTransit.entities.Tessera;
import UrbanTransit.enums.Stato_mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
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

    public void updateStato(UUID id, String nuovoStato_mezzo, LocalDate nuovoData_inizio, LocalDate nuovoData_fine) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Stato stato = entityManager.find(Stato.class, id);
            if (stato != null) {
                stato.setStato_mezzo(Stato_mezzo.valueOf(nuovoStato_mezzo));
                stato.setData_inizio(nuovoData_inizio);
                stato.setData_fine(nuovoData_fine);
                entityManager.merge(stato);
            }

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
