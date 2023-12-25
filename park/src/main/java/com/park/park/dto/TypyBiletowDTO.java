package com.park.park.dto;

import lombok.Data;

@Data
public class TypyBiletowDTO {
    private long idTypuBiletu;
    private String standard;
    private double cena = -1;
    private String ulga;
    private String opisTypuBiletu;
}
