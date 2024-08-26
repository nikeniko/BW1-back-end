package UrbanTransit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-UrbanTransit");
    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();



        System.out.println("Hello World!");
    }
}
