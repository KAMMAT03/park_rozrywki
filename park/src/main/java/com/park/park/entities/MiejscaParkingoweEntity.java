package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MIEJSCA_PARKINGOWE", schema = "PARK_ROZRYWKI")
public class MiejscaParkingoweEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_MIEJSCA_PARKINGOWEGO", nullable = false)
    private long idMiejscaParkingowego;
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
    @Column(name = "NAZWA_PARKINGU", length = 30)
    private String nazwaParkingu;
    @Basic
    @Column(name = "ID_KLIENTA")
    private long idKlienta;
    @Basic
    @Column(name = "ID_PARKU_ROZRYWKI", nullable = false)
    private long idParkuRozrywki;
    @Basic
    @Column(name = "ID_RODZAJU_MIEJSCA_PARKINGOWEGO", nullable = false)
    private long idRodzajuMiejscaParkingowego;
}
