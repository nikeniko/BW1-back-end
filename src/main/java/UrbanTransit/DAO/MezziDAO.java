package UrbanTransit.DAO;

import UrbanTransit.entities.Biglietto;
import UrbanTransit.entities.Mezzi;
import UrbanTransit.entities.Stato;
import UrbanTransit.enums.Stato_mezzo;
import UrbanTransit.enums.Tipo_mezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MezziDAO {

    private EntityManager entityManager;

    public MezziDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createMezzi(Mezzi mezzo) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(mezzo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Mezzi getMezzoById(UUID id) {
        return entityManager.find(Mezzi.class, id);
    }


    public void updateInfoMezzi(UUID id, int nuovoNum_giri, int nuovoCapienza) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Mezzi mezzi = entityManager.find(Mezzi.class, id);
            if (mezzi != null) {
                mezzi.setNum_giri(nuovoNum_giri);
                mezzi.setCapienza(nuovoCapienza);
                entityManager.merge(mezzi);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteMezzi(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Mezzi mezzo = entityManager.find(Mezzi.class, id);
            if (mezzo != null) {
                entityManager.remove(mezzo);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Mezzi> getAllMezzi() {
        return entityManager.createQuery("SELECT m FROM Mezzi m", Mezzi.class).getResultList();
    }

    public List<Mezzi> getMezziByTipo(Tipo_mezzo tipo) {
        return entityManager.createQuery("SELECT m FROM Mezzi m WHERE m.tipo_mezzo = :tipo_mezzo", Mezzi.class)
                .setParameter("tipo_mezzo", tipo)
                .getResultList();
    }
    // Trova i biglietti vidimati su un mezzo in un determinato intervallo di tempo
    public List<Biglietto> trovaBigliettiVidimati(UUID mezzoId, LocalDate dataInizio, LocalDate dataFine) {
        TypedQuery<Biglietto> query = entityManager.createQuery(
                "SELECT b FROM Biglietto b WHERE b.timbrati.mezzo.id = :mezzoId AND b.timbrati.data_timbro BETWEEN :dataInizio AND :dataFine",
                Biglietto.class
        );
        query.setParameter("mezzoId", mezzoId);
        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);
        return query.getResultList();
    }
}




