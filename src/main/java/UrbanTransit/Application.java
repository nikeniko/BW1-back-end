package UrbanTransit;

import UrbanTransit.DAO.UtenteDAO;
import UrbanTransit.entities.*;
import UrbanTransit.enums.Periodicita_abbonamento;
import UrbanTransit.enums.Stato_Distribrutori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-UrbanTransit");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        UtenteDAO utenteDAO = new UtenteDAO(em);

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            // Menu di selezione
            System.out.println("Seleziona un'opzione:");
            System.out.println("1. Registra Utente");
            System.out.println("2. Trova Utente per ID");
            System.out.println("3. Trova Tutti gli Utenti");
            System.out.println("4. Aggiorna Utente");
            System.out.println("5. Elimina Utente");
            System.out.println("6. Esci");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    //registrazione utente
                    System.out.println("Inserisci nome:");
                    String nome = scanner.nextLine();
                    System.out.println("Inserisci cognome:");
                    String cognome = scanner.nextLine();
                    System.out.println("Inserisci data di nascita:");
                    String dataNascitaStr = scanner.nextLine();
                    LocalDate dataNascita = LocalDate.parse(dataNascitaStr, formatter);

                    Utente nuovoUtente = new Utente();
                    nuovoUtente.setNome(nome);
                    nuovoUtente.setCognome(cognome);
                    nuovoUtente.setData_nascita(dataNascita);

                    utenteDAO.salvaUtente(nuovoUtente);
                    System.out.println("Utente registrato con successo!");
                    System.out.println("ID Utente: " + nuovoUtente.getId());
                    break;
                case 2:
                    //Trova utente per id
                    System.out.println("Inserisci ID utente (UUID):");
                    String id = scanner.nextLine();
                    try {
                        UUID uuid = UUID.fromString(id);
                        Utente utente = utenteDAO.trovaUtentePerId(uuid);

                        if (utente != null) {
                            System.out.println("Dettagli utente:");
                            System.out.println("Nome: " + utente.getNome());
                            System.out.println("Cognome: " + utente.getCognome());
                            System.out.println("Data di nascita: " + utente.getData_nascita());
                        } else {
                            System.out.println("Utente non trovato.");
                        }
                    } catch (IllegalArgumentException e){
                        System.out.println("Formato UUID non valido");
                    }
                    break;
                case 3:
                    //Lista utenti nel db
                    List<Utente> utenti = utenteDAO.trovaTuttiGliUtenti();
                    System.out.println("Lista di tutti gli utenti:");
                    for (Utente u : utenti){
                        System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Cognome: " + u.getCognome() + ", Data di nascita: " + u.getData_nascita());
                    }
                    break;
                case 4:
                    //Aggiorna utente
                    System.out.println("Inserisci id dell'utente da aggiornare:");
                    String idAggiornaStr = scanner.nextLine();
                    try {
                        UUID idAggiorna = UUID.fromString(idAggiornaStr);
                        Utente utenteAggiorna = utenteDAO.trovaUtentePerId(idAggiorna);

                        if (utenteAggiorna != null) {
                            System.out.println("Inserisci nuovo nome (attuale: " + utenteAggiorna.getNome() + "):");
                            String nuovoNome = scanner.nextLine();
                            utenteAggiorna.setNome(nuovoNome);

                            System.out.println("Inserisci nuovo cognome (attuale: " + utenteAggiorna.getCognome() + "):");
                            String nuovoCognome = scanner.nextLine();
                            utenteAggiorna.setCognome(nuovoCognome);

                            System.out.println("Inserisci nuova data di nascita (dd/MM/yyyy) (attuale: " + utenteAggiorna.getData_nascita().format(formatter) + "):");
                            String nuovaDataNascitaStr = scanner.nextLine();
                            LocalDate nuovaDataNascita = LocalDate.parse(nuovaDataNascitaStr, formatter);
                            utenteAggiorna.setData_nascita(nuovaDataNascita);

                            utenteDAO.aggiornaUtente(utenteAggiorna);
                            System.out.println("Utente aggiornato con successo!");
                        } else {
                            System.out.println("Utente non trovato.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Formato UUID non valido.");
                    }
                    break;
                case 5:
                    //Elimina utente
                    System.out.println("Inserisci id dell'utente da eliminare:");
                    String idEliminaStr = scanner.nextLine();
                    try {
                        UUID idElimina = UUID.fromString(idEliminaStr);
                        Utente utenteElimina = utenteDAO.trovaUtentePerId(idElimina);

                        if (utenteElimina != null) {
                            utenteDAO.eliminaUtente(utenteElimina);
                            System.out.println("Utente eliminato con successo!");
                        } else {
                            System.out.println("Utente non trovato.");
                        }
                    } catch (IllegalArgumentException e){
                        System.out.println("Formato UUID non valido");
                    }
                    break;
                case 6:
                    //Esci
                    em.close();
                    emf.close();
                    System.out.println("Applicazione terminata.");
                    return;

                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
            //System.out.println("ti prego");
        }
    }
}
