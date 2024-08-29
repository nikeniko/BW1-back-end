package UrbanTransit;

import UrbanTransit.DAO.*;
import UrbanTransit.entities.*;
import UrbanTransit.enums.Periodicita_abbonamento;
import UrbanTransit.enums.Stato_Distributori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;



public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-UrbanTransit");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        UtenteDAO utenteDAO = new UtenteDAO(em);
        DistributoriDAO distributoriDAO = new DistributoriDAO(em);
        RivenditoriDAO rivenditoriDAO = new RivenditoriDAO(em);
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        MezziDAO mezziDAO = new MezziDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.println("Benvenuto in UrbanTransit System!");
            System.out.println("1 - Sono un utente");
            System.out.println("2 - Sono un amministratore");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    gestisciMenuUtente(scanner, formatter, utenteDAO, tesseraDAO, abbonamentoDAO, bigliettoDAO, distributoriDAO, rivenditoriDAO);
                    break;
                case 2:
                    gestisciMenuAmministratore(scanner, formatter, utenteDAO, tesseraDAO, distributoriDAO, rivenditoriDAO, abbonamentoDAO, bigliettoDAO);
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    private static void gestisciMenuUtente(Scanner scanner, DateTimeFormatter formatter, UtenteDAO utenteDAO, TesseraDAO tesseraDAO, AbbonamentoDAO abbonamentoDAO, BigliettoDAO bigliettoDAO, DistributoriDAO distributoriDAO, RivenditoriDAO rivenditoriDAO) {
        while (true) {
            try {
            System.out.println("Cosa vuoi fare oggi?");
            System.out.println("1. Registrati");
            System.out.println("2. Modifica Profilo");
            System.out.println("3. Elimina Profilo");
            System.out.println("4. Ottieni una tessera UrbanTransit");
            System.out.println("5. Compra un biglietto o abbonamento");
            System.out.println("6. Torna al menù precedente");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    registraUtente(scanner, formatter, utenteDAO);
                    break;
                case 2:
                    modificaUtente(scanner, formatter, utenteDAO);
                    break;
                case 3:
                    eliminaUtente(scanner, utenteDAO);
                    break;
                case 4:
                    creaNuovaTessera(scanner, formatter, tesseraDAO, utenteDAO);
                    break;
                case 5:
                    compraBigliettoOAbbonamento(scanner, formatter, abbonamentoDAO, bigliettoDAO, tesseraDAO, distributoriDAO, rivenditoriDAO, utenteDAO);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }

            } catch (InputMismatchException e) {
                System.out.println("Errore: Inserisci un numero valido.");
                scanner.nextLine();
            }
        }
    }

    private static void gestisciMenuAmministratore(Scanner scanner, DateTimeFormatter formatter, UtenteDAO utenteDAO, TesseraDAO tesseraDAO, DistributoriDAO distributoriDAO, RivenditoriDAO rivenditoriDAO, AbbonamentoDAO abbonamentoDAO, BigliettoDAO bigliettoDAO) {
        System.out.println("Inserisci la password amministratore:");
        String password = scanner.nextLine();

        if (!"1234".equals(password)) {
            System.out.println("Password errata. Accesso negato.");
            return;
        }

        while (true) {
            try {
            System.out.println("Cosa vuoi fare oggi?");
            System.out.println("1 - Gestione utenti");
            System.out.println("2 - Gestione tessere");
            System.out.println("3 - Gestione Distributori/Rivenditori");
            System.out.println("4 - Gestione Mezzi");
            System.out.println("5 - Gestione Tratte");
            System.out.println("6 - Esci");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    gestisciUtenti(scanner, formatter, utenteDAO);
                    break;
                case 2:
                    gestisciTessere(scanner, formatter, tesseraDAO);
                    break;
                case 3:
                    gestisciDistributoriERivenditori(scanner, formatter, distributoriDAO, rivenditoriDAO, abbonamentoDAO, bigliettoDAO);
                    break;
                case 4:
                    System.out.println("Funzione non ancora disponibile.");
                case 5:
                    System.out.println("Funzione non ancora disponibile.");
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }

            } catch (InputMismatchException e) {
                    System.out.println("Errore: Inserisci un numero valido.");
                    scanner.nextLine();
            }

        }
    }

    private static void gestisciUtenti(Scanner scanner, DateTimeFormatter formatter, UtenteDAO utenteDAO) {
        while (true) {
            try {
            System.out.println("Gestione Utenti:");
            System.out.println("1 - Trova utente per id");
            System.out.println("2 - Ottieni la lista di tutti gli utenti");
            System.out.println("3 - Modifica utente");
            System.out.println("4 - Elimina utente");
            System.out.println("5 - Torna al menu precedente");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    trovaUtentePerId(scanner, utenteDAO);
                    break;
                case 2:
                    ottieniListaUtenti(utenteDAO);
                    break;
                case 3:
                    modificaUtente(scanner, formatter, utenteDAO);
                    break;
                case 4:
                    eliminaUtente(scanner, utenteDAO);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Errore: Inserisci un numero valido.");
            scanner.nextLine();
        }
        }
    }

    private static void gestisciTessere(Scanner scanner, DateTimeFormatter formatter, TesseraDAO tesseraDAO) {
        while (true) {
            try {
            System.out.println("Gestione Tessere:");
            System.out.println("1 - Ricerca Tessera");
            System.out.println("2 - Ottieni la lista di tutte le tessere");
            System.out.println("3 - Modifica tessera");
            System.out.println("4 - Elimina Tessera");
            System.out.println("5 - Torna al menu precedente");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    ricercaTessera(scanner, tesseraDAO);
                    break;
                case 2:
                    ottieniListaTessere(tesseraDAO);
                    break;
                case 3:
                    modificaTessera(scanner, formatter, tesseraDAO);
                    break;
                case 4:
                    eliminaTessera(scanner, tesseraDAO);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Errore: Inserisci un numero valido.");
            scanner.nextLine();
        }
        }
    }

    private static void gestisciDistributoriERivenditori(Scanner scanner, DateTimeFormatter formatter, DistributoriDAO distributoriDAO, RivenditoriDAO rivenditoriDAO, AbbonamentoDAO abbonamentoDAO, BigliettoDAO bigliettoDAO) {
        while (true) {
            try {
            System.out.println("Gestione Distributori/Rivenditori:");
            System.out.println("1 - Gestione Distributori");
            System.out.println("2 - Gestione Rivenditori");
            System.out.println("3 - Torna al menu precedente");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    gestisciDistributori(scanner, formatter, distributoriDAO, abbonamentoDAO);
                    break;
                case 2:
                    gestisciRivenditori(scanner, formatter, rivenditoriDAO, abbonamentoDAO);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Errore: Inserisci un numero valido.");
            scanner.nextLine();
        }
        }
    }

    private static void gestisciDistributori(Scanner scanner, DateTimeFormatter formatter, DistributoriDAO distributoriDAO, AbbonamentoDAO abbonamentoDAO) {
        while (true) {
            try {
            System.out.println("Gestione Distributori:");
            System.out.println("1 - Elenco Distributori");
            System.out.println("2 - Aggiungi Distributore");
            System.out.println("3 - Modifica Distributore");
            System.out.println("4 - Elimina Distributore");
            System.out.println("5 - Cerca distributore per id");
            System.out.println("6 - Ottieni biglietti emessi da un distributore");
            System.out.println("7 - Ottieni abbonamenti emessi da un distributore");
            System.out.println("8 - Torna al menu precedente");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    elencoDistributori(distributoriDAO);
                    break;
                case 2:
                    aggiungiDistributore(scanner, distributoriDAO);
                    break;
                case 3:
                    modificaDistributore(scanner, distributoriDAO);
                    break;
                case 4:
                    eliminaDistributore(scanner, distributoriDAO);
                    break;
                case 5:
                    cercaDistributorePerId(scanner, distributoriDAO);
                    break;
                case 6:
                    ottieniBigliettiEmessiDaDistributore(scanner, formatter, distributoriDAO);
                    break;
                case 7:
                    ottieniAbbonamentiEmessiDaDistributore(scanner, formatter, abbonamentoDAO);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }

            } catch (InputMismatchException e) {
                System.out.println("Errore: Inserisci un numero valido.");
                scanner.nextLine();
            }
        }
    }

    private static void gestisciRivenditori(Scanner scanner, DateTimeFormatter formatter, RivenditoriDAO rivenditoriDAO, AbbonamentoDAO abbonamentoDAO) {
        while (true) {
            try{
            System.out.println("Gestione Rivenditori:");
            System.out.println("1 - Elenco Rivenditori");
            System.out.println("2 - Aggiungi Rivenditore");
            System.out.println("3 - Modifica Rivenditore");
            System.out.println("4 - Elimina Rivenditore");
            System.out.println("5 - Cerca rivenditore per id");
            System.out.println("6 - Ottieni biglietti emessi da un rivenditore");
            System.out.println("7 - Ottieni abbonamenti emessi da un rivenditore");
            System.out.println("8 - Torna al menu precedente");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    elencoRivenditori(rivenditoriDAO);
                    break;
                case 2:
                    aggiungiRivenditore(scanner, rivenditoriDAO);
                    break;
                case 3:
                    modificaRivenditore(scanner, rivenditoriDAO);
                    break;
                case 4:
                    eliminaRivenditore(scanner, rivenditoriDAO);
                    break;
                case 5:
                    cercaRivenditorePerId(scanner, rivenditoriDAO);
                    break;
                case 6:
                    ottieniBigliettiEmessiDaRivenditore(scanner, formatter, rivenditoriDAO);
                    break;
                case 7:
                    ottieniAbbonamentiEmessiDaRivenditore(scanner, formatter, abbonamentoDAO);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Errore: Inserisci un numero valido.");
            scanner.nextLine();
        }
        }
    }

    //METODI GESTIONE UTENTE

    private static void registraUtente(Scanner scanner, DateTimeFormatter formatter, UtenteDAO utenteDAO) {
        System.out.println("Inserisci nome:");
        String nome = scanner.nextLine();
        System.out.println("Inserisci cognome:");
        String cognome = scanner.nextLine();
        LocalDate dataNascita = null;

        while (dataNascita == null) {
            System.out.println("Inserisci data di nascita (dd/MM/yyyy):");
            String dataNascitaStr = scanner.nextLine();
            try {
                dataNascita = LocalDate.parse(dataNascitaStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato della data non valido. Per favore, riprova.");
            }
        }

        Utente nuovoUtente = new Utente();
        nuovoUtente.setNome(nome);
        nuovoUtente.setCognome(cognome);
        nuovoUtente.setData_nascita(dataNascita);

        utenteDAO.salvaUtente(nuovoUtente);
        System.out.println("Utente registrato con successo! ID: " + nuovoUtente.getId());
    }

    private static void modificaUtente(Scanner scanner, DateTimeFormatter formatter, UtenteDAO utenteDAO) {
        System.out.println("Inserisci l'ID dell'utente da modificare:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Utente utente = utenteDAO.trovaUtentePerId(id);

            if (utente != null) {
                System.out.println("Inserisci nuovo nome (attuale: " + utente.getNome() + "):");
                String nuovoNome = scanner.nextLine();
                utente.setNome(nuovoNome);

                System.out.println("Inserisci nuovo cognome (attuale: " + utente.getCognome() + "):");
                String nuovoCognome = scanner.nextLine();
                utente.setCognome(nuovoCognome);

                System.out.println("Inserisci nuova data di nascita (dd/MM/yyyy) (attuale: " + utente.getData_nascita().format(formatter) + "):");
                String nuovaDataNascitaStr = scanner.nextLine();
                try {
                    LocalDate nuovaDataNascita = LocalDate.parse(nuovaDataNascitaStr, formatter);
                    utente.setData_nascita(nuovaDataNascita);
                    utenteDAO.aggiornaUtente(utente);
                    System.out.println("Utente aggiornato con successo!");
                } catch (DateTimeParseException e) {
                    System.out.println("Formato della data non valido. Riprova.");
                }
            } else {
                System.out.println("Utente non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void eliminaUtente(Scanner scanner, UtenteDAO utenteDAO) {
        System.out.println("Inserisci l'ID dell'utente da eliminare:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Utente utente = utenteDAO.trovaUtentePerId(id);

            if (utente != null) {
                utenteDAO.eliminaUtente(utente);
                System.out.println("Utente eliminato con successo!");
            } else {
                System.out.println("Utente non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void trovaUtentePerId(Scanner scanner, UtenteDAO utenteDAO) {
        System.out.println("Inserisci l'ID dell'utente:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Utente utente = utenteDAO.trovaUtentePerId(id);

            if (utente != null) {
                System.out.println("Nome: " + utente.getNome());
                System.out.println("Cognome: " + utente.getCognome());
                System.out.println("Data di nascita: " + utente.getData_nascita());
            } else {
                System.out.println("Utente non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void ottieniListaUtenti(UtenteDAO utenteDAO) {
        List<Utente> utenti = utenteDAO.trovaTuttiGliUtenti();
        System.out.println("Lista di tutti gli utenti:");
        for (Utente u : utenti) {
            System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome() + ", Cognome: " + u.getCognome() + ", Data di nascita: " + u.getData_nascita());
        }
    }

    //METODI GESTIONE TESSERE

    private static void ricercaTessera(Scanner scanner, TesseraDAO tesseraDAO) {
        System.out.println("Inserisci l'ID della tessera:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Tessera tessera = tesseraDAO.trovaTesseraPerId(id);

            if (tessera != null) {
                System.out.println("Data di inizio: " + tessera.getData_inizio());
                System.out.println("Data di scadenza: " + tessera.getData_scadenza());
                System.out.println("Stato tessera: " + (tessera.isStato_tessera() ? "Attiva" : "Inattiva"));
                System.out.println("ID Utente: " + tessera.getUtente().getId());
            } else {
                System.out.println("Tessera non trovata.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void ottieniListaTessere(TesseraDAO tesseraDAO) {
        List<Tessera> tessere = tesseraDAO.trovaTutteLeTessere();
        System.out.println("Lista di tutte le tessere:");
        for (Tessera t : tessere) {
            System.out.println("ID: " + t.getId() + ", Data Inizio: " + t.getData_inizio() + ", Data Scadenza: " + t.getData_scadenza() + ", Stato: " + (t.isStato_tessera() ? "Attiva" : "Inattiva"));
        }
    }

    private static void modificaTessera(Scanner scanner, DateTimeFormatter formatter, TesseraDAO tesseraDAO) {
        System.out.println("Inserisci l'ID della tessera da modificare:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Tessera tessera = tesseraDAO.trovaTesseraPerId(id);

            if (tessera != null) {
                System.out.println("Inserisci nuova data di inizio tessera (dd/MM/yyyy) (attuale: " + tessera.getData_inizio().format(formatter) + "):");
                String nuovaDataInizioStr = scanner.nextLine();
                LocalDate nuovaDataInizio = LocalDate.parse(nuovaDataInizioStr, formatter);
                tessera.setData_inizio(nuovaDataInizio);
                tessera.setData_scadenza(nuovaDataInizio.plusYears(1));

                tesseraDAO.aggiornaTessera(tessera);
                System.out.println("Tessera aggiornata con successo!");
            } else {
                System.out.println("Tessera non trovata.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void eliminaTessera(Scanner scanner, TesseraDAO tesseraDAO) {
        System.out.println("Inserisci l'ID della tessera da eliminare:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Tessera tessera = tesseraDAO.trovaTesseraPerId(id);

            if (tessera != null) {
                tesseraDAO.eliminaTessera(tessera);
                System.out.println("Tessera eliminata con successo!");
            } else {
                System.out.println("Tessera non trovata.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    //GESTIONE DISTRIBUTORI

    private static void elencoDistributori(DistributoriDAO distributoriDAO) {
        List<Distributori> distributori = distributoriDAO.trovaTuttiDistributori();
        System.out.println("Elenco Distributori:");
        for (Distributori distributore : distributori) {
            System.out.println("ID: " + distributore.getId() + ", Indirizzo: " + distributore.getIndirizzo() + ", Stato: " + distributore.getStato_distribrutori());
        }
    }

    private static void aggiungiDistributore(Scanner scanner, DistributoriDAO distributoriDAO) {
        System.out.println("Inserisci l'indirizzo del distributore:");
        String indirizzo = scanner.nextLine();
        System.out.println("Inserisci lo stato del distributore (ATTIVO/FUORI_SERVIZIO):");
        String stato = scanner.nextLine();
        Stato_Distributori statoDistributori = Stato_Distributori.valueOf(stato.toUpperCase());

        Distributori nuovoDistributore = new Distributori();
        nuovoDistributore.setIndirizzo(indirizzo);
        nuovoDistributore.setStato_distribrutori(statoDistributori);

        distributoriDAO.salvaDistributore(nuovoDistributore);
        System.out.println("Distributore aggiunto con successo! ID: " + nuovoDistributore.getId());
    }

    private static void modificaDistributore(Scanner scanner, DistributoriDAO distributoriDAO) {
        System.out.println("Inserisci l'ID del distributore da modificare:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Distributori distributore = distributoriDAO.trovaDistributorePerId(id);

            if (distributore != null) {
                System.out.println("Inserisci nuovo indirizzo (attuale: " + distributore.getIndirizzo() + "):");
                String nuovoIndirizzo = scanner.nextLine();
                distributore.setIndirizzo(nuovoIndirizzo);

                System.out.println("Inserisci nuovo stato (attuale: " + distributore.getStato_distribrutori() + "):");
                String nuovoStato = scanner.nextLine();
                Stato_Distributori statoDistributori = Stato_Distributori.valueOf(nuovoStato.toUpperCase());
                distributore.setStato_distribrutori(statoDistributori);

                distributoriDAO.aggiornaDistributore(distributore);
                System.out.println("Distributore aggiornato con successo!");
            } else {
                System.out.println("Distributore non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void eliminaDistributore(Scanner scanner, DistributoriDAO distributoriDAO) {
        System.out.println("Inserisci l'ID del distributore da eliminare:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Distributori distributore = distributoriDAO.trovaDistributorePerId(id);

            if (distributore != null) {
                distributoriDAO.eliminaDistributore(distributore);
                System.out.println("Distributore eliminato con successo!");
            } else {
                System.out.println("Distributore non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void cercaDistributorePerId(Scanner scanner, DistributoriDAO distributoriDAO) {
        System.out.println("Inserisci l'ID del distributore:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Distributori distributore = distributoriDAO.trovaDistributorePerId(id);

            if (distributore != null) {
                System.out.println("Indirizzo: " + distributore.getIndirizzo());
                System.out.println("Stato: " + distributore.getStato_distribrutori());
            } else {
                System.out.println("Distributore non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void ottieniBigliettiEmessiDaDistributore(Scanner scanner, DateTimeFormatter formatter, DistributoriDAO distributoriDAO) {
        System.out.println("Inserisci l'ID del distributore:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Distributori distributore = distributoriDAO.trovaDistributorePerId(id);

            if (distributore != null) {
                System.out.println("Inserisci data di inizio (dd/MM/yyyy):");
                String dataInizioStr = scanner.nextLine();
                LocalDate dataInizio = LocalDate.parse(dataInizioStr, formatter);

                System.out.println("Inserisci data di fine (dd/MM/yyyy):");
                String dataFineStr = scanner.nextLine();
                LocalDate dataFine = LocalDate.parse(dataFineStr, formatter);

                List<Biglietto> biglietti = distributoriDAO.trovaBigliettiEmessi(id, dataInizio, dataFine);
                System.out.println("Biglietti emessi dal distributore:");
                for (Biglietto biglietto : biglietti) {
                    System.out.println("ID Biglietto: " + biglietto.getId() + ", Data Emissione: " + biglietto.getData_emissione());
                }
            } else {
                System.out.println("Distributore non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void ottieniAbbonamentiEmessiDaDistributore(Scanner scanner, DateTimeFormatter formatter, AbbonamentoDAO abbonamentoDAO) {
        System.out.println("Inserisci l'ID del distributore:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            System.out.println("Inserisci data di inizio (dd/MM/yyyy):");
            String dataInizioStr = scanner.nextLine();
            LocalDate dataInizio = LocalDate.parse(dataInizioStr, formatter);

            System.out.println("Inserisci data di fine (dd/MM/yyyy):");
            String dataFineStr = scanner.nextLine();
            LocalDate dataFine = LocalDate.parse(dataFineStr, formatter);

            List<Abbonamento> abbonamenti = abbonamentoDAO.trovaAbbonamentiEmessiDaRivenditore(id, dataInizio, dataFine);
            System.out.println("Abbonamenti emessi dal distributore:");
            for (Abbonamento abbonamento : abbonamenti) {
                System.out.println("ID Abbonamento: " + abbonamento.getId() + ", Data Inizio: " + abbonamento.getData_inizio());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    //GESTIONE RIVENDITORI

    private static void elencoRivenditori(RivenditoriDAO rivenditoriDAO) {
        List<Rivenditori> rivenditori = rivenditoriDAO.trovaTuttiRivenditori();
        System.out.println("Elenco Rivenditori:");
        for (Rivenditori rivenditore : rivenditori) {
            System.out.println("ID: " + rivenditore.getId() + ", Denominazione: " + rivenditore.getDenominazione() + ", Indirizzo: " + rivenditore.getIndirizzo());
        }
    }

    private static void aggiungiRivenditore(Scanner scanner, RivenditoriDAO rivenditoriDAO) {
        System.out.println("Inserisci la denominazione del rivenditore:");
        String denominazione = scanner.nextLine();
        System.out.println("Inserisci l'indirizzo del rivenditore:");
        String indirizzo = scanner.nextLine();
        Rivenditori nuovoRivenditore = new Rivenditori();
        nuovoRivenditore.setDenominazione(denominazione);
        nuovoRivenditore.setIndirizzo(indirizzo);

        rivenditoriDAO.salvaRivenditore(nuovoRivenditore);
        System.out.println("Rivenditore aggiunto con successo! ID: " + nuovoRivenditore.getId());
    }

    private static void modificaRivenditore(Scanner scanner, RivenditoriDAO rivenditoriDAO) {
        System.out.println("Inserisci l'ID del rivenditore da modificare:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Rivenditori rivenditore = rivenditoriDAO.trovaRivenditorePerId(id);

            if (rivenditore != null) {
                System.out.println("Inserisci nuova denominazione (attuale: " + rivenditore.getDenominazione() + "):");
                String nuovaDenominazione = scanner.nextLine();
                rivenditore.setDenominazione(nuovaDenominazione);

                System.out.println("Inserisci nuovo indirizzo (attuale: " + rivenditore.getIndirizzo() + "):");
                String nuovoIndirizzo = scanner.nextLine();
                rivenditore.setIndirizzo(nuovoIndirizzo);

                rivenditoriDAO.aggiornaRivenditore(rivenditore);
                System.out.println("Rivenditore aggiornato con successo!");
            } else {
                System.out.println("Rivenditore non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void eliminaRivenditore(Scanner scanner, RivenditoriDAO rivenditoriDAO) {
        System.out.println("Inserisci l'ID del rivenditore da eliminare:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Rivenditori rivenditore = rivenditoriDAO.trovaRivenditorePerId(id);

            if (rivenditore != null) {
                rivenditoriDAO.eliminaRivenditore(rivenditore);
                System.out.println("Rivenditore eliminato con successo!");
            } else {
                System.out.println("Rivenditore non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void cercaRivenditorePerId(Scanner scanner, RivenditoriDAO rivenditoriDAO) {
        System.out.println("Inserisci l'ID del rivenditore:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Rivenditori rivenditore = rivenditoriDAO.trovaRivenditorePerId(id);

            if (rivenditore != null) {
                System.out.println("Denominazione: " + rivenditore.getDenominazione());
                System.out.println("Indirizzo: " + rivenditore.getIndirizzo());
            } else {
                System.out.println("Rivenditore non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void ottieniBigliettiEmessiDaRivenditore(Scanner scanner, DateTimeFormatter formatter, RivenditoriDAO rivenditoriDAO) {
        System.out.println("Inserisci l'ID del rivenditore:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Rivenditori rivenditore = rivenditoriDAO.trovaRivenditorePerId(id);

            if (rivenditore != null) {
                System.out.println("Inserisci data di inizio (dd/MM/yyyy):");
                String dataInizioStr = scanner.nextLine();
                LocalDate dataInizio = LocalDate.parse(dataInizioStr, formatter);

                System.out.println("Inserisci data di fine (dd/MM/yyyy):");
                String dataFineStr = scanner.nextLine();
                LocalDate dataFine = LocalDate.parse(dataFineStr, formatter);

                List<Biglietto> biglietti = rivenditoriDAO.trovaBigliettiEmessi(id, dataInizio, dataFine);
                System.out.println("Biglietti emessi dal rivenditore:");
                for (Biglietto biglietto : biglietti) {
                    System.out.println("ID Biglietto: " + biglietto.getId() + ", Data Emissione: " + biglietto.getData_emissione());
                }
            } else {
                System.out.println("Rivenditore non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void ottieniAbbonamentiEmessiDaRivenditore(Scanner scanner, DateTimeFormatter formatter, AbbonamentoDAO abbonamentoDAO) {
        System.out.println("Inserisci l'ID del rivenditore:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            System.out.println("Inserisci data di inizio (dd/MM/yyyy):");
            String dataInizioStr = scanner.nextLine();
            LocalDate dataInizio = LocalDate.parse(dataInizioStr, formatter);

            System.out.println("Inserisci data di fine (dd/MM/yyyy):");
            String dataFineStr = scanner.nextLine();
            LocalDate dataFine = LocalDate.parse(dataFineStr, formatter);

            List<Abbonamento> abbonamenti = abbonamentoDAO.trovaAbbonamentiEmessiDaRivenditore(id, dataInizio, dataFine);
            System.out.println("Abbonamenti emessi dal rivenditore:");
            for (Abbonamento abbonamento : abbonamenti) {
                System.out.println("ID Abbonamento: " + abbonamento.getId() + ", Data Inizio: " + abbonamento.getData_inizio());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    //METODI DI ACQUISTO BIGLIETTO/ABBONAMENTO

    private static void compraBigliettoOAbbonamento(Scanner scanner, DateTimeFormatter formatter, AbbonamentoDAO abbonamentoDAO, BigliettoDAO bigliettoDAO, TesseraDAO tesseraDAO, DistributoriDAO distributoriDAO, RivenditoriDAO rivenditoriDAO, UtenteDAO utenteDAO) {
        System.out.println("Vuoi acquistare:");
        System.out.println("1 - Biglietto");
        System.out.println("2 - Abbonamento");
        int scelta = scanner.nextInt();
        scanner.nextLine();

        switch (scelta) {
            case 1:
                acquistaBiglietto(scanner, formatter, bigliettoDAO, tesseraDAO, distributoriDAO, rivenditoriDAO, utenteDAO);
                break;
            case 2:
                acquistaAbbonamento(scanner, formatter, abbonamentoDAO, tesseraDAO, distributoriDAO, rivenditoriDAO, utenteDAO);
                break;
            default:
                System.out.println("Scelta non valida. Riprova.");
        }
    }

    private static void acquistaBiglietto(Scanner scanner, DateTimeFormatter formatter, BigliettoDAO bigliettoDAO, TesseraDAO tesseraDAO, DistributoriDAO distributoriDAO, RivenditoriDAO rivenditoriDAO, UtenteDAO utenteDAO){
        System.out.println("Sei in possesso di una tessera? (si/no)");
        String haTessera = scanner.nextLine().trim().toLowerCase();
        Tessera tessera = null;

        if (haTessera.equals("si")) {
            System.out.println("Inserisci l'ID della tessera:");
            String tesseraIdStr = scanner.nextLine();
            try {
                UUID tesseraId = UUID.fromString(tesseraIdStr);
                tessera = tesseraDAO.trovaTesseraPerId(tesseraId);

                if (tessera == null) {
                    System.out.println("Tessera non trovata. Creane una nuova.");
                    tessera = creaNuovaTessera(scanner, formatter, tesseraDAO, utenteDAO);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Formato UUID non valido.");
                return;
            }
        } else {
            tessera = creaNuovaTessera(scanner, formatter, tesseraDAO, utenteDAO);
        }

        System.out.println("Inserisci data di acquisto (dd/MM/yyyy):");
        LocalDate dataInizio = LocalDate.parse(scanner.nextLine(), formatter);

        System.out.println("Stai acquistando da un distributore o da un rivenditore?");
        System.out.println("1 - Distributore");
        System.out.println("2 - Rivenditore");
        int sceltaEmittente = scanner.nextInt();
        scanner.nextLine();

        if (sceltaEmittente == 1) {
            System.out.println("Inserisci ID del distributore:");
            String distributoreId = scanner.nextLine();
            Distributori distributore = distributoriDAO.trovaDistributorePerId(UUID.fromString(distributoreId));

            if (distributore != null) {
                Biglietto nuovoBiglietto = new Biglietto(dataInizio, tessera, distributore);
                bigliettoDAO.createBiglietto(nuovoBiglietto);
                System.out.println("Biglietto emesso da distributore aggiunto con successo! ID: " + nuovoBiglietto.getId());
            } else {
                System.out.println("Distributore non trovato.");
            }
        } else if (sceltaEmittente == 2) {
            System.out.println("Inserisci ID del rivenditore:");
            String rivenditoreId = scanner.nextLine();
            Rivenditori rivenditore = rivenditoriDAO.trovaRivenditorePerId(UUID.fromString(rivenditoreId));

            if (rivenditore != null) {
                Biglietto nuovoBiglietto = new Biglietto(dataInizio, tessera, rivenditore);
                bigliettoDAO.createBiglietto(nuovoBiglietto);
                System.out.println("Abbonamento emesso da rivenditore aggiunto con successo! ID: " + nuovoBiglietto.getId());
            } else {
                System.out.println("Rivenditore non trovato.");
            }
        } else {
            System.out.println("Scelta non valida.");
        }
    }


    private static void acquistaAbbonamento(Scanner scanner, DateTimeFormatter formatter, AbbonamentoDAO abbonamentoDAO, TesseraDAO tesseraDAO, DistributoriDAO distributoriDAO, RivenditoriDAO rivenditoriDAO, UtenteDAO utenteDAO) {
        System.out.println("Sei in possesso di una tessera? (si/no)");
        String haTessera = scanner.nextLine().trim().toLowerCase();
        Tessera tessera = null;

        if (haTessera.equals("si")) {
            System.out.println("Inserisci l'ID della tessera:");
            String tesseraIdStr = scanner.nextLine();
            try {
                UUID tesseraId = UUID.fromString(tesseraIdStr);
                tessera = tesseraDAO.trovaTesseraPerId(tesseraId);

                if (tessera == null) {
                    System.out.println("Tessera non trovata. Creane una nuova.");
                    tessera = creaNuovaTessera(scanner, formatter, tesseraDAO, utenteDAO);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Formato UUID non valido.");
                return;
            }
        } else {
            tessera = creaNuovaTessera(scanner, formatter, tesseraDAO, utenteDAO);
        }

        System.out.println("Inserisci periodicità dell'abbonamento (SETTIMANALE/MENSILE):");
        String periodicita = scanner.nextLine();
        Periodicita_abbonamento periodicitaAbbonamento = Periodicita_abbonamento.valueOf(periodicita.toUpperCase());

        System.out.println("Inserisci data di inizio (dd/MM/yyyy):");
        LocalDate dataInizio = LocalDate.parse(scanner.nextLine(), formatter);

        System.out.println("Stai acquistando da un distributore o da un rivenditore?");
        System.out.println("1 - Distributore");
        System.out.println("2 - Rivenditore");
        int sceltaEmittente = scanner.nextInt();
        scanner.nextLine();

        if (sceltaEmittente == 1) {
            System.out.println("Inserisci ID del distributore:");
            String distributoreId = scanner.nextLine();
            Distributori distributore = distributoriDAO.trovaDistributorePerId(UUID.fromString(distributoreId));

            if (distributore != null) {
                Abbonamento nuovoAbbonamento = new Abbonamento(periodicitaAbbonamento, dataInizio, distributore, tessera);
                abbonamentoDAO.salvaAbbonamento(nuovoAbbonamento);
                System.out.println("Abbonamento emesso da distributore aggiunto con successo! ID: " + nuovoAbbonamento.getId());
            } else {
                System.out.println("Distributore non trovato.");
            }
        } else if (sceltaEmittente == 2) {
            System.out.println("Inserisci ID del rivenditore:");
            String rivenditoreId = scanner.nextLine();
            Rivenditori rivenditore = rivenditoriDAO.trovaRivenditorePerId(UUID.fromString(rivenditoreId));

            if (rivenditore != null) {
                Abbonamento nuovoAbbonamento = new Abbonamento(periodicitaAbbonamento, dataInizio, rivenditore, tessera);
                abbonamentoDAO.salvaAbbonamento(nuovoAbbonamento);
                System.out.println("Abbonamento emesso da rivenditore aggiunto con successo! ID: " + nuovoAbbonamento.getId());
            } else {
                System.out.println("Rivenditore non trovato.");
            }
        } else {
            System.out.println("Scelta non valida.");
        }
    }



    private static Tessera creaNuovaTessera(Scanner scanner, DateTimeFormatter formatter, TesseraDAO tesseraDAO, UtenteDAO utenteDAO) {
        System.out.println("Inserire il proprio ID utente: ");
        String idUtente = scanner.nextLine();
        Utente utenteTrovato = utenteDAO.trovaUtentePerId(UUID.fromString(idUtente));

        LocalDate Today = LocalDate.now();

        if (utenteTrovato.getTessera() != null) {
            LocalDate data_sca = utenteTrovato.getTessera().getData_scadenza();
            if (data_sca.isBefore(Today)) {
                System.out.println("La tessera è scaduta.");
            } else if (data_sca.equals(Today)) {
                System.out.println("La tessera scade oggi");
            } else {
                System.out.println("La tessera è presente e non è ancora scaduta.\n Scade il " + data_sca.format(formatter) + "\n\n");
            }

            return utenteTrovato.getTessera();
        } else {
            System.out.println("Creazione nuova tessera:");

            Tessera nuovaTessera = new Tessera(Today,Today.plusYears(1),true,utenteTrovato);
            utenteTrovato.setTessera(nuovaTessera);
            utenteDAO.aggiornaUtente(utenteTrovato);
            System.out.println("Tessera associata con successo all'utente: " + utenteTrovato.getNome() + " " + utenteTrovato.getCognome() + "\ncon ID: " + utenteTrovato.getId());
            System.out.println("tessera ID " + utenteTrovato.getTessera().getId() + "\n\n");

            return nuovaTessera;
        }


    }

}


