package com.park.park.services;

import com.park.park.dto.BiletyDTO;
import com.park.park.responses.ModelResponse;

public interface BiletyService {
    ModelResponse<BiletyDTO> getAllBilety(int pageNo, int pageSize);
    ModelResponse<BiletyDTO> getAllBiletyByKlient(long idKlienta, int pageNo, int pageSize);
    BiletyDTO getBiletyById(long idBiletu);
    BiletyDTO createBilety(BiletyDTO biletyDTO, long idKlienta);
    BiletyDTO updateBilety(BiletyDTO biletyDTO, long idBiletu);
    void deleteBilety(long idBiletu);
}
