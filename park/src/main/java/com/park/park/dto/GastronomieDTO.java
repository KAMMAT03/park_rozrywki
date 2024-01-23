package com.park.park.dto;

import lombok.Data;

@Data
public class GastronomieDTO {
    private long idAtrakcji;
    private String nazwaAtrakcji;
    private String typAtrakcji;
    private String opisAtrakcji;
    private String typ;
    private String godzinyOtwarcia;
    private int liczbaMiejsc = -1;
    private String imageUrl;
}
