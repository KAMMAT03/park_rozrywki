package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KOLEJKI_GORSKIE", schema = "PARK_ROZRYWKI")
public class KolejkiGorskieEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_ATRAKCJI", nullable = false, precision = 0)
    private BigInteger idAtrakcji;
    @Basic
    @Column(name = "CZAS_OCZEKIWANIA", nullable = false, precision = 0)
    private BigInteger czasOczekiwania;
    @Basic
    @Column(name = "MAKSYMALNA_WYSOKOSC", nullable = false, precision = 2)
    private short maksymalnaWysokosc;
    @Basic
    @Column(name = "MINIMALNY_WZROST", nullable = false, precision = 2)
    private short minimalnyWzrost;
    @Basic
    @Column(name = "SREDNIA_PREDKOSC", nullable = false, precision = 2)
    private short sredniaPredkosc;
    @Basic
    @Column(name = "CZAS_JAZDY", nullable = false, precision = 2)
    private short czasJazdy;
    @Basic
    @Column(name = "POJEMNOSC_WAGONIKA", nullable = false, precision = 0)
    private BigInteger pojemnoscWagonika;
    @Basic
    @Column(name = "DATA_OSTATNIEGO_PRZEGLADU", nullable = false)
    private Date dataOstatniegoPrzegladu;
    @Basic
    @Column(name = "MINIMIALNY_WIEK", nullable = false, precision = 0)
    private BigInteger minimialnyWiek;
}
