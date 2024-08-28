package UrbanTransit.DAO;

import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class UtenteDAO {

    private EntityManager entityManager;

    public UtenteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createUtente(Utente utente) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(utente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Utente getUtenteById(UUID id) {
        return entityManager.find(Utente.class, id);
    }

    public void updateUtente(UUID id, String nuovoNome, String nuovoCognome, LocalDate nuovoData_nascita) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Utente utente = entityManager.find(Utente.class, id);
            if (utente != null) {
                utente.setNome(nuovoNome);
                utente.setCognome(nuovoCognome);
                utente.setData_nascita(nuovoData_nascita);
                entityManager.merge(utente);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void deleteUtente(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Utente utente = entityManager.find(Utente.class, id);
            if (utente != null) {
                entityManager.remove(utente);
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

