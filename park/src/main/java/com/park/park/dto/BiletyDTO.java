package com.park.park.dto;

import lombok.Data;

@Data
public class BiletyDTO {
    private long idBiletu;
    private String dataWaznosci;
    private String dataZakupu;
    private long idKlienta = -1;
    private long idTypuBiletu = -1;
    private long idParkuRozrywki;
    private String standard;
    private double cena = -1;
    private String ulga;
    private String opisTypuBiletu;
}
