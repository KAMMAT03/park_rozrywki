package com.park.park.services;

import com.park.park.dto.AtrakcjeDTO;
import com.park.park.responses.AtrakcjeResponse;

public interface AtrakcjeService {
    AtrakcjeResponse getAllAtrakcje(int pageNo, int pageSize);
    AtrakcjeDTO createAtrakcje(AtrakcjeDTO atrakcjeDTO);
}
