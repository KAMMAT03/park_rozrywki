package com.park.park.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Kolejki_gorskie")
public class KolejkaGorska {

    @Id
    @Column(name = "ID_atrakcji", nullable = false)
    private Integer idAtrakcji;

    @Column(name = "Czas_oczekiwania", nullable = false)
    private Integer czasOczekiwania;

    @Column(name = "Maksymalna_wysokosc", nullable = false, precision = 5, scale = 2)
    private Double maksymalnaWysokosc;

    @Column(name = "Minimalny_wzrost", nullable = false, precision = 5, scale = 2)
    private Double minimalnyWzrost;

    @Column(name = "Srednia_predkosc", nullable = false, precision = 5, scale = 2)
    private Double sredniaPredkosc;

    @Column(name = "Czas_jazdy", nullable = false, precision = 4, scale = 2)
    private Double czasJazdy;

    @Column(name = "Pojemnosc_wagonika", nullable = false)
    private Integer pojemnoscWagonika;

    @Column(name = "Data_ostatniego_przegladu", nullable = false)
    private Date dataOstatniegoPrzegladu;

    @Column(name = "Minimialny_wiek", nullable = false)
    private Integer minimialnyWiek;

    @OneToOne
    @MapsId
    private Atrakcja atrakcja;
}
