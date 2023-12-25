package com.park.park.services;

import com.park.park.dto.KlienciDTO;
import com.park.park.responses.ModelResponse;

public interface KlienciService {
    ModelResponse<KlienciDTO> getAllKlienci(int pageNo, int pageSize);
    KlienciDTO getKlienciById(long idKlienta);
    KlienciDTO createKlienci(KlienciDTO klienciDTO);
    KlienciDTO updateKlienci(KlienciDTO klienciDTO, long idKlienta);
    void deleteKlienci(long idKlienta);
}
