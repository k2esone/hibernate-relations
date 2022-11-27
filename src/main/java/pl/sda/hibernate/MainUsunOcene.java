package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.hibernate.model.Ocena;
import pl.sda.hibernate.model.Student;

import java.util.Scanner;

public class MainUsunOcene {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Czy chcesz usunac studenta czy ocene?");
        String szukaj = scanner.nextLine();

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            if (szukaj.equalsIgnoreCase("ocena")) {

                System.out.println("Podaj id oceny, ktora chcesz usunac");
                String idOceny = scanner.nextLine();
                Long id = Long.parseLong(idOceny);

                Ocena ocena = session.get(Ocena.class, id);
                if (ocena == null) {
                    System.err.println("Nie znaleziono oceny");
                } else {

                    session.remove(ocena);

                }
            }

            if (szukaj.equalsIgnoreCase("student")) {
                System.out.println("Podaj id studenta, ktorego chcesz usunac");
                String idStudenta = scanner.nextLine();
                Long id = Long.parseLong(idStudenta);

                Student student = session.get(Student.class, id);
                if (student == null) {
                    System.err.println("Nie znaleziono studenta");
                } else {
                    if (!student.getOceny().isEmpty()){
                        for (Ocena ocena : student.getOceny()) {
                            session.remove(ocena);
                        }
                    }

                    session.remove(student);
                }

                transaction.commit();

            }
        } catch (Exception ioe) {
            System.err.println("Błąd bazy: " + ioe);
        }
    }
}
