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
@Table(name = "MIEJSCA_PARKINGOWE", schema = "KAMMAT3")
public class MiejscaParkingoweEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_MIEJSCA_PARKINGOWEGO", nullable = false, precision = 0)
    private BigInteger idMiejscaParkingowego;
    @Basic
    @Column(name = "SEKTOR", nullable = false, length = 2)
    private String sektor;
    @Basic
    @Column(name = "MIASTO", nullable = false, length = 20)
    private String miasto;
    @Basic
    @Column(name = "ULICA", nullable = false, length = 30)
    private String ulica;
    @Basic
    @Column(name = "KOD_POCZTOWY", nullable = false, length = 6)
    private String kodPocztowy;
    @Basic
    @Column(name = "NR_POSESJI", nullable = false, length = 6)
    private String nrPosesji;
    @Basic
    @Column(name = "NAZWA_PARKINGU", nullable = true, length = 30)
    private String nazwaParkingu;
    @Basic
    @Column(name = "ID_KLIENTA", nullable = true, precision = 0)
    private BigInteger idKlienta;
    @Basic
    @Column(name = "ID_PARKU_ROZRYWKI", nullable = false, precision = 0)
    private BigInteger idParkuRozrywki;
    @Basic
    @Column(name = "ID_RODZAJU_MIEJSCA_PARKINGOWEGO", nullable = false, precision = 0)
    private BigInteger idRodzajuMiejscaParkingowego;
}
