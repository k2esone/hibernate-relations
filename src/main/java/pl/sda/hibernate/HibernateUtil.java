package pl.sda.hibernate;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    // jedno pole statyczne i finalne:
    // - nikt tego pola nie nadpisze
    // - kazdy moze go uzywac wszedzie
    // - wywolujemy konstruktor, ktory jest prywatny
    //   - konstruktora nie da sie wywolac nigdzie indziej
    public final static HibernateUtil INSTANCE = new HibernateUtil();
    private final SessionFactory sessionFactor;

    private HibernateUtil() {
        this.sessionFactor = loadConfigFile();
    }

    public SessionFactory loadConfigFile() {
        // Zaladuj plik konfiguracyjn: hibernate.cfg.xml
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // <-- jesli nie podamy nazwy - ustawi sie domyslna
                .build();
        // Metadane to dane ktore opisuja dane
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        // Na podstawie metadanych z pliku konfiguracyjnego tworzymy fabryke sesji
        return metadata.getSessionFactoryBuilder().build();

    }

    public SessionFactory getSessionFactory() {
        return sessionFactor;
    }
}
