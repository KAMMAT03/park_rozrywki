package com.park.park.services;

import com.park.park.dto.KolejkiGorskieDTO;
import com.park.park.responses.ModelResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface KolejkiGorskieService {
    ModelResponse<KolejkiGorskieDTO> getAllKolejkiGorskie(int pageNo, int pageSize);
    KolejkiGorskieDTO getKolejkiGorskieById(long idAtrakcji);
    KolejkiGorskieDTO createKolejkiGorskie(KolejkiGorskieDTO kolejkiGorskieDTO);
    KolejkiGorskieDTO updateKolejkiGorskie(
            KolejkiGorskieDTO kolejkiGorskieDTO, long idAtrakcji);
    void deleteKolejkiGorskie(long idAtrakcji);
}
