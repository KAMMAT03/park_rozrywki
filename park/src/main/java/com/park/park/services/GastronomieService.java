package com.park.park.services;

import com.park.park.dto.GastronomieDTO;
import com.park.park.responses.ModelResponse;

public interface GastronomieService {
    ModelResponse<GastronomieDTO> getAllGastronomie(int pageNo, int pageSize);
    GastronomieDTO getGastronomieById(long idAtrakcji);
    GastronomieDTO createGastronomie(GastronomieDTO gastronomieDTO);
    GastronomieDTO updateGastronomie(
            GastronomieDTO gastronomieDTO, long idAtrakcji);
    void deleteGastronomie(long idAtrakcji);
}
