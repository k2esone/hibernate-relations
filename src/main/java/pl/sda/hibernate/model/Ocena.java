package pl.sda.hibernate.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ocena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column(nullable = false)
    private double wartosc;
    // INSERT INTO OCENA VALUES (now())
    private LocalDateTime dataCzasDodania;

    @Enumerated(value = EnumType.STRING)
    private Przedmiot przedmiot;

    // RELACJA
    @ManyToOne
//    @ToString.Exclude (wystarczy z jednej strony)
    @EqualsAndHashCode.Exclude // musi byc po obydwu stronach
    private Student student; // kolumna student_id
}
