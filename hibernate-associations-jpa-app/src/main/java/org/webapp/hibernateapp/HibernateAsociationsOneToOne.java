package org.webapp.hibernateapp;

import jakarta.persistence.EntityManager;
import org.webapp.hibernateapp.Entity.Client;
import org.webapp.hibernateapp.Entity.ClientDetail;
import org.webapp.hibernateapp.Util.JpaUtil;

public class HibernateAsociationsOneToOne {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Client client = new Client("Cata", "edu");
            client.setFormPage("paypal");
            em.persist(client);

            ClientDetail detail = new ClientDetail(true, 4000L);
            detail.setClient(client);
            em.persist(detail);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        finally {
            em.close();
        }
    }
}
