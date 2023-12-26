package com.park.park.dto;

import lombok.Data;

@Data
public class RodzajeMiejscaParkingowegoDTO {
    private long idRodzajuMiejscaParkingowego;
    private String standard;
    private double stawkaGodzinowa;
    private String opisMiejscaParkingowego;
}
