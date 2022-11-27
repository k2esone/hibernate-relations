package pl.sda.hibernate.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    // RELACJA
    @ManyToOne
    private Student student; // kolumna student_id
}
