package org.webapp.hibernateapp;

import jakarta.persistence.EntityManager;
import org.webapp.hibernateapp.Entity.Client;
import org.webapp.hibernateapp.Entity.Recive;
import org.webapp.hibernateapp.Util.JpaUtil;

public class HibernateAsociationsManyToOne {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Client client = new Client("Lola", "Colum");
            client.setFormPage("credit");
            em.persist(client);

            Recive recive = new Recive("Office", 100L);
            recive.setClient(client);
            em.persist(recive);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}
