package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STANOWISKA")
public class StanowiskaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_STANOWISKA", nullable = false)
    private long idStanowiska;
    @Basic
    @Column(name = "NAZWA_STANOWISKA", nullable = false, length = 20)
    private String nazwaStanowiska;
    @Basic
    @Column(name = "WYNAGRODZENIE", nullable = false, precision = 2)
    private int wynagrodzenie;
    @Basic
    @Column(name = "GODZINY_PRACY", nullable = false, length = 50)
    private String godzinyPracy;
    @Basic
    @Column(name = "ZAKRES_OBOWIAZKOW", length = 300)
    private String zakresObowiazkow;
}
