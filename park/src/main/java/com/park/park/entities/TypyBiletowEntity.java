package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TYPY_BILETOW", schema = "PARK_ROZRYWKI")
public class TypyBiletowEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_TYPU_BILETU", nullable = false)
    private long idTypuBiletu;
    @Basic
    @Column(name = "STANDARD", nullable = false, length = 20)
    private String standard;
    @Basic
    @Column(name = "CENA", nullable = false, precision = 2)
    private float cena;
    @Basic
    @Column(name = "ULGA", length = 20)
    private String ulga;
    @Basic
    @Column(name = "OPIS_TYPU_BILETU", length = 300)
    private String opisTypuBiletu;
}
