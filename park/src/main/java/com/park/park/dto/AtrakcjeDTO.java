package com.park.park.dto;

import lombok.Data;

@Data
public class AtrakcjeDTO {
    private long idAtrakcji;
    private String nazwaAtrakcji;
    private String typAtrakcji;
    private String opisAtrakcji;
    private long idParkuRozrywki;
    private String imageUrl;
}

