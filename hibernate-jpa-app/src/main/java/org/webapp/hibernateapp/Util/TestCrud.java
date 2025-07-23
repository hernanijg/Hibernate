package org.webapp.hibernateapp.Util;

import jakarta.persistence.EntityManager;
import org.webapp.hibernateapp.Entity.Client;
import org.webapp.hibernateapp.Services.ClientService;
import org.webapp.hibernateapp.Services.ClientServiceImpl;

import java.util.List;
import java.util.Optional;

public class TestCrud {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        ClientService service = new ClientServiceImpl(em);

        System.out.println("============= List =============");
        List<Client> client = service.list();
        client.forEach(System.out::println);

        System.out.println("============= for Id =============");
        Optional<Client> optionalClient = service.forId(1L);
        optionalClient.ifPresent(System.out::println);

        System.out.println("============= Insert and Edit Client =============");
        Client nc = new Client();
        //nc.setId(6L); // -> If we put this, we change
        nc.setName("Lola");
        nc.setLastname("Rodriguez");
        nc.setFormPage("Paypal");

        service.save(nc);
        System.out.println("The client is save or save");

        service.list().forEach(System.out::println);

        System.out.println("============= Insert and Edit Client =============");
        Long id = nc.getId();
        optionalClient = service.forId(id);
        optionalClient.ifPresent(c -> {
            service.delete(c.getId());
        });
        service.list().forEach(System.out::println);

        em.close();
    }
}
