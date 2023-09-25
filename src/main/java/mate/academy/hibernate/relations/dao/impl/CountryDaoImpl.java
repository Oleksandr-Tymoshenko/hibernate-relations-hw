package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        try{
            factory.inTransaction(session -> session.persist(country));
            return country;
        } catch(Exception ex) {
            throw new DataProcessingException("Can't add country to the Database");
        }
    }

    @Override
    public Optional<Country> get(Long id) {
        Session session = factory.openSession();
        Optional<Country> countryFromDb = Optional.ofNullable(session.get(Country.class, id));
        session.close();
        return countryFromDb;
    }
}
