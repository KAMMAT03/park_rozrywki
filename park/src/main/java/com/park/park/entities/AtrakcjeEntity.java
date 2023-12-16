package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ATRAKCJE", schema = "KAMMAT3")
public class AtrakcjeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_ATRAKCJI", nullable = false, precision = 0)
    private BigInteger idAtrakcji;
    @Basic
    @Column(name = "NAZWA_ATRAKCJI", nullable = false, length = 40)
    private String nazwaAtrakcji;
    @Basic
    @Column(name = "TYP_ATRAKCJI", nullable = false, length = 15)
    private String typAtrakcji;
    @Basic
    @Column(name = "OPIS_ATRAKCJI", nullable = true, length = 1200)
    private String opisAtrakcji;
    @Basic
    @Column(name = "ID_PARKU_ROZRYWKI", nullable = false, precision = 0)
    private BigInteger idParkuRozrywki;
}
