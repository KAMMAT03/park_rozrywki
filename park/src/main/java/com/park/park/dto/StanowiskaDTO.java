package com.park.park.dto;

import lombok.Data;

@Data
public class StanowiskaDTO {
    private long idStanowiska;
    private String nazwaStanowiska;
    private int wynagrodzenie = -1;
    private String godzinyPracy;
    private String zakresObowiazkow;
}
