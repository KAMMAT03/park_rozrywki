package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ATRAKCJE", schema = "PARK_ROZRYWKI")
public class AtrakcjeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_ATRAKCJI", nullable = false)
    private long idAtrakcji;
    @Basic
    @Column(name = "NAZWA_ATRAKCJI", nullable = false, length = 40)
    private String nazwaAtrakcji;
    @Basic
    @Column(name = "TYP_ATRAKCJI", nullable = false, length = 15)
    private String typAtrakcji;
    @Basic
    @Column(name = "OPIS_ATRAKCJI", length = 1200)
    private String opisAtrakcji;
    @Basic
    @Column(name = "ID_PARKU_ROZRYWKI", nullable = false)
    private long idParkuRozrywki;
    @Basic
    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;
}
