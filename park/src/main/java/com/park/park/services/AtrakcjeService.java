package com.park.park.services;

import com.park.park.dto.AtrakcjeDTO;
import com.park.park.responses.ModelResponse;

public interface AtrakcjeService {
    ModelResponse<AtrakcjeDTO> getAllAtrakcje(int pageNo, int pageSize);
    ModelResponse<AtrakcjeDTO> getOnlyAtrakcje(int pageNo, int pageSize);
    AtrakcjeDTO getAtrakcjeById(long idAtrakcji);
    AtrakcjeDTO createAtrakcje(AtrakcjeDTO atrakcjeDTO);
    AtrakcjeDTO updateAtrakcje(AtrakcjeDTO atrakcjeDTO, long idAtrakcji);
    void deleteAtrakcje(long idAtrakcji);
}
