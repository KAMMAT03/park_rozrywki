package com.park.park.services;

import com.park.park.dto.StanowiskaDTO;
import com.park.park.responses.ModelResponse;

public interface StanowiskaService {
    ModelResponse<StanowiskaDTO> getAllStanowiska(int pageNo, int pageSize);
    StanowiskaDTO getStanowiskaById(long idStanowiska);
    StanowiskaDTO createStanowiska(StanowiskaDTO stanowiskaDTO);
    StanowiskaDTO updateStanowiska(StanowiskaDTO stanowiskaDTO, long idStanowiska);
    void deleteStanowiska(long idStanowiska);
}
