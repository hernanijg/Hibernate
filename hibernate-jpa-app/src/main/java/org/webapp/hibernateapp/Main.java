package org.webapp.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.webapp.hibernateapp.Entity.Client;
import org.webapp.hibernateapp.Util.JpaUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
//
//        List<Client> clients = em.createQuery("SELECT c FROM Client c").getResultList();
//        clients.forEach(c -> System.out.println("c = " + c));
//
//        Query query = em.createQuery("SELECT c FROM Client c where c.formPage=?1", Client.class);
//        query.setParameter(1, "Debito");
//        query.setMaxResults(1);
//        Client c = (Client) query.getSingleResult();
//        //System.out.println("c = " + c);
//
//        Client cId = em.find(Client.class, 1L);
//        //System.out.println("cId = " + cId);


        try {
            em.getTransaction().begin();

            /** ===== Create in database ===== */
            String name = "MarcusRoe";
            String lastname = "Loto";
            String formPage = "Debito";
            Client c1 = new Client();
            c1.setName(name);
            c1.setLastname(lastname);
            c1.setFormPage(formPage);
            em.persist(c1);

            /* ===== Edit Client ===== */
//            Long id = 5L;
//            Client c1 = em.find(Client.class, id);
//            String name = "Jhon";
//            String lastname = "Doe";
//            String formPage = "Paypal";
//            c1.setName(name);
//            c1.setLastname(lastname);
//            c1.setFormPage(formPage);
//            em.merge(c1);

            /* ===== Remove ===== */
//            Long id = 5L;
//            Client c1 = em.find(Client.class, id);
//            em.remove(c1);
            em.getTransaction().commit();
        } catch (Exception e ) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}