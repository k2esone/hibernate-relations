package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.hibernate.model.Ocena;
import pl.sda.hibernate.model.Student;

import java.util.Scanner;

public class MainDodajOcene {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj identyfikator studenta, ktoremu chcesz dodac ocene: ");
        String idStudenta = scanner.nextLine();
        Long id = Long.parseLong(idStudenta);

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Student student = session.get(Student.class, id);
            if (student == null) {
                System.err.println("Nie znaleziono studenta");
            } else {
                System.out.println("Podaj wartosc oceny");
                double ocenastudenta = Double.parseDouble(scanner.nextLine());
                Ocena ocena = Ocena.builder()
                        .wartosc(ocenastudenta)
                        .student(student)
                        .build();

                session.persist(ocena);


            }
            transaction.commit();

        } catch (Exception ioe) {
            System.err.println("Błąd bazy: " + ioe);
        }
    }
}
