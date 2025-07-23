package org.webapp.hibernateapp.Services;

import jakarta.persistence.EntityManager;
import org.webapp.hibernateapp.Entity.Client;
import org.webapp.hibernateapp.Repository.ClientRepository;
import org.webapp.hibernateapp.Repository.Repository;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService{
    private EntityManager em;
    private Repository<Client> repository;

    public ClientServiceImpl(EntityManager em) {
        this.em = em;
        this.repository = new ClientRepository(em);
    }

    @Override
    public List<Client> list() {
        return repository.list();
    }

    @Override
    public Optional<Client> forId(Long id) {
        return Optional.ofNullable(repository.forId(id));
    }

    @Override
    public void save(Client c) {
        try {
            em.getTransaction().begin();
            repository.save(c);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            em.getTransaction().begin();
            repository.delete(id);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
