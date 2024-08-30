package UrbanTransit;

import UrbanTransit.DAO.*;
import UrbanTransit.entities.*;
import UrbanTransit.enums.Periodicita_abbonamento;
import UrbanTransit.enums.Stato_Distributori;
import UrbanTransit.enums.Stato_mezzo;
import UrbanTransit.enums.Tipo_mezzo;
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
        MezziDAO mezzoDAO = new MezziDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
        StatoDAO statoDAO = new StatoDAO(em);
        TimbratiDAO timbratiDAO = new TimbratiDAO(em);

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            try {
                System.out.println("Benvenuto in UrbanTransit System!");
                System.out.println("1 - Sono un utente");
                System.out.println("2 - Sono un amministratore");
                int scelta = scanner.nextInt();
                scanner.nextLine();

                switch (scelta) {
                    case 1:
                        gestisciMenuUtente(scanner, formatter, utenteDAO, tesseraDAO, abbonamentoDAO, bigliettoDAO, distributoriDAO, rivenditoriDAO, mezzoDAO, timbratiDAO);
                        break;
                    case 2:
                        gestisciMenuAmministratore(scanner, formatter, utenteDAO, tesseraDAO, distributoriDAO, rivenditoriDAO, abbonamentoDAO, bigliettoDAO, mezzoDAO, trattaDAO, percorrenzaDAO, statoDAO, timbratiDAO);
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Errore: Inserisci un numero valido.");
                scanner.nextLine();
            }
        }
    }

    private static void gestisciMenuUtente(Scanner scanner, DateTimeFormatter formatter, UtenteDAO utenteDAO, TesseraDAO tesseraDAO, AbbonamentoDAO abbonamentoDAO, BigliettoDAO bigliettoDAO, DistributoriDAO distributoriDAO, RivenditoriDAO rivenditoriDAO, MezziDAO mezzoDAO, TimbratiDAO timbratiDAO) {
        while (true) {
            try {
                System.out.println("Cosa vuoi fare oggi?");
                System.out.println("1. Registrati");
                System.out.println("2. Modifica Profilo");
                System.out.println("3. Elimina Profilo");
                System.out.println("4. Ottieni una tessera UrbanTransit");
                System.out.println("5. Compra un biglietto o abbonamento");
                System.out.println("6. Vidima biglietto");
                System.out.println("7. Torna al menù precedente");
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
                        vidimaBiglietto(scanner, bigliettoDAO, mezzoDAO, tesseraDAO, utenteDAO, timbratiDAO);
                    case 7:
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

    private static void gestisciMenuAmministratore(Scanner scanner, DateTimeFormatter formatter, UtenteDAO utenteDAO, TesseraDAO tesseraDAO, DistributoriDAO distributoriDAO, RivenditoriDAO rivenditoriDAO, AbbonamentoDAO abbonamentoDAO, BigliettoDAO bigliettoDAO, MezziDAO mezzoDAO, TrattaDAO trattaDAO, PercorrenzaDAO percorrenzaDAO, StatoDAO statoDAO, TimbratiDAO timbratiDAO) {
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
                System.out.println("6 - Gestisci biglietti vidimati");
                System.out.println("7 - Calcola tempi di percorrenza effettivi");
                System.out.println("8 - Esci");
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
                        gestisciMezzi(scanner, formatter, mezzoDAO, statoDAO);
                        break;
                    case 5:
                        gestisciTratte(scanner, trattaDAO, percorrenzaDAO, mezzoDAO, statoDAO);
                        break;
                    case 6:
                        gestisciBigliettiVidimati(scanner, timbratiDAO, mezzoDAO);
                    case 7:
                        calcolaTempiPercorrenzaEffettivi(scanner, formatter, mezzoDAO, percorrenzaDAO, trattaDAO);
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
            try {
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

    private static void gestisciMezzi(Scanner scanner, DateTimeFormatter formatter, MezziDAO mezzoDAO, StatoDAO statoDAO) {
        while (true) {
            try {
                System.out.println("Gestione Mezzi:");
                System.out.println("1 - Gestione autobus");
                System.out.println("2 - Gestione Tram");
                System.out.println("3 - Parco mezzi");
                System.out.println("4 - Aggiungi mezzo");
                System.out.println("5 - Torna al menu precedente");
                int scelta = scanner.nextInt();
                scanner.nextLine();

                switch (scelta) {
                    case 1:
                        gestisciAutobus(scanner, formatter, mezzoDAO);
                        break;
                    case 2:
                        gestisciTram(scanner, formatter, mezzoDAO);
                        break;
                    case 3:
                        mostraParcoMezzi(mezzoDAO);
                        break;
                    case 4:
                        aggiungiMezzo(mezzoDAO, statoDAO, scanner, formatter);
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

    private static void gestisciAutobus(Scanner scanner, DateTimeFormatter formatter, MezziDAO mezzoDAO) {
        while (true) {
            try {

            System.out.println("Gestione Autobus:");
                System.out.println("1 - Lista autobus");
                System.out.println("2 - Trova autobus per id");
                System.out.println("3 - Verifica stato autobus (attivo/in manutenzione)");
                System.out.println("4 - Torna indietro");
                int scelta = scanner.nextInt();
                scanner.nextLine();

                switch (scelta) {
                    case 1:
                        mostraListaMezzi(mezzoDAO, Tipo_mezzo.AUTOBUS);
                        break;
                    case 2:
                        trovaMezzoPerId(scanner, mezzoDAO, Tipo_mezzo.AUTOBUS);
                        break;
                    case 3:
                        verificaStatoMezzo(scanner, mezzoDAO, Tipo_mezzo.AUTOBUS);
                        break;
                    case 4:
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

    private static void gestisciTram(Scanner scanner, DateTimeFormatter formatter, MezziDAO mezzoDAO) {
        while (true) {
            try {


            System.out.println("Gestione Tram:");
                System.out.println("1 - Lista tram");
                System.out.println("2 - Trova tram per id");
                System.out.println("3 - Verifica stato tram (attivo/in manutenzione)");
                System.out.println("4 - Torna indietro");
                int scelta = scanner.nextInt();
                scanner.nextLine();

                switch (scelta) {
                    case 1:
                        mostraListaMezzi(mezzoDAO, Tipo_mezzo.TRAM);
                        break;
                    case 2:
                        trovaMezzoPerId(scanner, mezzoDAO, Tipo_mezzo.TRAM);
                        break;
                    case 3:
                        verificaStatoMezzo(scanner, mezzoDAO, Tipo_mezzo.TRAM);
                        break;
                    case 4:
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

    private static void gestisciTratte(Scanner scanner, TrattaDAO trattaDAO,PercorrenzaDAO percorrenzaDAO, MezziDAO mezziDAO, StatoDAO statoDAO) {
        while (true) {
            try {
                System.out.println("Gestione Tratte:");
                System.out.println("1 - Aggiungi tratta");
                System.out.println("2 - Modifica tratta");
                System.out.println("3 - Elimina tratta");
                System.out.println("4 - Lista tratte");
                System.out.println("5 - Associa tratta ad un autobus");
                System.out.println("6 - Torna indietro");
                int scelta = scanner.nextInt();
                scanner.nextLine();

                switch (scelta) {
                    case 1:
                        aggiungiTratta(scanner, trattaDAO);
                        break;
                    case 2:
                        modificaTratta(scanner, trattaDAO);
                        break;
                    case 3:
                        eliminaTratta(scanner, trattaDAO);
                        break;
                    case 4:
                        listaTratte(trattaDAO, percorrenzaDAO);
                        break;
                    case 5:
                        associaTratta(scanner,mezziDAO, statoDAO, percorrenzaDAO, trattaDAO);
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

            Tessera nuovaTessera = new Tessera(Today, Today.plusYears(1), true, utenteTrovato);
            utenteTrovato.setTessera(nuovaTessera);
            utenteDAO.aggiornaUtente(utenteTrovato);
            System.out.println("Tessera associata con successo all'utente: " + utenteTrovato.getNome() + " " + utenteTrovato.getCognome() + "\ncon ID: " + utenteTrovato.getId());
            System.out.println("tessera ID " + utenteTrovato.getTessera().getId() + "\n\n");

            return nuovaTessera;
        }
    }

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
                LocalDate nuovaDataInizio = null;
                while (nuovaDataInizio == null) {
                    System.out.println("Inserisci nuova data di inizio tessera (dd/MM/yyyy) (attuale: " + tessera.getData_inizio().format(formatter) + "):");
                    String nuovaDataInizioStr = scanner.nextLine();

                    try {
                        nuovaDataInizio = LocalDate.parse(nuovaDataInizioStr, formatter);

                        if (nuovaDataInizio.isBefore(LocalDate.now())) {
                            System.out.println("La data inserita deve essere dopo della data di inizio passata. Inserisci una data valida.");
                            nuovaDataInizio = null;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato della data non valido. Riprova.");
                    }
                }
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

        Stato_Distributori statoDistributori = null;
        while (statoDistributori == null) {
            System.out.println("Inserisci lo stato del distributore (ATTIVO/FUORI_SERVIZIO):");
            String stato = scanner.nextLine().toUpperCase();

            try {
                statoDistributori = Stato_Distributori.valueOf(stato);
            } catch (IllegalArgumentException e) {
                System.out.println("Stato non valido. Inserisci 'ATTIVO' o 'FUORI_SERVIZIO'.");
            }
        }

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

                Stato_Distributori statoDistributori = null;
                while (statoDistributori == null) {
                    System.out.println("Inserisci nuovo stato tra 'ATTIVO' o 'FUORI_SERVIZIO' (attuale: " + distributore.getStato_distribrutori() + "):");
                    String nuovoStato = scanner.nextLine().toUpperCase();

                    try {
                        statoDistributori = Stato_Distributori.valueOf(nuovoStato);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Stato non valido. Inserisci 'ATTIVO' o 'FUORI_SERVIZIO'.");
                    }
                }

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
                LocalDate dataInizio = null;
                while (dataInizio == null) {
                    System.out.println("Inserisci data di inizio (dd/MM/yyyy):");
                    String dataInizioStr = scanner.nextLine();
                    try {
                        dataInizio = LocalDate.parse(dataInizioStr, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato data di inizio non valido. Inserisci nuovamente la data (dd/MM/yyyy).");
                    }
                }

                LocalDate dataFine = null;
                while (dataFine == null) {
                    System.out.println("Inserisci data di fine (dd/MM/yyyy):");
                    String dataFineStr = scanner.nextLine();
                    try {
                        dataFine = LocalDate.parse(dataFineStr, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato data di fine non valido. Inserisci nuovamente la data (dd/MM/yyyy).");
                    }
                }

                List<Biglietto> biglietti = distributoriDAO.trovaBigliettiEmessi(id, dataInizio, dataFine);
                if (biglietti.isEmpty()) {
                    System.out.println("Nessun biglietto emesso nel periodo specificato.");
                } else {
                    System.out.println("Biglietti emessi dal distributore:");
                    for (Biglietto biglietto : biglietti) {
                        System.out.println("ID Biglietto: " + biglietto.getId() + ", Data Emissione: " + biglietto.getData_emissione());
                    }
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

            LocalDate dataInizio = null;
            while (dataInizio == null) {
                System.out.println("Inserisci data di inizio (dd/MM/yyyy):");
                String dataInizioStr = scanner.nextLine();
                try {
                    dataInizio = LocalDate.parse(dataInizioStr, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato data di inizio non valido. Inserisci nuovamente la data (dd/MM/yyyy).");
                }
            }

            LocalDate dataFine = null;
            while (dataFine == null) {
                System.out.println("Inserisci data di fine (dd/MM/yyyy):");
                String dataFineStr = scanner.nextLine();
                try {
                    dataFine = LocalDate.parse(dataFineStr, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato data di fine non valido. Inserisci nuovamente la data (dd/MM/yyyy).");
                }
            }

            List<Abbonamento> abbonamenti = abbonamentoDAO.trovaAbbonamentiEmessiDaRivenditore(id, dataInizio, dataFine);
            if (abbonamenti.isEmpty()) {
                System.out.println("Nessun abbonamento emesso nel periodo specificato.");
            } else {
                System.out.println("Abbonamenti emessi dal distributore:");
                for (Abbonamento abbonamento : abbonamenti) {
                    System.out.println("ID Abbonamento: " + abbonamento.getId() + ", Data Inizio: " + abbonamento.getData_inizio());
                }
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
                LocalDate dataInizio = null;
                while (dataInizio == null) {
                    System.out.println("Inserisci data di inizio (dd/MM/yyyy):");
                    String dataInizioStr = scanner.nextLine();
                    try {
                        dataInizio = LocalDate.parse(dataInizioStr, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato data di inizio non valido. Inserisci nuovamente la data (dd/MM/yyyy).");
                    }
                }

                LocalDate dataFine = null;
                while (dataFine == null) {
                    System.out.println("Inserisci data di fine (dd/MM/yyyy):");
                    String dataFineStr = scanner.nextLine();
                    try {
                        dataFine = LocalDate.parse(dataFineStr, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato data di fine non valido. Inserisci nuovamente la data (dd/MM/yyyy).");
                    }
                }

                List<Biglietto> biglietti = rivenditoriDAO.trovaBigliettiEmessi(id, dataInizio, dataFine);
                if (biglietti.isEmpty()) {
                    System.out.println("Nessun biglietto emesso nel periodo specificato.");
                } else {
                    System.out.println("Biglietti emessi dal rivenditore:");
                    for (Biglietto biglietto : biglietti) {
                        System.out.println("ID Biglietto: " + biglietto.getId() + ", Data Emissione: " + biglietto.getData_emissione());
                    }
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

            LocalDate dataInizio = null;
            while (dataInizio == null) {
                System.out.println("Inserisci data di inizio (dd/MM/yyyy):");
                String dataInizioStr = scanner.nextLine();
                try {
                    dataInizio = LocalDate.parse(dataInizioStr, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato data di inizio non valido. Inserisci nuovamente la data (dd/MM/yyyy).");
                }
            }

            LocalDate dataFine = null;
            while (dataFine == null) {
                System.out.println("Inserisci data di fine (dd/MM/yyyy):");
                String dataFineStr = scanner.nextLine();
                try {
                    dataFine = LocalDate.parse(dataFineStr, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato data di fine non valido. Inserisci nuovamente la data (dd/MM/yyyy).");
                }
            }

            List<Abbonamento> abbonamenti = abbonamentoDAO.trovaAbbonamentiEmessiDaRivenditore(id, dataInizio, dataFine);
            if (abbonamenti.isEmpty()) {
                System.out.println("Nessun abbonamento emesso nel periodo specificato.");
            } else {
                System.out.println("Abbonamenti emessi dal rivenditore:");
                for (Abbonamento abbonamento : abbonamenti) {
                    System.out.println("ID Abbonamento: " + abbonamento.getId() + ", Data Inizio: " + abbonamento.getData_inizio());
                }
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

    private static void acquistaBiglietto(Scanner scanner, DateTimeFormatter formatter, BigliettoDAO bigliettoDAO, TesseraDAO tesseraDAO, DistributoriDAO distributoriDAO, RivenditoriDAO rivenditoriDAO, UtenteDAO utenteDAO) {
        System.out.println("Sei in possesso di una tessera? (si/no)");
        String haTessera = scanner.nextLine().trim().toLowerCase();
        Tessera tessera = null;

        while (!haTessera.equals("si") && !haTessera.equals("no")) {
            System.out.println("Sei in possesso di una tessera? (si/no)");
            haTessera = scanner.nextLine().trim().toLowerCase();

            if (!haTessera.equals("si") && !haTessera.equals("no")) {
                System.out.println("Input non valido. Inserisci 'si' o 'no'.");
            }
        }

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
                System.out.println("Formato UUID della tessera non valido.");
                return;
            }
        } else {
            tessera = creaNuovaTessera(scanner, formatter, tesseraDAO, utenteDAO);
        }

        LocalDate dataInizio = LocalDate.now();
        System.out.println("Grazie per l'acquisto; " + dataInizio.format(formatter));

        System.out.println("Stai acquistando da un distributore o da un rivenditore?");
        System.out.println("1 - Distributore");
        System.out.println("2 - Rivenditore");

        int sceltaEmittente = 0;
        while (sceltaEmittente != 1 && sceltaEmittente != 2) {
            try {
                sceltaEmittente = Integer.parseInt(scanner.nextLine());
                if (sceltaEmittente != 1 && sceltaEmittente != 2) {
                    System.out.println("Scelta non valida. Inserisci 1 per Distributore o 2 per Rivenditore.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inserimento non valido. Inserisci 1 per Distributore o 2 per Rivenditore.");
            }
        }

        if (sceltaEmittente == 1) {
            System.out.println("Inserisci ID del distributore:");
            String distributoreId = scanner.nextLine();
            try {
                Distributori distributore = distributoriDAO.trovaDistributorePerId(UUID.fromString(distributoreId));

                if (distributore != null) {
                    Biglietto nuovoBiglietto = new Biglietto(dataInizio, tessera, distributore);
                    bigliettoDAO.createBiglietto(nuovoBiglietto);
                    System.out.println("Biglietto emesso da distributore aggiunto con successo! ID: " + nuovoBiglietto.getId());
                } else {
                    System.out.println("Distributore non trovato.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Formato UUID del distributore non valido.");
            }
        } else if (sceltaEmittente == 2) {
            System.out.println("Inserisci ID del rivenditore:");
            String rivenditoreId = scanner.nextLine();
            try {
                Rivenditori rivenditore = rivenditoriDAO.trovaRivenditorePerId(UUID.fromString(rivenditoreId));

                if (rivenditore != null) {
                    Biglietto nuovoBiglietto = new Biglietto(dataInizio, tessera, rivenditore);
                    bigliettoDAO.createBiglietto(nuovoBiglietto);
                    System.out.println("Biglietto emesso da rivenditore aggiunto con successo! ID: " + nuovoBiglietto.getId());
                } else {
                    System.out.println("Rivenditore non trovato.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Formato UUID del rivenditore non valido.");
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

    private static void vidimaBiglietto(Scanner scanner, BigliettoDAO bigliettoDAO, MezziDAO mezzoDAO, TesseraDAO tesseraDAO, UtenteDAO utenteDAO, TimbratiDAO timbratiDAO){
        System.out.println("Inserisci l'ID del biglietto:");
        String bigliettoIdStr = scanner.nextLine();
        try {
            UUID bigliettoId = UUID.fromString(bigliettoIdStr);
            Biglietto biglietto = bigliettoDAO.getBigliettoById(bigliettoId);

            if (biglietto == null){
                System.out.println("Biglietto non trovato");
                return;
            }

            System.out.println("Inserisci l'ID del mezzo:");
            String mezzoIdStr = scanner.nextLine();
            UUID mezzoId = UUID.fromString(mezzoIdStr);
            Mezzi mezzo = mezzoDAO.getMezzoById(mezzoId);

            if (mezzo == null) {
                System.out.println("Mezzo non trovato.");
                return;
            }

            Timbrati timbrato = new Timbrati(LocalDate.now(), mezzo, biglietto);
            timbratiDAO.createTimbrati(timbrato);

            bigliettoDAO.annullaBiglietto(bigliettoId);
            System.out.println("Biglietto vidimato e annullato con successo!");

        } catch (IllegalArgumentException e){
            System.out.println("Formato UUID non valido.");

        }
    }

    //METODI GESTIONE MEZZI

    private static void aggiungiMezzo(MezziDAO mezziDAO, StatoDAO statoDAO, Scanner scanner, DateTimeFormatter formatter) {
        try {
            System.out.println("Che tipo di mezzo è? [AUTOBUS, TRAM]");
            String tipoMezzo = scanner.nextLine();

            System.out.println("Qual è la capienza del mezzo? ");
            int capienzaMezzo = Integer.parseInt(scanner.nextLine());

            System.out.println("Quanti giri deve percorrere? ");
            int giriMezzo = Integer.parseInt(scanner.nextLine());

            System.out.println("Vuoi inserire uno stato al mezzo? [si/no]");
            String risp = scanner.nextLine();

            String statoMezzo = "";
            LocalDate dataInizio = null;
            LocalDate dataFine = null;


            if (risp.equalsIgnoreCase("si")) {
                System.out.println("Inserisci lo stato del mezzo: [IN_SERVIZIO, MANUTENZIONE]");
                statoMezzo = scanner.nextLine();

                System.out.println("Inserire data di inizio: [gg/mm/aaaa]");
                dataInizio = LocalDate.parse(scanner.nextLine(), formatter);

                if (statoMezzo.equals("MANUTENZIONE")) dataFine = dataInizio.plusMonths(1);

            }

            Mezzi nuovoMezzo = new Mezzi(capienzaMezzo, Tipo_mezzo.valueOf(tipoMezzo), giriMezzo);
            Stato nuovoStato = new Stato(Stato_mezzo.valueOf(statoMezzo), dataInizio, dataFine, nuovoMezzo);

            nuovoMezzo.setStato(nuovoStato);

            System.out.println(nuovoMezzo.getTipo_mezzo() + "è stato creato con successo");
            mezziDAO.createMezzi(nuovoMezzo);

            System.out.println("L'ID del mezzo sarà: " + nuovoMezzo.getId());
            System.out.println("Stato del mezzo ha ID: " + nuovoStato.getId());

        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }

    }

    private static void mostraParcoMezzi(MezziDAO mezziDAO) {
        List<Mezzi> mezziList = mezziDAO.getAllMezzi();
        if (mezziList.isEmpty()) {
            System.out.println("Il parco mezzi è vuoto.");
        } else {
            System.out.println("Elenco completo dei mezzi:");
            for (Mezzi mezzo : mezziList) {
                System.out.println("ID: " + mezzo.getId() + ", Tipo: " + mezzo.getTipo_mezzo() + ", Capienza: " + mezzo.getCapienza() + ", Numero di giri: " + mezzo.getNum_giri());
            }
        }
    }


    private static void mostraListaMezzi(MezziDAO mezziDAO, Tipo_mezzo tipo) {
        System.out.println("Lista degli autobus:\n\n" + mezziDAO.getMezziByTipo(tipo) + "\n");
    }

    private static void trovaMezzoPerId(Scanner scanner, MezziDAO mezziDAO, Tipo_mezzo tipo) {

        try {
            System.out.println("Inserire l'ID del mezzo da cercare: ");
            String mezzoID = scanner.nextLine();

            if (mezzoID == null || mezzoID.trim().isEmpty()) {
                System.out.println("Errore: ID del mezzo non può essere vuoto.");
                return;
            }

            Mezzi mezzoTrovato = mezziDAO.getMezzoById(UUID.fromString(mezzoID));

            if (mezzoTrovato != null) {
                System.out.println("Il mezzo è stato trovato\nID : " + mezzoTrovato.getId() + " Capienza dell'autobus: " + mezzoTrovato.getCapienza() + " Numero dei giri: " + mezzoTrovato.getNum_giri() + "\n\n");
            } else {
                System.out.println("Nessun mezzo trovato con l'ID fornito.");
            }

        } catch (Exception e) {
            System.out.println("errore: " + e.getMessage());
        }

    }

    private static void verificaStatoMezzo(Scanner scanner, MezziDAO mezziDAO, Tipo_mezzo tipo) {
        System.out.println("Inserisci l'ID del mezzo per verificare lo stato: ");
        String mezzoID = scanner.nextLine();

        if (mezzoID == null || mezzoID.trim().isEmpty()) {
            System.out.println("Errore: ID del mezzo non può essere vuoto.");
            return;
        }

        Mezzi mezzoTrovato = mezziDAO.getMezzoById(UUID.fromString(mezzoID));

        try {
            System.out.println("Stato del mezzo con ID " + mezzoTrovato.getId() + ": " + mezzoTrovato.getStato().getStato_mezzo());
        } catch (Exception e) {
            System.out.println("Nessun mezzo trovato con l'ID fornito.");
            System.out.println(e.getMessage());
        }

    }


    private static void gestisciBigliettiVidimati(Scanner scanner, TimbratiDAO timbratiDAO, MezziDAO mezzoDAO) {
        System.out.println("Inserisci l'ID del mezzo per visualizzare i biglietti vidimati:");
        String mezzoIdStr = scanner.nextLine();
        try {
            UUID mezzoId = UUID.fromString(mezzoIdStr);
            Mezzi mezzo = mezzoDAO.getMezzoById(mezzoId);

            if (mezzo == null) {
                System.out.println("Mezzo non trovato");
                return;
            }

            List<Timbrati> bigliettiVidimati = timbratiDAO.trovaBigliettiVidimatiPerMezzo(mezzoId);

            if (bigliettiVidimati.isEmpty()) {
                System.out.println("Nessun biglietto vidimato per questo mezzo");
            } else {
                System.out.println("Biglietti vidimati per il mezzo con ID: " + mezzoId);
                for (Timbrati timbrato : bigliettiVidimati) {
                    System.out.println("ID Biglietto: " + timbrato.getBiglietto().getId() + ", Data Timbro: " + timbrato.getData_timbro());
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println("Formato UUID non valido");
        }
    }

    //METODI GESTIONE TRATTA

    private static void aggiungiTratta(Scanner scanner, TrattaDAO trattaDAO) {
        System.out.println("Inserisci zona di partenza:");
        String zonaPartenza = scanner.nextLine();
        System.out.println("Inserisci capolinea:");
        String capolinea = scanner.nextLine();
        System.out.println("Inserisci tempo di percorrenza previsto (in minuti):");
        int tempoPercorrenzaPrevisto = scanner.nextInt();
        scanner.nextLine();

        Tratta nuovaTratta = new Tratta(zonaPartenza, capolinea, tempoPercorrenzaPrevisto);
        trattaDAO.createTratta(nuovaTratta);
        System.out.println("Tratta aggiunta con successo! ID: " + nuovaTratta.getId());
    }

    private static void modificaTratta(Scanner scanner, TrattaDAO trattaDAO) {
        System.out.println("Inserisci l'ID della tratta da modificare:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Tratta tratta = trattaDAO.getTrattaById(id);

            if (tratta != null) {
                System.out.println("Inserisci nuova zona di partenza (attuale: " + tratta.getZona_partenza() + "):");
                String nuovaZonaPartenza = scanner.nextLine();
                tratta.setZona_partenza(nuovaZonaPartenza);

                System.out.println("Inserisci nuovo capolinea (attuale: " + tratta.getCapolinea() + "):");
                String nuovoCapolinea = scanner.nextLine();
                tratta.setCapolinea(nuovoCapolinea);

                System.out.println("Inserisci nuovo tempo di percorrenza previsto (attuale: " + tratta.getTempo_percorrenza() + " minuti):");
                int nuovoTempoPercorrenza = scanner.nextInt();
                scanner.nextLine();
                tratta.setTempo_percorrenza(nuovoTempoPercorrenza);

                trattaDAO.updateTratta(id, nuovaZonaPartenza, nuovoCapolinea, nuovoTempoPercorrenza);
                System.out.println("Tratta aggiornata con successo!");
            } else {
                System.out.println("Tratta non trovata.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void eliminaTratta(Scanner scanner, TrattaDAO trattaDAO) {
        System.out.println("Inserisci l'ID della tratta da eliminare:");
        String idStr = scanner.nextLine();
        try {
            UUID id = UUID.fromString(idStr);
            Tratta tratta = trattaDAO.getTrattaById(id);

            if (tratta != null) {
                trattaDAO.deleteTratta(tratta.getId());
                System.out.println("Tratta eliminata con successo!");
            } else {
                System.out.println("Tratta non trovata.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }

    private static void listaTratte(TrattaDAO trattaDAO, PercorrenzaDAO percorrenzaDAO) {
        List<Tratta> tratte = trattaDAO.trovaTutteTratte();
        System.out.println("Lista delle tratte:");
        for (Tratta tratta : tratte) {
            System.out.println("ID: " + tratta.getId() + ", Zona di Partenza: " + tratta.getZona_partenza() +
                    ", Capolinea: " + tratta.getCapolinea() +
                    ", Tempo di Percorrenza Previsto: " + tratta.getTempo_percorrenza() + " minuti" + (tratta.getPercorrenza() != null ? ", Tempo di Percorrenza Effettivo: " + tratta.getPercorrenza().getTempo_effettivo() + " minuti" : ""));
        }
    }

    private static void associaTratta(Scanner scanner, MezziDAO mezziDAO, StatoDAO statoDAO, PercorrenzaDAO percorrenzaDAO, TrattaDAO trattaDAO) {

        System.out.println("Inserisci l'ID dell'autobus a cui associare una tratta: ");
        String autobusID = scanner.nextLine();

        Mezzi autobusTrovato = mezziDAO.getMezzoById(UUID.fromString(autobusID));

        Stato statoTrovato = statoDAO.getStatoById(autobusTrovato.getStato().getId());



        if (statoTrovato.getStato_mezzo().equals(Stato_mezzo.IN_SERVIZIO)) {
            System.out.println("Quale tratta vuoi associare a quest'autobus?");
            trattaDAO.trovaTutteTratte().stream().forEach(System.out::println);

            System.out.println("\nInserisci l'ID della tratta che vuoi associare: ");
            String trattaID = scanner.nextLine();

            Tratta trattaTrovata = trattaDAO.getTrattaById(UUID.fromString(trattaID));

            Percorrenza nuovaPercorrenza = new Percorrenza(statoTrovato, trattaTrovata);
            percorrenzaDAO.createPercorrenza(nuovaPercorrenza);

            System.out.println("Fantastico! l'autobus è stato correttamente associato ad una tratta!");
            System.out.println("L'autobus " + nuovaPercorrenza.getStato().getMezzo().getId() +
                    "\n l'autobus partirà da : " + nuovaPercorrenza.getTratta().getZona_partenza() +
                    "\n e arriverà a :" + nuovaPercorrenza.getTratta().getCapolinea());
            System.out.println("Il tempo previsto per la tratta è di : " + trattaTrovata.getTempo_percorrenza() + " minuti");
        }

    }

    //METODI PERCORRENZA

    private static void calcolaTempiPercorrenzaEffettivi(Scanner scanner, DateTimeFormatter formatter, MezziDAO mezziDAO, PercorrenzaDAO percorrenzaDAO, TrattaDAO trattaDAO) {
        System.out.println("Inserisci l'ID del mezzo:");
        String mezzoIdStr = scanner.nextLine();
        try {
            UUID mezzoId = UUID.fromString(mezzoIdStr);
            Mezzi mezzo = mezziDAO.getMezzoById(mezzoId);

            if (mezzo != null) {
                Stato stato = mezzo.getStato(); // Otteniamo lo stato del mezzo
                if (stato != null) {
                    Percorrenza percorrenza = stato.getPercorrenza(); // Otteniamo la tratta associata

                    if (percorrenza != null) {
                        Tratta tratta = percorrenza.getTratta();

                        if (tratta != null) {
                            System.out.println("Inserisci la data per il calcolo (dd/MM/yyyy):");
                            String dataStr = scanner.nextLine();
                            LocalDate data = LocalDate.parse(dataStr, formatter);

                            int tempoPercorrenzaPrevisto = tratta.getTempo_percorrenza() * mezzo.getNum_giri();
                            int ritardoCasuale = new Random().nextInt(100) + 1; // Generiamo un ritardo casuale tra 1 e 100 minuti

                            int tempoPercorrenzaEffettivo = tempoPercorrenzaPrevisto + ritardoCasuale;

                            percorrenza.setTempo_effettivo(tempoPercorrenzaEffettivo);
                            percorrenzaDAO.updatePercorrenza(percorrenza.getId(), tempoPercorrenzaEffettivo);

                            System.out.println("Tempo di percorrenza effettivo calcolato e salvato: " + tempoPercorrenzaEffettivo + " minuti.");
                        } else {
                            System.out.println("La tratta associata a questa percorrenza non esiste.");
                        }
                    } else {
                        System.out.println("Il mezzo selezionato non ha una percorrenza associata");
                    }
                } else {
                    System.out.println("Il mezzo non ha uno stato attivo associato.");
                }
            } else {
                System.out.println("Mezzo non trovato.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato UUID non valido.");
        }
    }
}

