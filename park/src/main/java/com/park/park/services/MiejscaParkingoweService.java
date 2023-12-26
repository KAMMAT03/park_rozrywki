package com.park.park.services;

import com.park.park.dto.MiejscaParkingoweDTO;
import com.park.park.responses.ModelResponse;

public interface MiejscaParkingoweService {
    ModelResponse<MiejscaParkingoweDTO> getAllMiejscaParkingowe(int pageNo, int pageSize);
    ModelResponse<MiejscaParkingoweDTO> getAllMiejscaParkingoweByRodzaj(long idRodzaju, int pageNo, int pageSize);
    MiejscaParkingoweDTO getMiejscaParkingoweById(long idMiejscaParkingowego);
    MiejscaParkingoweDTO createMiejscaParkingowe(MiejscaParkingoweDTO miejscaParkingoweDTO);
    MiejscaParkingoweDTO updateMiejscaParkingowe(
            MiejscaParkingoweDTO miejscaParkingoweDTO, long idMiejscaParkingowego);
    void deleteMiejscaParkingowe(long idMiejscaParkingowego);
}
