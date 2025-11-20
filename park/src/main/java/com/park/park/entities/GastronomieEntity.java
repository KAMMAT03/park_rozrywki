package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GASTRONOMIE")
public class GastronomieEntity {
    @Id
    @Column(name = "ID_ATRAKCJI", nullable = false)
    private long idAtrakcji;
    @Basic
    @Column(name = "TYP", nullable = false, length = 30)
    private String typ;
    @Basic
    @Column(name = "GODZINY_OTWARCIA", nullable = false, length = 50)
    private String godzinyOtwarcia;
    @Basic
    @Column(name = "LICZBA_MIEJSC")
    private int liczbaMiejsc;
}
