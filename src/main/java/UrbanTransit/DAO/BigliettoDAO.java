package UrbanTransit.DAO;

import UrbanTransit.entities.Abbonamento;
import UrbanTransit.entities.Biglietto;
import UrbanTransit.entities.Stato;
import UrbanTransit.enums.Stato_mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BigliettoDAO {

    private EntityManager em;

    public BigliettoDAO(EntityManager entityManager) {
        this.em = entityManager;
    }
    public void createBiglietto(Biglietto biglietto) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(biglietto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Biglietto getBigliettoById(UUID id) {
        return em.find(Biglietto.class, id);
    }


    public void updateBiglietto(UUID id, LocalDate nuovoData_emissione) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Biglietto biglietto = em.find(Biglietto.class, id);
            if (biglietto != null) {
                biglietto.setData_emissione(nuovoData_emissione);
                em.merge(biglietto);
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
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Biglietto biglietto = em.find(Biglietto.class, id);
            if (biglietto != null) {
                em.remove(biglietto);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //Metodo per trovare biglietti venduti da un distributore in un determinato intervallo di tempo
    public List<Biglietto> trovaBigliettiDistributore(UUID distributoreId, LocalDate dataInizio, LocalDate dataFine){
        TypedQuery<Biglietto> query = em.createQuery(
                "SELECT a FROM Biglietto a WHERE a.distributore.id = :distributoreId AND a.data_inizio BETWEEN :dataInizio AND :dataFine", Biglietto.class);
        query.setParameter("distributoreId", distributoreId);
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);
        return query.getResultList();
    }

    //Metodo per trovare biglietti venduti da un rivenditore in un determinato intervallo di tempo
    public List<Biglietto> trovaBigliettiEmessiDaRivenditore(UUID rivenditoreId, LocalDate dataInizio, LocalDate dataFine) {
        TypedQuery<Biglietto> query = em.createQuery(
                "SELECT a FROM Biglietto a WHERE a.rivenditore.id = :rivenditoreId AND a.data_inizio BETWEEN :dataInizio AND :dataFine",
                Biglietto.class
        );
        query.setParameter("rivenditoreId", rivenditoreId);
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);
        return query.getResultList();
    }

    public void annullaBiglietto(UUID bigliettoId) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Biglietto biglietto = em.find(Biglietto.class, bigliettoId);
            if (biglietto != null) {
                biglietto.setValido(false);
                em.merge(biglietto);
                System.out.println("Biglietto con ID: " + bigliettoId + " annullato con successo.");
            } else {
                System.out.println("Biglietto con ID: " + bigliettoId + " non trovato.");
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
