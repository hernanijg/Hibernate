package org.webapp.hibernateapp;

import jakarta.persistence.EntityManager;
import org.webapp.hibernateapp.Entity.Client;
import org.webapp.hibernateapp.Entity.Recive;
import org.webapp.hibernateapp.Util.JpaUtil;

public class HibernateAsociationOneToManyBiDirectional {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Client client = new Client("Cata", "EDU");
            client.setFormPage("debit");
            Recive r1 = new Recive("Payments", 5000L);
            Recive r2 = new Recive("Technology ", 23000L);

            client.addRecive(r1);
            client.addRecive(r2);

            em.persist(client);
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
