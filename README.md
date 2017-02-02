# javafx-boilerplate-hibernate
A boilerplate project for JavaFX development with JPA and hibernate. Features:

- guice DI integration with gluonhq's ignite
- a sample for communication between two controllers
- simple sync of elements in `ListView` with the help of `Bean Properties` and `Observable` (changes are synced across different views)
- a simple window manager concept
- jpa with hibernate
- h2 database is created, schema is created automatically. See `persistence.xml`
- jpa guice provider
- GenericDAO sample

This project is not meant to be a full fleged template. Rather it is a snapshot of a bigger project I'm working on. The key-features are ported into this template. For it contains bugs and misconceptions. Especially the included GenericDAO needs further work.

There are a vast of libraries included:

| Library | Description |
|-------|-------------|
| com.google.inject:guice:4.1.0       | DI Framework           |
| com.gluonhq:ignite-guice:1.0.1       | DI Framework for JavaFX (FXML-Loader)           |
| org.hibernate:hibernate-core:5.2.1.Final       | Hibernate JPA Implementation           |
| com.google.inject.extensions:guice-persist:4.1.0       | Guice JPA Extension           |
| org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.0.Final       | Hibernate JPA Interface           |
| com.h2database:h2:1.4.193       | H2 Database Driver           |
| org.lazyluke:log4jdbc-remix:0.2.7       | Logging SQL Statements trough dedicated Driver           |
| org.slf4j:slf4j-api:1.7.21       | Logger           |
| org.slf4j:slf4j-log4j12:1.7.21       | Logger           |
| log4j:log4j:1.2.17       | Logger           |
| uaihebert.com:uaiCriteria:4.0.0       | JPA Criterion API / Library / Simplification           |
| com.github.vbauer:herald:1.2.3       | `@Log` Annotation for Logger injection           |
| org.kohsuke:wordnet-random-name:1.3       | Create random names used for random person generator           |


See [javafx-boilerplate](https://github.com/No3x/javafx-boilerplate) for a basic example without JPA and hibernate.

