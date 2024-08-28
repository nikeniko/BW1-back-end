package UrbanTransit;

import UrbanTransit.DAO.AbbonamentoDAO;
import UrbanTransit.DAO.DistributoriDAO;
import UrbanTransit.DAO.RivenditoriDAO;
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
        DistributoriDAO distributoriDAO = new DistributoriDAO(em);
        RivenditoriDAO rivenditoriDAO = new RivenditoriDAO(em);
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.println("Cosa vuoi fare oggi?");
            System.out.println("1. Gestisci Utenti");
            System.out.println("2. Gestisci Abbonamenti");
            System.out.println("3. Gestisci Distributori/Rivenditori");
            System.out.println("4. Esci");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    gestisciUtenti(scanner, formatter, utenteDAO);
                    break;
                case 2:
                    gestisciAbbonamenti(scanner, formatter, abbonamentoDAO, distributoriDAO, rivenditoriDAO);
                    break;
                case 3:
                    gestisciDistributoriERivenditori(scanner, distributoriDAO, rivenditoriDAO);
                    break;
                case 4:
                    em.close();
                    emf.close();
                    System.out.println("Applicazione terminata.");
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    private static void gestisciUtenti(Scanner scanner, DateTimeFormatter formatter, UtenteDAO utenteDAO) {
        while (true) {

            // Menu di selezione utenti
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
                    } catch (IllegalArgumentException e) {
                        System.out.println("Formato UUID non valido");
                    }
                    break;
                case 3:
                    //Lista utenti nel db
                    List<Utente> utenti = utenteDAO.trovaTuttiGliUtenti();
                    System.out.println("Lista di tutti gli utenti:");
                    for (Utente u : utenti) {
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
                    } catch (IllegalArgumentException e) {
                        System.out.println("Formato UUID non valido");
                    }
                    break;
                case 6:
                    //Esci
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }


     private static void gestisciAbbonamenti(Scanner scanner, DateTimeFormatter formatter, AbbonamentoDAO abbonamentoDAO, DistributoriDAO distributoriDAO, RivenditoriDAO rivenditoriDAO) {
        while (true) {
            System.out.println("Menu Abbonamenti:");
            System.out.println("1. Crea Nuovo Abbonamento");
            System.out.println("2. Aggiorna Abbonamento");
            System.out.println("3. Elimina Abbonamento");
            System.out.println("4. Torna al menu principale");
            int scelta = scanner.nextInt();
            scanner.nextLine();

             switch (scelta){
                case 1:
                    System.out.println("Vuoi emettere l'abbonamento da un distributore o un rivenditore?");
                    System.out.println("1. Distributore");
                    System.out.println("2. Rivenditore");
                    int sceltaEmittente = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Inserisci periodicità dell'abbonamento (SETTIMANALE/MENSILE):");
                    String periodicita = scanner.nextLine();
                    Periodicita_abbonamento periodicitaAbbonamento = Periodicita_abbonamento.valueOf(periodicita.toUpperCase());

                    System.out.println("Inserisci data di inizio (dd/MM/yyyy):");
                    LocalDate dataInizio = LocalDate.parse(scanner.nextLine(), formatter);

                    // Creazione di una tessera test
                    Tessera tessera = new Tessera();
                    tessera.setData_inizio(dataInizio);
                    tessera.setData_scadenza(dataInizio.plusYears(1));
                    tessera.setStato_tessera(true);

                    if (sceltaEmittente == 1) {
                        System.out.println("Inserisci ID del distributore:");
                        String distributoreId = scanner.nextLine();
                        Distributori distributore = distributoriDAO.trovaDistributorePerId(UUID.fromString(distributoreId));

                       if (distributore != null) {
                            Abbonamento nuovoAbbonamento = new Abbonamento(periodicitaAbbonamento, dataInizio, distributore, tessera);
                            abbonamentoDAO.nuovoAbbonamento(nuovoAbbonamento);
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
                            abbonamentoDAO.nuovoAbbonamento(nuovoAbbonamento);
                            System.out.println("Abbonamento emesso da rivenditore aggiunto con successo! ID: " + nuovoAbbonamento.getId());
                        } else {
                            System.out.println("Rivenditore non trovato.");
                        }
                    } else {
                        System.out.println("Scelta non valida.");
                    }
                    break;

                case 2:
                    System.out.println("Inserisci ID dell'abbonamento da aggiornare:");
                    String idAggiornaAbbonamentoStr = scanner.nextLine();
                    try {
                        UUID idAggiornaAbbonamento = UUID.fromString(idAggiornaAbbonamentoStr);
                        Abbonamento abbonamentoAggiorna = abbonamentoDAO.trovaAbbonamentoPerId(idAggiornaAbbonamento);

                        if (abbonamentoAggiorna != null) {
                            System.out.println("Inserisci nuova periodicità (attuale: " + abbonamentoAggiorna.getPeriodicita_abbonamento() + "):");
                            String nuovaPeriodicita = scanner.nextLine();
                            abbonamentoAggiorna.setPeriodicita_abbonamento(Periodicita_abbonamento.valueOf(nuovaPeriodicita.toUpperCase()));

                            System.out.println("Inserisci nuova data di inizio (dd/MM/yyyy) (attuale: " + abbonamentoAggiorna.getData_inizio().format(formatter) + "):");
                            String nuovaDataInizioStr = scanner.nextLine();
                            LocalDate nuovaDataInizio = LocalDate.parse(nuovaDataInizioStr, formatter);
                            abbonamentoAggiorna.setData_inizio(nuovaDataInizio);

                            abbonamentoDAO.aggiornaAbbonamento(abbonamentoAggiorna);
                            System.out.println("Abbonamento aggiornato con successo!");
                        } else {
                            System.out.println("Abbonamento non trovato.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Formato UUID non valido.");
                    }
                    break;

                case 3:
                    System.out.println("Inserisci ID dell'abbonamento da eliminare:");
                    String idEliminaAbbonamentoStr = scanner.nextLine();
                    try {
                        UUID idEliminaAbbonamento = UUID.fromString(idEliminaAbbonamentoStr);
                        Abbonamento abbonamentoElimina = abbonamentoDAO.trovaAbbonamentoPerId(idEliminaAbbonamento);

                        if (abbonamentoElimina != null) {
                            abbonamentoDAO.eliminaAbbonamento(abbonamentoElimina);
                            System.out.println("Abbonamento eliminato con successo!");
                        } else {
                            System.out.println("Abbonamento non trovato.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Formato UUID non valido.");
                    }
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

      private static void gestisciDistributoriERivenditori(Scanner scanner, DistributoriDAO distributoriDAO, RivenditoriDAO rivenditoriDAO) {
          while (true) {
              System.out.println("Gestisci Distributori/Rivenditori:");
              System.out.println("1. Gestisci Distributori");
              System.out.println("2. Gestisci Rivenditori");
              System.out.println("3. Torna al menu principale");
              int scelta = scanner.nextInt();
              scanner.nextLine();

              switch (scelta) {
                  case 1:
                      gestisciDistributori(scanner, distributoriDAO);
                      break;
                  case 2:
                      gestisciRivenditori(scanner, rivenditoriDAO);
                      break;
                  case 3:
                      return;
                  default:
                      System.out.println("Scelta non valida. Riprova.");
              }
          }
      }

      private static void gestisciDistributori(Scanner scanner, DistributoriDAO distributoriDAO) {
          while (true) {
              System.out.println("Gestisci Distributori:");
              System.out.println("1. Aggiungi Distributore");
              System.out.println("2. Aggiorna Distributore");
              System.out.println("3. Elimina Distributore");
              System.out.println("4. Visualizza tutti i Distributori");
              System.out.println("5. Torna al menu precedente");
              int scelta = scanner.nextInt();
              scanner.nextLine();

              switch (scelta) {
                  case 1:
                      System.out.println("Inserisci indirizzo del distributore:");
                      String indirizzoDistributore = scanner.nextLine();
                      Distributori nuovoDistributore = new Distributori();
                      nuovoDistributore.setIndirizzo(indirizzoDistributore);
                      distributoriDAO.salvaDistributore(nuovoDistributore);
                      System.out.println("Distributore aggiunto con successo! ID: " + nuovoDistributore.getId());
                      break;
                  case 2:
                      System.out.println("Inserisci ID del distributore da aggiornare:");
                      String idAggiornaStr = scanner.nextLine();
                      try {
                          UUID idAggiorna = UUID.fromString(idAggiornaStr);
                          Distributori distributoreDaAggiornare = distributoriDAO.trovaDistributorePerId(idAggiorna);
                          if (distributoreDaAggiornare != null) {
                              System.out.println("Inserisci nuovo indirizzo (attuale: " + distributoreDaAggiornare.getIndirizzo() + "):");
                              String nuovoIndirizzo = scanner.nextLine();
                              distributoreDaAggiornare.setIndirizzo(nuovoIndirizzo);
                              distributoriDAO.aggiornaDistributore(distributoreDaAggiornare);
                              System.out.println("Distributore aggiornato con successo!");
                          } else {
                              System.out.println("Distributore non trovato.");
                          }
                      } catch (IllegalArgumentException e) {
                          System.out.println("Formato UUID non valido.");
                      }
                      break;
                  case 3:
                      System.out.println("Inserisci ID del distributore da eliminare:");
                      String idEliminaStr = scanner.nextLine();
                      try {
                          UUID idElimina = UUID.fromString(idEliminaStr);
                          Distributori distributoreDaEliminare = distributoriDAO.trovaDistributorePerId(idElimina);
                          if (distributoreDaEliminare != null) {
                              distributoriDAO.eliminaDistributore(distributoreDaEliminare);
                              System.out.println("Distributore eliminato con successo!");
                          } else {
                              System.out.println("Distributore non trovato.");
                          }
                      } catch (IllegalArgumentException e) {
                          System.out.println("Formato UUID non valido.");
                      }
                      break;
                  case 4:
                      List<Distributori> distributori = distributoriDAO.trovaTuttiDistributori();
                      System.out.println("Elenco dei distributori:");
                      for (Distributori distributore : distributori) {
                          System.out.println("ID: " + distributore.getId() + ", Indirizzo: " + distributore.getIndirizzo());
                      }
                      break;
                  case 5:
                      return;
                  default:
                      System.out.println("Scelta non valida. Riprova.");
              }
          }
      }

      private static void gestisciRivenditori(Scanner scanner, RivenditoriDAO rivenditoriDAO) {
          while (true) {
              System.out.println("Gestisci Rivenditori:");
              System.out.println("1. Aggiungi Rivenditore");
              System.out.println("2. Aggiorna Rivenditore");
              System.out.println("3. Elimina Rivenditore");
              System.out.println("4. Visualizza tutti i Rivenditori");
              System.out.println("5. Torna al menu precedente");
              int scelta = scanner.nextInt();
              scanner.nextLine();

              switch (scelta) {
                  case 1:
                      System.out.println("Inserisci denominazione del rivenditore:");
                      String denominazioneRivenditore = scanner.nextLine();
                      System.out.println("Inserisci indirizzo del rivenditore:");
                      String indirizzoRivenditore = scanner.nextLine();
                      Rivenditori nuovoRivenditore = new Rivenditori();
                      nuovoRivenditore.setDenominazione(denominazioneRivenditore);
                      nuovoRivenditore.setIndirizzo(indirizzoRivenditore);
                      rivenditoriDAO.salvaRivenditore(nuovoRivenditore);
                      System.out.println("Rivenditore aggiunto con successo! ID: " + nuovoRivenditore.getId());
                      break;
                  case 2:
                      System.out.println("Inserisci ID del rivenditore da aggiornare:");
                      String idAggiornaStr = scanner.nextLine();
                      try {
                          UUID idAggiorna = UUID.fromString(idAggiornaStr);
                          Rivenditori rivenditoreDaAggiornare = rivenditoriDAO.trovaRivenditorePerId(idAggiorna);
                          if (rivenditoreDaAggiornare != null) {
                              System.out.println("Inserisci nuova denominazione (attuale: " + rivenditoreDaAggiornare.getDenominazione() + "):");
                              String nuovaDenominazione = scanner.nextLine();
                              rivenditoreDaAggiornare.setDenominazione(nuovaDenominazione);

                              System.out.println("Inserisci nuovo indirizzo (attuale: " + rivenditoreDaAggiornare.getIndirizzo() + "):");
                              String nuovoIndirizzo = scanner.nextLine();
                              rivenditoreDaAggiornare.setIndirizzo(nuovoIndirizzo);

                              rivenditoriDAO.aggiornaRivenditore(rivenditoreDaAggiornare);
                              System.out.println("Rivenditore aggiornato con successo!");
                          } else {
                              System.out.println("Rivenditore non trovato.");
                          }
                      } catch (IllegalArgumentException e) {
                          System.out.println("Formato UUID non valido.");
                      }
                      break;
                  case 3:
                      System.out.println("Inserisci ID del rivenditore da eliminare:");
                      String idEliminaStr = scanner.nextLine();
                      try {
                          UUID idElimina = UUID.fromString(idEliminaStr);
                          Rivenditori rivenditoreDaEliminare = rivenditoriDAO.trovaRivenditorePerId(idElimina);
                          if (rivenditoreDaEliminare != null) {
                              rivenditoriDAO.eliminaRivenditore(rivenditoreDaEliminare);
                              System.out.println("Rivenditore eliminato con successo!");
                          } else {
                              System.out.println("Rivenditore non trovato.");
                          }
                      } catch (IllegalArgumentException e) {
                          System.out.println("Formato UUID non valido.");
                      }
                      break;
                  case 4:
                      List<Rivenditori> rivenditori = rivenditoriDAO.trovaTuttiRivenditori();
                      System.out.println("Elenco dei rivenditori:");
                      for (Rivenditori rivenditore : rivenditori) {
                          System.out.println("ID: " + rivenditore.getId() + ", Denominazione: " + rivenditore.getDenominazione() + ", Indirizzo: " + rivenditore.getIndirizzo());
                      }
                      break;
                  case 5:
                      return;
                  default:
                      System.out.println("Scelta non valida. Riprova.");
              }
          }
      }


                          }
