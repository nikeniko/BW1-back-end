package UrbanTransit.DAO;

import UrbanTransit.entities.Tessera;
import UrbanTransit.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
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

    public void updateTessera(UUID id, Boolean nuovoStato_tessera, LocalDate nuovoData_inizio, LocalDate nuovoData_scadenza) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Tessera tessera = entityManager.find(Tessera.class, id);
            if (tessera != null) {
                tessera.setStato_tessera(nuovoStato_tessera);
                tessera.setData_inizio(nuovoData_inizio);
                tessera.setData_scadenza(nuovoData_scadenza);
                entityManager.merge(tessera);
            }

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
