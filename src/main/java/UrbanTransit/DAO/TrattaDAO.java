package UrbanTransit.DAO;

import UrbanTransit.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class TrattaDAO {

    private EntityManager entityManager;

    public TrattaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createTratta(Tratta tratta) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(tratta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Tratta getTrattaById(UUID id) {
        return entityManager.find(Tratta.class, id);
    }

    public void updateTratta(UUID id, String nuovoZona_partenza, String nuovoCapolinea, int nuovoTempo_percorrenza) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Tratta tratta = entityManager.find(Tratta.class, id);
            if (tratta != null) {
                tratta.setZona_partenza(nuovoZona_partenza);
                tratta.setCapolinea(nuovoCapolinea);
                tratta.setTempo_percorrenza(nuovoTempo_percorrenza);
                entityManager.merge(tratta);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTratta(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Tratta tratta = entityManager.find(Tratta.class, id);
            if (tratta != null) {
                entityManager.remove(tratta);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Ottieni tutte le tratte
    public List<Tratta> trovaTutteTratte() {
        TypedQuery<Tratta> query = entityManager.createQuery("SELECT t FROM Tratta t", Tratta.class);
        return query.getResultList();
    }
}
