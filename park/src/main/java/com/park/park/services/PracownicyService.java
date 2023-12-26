package com.park.park.services;

import com.park.park.dto.PracownicyDTO;
import com.park.park.responses.ModelResponse;

public interface PracownicyService {
    ModelResponse<PracownicyDTO> getAllPracownicy(int pageNo, int pageSize);
    ModelResponse<PracownicyDTO> getAllPracownicyByStanowisko(long idStanowiska, int pageNo, int pageSize);
    PracownicyDTO getPracownicyById(long idPracownika);
    PracownicyDTO createPracownicy(PracownicyDTO pracownicyDTO);
    PracownicyDTO updatePracownicy(PracownicyDTO pracownicyDTO, long idPracownika);
    void deletePracownicy(long idPracownika);
}
