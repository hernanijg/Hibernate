package org.webapp.hibernateapp.Util;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final  EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("ejemploJPA");
    }

    public  static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
