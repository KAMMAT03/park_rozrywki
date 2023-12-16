package com.park.park.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Parki_rozrywki")
public class ParkRozrywki {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_parku_rozrywki")
    private Integer idParkuRozrywki;

    @Column(name = "Nazwa_parku_rozrywki", nullable = false, length = 30)
    private String nazwaParkuRozrywki;

    @Column(name = "Nr_telefonu", nullable = false, length = 15)
    private String nrTelefonu;

    @Column(name = "Adres_e_mail", nullable = false, length = 30)
    private String adresEMail;

    @Column(name = "Adres_strony_internetowej", nullable = false, length = 30)
    private String adresStronyInternetowej;

    @Column(name = "Opis_parku_rozrywki", length = 1200)
    private String opisParkuRozrywki;

    @Column(name = "Miasto", nullable = false, length = 20)
    private String miasto;

    @Column(name = "Ulica", nullable = false, length = 30)
    private String ulica;

    @Column(name = "Nr_posesji", nullable = false, length = 6)
    private String nrPosesji;

    @Column(name = "Nr_lokalu", length = 6)
    private String nrLokalu;

    @Column(name = "Kod_pocztowy", nullable = false, length = 6)
    private String kodPocztowy;
}
