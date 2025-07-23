package org.webapp.hibernateapp;

import jakarta.persistence.EntityManager;
import org.webapp.hibernateapp.Entity.Client;
import org.webapp.hibernateapp.Entity.ClientDetail;
import org.webapp.hibernateapp.Util.JpaUtil;

public class HibernateAsociationOneToOneBiDirectional {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Client client = new Client("cata", "edu");
            client.setFormPage("paypa;");
            ClientDetail detail = new ClientDetail(true, 6000L);

            client.setDetail(detail);
            detail.setClient(client);
            em.persist(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}
