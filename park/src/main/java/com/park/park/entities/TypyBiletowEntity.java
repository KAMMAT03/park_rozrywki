package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TYPY_BILETOW", schema = "KAMMAT3")
public class TypyBiletowEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_TYPU_BILETU", nullable = false, precision = 0)
    private BigInteger idTypuBiletu;
    @Basic
    @Column(name = "STANDARD", nullable = false, length = 20)
    private String standard;
    @Basic
    @Column(name = "CENA", nullable = false, precision = 2)
    private int cena;
    @Basic
    @Column(name = "ULGA", nullable = true, length = 20)
    private String ulga;
    @Basic
    @Column(name = "OPIS_TYPU_BILETU", nullable = true, length = 300)
    private String opisTypuBiletu;
}
