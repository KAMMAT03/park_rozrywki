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
@Table(name = "BILETY", schema = "PARK_ROZRYWKI")
public class BiletyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_BILETU", nullable = false, precision = 0)
    private long idBiletu;
    @Basic
    @Column(name = "DATA_WAZNOSCI", nullable = false)
    private Date dataWaznosci;
    @Basic
    @Column(name = "DATA_ZAKUPU", nullable = false)
    private Date dataZakupu;
    @Basic
    @Column(name = "ID_KLIENTA", nullable = false, precision = 0)
    private BigInteger idKlienta;
    @Basic
    @Column(name = "ID_TYPU_BILETU", nullable = false, precision = 0)
    private BigInteger idTypuBiletu;
    @Basic
    @Column(name = "ID_PARKU_ROZRYWKI", nullable = false, precision = 0)
    private BigInteger idParkuRozrywki;
}
