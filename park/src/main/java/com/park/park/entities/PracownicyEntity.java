package com.park.park.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRACOWNICY", schema = "PARK_ROZRYWKI")
public class PracownicyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_PRACOWNIKA", nullable = false)
    private long idPracownika;
    @Basic
    @Column(name = "NUMER_DOKUMENTU", nullable = false, length = 20)
    private String numerDokumentu;
    @Basic
    @Column(name = "IMIE", nullable = false, length = 20)
    private String imie;
    @Basic
    @Column(name = "NAZWISKO", nullable = false, length = 30)
    private String nazwisko;
    @Basic
    @Column(name = "PESEL", length = 11)
    private String pesel;
    @Basic
    @Column(name = "DATA_URODZENIA", nullable = false)
    private Date dataUrodzenia;
    @Basic
    @Column(name = "DATA_ZATRUDNIENIA", nullable = false)
    private Date dataZatrudnienia;
    @Basic
    @Column(name = "NR_KONTA", nullable = false, length = 26)
    private String nrKonta;
    @Basic
    @Column(name = "NR_TELEFONU", length = 15)
    private String nrTelefonu;
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
    @Column(name = "NR_LOKALU", length = 6)
    private String nrLokalu;
    @Basic
    @Column(name = "BONUS", precision = 2)
    private int bonus;
    @Basic
    @Column(name = "ID_PARKU_ROZRYWKI", nullable = false)
    private long idParkuRozrywki;
    @Basic
    @Column(name = "ID_STANOWISKA", nullable = false)
    private long idStanowiska;
}
