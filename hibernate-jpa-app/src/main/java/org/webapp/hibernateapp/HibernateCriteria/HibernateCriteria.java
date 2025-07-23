package org.webapp.hibernateapp.HibernateCriteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.webapp.hibernateapp.Entity.Client;
import org.webapp.hibernateapp.Util.JpaUtil;

import java.util.Arrays;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder criteria = em.getCriteriaBuilder();

        CriteriaQuery<Client> query = criteria.createQuery(Client.class);
        Root<Client> from = query.from(Client.class);
        query.select(from);

        List<Client> clients = em.createQuery(query).getResultList();
        clients.forEach(System.out::println);

        System.out.println("===== List where equals =====");
        ParameterExpression<String> parameter = criteria.parameter(String.class, "name");

        query = criteria.createQuery(Client.class);
        from = query.from(Client.class);
        query.select(from).where(criteria.equal(from.get("name"), parameter));

        clients = em.createQuery(query).setParameter("name", "Marcus").getResultList();
        clients.forEach(System.out::println);

        System.out.println("===== Where like search =====");
        query = criteria.createQuery(Client.class);
        from = query.from(Client.class);
        query.select(from).where(criteria.like(criteria.upper(from.get("name")),
                criteria.upper(parameter)));
        clients = em.createQuery(query).setParameter("name", "%ar%").getResultList();
        clients.forEach(System.out::println);

        System.out.println("========= Where beetween ==========");
        query = criteria.createQuery(Client.class);
        from = query.from(Client.class);
        query.select(from).where(criteria.between(from.get("id"),
                2L, 6L));
        clients = em.createQuery(query).getResultList();
        clients.forEach(System.out::println);

        System.out.println("========= Where + in ==========");
        ParameterExpression<List> parameterList = criteria.parameter(List.class, "names");
        query = criteria.createQuery(Client.class);
        from = query.from(Client.class);
        query.select(from).where(from.get("name").in(parameterList));
        clients = em.createQuery(query)
                .setParameter("names", Arrays.asList("Andres", "Marcus", "Mary"))
                .getResultList();
        clients.forEach(System.out::println);

        System.out.println("========= Filter with < > ==========");
        query = criteria.createQuery(Client.class);
        from = query.from(Client.class);
        //query.select(from)
        // .where(criteria.gt(from.get("id"), 3L)); // By numbers || for - use criteria.le
        query.select(from)
                .where(criteria.gt(criteria.length(from.get("name")), 6L)); // By String || for - use criteria.ge
        clients = em.createQuery(query)
                        .getResultList();
        clients.forEach(System.out::println);

        System.out.println("========= Consult cunjungt and distunsion < > ==========");
        query = criteria.createQuery(Client.class);
        from = query.from(Client.class);
        Predicate forName = criteria.equal(from.get("name"), "Marcus");
        Predicate forFormPage = criteria.equal(from.get("formPage"), "Debito");
        query.select(from).where(criteria.and(forName, forFormPage)); // We can use or => ||
        clients = em.createQuery(query).getResultList();
        clients.forEach(System.out::println);

        System.out.println("========= Order==========");
        query = criteria.createQuery(Client.class);
        from = query.from(Client.class);
        query.select(from)
                .orderBy(
                        criteria.desc(from.get("name")),
                        criteria.asc(from.get("lastname"))
                );
        clients = em.createQuery(query).getResultList();
        clients.forEach(System.out::println);

        System.out.println("========= Only names ==========");
        CriteriaQuery<String> queryString = criteria.createQuery(String.class);
        from = queryString.from(Client.class);
        queryString.select(from.get("name")).distinct(true); // False take all of them
        List<String> names = em.createQuery(queryString).getResultList();
        names.forEach(System.out::println);

        System.out.println("========= Only names and lastname ==========");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Client.class);
        queryString.select(criteria.upper(criteria.concat(
                criteria.concat(from.get("name"), " "),
                from.get("lastname")
                )));
        names = em.createQuery(queryString).getResultList();
        names.forEach(System.out::println);

        System.out.println("========= Personalizad Obj ==========");
        CriteriaQuery<Object[]> queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Client.class);
        queryObject.multiselect(from.get("id"), from.get("name"), from.get("lastname"));
        List<Object[]> register = em.createQuery(queryObject).getResultList();

        register.forEach(reg ->{
            Long i = (Long) reg[0];
            String name = (String) reg[1];
            String lastname = (String) reg[2];
            System.out.println("Id: " + i + " name: " + name + " lastname: " + lastname);

        });

        System.out.println("========= Count filter ==========");
        CriteriaQuery<Long> queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Client.class);
        queryLong.select(criteria.count(from.get("id")));
        Long count = em.createQuery(queryLong).getSingleResult();
        System.out.println(count);

        System.out.println("========= Sum ==========");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Client.class);
        queryLong.select(criteria.sum(from.get("id")));
        count = em.createQuery(queryLong).getSingleResult();
        System.out.println("count = " + count);


        System.out.println("========= Max ==========");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Client.class);
        queryLong.select(criteria.max(from.get("id"))); // for minimun, use min
        count = em.createQuery(queryLong).getSingleResult();
        System.out.println("count = " + count);

        System.out.println("========= Min ==========");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Client.class);
        queryLong.select(criteria.min(from.get("id"))); // for minimun, use min
        count = em.createQuery(queryLong).getSingleResult();
        System.out.println("count = " + count);
        em.close();
    }
}
