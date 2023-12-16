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
@Table(name = "RODZAJE_MIEJSCA_PARKINGOWEGO", schema = "KAMMAT3")
public class RodzajeMiejscaParkingowegoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_RODZAJU_MIEJSCA_PARKINGOWEGO", nullable = false, precision = 0)
    private BigInteger idRodzajuMiejscaParkingowego;
    @Basic
    @Column(name = "STANDARD", nullable = false, length = 20)
    private String standard;
    @Basic
    @Column(name = "STAWKA_GODZINOWA", nullable = false, precision = 2)
    private short stawkaGodzinowa;
    @Basic
    @Column(name = "OPIS_MIEJSCA_PARKINGOWEGO", nullable = true, length = 300)
    private String opisMiejscaParkingowego;
}
