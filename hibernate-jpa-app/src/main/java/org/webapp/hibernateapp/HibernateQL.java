package org.webapp.hibernateapp;

import jakarta.persistence.EntityManager;
import org.webapp.hibernateapp.Entity.Client;
import org.webapp.hibernateapp.Util.JpaUtil;

import java.util.List;
import java.util.Objects;

public class HibernateQL {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("========== Consultar =========");
        List<Client> clients = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        clients.forEach(System.out::println);

        System.out.println("========== for id ========");
        Client client = em.createQuery("select c from Client c where c.id=:id", Client.class)
                .setParameter("id", 1L)
                .getSingleResult();

        System.out.println("client = " + client);

        System.out.println("========== for id and show name ======== ");
        String nameClient = em.createQuery("select c.name from Client c where c.id=:id", String.class)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println("nameClient = " + nameClient);

        System.out.println("========== Save personalize names ======== ");
        List<Object[]> registers = em.createQuery("select c.id, c.name, c.lastname from Client c", Object[].class)
                .getResultList();

        registers.forEach(reg -> {
            Long i = (Long) reg[0];
            String name = (String) reg[1];
            String lastname = (String) reg[2];
            System.out.println(
                    "Id: " + i +
                    " Name:" + name +
                    " Lastname: " + lastname
            );
        });

        System.out.println("========== Loking for unique names ======== ");
        List<String> uniqueN = em.createQuery("select distinct(c.name) from Client c", String.class)
                .getResultList();
        uniqueN.forEach(System.out::println);

        System.out.println("========== Count ======== ");
        Long userCount = em.createQuery("select count(c) from Client c", Long.class).getSingleResult();
        System.out.println("userCount = " + userCount);

        System.out.println("========== Looking for params  ======== ");
        String param = "ar".toUpperCase();
        List<String> c = em.createQuery("select upper(c.name) from Client c where c.name like upper(:param)", String.class)
                .setParameter("param", "%" + param +"%")
                .getResultList();

        c.forEach(System.out::println);

    }
}
