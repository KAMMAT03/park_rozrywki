package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PARKI_ROZRYWKI", schema = "PARK_ROZRYWKI")
public class ParkiRozrywkiEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_PARKU_ROZRYWKI", nullable = false)
    private long idParkuRozrywki;
    @Basic
    @Column(name = "NAZWA_PARKU_ROZRYWKI", nullable = false, length = 30)
    private String nazwaParkuRozrywki;
    @Basic
    @Column(name = "NR_TELEFONU", nullable = false, length = 15)
    private String nrTelefonu;
    @Basic
    @Column(name = "ADRES_E_MAIL", nullable = false, length = 30)
    private String adresEMail;
    @Basic
    @Column(name = "ADRES_STRONY_INTERNETOWEJ", nullable = false, length = 30)
    private String adresStronyInternetowej;
    @Basic
    @Column(name = "OPIS_PARKU_ROZRYWKI", length = 1200)
    private String opisParkuRozrywki;
    @Basic
    @Column(name = "MIASTO", nullable = false, length = 20)
    private String miasto;
    @Basic
    @Column(name = "ULICA", nullable = false, length = 30)
    private String ulica;
    @Basic
    @Column(name = "NR_POSESJI", nullable = false, length = 6)
    private String nrPosesji;
    @Basic
    @Column(name = "NR_LOKALU", length = 6)
    private String nrLokalu;
    @Basic
    @Column(name = "KOD_POCZTOWY", nullable = false, length = 6)
    private String kodPocztowy;
}
