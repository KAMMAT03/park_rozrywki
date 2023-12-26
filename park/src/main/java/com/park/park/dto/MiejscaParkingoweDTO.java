package com.park.park.dto;

import lombok.Data;

@Data
public class MiejscaParkingoweDTO {
    private long idMiejscaParkingowego;
    private String sektor;
    private String miasto;
    private String ulica;
    private String kodPocztowy;
    private String nrPosesji;
    private String nazwaParkingu;
    private long idKlienta = 1;
    private long idParkuRozrywki;
    private long idRodzajuMiejscaParkingowego = -1;
}
