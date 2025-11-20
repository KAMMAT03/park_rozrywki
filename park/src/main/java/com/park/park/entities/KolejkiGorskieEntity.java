package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KOLEJKI_GORSKIE")
public class KolejkiGorskieEntity {
    @Id
    @Column(name = "ID_ATRAKCJI", nullable = false)
    private long idAtrakcji;
    @Basic
    @Column(name = "CZAS_OCZEKIWANIA", nullable = false)
    private int czasOczekiwania;
    @Basic
    @Column(name = "MAKSYMALNA_WYSOKOSC", nullable = false)
    private int maksymalnaWysokosc;
    @Basic
    @Column(name = "MINIMALNY_WZROST", nullable = false)
    private int minimalnyWzrost;
    @Basic
    @Column(name = "SREDNIA_PREDKOSC", nullable = false, precision = 2)
    private double sredniaPredkosc;
    @Basic
    @Column(name = "CZAS_JAZDY", nullable = false, precision = 2)
    private double czasJazdy;
    @Basic
    @Column(name = "POJEMNOSC_WAGONIKA", nullable = false)
    private int pojemnoscWagonika;
    @Basic
    @Column(name = "DATA_OSTATNIEGO_PRZEGLADU", nullable = false)
    private Date dataOstatniegoPrzegladu;
    @Basic
    @Column(name = "MINIMIALNY_WIEK", nullable = false)
    private int minimialnyWiek;
}
