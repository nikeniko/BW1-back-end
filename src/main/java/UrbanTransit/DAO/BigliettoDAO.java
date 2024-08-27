package UrbanTransit.DAO;

import UrbanTransit.entities.Biglietto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
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

    public List<Biglietto> getAllBiglietti() {
        TypedQuery<Biglietto> query = entityManager.createQuery("SELECT b FROM Biglietto b", Biglietto.class);
        return query.getResultList();
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

    public List<Biglietto> getBigliettiByTessera(UUID tesseraId) {
        TypedQuery<Biglietto> query = entityManager.createQuery(
                "SELECT b FROM Biglietto b WHERE b.tessera.id = :tesseraId", Biglietto.class);
        query.setParameter("tesseraId", tesseraId);
        return query.getResultList();
    }
}
