package com.park.park.services;

import com.park.park.dto.RodzajeMiejscaParkingowegoDTO;
import com.park.park.responses.ModelResponse;

public interface RodzajeMiejscaParkingowegoService {
    ModelResponse<RodzajeMiejscaParkingowegoDTO> getAllRodzajeMiejscaParkingowego(int pageNo, int pageSize);
    RodzajeMiejscaParkingowegoDTO getRodzajeMiejscaParkingowegoById(long idRodzajuMiejscaParkingowego);
    RodzajeMiejscaParkingowegoDTO createRodzajeMiejscaParkingowego(
            RodzajeMiejscaParkingowegoDTO rodzajeMiejscaParkingowegoDTO);
    RodzajeMiejscaParkingowegoDTO updateRodzajeMiejscaParkingowego(
            RodzajeMiejscaParkingowegoDTO rodzajeMiejscaParkingowegoDTO, long idRodzajuMiejscaParkingowego);
    void deleteRodzajeMiejscaParkingowego(long idRodzajuMiejscaParkingowego);
}
