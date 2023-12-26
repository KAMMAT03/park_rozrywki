package com.park.park.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class PracownicyDTO {
    private long idPracownika;
    private String numerDokumentu;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String dataUrodzenia;
    private String dataZatrudnienia;
    private String nrKonta;
    private String nrTelefonu;
    private String miasto;
    private String ulica;
    private String kodPocztowy;
    private String nrPosesji;
    private String nrLokalu;
    private int bonus = -1;
    private long idParkuRozrywki = -1;
    private long idStanowiska = -1;
}
