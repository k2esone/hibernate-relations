package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.hibernate.model.Student;

import java.util.Scanner;


public class MainDodajStudenta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
// wywolaj try-with-resources ktory zamknie sesje automatycznie po opuszczeniu try
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("Podaj imie");
            String imie = scanner.nextLine();

            System.out.println("Podaj nazwisko");
            String nazwisko = scanner.nextLine();

            System.out.println("Podaj rok rozpoczecia");
            int rok = Integer.parseInt(scanner.nextLine());

            Student student = Student.builder()
                    .imie(imie)
                    .nazwisko(nazwisko)
                    .rokRozpoczeciaStudiow(rok)
                    .sredniaOcen(null)
                    .build();

            session.persist(student);

            transaction.commit();

        } catch (Exception ioe) {


        }

    }
}
