package com.park.park.services;

import com.park.park.dto.BiletyDTO;
import com.park.park.responses.ModelResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface BiletyService {
    ModelResponse<BiletyDTO> getAllBilety(int pageNo, int pageSize);
    ModelResponse<BiletyDTO> getAllBiletyByKlient(HttpServletRequest request, int pageNo, int pageSize);
    BiletyDTO getBiletyById(long idBiletu);
    BiletyDTO createBilety(HttpServletRequest request, BiletyDTO biletyDTO);
    BiletyDTO updateBilety(HttpServletRequest request, BiletyDTO biletyDTO, long idBiletu);
    void deleteBilety(HttpServletRequest request, long idBiletu);
}
