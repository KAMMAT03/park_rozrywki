package com.park.park.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class KolejkiGorskieDTO {
    private long idAtrakcji;
    private String nazwaAtrakcji;
    private String typAtrakcji;
    private String opisAtrakcji;
    private int czasOczekiwania = -1;
    private int maksymalnaWysokosc = -1;
    private int minimalnyWzrost = -1;
    private double sredniaPredkosc = -1;
    private double czasJazdy = -1;
    private int pojemnoscWagonika = -1;
    private Date dataOstatniegoPrzegladu;
    private int minimialnyWiek = -1;
}
