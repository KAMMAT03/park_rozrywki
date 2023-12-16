package com.park.park.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Gastronomie")
public class Gastronomia {

    @Id
    @Column(name = "ID_atrakcji", nullable = false)
    private Integer idAtrakcji;

    @Column(name = "Typ", nullable = false, length = 30)
    private String typ;

    @Column(name = "Godziny_otwarcia", nullable = false, length = 50)
    private String godzinyOtwarcia;

    @Column(name = "Liczba_miejsc")
    private Integer liczbaMiejsc;

    @OneToOne
    @MapsId
    private Atrakcja atrakcja;
}
