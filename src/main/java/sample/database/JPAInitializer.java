package sample.database;

import com.google.inject.persist.PersistService;

import javax.inject.Inject;

/**
 * Created by No3x on 02.02.2017.
 */
public class JPAInitializer {
    @Inject
    JPAInitializer(PersistService service) {
        service.start();
        // At this point JPA is started and ready.
    }
}
