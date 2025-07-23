package org.webapp.hibernateapp;

import jakarta.persistence.EntityManager;
import org.webapp.hibernateapp.Entity.Client;
import org.webapp.hibernateapp.Entity.Recive;
import org.webapp.hibernateapp.Util.JpaUtil;

public class HibernateAsociationOneToManyBiDirectionalFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Client client = em.find(Client.class, 1L);
            Recive r1 = new Recive("Payments", 5000L);
            Recive r2 = new Recive("Technology ", 23000L);

            client.addRecive(r1);
            client.addRecive(r2);

            em.merge(client);
            em.getTransaction().commit();

            em.getTransaction().begin();
            Recive r3 = em.find(Recive.class, 1L);
            client.removeRecive(r3);
            //client.getRecive().remove(r3);
            r3.setClient(null);
            em.getTransaction().commit();

            System.out.println(client);

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }finally {
            em.close();
        }
    }
}
