package com.park.park.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Atrakcje")
public class Atrakcja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_atrakcji")
    private Integer idAtrakcji;

    @Column(name = "Nazwa_atrakcji", nullable = false, length = 40)
    private String nazwaAtrakcji;

    @Column(name = "Typ_atrakcji", nullable = false, length = 15)
    private String typAtrakcji;

    @Column(name = "Opis_atrakcji", length = 1200)
    private String opisAtrakcji;

    @ManyToOne
    @JoinColumn(name = "ID_parku_rozrywki", nullable = false)
    private ParkRozrywki parkRozrywki;
}
