package org.webapp.hibernateapp.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.webapp.hibernateapp.Entity.Client;

import java.util.List;

public class ClientRepository implements Repository<Client> {
    EntityManager em;

    public ClientRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Client> list() {
        return em.createQuery("Select c from Client c", Client.class).getResultList();
    }

    @Override
    public Client forId(Long id) {
        return em.find(Client.class, id);
    }

    @Override
    public void save(Client client) {
        if(client.getId() != null && client.getId() > 0)
            em.merge(client);
        else
            em.persist(client);
    }

    @Override
    public void delete(Long id) {
        Client c = forId(id);
        em.remove(c);
    }
}
