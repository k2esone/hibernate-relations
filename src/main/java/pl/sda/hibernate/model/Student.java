package pl.sda.hibernate.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imie;

    @Column(nullable = false)
    private String nazwisko;

//    @Column(nullable = false) - typ prymitywny
    private int rokRozpoczeciaStudiow;

    // nie chcemy, aby to byla kolumna
    @Formula("(SELECT AVG(o.wartosc) FROM Ocena o WHERE o.student_id=id)")
    private Double sredniaOcen;

    // RELACJA
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "student")
    private Set<Ocena> oceny;

}
