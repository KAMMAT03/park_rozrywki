package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RODZAJE_MIEJSCA_PARKINGOWEGO", schema = "PARK_ROZRYWKI")
public class RodzajeMiejscaParkingowegoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_RODZAJU_MIEJSCA_PARKINGOWEGO", nullable = false)
    private long idRodzajuMiejscaParkingowego;
    @Basic
    @Column(name = "STANDARD", nullable = false, length = 20)
    private String standard;
    @Basic
    @Column(name = "STAWKA_GODZINOWA", nullable = false, precision = 2)
    private double stawkaGodzinowa;
    @Basic
    @Column(name = "OPIS_MIEJSCA_PARKINGOWEGO", length = 300)
    private String opisMiejscaParkingowego;
}
