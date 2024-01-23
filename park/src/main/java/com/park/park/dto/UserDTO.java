package com.park.park.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private long systemId;
    private String imie;
    private String nazwisko;
    private String dataUrodzenia;
    private String roleType;
}
