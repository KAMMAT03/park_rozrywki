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
@Table(name = "KLIENCI", schema = "PARK_ROZRYWKI")
public class KlienciEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_KLIENTA", nullable = false, precision = 0)
    private BigInteger idKlienta;
    @Basic
    @Column(name = "IMIE", nullable = false, length = 20)
    private String imie;
    @Basic
    @Column(name = "NAZWISKO", nullable = false, length = 30)
    private String nazwisko;
    @Basic
    @Column(name = "DATA_URODZENIA", nullable = false)
    private Date dataUrodzenia;
}
