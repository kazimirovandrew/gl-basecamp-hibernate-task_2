package util;

import entity.Department;
import entity.Worker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DaoUtil {

    private Session currentSession;
    private Transaction currentTransaction;
    private String configurationFile;

    public DaoUtil(String configurationFile) {
        this.configurationFile = configurationFile;
    }

    public void openSession() {
        currentSession = getSessionFactory(configurationFile).openSession();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public Session getCurrentSessionWithTransaction() {
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    private SessionFactory getSessionFactory(String configurationFile) {
        Configuration configuration = new Configuration().configure(configurationFile);
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Worker.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;
    }
}
