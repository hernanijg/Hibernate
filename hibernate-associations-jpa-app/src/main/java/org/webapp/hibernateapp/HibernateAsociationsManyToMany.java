package org.webapp.hibernateapp;

import jakarta.persistence.EntityManager;
import org.webapp.hibernateapp.Entity.Curse;
import org.webapp.hibernateapp.Entity.Student;
import org.webapp.hibernateapp.Util.JpaUtil;

public class HibernateAsociationsManyToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Student st1 = new Student("Cata", "Edu");
            Student st2 = new Student("Maria", "Edu");

            Curse c1 = new Curse("Curso Java", "Andres");
            Curse c2 = new Curse("Hibernate", "Andres");

            st1.getCurses().add(c1);
            st1.getCurses().add(c2);

            st2.getCurses().add(c1);

            em.persist(st1);
            em.persist(st2);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}
