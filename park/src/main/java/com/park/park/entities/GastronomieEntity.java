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
@Table(name = "GASTRONOMIE", schema = "KAMMAT3")
public class GastronomieEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_ATRAKCJI", nullable = false, precision = 0)
    private BigInteger idAtrakcji;
    @Basic
    @Column(name = "NAZWA", nullable = false, length = 50)
    private String nazwa;
    @Basic
    @Column(name = "TYP", nullable = false, length = 30)
    private String typ;
    @Basic
    @Column(name = "GODZINY_OTWARCIA", nullable = false, length = 50)
    private String godzinyOtwarcia;
    @Basic
    @Column(name = "LICZBA_MIEJSC", nullable = true, precision = 0)
    private BigInteger liczbaMiejsc;
}
