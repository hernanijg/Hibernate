package org.webapp.hibernateapp;

import jakarta.persistence.EntityManager;
import org.webapp.hibernateapp.Entity.Client;
import org.webapp.hibernateapp.Entity.Direction;
import org.webapp.hibernateapp.Util.JpaUtil;

public class HibernateAsociationsOneToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Client client = new Client("Kata", "EDU");
            client.setFormPage("Debit");

            Direction d1 = new Direction("Vergel", 123);
            Direction d2 = new Direction("SauPablo", 11363);

            client.getDirections().add(d1);
            client.getDirections().add(d2);

            em.persist(client);
            em.getTransaction().commit();

            em.getTransaction().begin();

            client = em.find(Client.class, client.getId());
            client.getDirections().remove(d1);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}
