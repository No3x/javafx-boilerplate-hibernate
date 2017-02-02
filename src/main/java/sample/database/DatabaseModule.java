package sample.database;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.google.inject.persist.jpa.JpaPersistModule;
import sample.database.dao.GenericDAOImpl;
import sample.database.dao.IGenericDAO;
import sample.model.Person;

import java.util.Properties;

/**
 * Created by No3x on 02.02.2017.
 */
public class DatabaseModule extends AbstractModule {

    @Override
    protected void configure() {
        install( createJpaPersistModule() );
        bind(new TypeLiteral<IGenericDAO<Person, Integer>>(){}).to(new TypeLiteral<GenericDAOImpl<Person, Integer>>(){}).in(Scopes.SINGLETON);
        bind(JPAInitializer.class).asEagerSingleton();
    }

    private JpaPersistModule createJpaPersistModule() {
        Properties props = new Properties();

        props.put("javax.persistence.jdbc.url","jdbc:log4jdbc:h2:file:./database/sample;DB_CLOSE_DELAY=-1");
        JpaPersistModule jpaModule = new JpaPersistModule("javafx-db");
        jpaModule.properties(props);
        return jpaModule;
    }

}

