package UrbanTransit;


import UrbanTransit.DAO.*;
import UrbanTransit.entities.*;
import UrbanTransit.enums.Stato_Distribrutori;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-UrbanTransit");
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

       // -----dao------

        UtenteDAO ud = new UtenteDAO(em);
        tesseraDAO td = new tesseraDAO(em);
        bigliettiDAO bd = new bigliettiDAO(em);
        abbonamentiDAO ad = new abbonamentiDAO(em);
        distributoriDAO dd = new distributoriDAO(em);
        rivenditoriDAO rd = new rivenditoriDAO(em);

        System.out.println("Salve, benvenuto nel nostro sistema di trasposrto pubblico!");

        while (true) {

            /*
            * salvare nuovo utente
            * cercare utente per id
            * aggiornare utente
            * eliminare utente
            * lista completa di tutti gli utenti
            * */

            System.out.println("\nCome possiamo aiutarla?\n\n" +
                    "1- Salvare nuovo utente" +
                    "\n2- Cercare un utente tramite ID" +
                    "\n3- Aggiornare dati di un utente" +
                    "\n4- Eliminare un utente" +
                    "\n5- Visualizzare la lista degli utenti registrati" +
                    "\n6- Voglio associare una tessera" +
                    "\n7- Acquistare un nuovo biglietto o abbonamento" +
                    "\n- " +
                    "\n- ");

            int risp = scan.nextInt();
            scan.nextLine();
            if (risp == 0) break;

            switch(risp) {
                case 1:
                    System.out.println("inserire nome: ");
                    String nome = scan.nextLine();

                    System.out.println("inserire cognome: ");
                    String cognome = scan.nextLine();
                    // data di nascita
                    LocalDate data_nascita = LocalDate.of(2000,6,23);

                    Utente utente = new Utente(nome, cognome, data_nascita);

                    ud.salvaUtente(utente);

                    break;
                case 2:
                    System.out.println("inserire id utente: ");
                    UUID id = UUID.fromString(scan.nextLine());

                    Utente utenteTrovato = ud.trovaUtentePerId(id);
                    System.out.println(utenteTrovato);
                    break;
                case 3:
                    System.out.println("inserire id utente da modificare: ");
                    UUID idMod = UUID.fromString(scan.nextLine());

                    Utente utenteMod = ud.trovaUtentePerId(idMod);
                    System.out.println(utenteMod);

                    System.out.println("nome da modificare: ");
                    String nomeMod = scan.nextLine();
                    utenteMod.setNome(nomeMod);

                    System.out.println("cognome da modificare: ");
                    String cognomeMod = scan.nextLine();
                    utenteMod.setCognome(cognomeMod);

                    ud.aggiornaUtente(utenteMod);

                    break;
                case 4:
                    System.out.println("quale utente si vuole eliminare? (inserire ID)");
                    // idDel = id delete
                    String idDel = scan.nextLine();
                    Utente utenteDel = ud.trovaUtentePerId(UUID.fromString(idDel));

                    ud.eliminaUtente(utenteDel);
                    break;
                case 5:
                    System.out.println("ecco la lista di tutti gli utenti: ");
                    List <Utente> utentiTrovati = ud.trovaTuttiGliUtenti();
                    utentiTrovati.stream().forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Inserire ID utente per associare la tessera: ");
                    String idTes = scan.nextLine();

                    Utente utenteTes = ud.trovaUtentePerId(UUID.fromString(idTes));

                    System.out.println("Inserire una data: [gg-mm-aaaa]");
                    String data = scan.nextLine();
                    String[] dataSplit = data.split("-");

                    int giorno = Integer.parseInt(dataSplit[0]);
                    int mese = Integer.parseInt(dataSplit[1]);
                    int anno = Integer.parseInt(dataSplit[2]);

                    LocalDate data_inizio = LocalDate.of(anno, mese, giorno);

                    Tessera tessera = new Tessera(data_inizio, true, utenteTes);

                    utenteTes.setTessera(tessera);

                    ud.aggiornaUtente(utenteTes);



                    break;
                case 7:
                    System.out.println("inserisci l'id della tua tessera: ");
                    String tesseraId = scan.nextLine();

                    Tessera tesseraTrovata = td.findById(UUID.fromString(tesseraId));
                    System.out.println("tessera trovata! ");

                    System.out.println("vuoi acquistare: " +
                            "\n1- un biglietto" +
                            "\n2- un abbonamento");

                    int risp7 = scan.nextInt();
                    scan.nextLine();

                    switch (risp7){
                        case 1:
                            System.out.println("inserire la data di acquisto: [gg-mm-aaaa]");

                            String dataBig = scan.nextLine();
                            String[] dataSplitBig = dataBig.split("-");

                            int giornoBig = Integer.parseInt(dataSplitBig[0]);
                            int meseBig = Integer.parseInt(dataSplitBig[1]);
                            int annoBig = Integer.parseInt(dataSplitBig[2]);

                            LocalDate data_acquisto = LocalDate.of(annoBig, meseBig, giornoBig);

                            System.out.println("L'hai acquistato da un rivenditore o distributore?");

                            int rispRiv = Integer.parseInt(scan.nextLine());

                            switch (rispRiv) {
                                case 1:
                                    System.out.println("Inserire l'indirizzo del rivenditore: ");
                                    String indirizzoRiv = scan.nextLine();

                                    System.out.println("Inserire la denominazione del rivenditore: ");
                                    String denominazioneRiv = scan.nextLine();

                                    Rivenditori rivenditore = new Rivenditori(indirizzoRiv, denominazioneRiv);

                                    rd.save(rivenditore);
                                    // C1 --> case 1
                                    Biglietto bigliettoC1 = new Biglietto(data_acquisto, tesseraTrovata, rivenditore);

                                    bd.save(bigliettoC1);


                                    break;
                                case 2:
                                    System.out.println("Inserire lo stato del rivenditore: [ATTIVO, FUORI_SERVIZIO]");
                                    String statoDis = scan.nextLine();

                                    System.out.println("Inserire l'indirizzo del distributore");
                                    String indirizzoDis = scan.nextLine();

                                    Distributori distributore = new Distributori(Stato_Distribrutori.valueOf(statoDis), indirizzoDis);

                                    dd.save(distributore);
                                    // C2 --> case 2
                                    Biglietto bigliettoC2 = new Biglietto(data_acquisto, tesseraTrovata, distributore);

                                    bd.save(bigliettoC2);

                                    break;
                                default:
                                    break;
                            }

                            break;
                        case 2:
                            // inserimento di abbonamento
                            break;
                        default:
                            break;
                    }





                    break;
                default:
                    break;

            }
        }

        System.out.println("ti prego");






        em.close();
        emf.close();
    }
}
