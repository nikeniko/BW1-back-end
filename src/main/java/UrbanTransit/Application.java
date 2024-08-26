package UrbanTransit;

import UrbanTransit.DAO.UtenteDAO;
import UrbanTransit.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-UrbanTransit");
    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        UtenteDAO utenteDAO = new UtenteDAO(em);

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("1. Registra Utente");
            System.out.println("2. Esci");
            int scelta = scanner.nextInt();

            switch (scelta){
                case 1:
                    System.out.println("Inserisci nome");
                    String nome = scanner.next();
                    System.out.println("Inserisci cognome");
                    String cognome = scanner.next();
                    Utente utente = new Utente();
                    utente.setNome(nome);
                    utente.setCognome(cognome);
                    utenteDAO.salvaUtente(utente);
                    break;
                case 2:
                    em.close();
                    emf.close();
                    return;
            }
        }



        //System.out.println("Hello World!");
    }
}
