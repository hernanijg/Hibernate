package org.webapp.hibernateapp.Services;

import org.webapp.hibernateapp.Entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> list();
    Optional<Client> forId(Long id);
    void save(Client c);
    void delete(Long id);
}
