package com.park.park.services;

import com.park.park.dto.TypyBiletowDTO;
import com.park.park.responses.ModelResponse;

public interface TypyBiletowService {
    ModelResponse<TypyBiletowDTO> getAllTypyBiletow(int pageNo, int pageSize);
    TypyBiletowDTO getTypyBiletowById(long idTypuBiletu);
    TypyBiletowDTO createTypyBiletow(TypyBiletowDTO typyBiletowDTO);
    TypyBiletowDTO updateTypyBiletow(TypyBiletowDTO typyBiletowDTO, long idTypuBiletu);
    void deleteTypyBiletow(long idTypuBiletu);
}
